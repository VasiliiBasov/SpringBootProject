# 📋 Прогресс: контекст и навигация

Здесь — **только** то, что нужно для быстрого входа в работу: где остановились, что дальше, шпаргалка для следующего открытия.

**Цифры, время, баллы — в `STATS.md`.**
**Конспект теории — в `LEARNING_LOG.md`.**
**Краткое резюме (если история слетит) — в `HANDOFF.md`.**
**Сводка по всем проектам — в `OVERALL_STATS.md`.**
**Программа курса + история — в `COURSE_HANDBOOK.md`.**

---

## 📌 Где мы сейчас

**Текущий шаг:** 9 / 15 — Spring Security basics ✅ (9-A starter, 9-B SecurityFilterChain, 9-C InMemory users + BCrypt + roles; коммит `e93bbbe`)
**Следующий шаг:** шаг 10 (TBD — не определён в плане; возможно JWT/OAuth2 или кэш/RateLimit)
**Процент:** 60% (9/15) — шаги 1, 2, 3, 4, 5, 6 (micro-1 + micro-2), 7, 8, 9 закрыты

**Что сделано в шаге 9 (финал, 11.09.2026):**
- ✅ **9-A:** `pom.xml` + `spring-boot-starter-security` (коммит `3174036`). Дефолтный Security подтверждён: `curl -u "user:<uuid>"` → 200 + JSON, headers видны. Фронт (path C) создан: `static/index.html` + `app.js` + `style.css`. Порт 8081.
- ✅ **9-B:** `SecurityConfig.java` с `@Bean SecurityFilterChain` — `permitAll` для статики (`/`, `/index.html`, `/app.js`, `/style.css`, `/favicon.ico`, `/error`), `anyRequest().authenticated()` для API, `httpBasic(Customizer.withDefaults())` для логина через curl, `csrf.disable()` для REST. Фикс: пропустил слэш у `"style.css"` → `pattern must start with a /`. 3 проверки прошли: 200/401/200.
- ✅ **9-C:** `BCryptPasswordEncoder` + `InMemoryUserDetailsManager` с 2 юзерами: `alice/alice123` (USER), `admin/admin123` (USER+ADMIN). `requestMatchers(HttpMethod.POST, "/messages").hasRole("ADMIN")` — **обязательно с `HttpMethod`, иначе rule ловит и GET**. 5 проверок прошли.
- 🔍 **Бонус-диагностика 1:** `requestMatchers("POST", "/messages")` со String `"POST"` → Spring парсит оба аргумента как URL-паттерны → `pattern must start with a /`. Фикс: `HttpMethod.POST` (enum), а не String.
- 🔍 **Бонус-диагностика 2:** PowerShell splatting — `@body.json` ломается в парсере PS (включая PS 7 в IDEA-терминале). Фикс: `-d (Get-Content -Raw body.json)`. Побочно: установлен **PowerShell 7.4.6** через msi, настроен в IDEA (Shell path → `C:\Program Files\PowerShell\7\pwsh.exe`).
- 🧪 Мини-экзамен (4 вопроса, **~87%**): UserDetailsService частота (60%) + HttpMethod в matchers (100%) + порядок matcher'ов (100%) + permitAll vs authenticated (100%)
- ✅ Коммит `e93bbbe` на main

**Что сделано в шаге 8 (финал, 10.09.2026, сессия №18):**

**Сравнение 3 способов запросов (на собесе любят):**

| Способ | Когда | Код |
|---|---|---|
| **Именованный метод** | Простые фиксированные запросы | `findByRecipient(String r)` |
| **`@Query` JPQL** | Проекции/агрегации/DTO, сложная логика | `SELECT new com.example.MessageStats(recipient, COUNT(id)) ... GROUP BY recipient` |
| **`@Query` Native** | Специфика БД (JSON, окна, `FOR UPDATE`) | `SELECT * FROM messages WHERE event_type = ?` |
| **Specification** | Динамические фильтры, любые комбинации | `(root, query, cb) -> cb.equal(...)` |

**Что сделано в шаге 7 (финал, 05.09.2026, сессии №13–14):**
- ✅ Ученик **сам** написал `AuditLog` entity + `AuditLogRepository` + переписал `auditSend` на сохранение в `audit_log`
- ✅ `pom.xml`: +`flyway-core` 11.14.1 (Boot BOM), +`spring-boot-starter-flyway` (нужен в Boot 4), +explicit `h2` со `scope=compile` (transitive scope=runtime блокирует `org.h2.tools.Server` в IDE)
- ✅ Миграции созданы и переименованы по спеке: `V1__init_messages.sql`, `V2__init_audit_log.sql` (двойное `_`). Фикс `V2`: `event_type VARCHAR(100) NOT NULL` (иначе `ddl-auto=validate` упал бы на entity↔schema drift)
- ✅ `application.properties`: `url=jdbc:h2:tcp://localhost:9092/file:./data/notificationhub`, `ddl-auto=validate`
- ✅ `application-dev.yml`: убран `ddl-auto:none` (всё равно не перебивал `.properties`), оставлен `show-sql:true`
- ✅ `H2ServerConfig`: `@Component` + `BeanFactoryPostProcessor` — стартует TCP-сервер ДО создания DataSource/Flyway
- ✅ `.gitignore`: +`data/` (файл `notificationhub.mv.db` НЕ в git)
- 🔍 **Диагностика:** `Connection refused: localhost:9092` → `BeanFactoryPostProcessor` без `@Component` не регистрируется → добавил `@Component`. Ученик **сам** ответил «почему не `@Configuration`» — правильно (нет `@Bean`, только side-effect)
- ✅ Верификация: `Flyway: Successfully validated 2 migrations` (H2: `Successfully validated 2 migrations`), Hibernate: 0 DDL в логе (validate не мутирует). POST /messages → 201, kill app → restart → 5 записей на месте (доказательство persistent)
- 🧪 Мини-экзамен (2 вопроса, **3/5**): checksum mismatch V3 (4/5) + V vs R миграции (2/5)
- ✅ Коммит `7634659` на main

