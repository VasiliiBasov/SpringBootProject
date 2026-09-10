/* Notification Hub — frontend logic
 *
 * Работает в трёх режимах Security:
 *   1. Security не подключен           — UI доступен анонимно (модалка не нужна)
 *   2. Security дефолт (9-A)            — Basic Auth, модалка не нужна (логин-экран Spring)
 *   3. Security свой (9-B/C)            — Basic Auth или form login, модалка помогает
 *
 * Логика проста: пытаемся fetch → если 401 → открываем модалку → после входа повторяем.
 * Если 200 сразу — работаем без логина.
 */

(function () {
  'use strict';

  // ---------- State ----------
  const state = {
    credentials: null, // null = аноним, иначе { username, password } для Basic Auth
  };

  // ---------- DOM ----------
  const $ = (id) => document.getElementById(id);
  const userArea = $('userArea');
  const loginModal = $('loginModal');
  const loginForm = $('loginForm');
  const loginUsername = $('loginUsername');
  const loginPassword = $('loginPassword');
  const loginCancel = $('loginCancel');
  const loginHint = $('loginHint');

  const sendForm = $('sendForm');
  const searchForm = $('searchForm');
  const clearFiltersBtn = $('clearFilters');
  const messagesEl = $('messages');
  const auditEl = $('audit');
  const messageCount = $('messageCount');
  const auditCount = $('auditCount');

  // ---------- Helpers ----------
  function showError(msg, el) {
    el = el || messagesEl;
    const banner = document.createElement('div');
    banner.className = 'error-banner';
    banner.textContent = msg;
    el.parentNode.insertBefore(banner, el);
    setTimeout(() => banner.remove(), 5000);
  }

  function showSuccess(msg, el) {
    el = el || messagesEl;
    const banner = document.createElement('div');
    banner.className = 'success-banner';
    banner.textContent = msg;
    el.parentNode.insertBefore(banner, el);
    setTimeout(() => banner.remove(), 3000);
  }

  function basicAuthHeader() {
    if (!state.credentials) return null;
    return 'Basic ' + btoa(`${state.credentials.username}:${state.credentials.password}`);
  }

  // fetch с прокидыванием Authorization
  async function api(path, options = {}) {
    const headers = Object.assign(
      options.headers || {},
      { 'Content-Type': 'application/json' }
    );
    const auth = basicAuthHeader();
    if (auth) headers['Authorization'] = auth;
    return fetch(path, Object.assign({}, options, { headers }));
  }

  // fetch с автоматическим логином при 401
  async function apiWithAuth(path, options = {}) {
    let res = await api(path, options);
    if (res.status === 401) {
      const ok = await showLoginModal();
      if (ok) {
        res = await api(path, options);
      } else {
        throw new Error('Unauthorized');
      }
    }
    return res;
  }

  // ---------- Login modal ----------
  function showLoginModal() {
    return new Promise((resolve) => {
      loginHint.textContent = 'Введите логин и пароль';
      loginHint.className = 'hint';
      loginModal.classList.remove('hidden');
      loginUsername.value = state.credentials ? state.credentials.username : '';
      loginPassword.value = '';
      setTimeout(() => loginUsername.focus(), 50);

      const onSubmit = (e) => {
        e.preventDefault();
        const username = loginUsername.value.trim();
        const password = loginPassword.value;
        if (!username || !password) return;
        state.credentials = { username, password };
        cleanup();
        resolve(true);
      };
      const onCancel = () => { cleanup(); resolve(false); };
      const onKey = (e) => { if (e.key === 'Escape') onCancel(); };
      function cleanup() {
        loginModal.classList.add('hidden');
        loginForm.removeEventListener('submit', onSubmit);
        loginCancel.removeEventListener('click', onCancel);
        document.removeEventListener('keydown', onKey);
      }
      loginForm.addEventListener('submit', onSubmit);
      loginCancel.addEventListener('click', onCancel);
      document.addEventListener('keydown', onKey);
    });
  }

  function renderUserArea() {
    userArea.innerHTML = '';
    if (state.credentials) {
      const span = document.createElement('span');
      span.className = 'username';
      span.textContent = `👤 ${state.credentials.username}`;
      const out = document.createElement('button');
      out.className = 'danger';
      out.textContent = 'Выйти';
      out.onclick = () => {
        state.credentials = null;
        renderUserArea();
        showSuccess('Вышли. Дальнейшие запросы — анонимные.');
      };
      userArea.appendChild(span);
      userArea.appendChild(out);
    } else {
      const btn = document.createElement('button');
      btn.className = 'primary';
      btn.textContent = 'Войти';
      btn.onclick = async () => {
        const ok = await showLoginModal();
        if (ok) {
          renderUserArea();
          loadMessages();
          loadAudit();
        }
      };
      userArea.appendChild(btn);
    }
  }

  // ---------- Render messages ----------
  function escapeHtml(s) {
    if (s == null) return '';
    return String(s)
      .replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;').replace(/'/g, '&#39;');
  }

  function renderMessages(items) {
    messagesEl.innerHTML = '';
    messageCount.textContent = items.length;
    if (items.length === 0) {
      const li = document.createElement('li');
      li.className = 'empty';
      li.textContent = 'Нет сообщений';
      messagesEl.appendChild(li);
      return;
    }
    for (const m of items) {
      const li = document.createElement('li');
      const main = document.createElement('div');
      main.innerHTML = `<strong>${escapeHtml(m.recipient)}</strong>: ${escapeHtml(m.text)}`;
      const meta = document.createElement('div');
      meta.className = 'message-meta';
      const created = m.createdAt ? new Date(m.createdAt).toLocaleString('ru-RU') : '—';
      meta.innerHTML = `<span>🕒 ${escapeHtml(created)}</span><span>id: ${m.id}</span>`;
      li.appendChild(main);
      li.appendChild(meta);
      messagesEl.appendChild(li);
    }
  }

  function renderAudit(items) {
    auditEl.innerHTML = '';
    auditCount.textContent = items.length;
    if (items.length === 0) {
      const li = document.createElement('li');
      li.className = 'empty';
      li.textContent = 'Нет audit-записей';
      auditEl.appendChild(li);
      return;
    }
    for (const a of items) {
      const li = document.createElement('li');
      const main = document.createElement('div');
      main.innerHTML = `<strong>${escapeHtml(a.eventType)}</strong>`;
      const meta = document.createElement('div');
      meta.className = 'audit-meta';
      const created = a.createdAt ? new Date(a.createdAt).toLocaleString('ru-RU') : '—';
      meta.innerHTML = `<span>🕒 ${escapeHtml(created)}</span><span>id: ${a.id}</span>`;
      li.appendChild(main);
      li.appendChild(meta);
      auditEl.appendChild(li);
    }
  }

  // ---------- Loaders ----------
  async function loadMessages(recipient, q, from, to) {
    const params = new URLSearchParams();
    if (recipient) params.set('recipient', recipient);
    if (q) params.set('q', q);
    if (from) params.set('from', from);
    if (to) params.set('to', to);
    const url = params.toString() ? `/messages/search?${params}` : '/messages';
    try {
      const res = await apiWithAuth(url);
      if (!res.ok) { showError(`GET ${url} → ${res.status}`); return; }
      const data = await res.json();
      renderMessages(data);
    } catch (e) {
      showError(`Ошибка: ${e.message}`);
    }
  }

  async function loadAudit() {
    try {
      const res = await apiWithAuth('/audit');
      if (!res.ok) {
        if (res.status === 404) { renderAudit([]); return; }
        showError(`GET /audit → ${res.status}`);
        return;
      }
      const data = await res.json();
      renderAudit(data);
    } catch (e) {
      renderAudit([]);
    }
  }

  // ---------- Forms ----------
  sendForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const recipient = $('recipient').value.trim();
    const text = $('text').value.trim();
    if (!recipient || !text) return;
    try {
      const res = await apiWithAuth('/messages', {
        method: 'POST',
        body: JSON.stringify({ recipient, text }),
      });
      if (res.status === 201) {
        showSuccess('Сообщение отправлено');
        $('recipient').value = '';
        $('text').value = '';
        loadMessages();
        setTimeout(loadAudit, 200);
      } else if (res.status === 400) {
        const body = await res.json().catch(() => ({}));
        showError(`Ошибка валидации: ${JSON.stringify(body)}`);
      } else if (res.status === 403) {
        showError('Доступ запрещён (нужна роль ADMIN)');
      } else {
        showError(`POST /messages → ${res.status}`);
      }
    } catch (e) {
      showError(`Ошибка: ${e.message}`);
    }
  });

  searchForm.addEventListener('submit', (e) => {
    e.preventDefault();
    loadMessages(
      $('filterRecipient').value.trim(),
      $('filterQ').value.trim(),
      $('filterFrom').value,
      $('filterTo').value
    );
  });

  clearFiltersBtn.addEventListener('click', () => {
    searchForm.reset();
    loadMessages();
  });

  // ---------- Init ----------
  renderUserArea();
  loadMessages();
  loadAudit();
})();