**Архитектурное решение (prod-like dev):** TCP + файл = persistent + параллельный доступ из IDEA + точно так же конфигурится реальный PostgreSQL/MySQL на проде через TCP. Ученик явно выбрал этот подход для джоб-релевантности.

**Ближайшие шаги (шаг 9 — Spring Security basics):**
1. `SecurityFilterChain` — цепочка фильтров, дефолтный `formLogin()` и `httpBasic()`
2. `BCryptPasswordEncoder` — хеширование паролей
3. InMemory `UserDetailsService` для учебного проекта
4. (Опционально) JDBC `UserDetailsService` поверх нашей H2 БД (использовать существующую схему)

**Что сделано в шаге 6 (micro-2, 04.09.2026):**
- ✅ `NotificationService` дополнен self-injection через `@Lazy` (поле `self`, конструктор с 3-м параметром)
- ✅ Добавлен метод `auditSend(...)` с `@Transactional(propagation = REQUIRES_NEW)` — пишет запись с префиксом `[audit]` в `messages`
- ✅ Ученик **сам** догадался поменять местами `emailSender.send` и `self.auditSend` — иначе audit не успевал закоммититься до исключения на dev-fail
- ✅ Проверено на `dev-fail`: в БД **1 запись** `[audit]` (от auditSend, REQUIRES_NEW), основная запись из `send` откатилась
- ✅ Теория propagation: REQUIRED / REQUIRES_NEW / NESTED / MANDATORY / SUPPORTS / NOT_SUPPORTED / NEVER
- ✅ Мини-экзамен (1 вопрос, **95%**): «откатится ли audit-запись при падении внешней TX» — ответил правильно

**Что сделано в шаге 6 (micro-1, 04.09.2026):**
- ✅ Архитектура Controller → Service → Repository починена (`HelloController` дёргает `notificationService.send(...)`, а не `repository.save` напрямую)
- ✅ `EmailSender` интерфейс + 2 реализации по `@Profile`:
  - `dev` → `ConsoleEmailSender` (печатает в stdout)
  - `dev-fail` → `FailingEmailSender` (кидает `RuntimeException` — для проверки ROLLBACK)
- ✅ `@Transactional` на `NotificationService.send(...)` — обёртка `save + emailSender.send` в одну TX
- ✅ HTTP-файл `requests/messages-rollback.http` для удобного теста
- ✅ Старые сервисы удалены: `GreetingService`, `MessageService`, `Dev/ProdMessageService`
- ✅ `pom.xml`: +`spring-boot-starter-actuator` (заготовка к шагу 13)
- ✅ `.gitignore`: +`.git.backup_*/`, `.tmp_boot.*`, `run.err/log` (после инцидента с рекурсивным `git add .`)

**Мини-экзамен шага 6 (04.09.2026):**
- Q1 (rollback при исключении из `@Transactional`-метода): 🟢 90%
- Q2 (propagation + `@TransactionalEventListener(AFTER_COMMIT)`): 🟡 65%
- Q3 (REQUIRES_NEW + падение внешней TX — откатится ли audit): 🟢 95%
- **Средний по 3 экзаменам:** **(90+65+95)/3 = 83.3%** → 🟢 крепко

**Пробелы для подтяжки на шаге 8 (Query + спецификации):**
- Где публикуются события (сервис, не сендер)
- Outbox-паттерн для надёжной доставки событий после коммита
- Propagation REQUIRED vs REQUIRES_NEW (частично — 95% на экзамене, но без других режимов)

**Завтра: шаг 7 — Flyway/Liquibase.** Аудит-таблицу вынесем в отдельную миграцию `V2__create_audit_log.sql`, костыль `[audit] ...` в MessageLog уйдёт.

---

## 📅 Сессия №14 (05.09.2026, начало)

**Время старта:** 05.09.2026 08:23 (UTC+3)
**Продолжительность:** 0.0h (на момент старта)
**Правило фиксации времени (новое):** при команде «пауза» / «закончили» / «продолжим» — **обязательно** зафиксировать:
- конец прошлой сессии (точное время + длительность)
- начало новой сессии (точное время, длительность = 0)
Время проставляется в `STATS.md` (таблица) + `OVERALL_STATS.md` (итого) + `PROGRESS.md` (блок сессии) + `HANDOFF.md` (блок статуса).
**Причина правила:** 04.09.2026 сессия #12 закончилась в 00:10, новая сессия #13 стартовала 05.09.2026 04:27 — в сумме +3.2h, но в `STATS.md` это разнеслось правильно только после напоминания ученика. Ученик попросил явно фиксировать при паузах/возобновлениях, без напоминания.

**Зависших задач нет.**

## 🔑 Шпаргалка для следующего открытия (ПРОЧИТАТЬ ПЕРВЫМ ДЕЛОМ)

**Если ученик написал «прочитай прогресс» — прочитай ОБЯЗАТЕЛЬНО все 8 файлов в таком порядке:**

0. **`HANDOFF_TO_AGENT.md`** ⚠️ — **н**юансы работы с учеником от предыдущего агента (если есть). Важнее остальных, потому что содержит личные наблюдения о стиле общения с Василием. **Удалить после первой полноценной сессии.**
1. **`COURSE_HANDBOOK.md`** — master-файл с историей
2. **`OVERALL_STATS.md`** — сводка по всем проектам
3. **`HANDOFF.md`** — краткое резюме
4. **`PROGRESS.md`** (этот файл) — где остановились
5. **`STATS.md`** — баллы, время, прогресс
6. **`LEARNING_LOG.md`** — конспект теории
7. **`HELP.md`** — служебный файл от Spring Initializr (не редактировать, только знать что есть)

### Суть

- **Ученик:** Василий. Готовится к собеседованию по Spring Boot. См. `COURSE_HANDBOOK.md`.
- **Профиль:** ценит глубокие разборы с аналогиями, ASCII-схемы, мини-экзамены без вариантов. **Не любит готовый код классов целиком** — давай только подсказки и направление.

### Главное правило про время
**Не угадывай время — всегда `Get-Date`.**

### Правила работы с паузами и сессиями (обновлено 31.08.2026)

Ученик жёстко просил 31.08.2026 зафиксировать следующие команды — **проверяй время `Get-Date` перед каждой реакцией**:

| Команда ученика | Что делать ассистенту |
|---|---|
| **«начинаю обучение»** / **«продолжим»** | Зафиксировать время старта (`Get-Date`), отметить в `STATS.md` новую строку с датой и часами (по факту, в конце сессии), продолжить с того места, где остановились. |
| **«пауза»** | Проверить время (`Get-Date`), **не коммитить**, просто ждать следующей команды. Никаких записей в `STATS.md` не делать — сессия не закрыта. |
| **«закончили на сегодня»** / **«заканчиваем на сегодня»** | Проверить время (`Get-Date`), записать в `STATS.md` итого за день, синхронизировать `HANDOFF.md` / `OVERALL_STATS.md` / `COURSE_HANDBOOK.md`, **закоммитить и запушить**. |
| **«коммитим как есть»** | Дозаписать прогресс в `LEARNING_LOG.md`, синхронизировать дневники, коммит + пуш. |

**Ключевое правило:** каждая сессия — отдельная строка в `STATS.md` с датой. Не путать «сегодня» и «вчера» — `Get-Date` обязателен.

### Главное правило про git
**После каждого завершённого шага — `git add . && git commit -m "step N: <название>" && git push`.** Ученик явно попросил 28.08.2026.

## 📋 План курса (15 шагов)

### Блок 1: Spring Boot Basics (шаги 1–4)
1. **Старт Spring Boot проекта** — `@SpringBootApplication`, `SpringApplication.run()`, embedded Tomcat
2. **Автоконфигурация** — `application.properties`/`yml`, профили (`dev`/`prod`), `@Profile`
3. **REST API: `@RestController`, `@GetMapping`**
4. **DTO + валидация** (`@Valid`, `@NotNull`)

### Блок 2: Spring Data JPA (шаги 5–8)
5. **Spring Data JPA: `@Entity`, `JpaRepository`** (H2 in-memory) + фикс `/h2-console` loop (микро-шаг 2 — `forward:` vs `redirect:`, урок для собеса) ✅
6. **Транзакции: `@Transactional`** (связь с AOP из курса №1)
7. **Миграции: Flyway/Liquibase**
8. **Query: JPQL, native, Specification**

### Блок 3: Spring Security (шаги 9–11)
9. **Spring Security basics** — `SecurityFilterChain`, `BCryptPasswordEncoder`
10. **JWT / OAuth2**
11. **Method Security: `@PreAuthorize`, `@Secured`**

### Блок 4: Testing + Production (шаги 12–14)
12. **Тестирование** — `@SpringBootTest`, `MockMvc`, `Testcontainers` (базу заложили в шаге 5 — `H2ConsoleRedirectTest` с `@WebMvcTest` + `@SpringBootTest`)
13. **Логирование + мониторинг** — SLF4J, Micrometer, Actuator
14. **Кэширование** — `@Cacheable`, `@CacheEvict`

### Блок 5: Финал (шаг 15)
15. **Ревью курса, финальная шпаргалка, чеклист «готов к собесу»**
