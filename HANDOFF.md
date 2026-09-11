# 🚀 HANDOFF: быстрое восстановление контекста

**Когда читать:** если чат/история потеряна и нужно понять, **что вообще происходит**, за 2 минуты.

Если что-то непонятно — открой `PROGRESS.md` (навигация) и `LEARNING_LOG.md` (теория).

---

## 🎯 Суть курса

- **Ученик:** Василий
- **Цель:** подготовиться к собеседованию по **Spring Boot** + смежные
- **Формат:** 15-шаговый проект **Notification Hub** (расширенный)
- **Подход:** реальный код + разбор теории + мини-экзамены
- **Контекст:** ученик уже завершил Spring Core (12 шагов, ~85%) — подробности в `COURSE_HANDBOOK.md`

## 🛠 Проект

- **Язык:** Java 21 (target в `pom.xml`); **в IDEA работает JDK 23.0.1** (Boot 4 совместим, видим в логе старта)
- **Сборка:** Maven 3.9.9 (по полному пути `C:\tools\apache-maven-3.9.9`)
- **Фреймворк:** Spring Boot **4.0.8** (Boot 4 — новая ветка, не 3.x!)
- **Starter:** `spring-boot-starter-webmvc` (Boot 4 переименовал `web` → `webmvc`)
- **IDE:** IntelliJ IDEA, Windows 11

## 📊 Текущий статус

- **Шаг:** 9 / 15 ✅ (Spring Security basics, **закрыт 11.09.2026**, коммит `e93bbbe`)
- **Дата старта курса №2:** 28.08.2026 17:10
- **Дата последнего обновления:** 11.09.2026 10:20 (финал шага 9)
- **Средний балл по мини-экзаменам (12 тем):** **~71%** (12 тем: 90+60+90+95+70+90+65+95+80+40+55+100+80 = 1010/13 ≈ 77.7% — но включая частичные вопросы шага 8 = 71% с весом)
- **Средний балл по шагам (7 закрытых шагов):** **~82%** (80+93+85+83+70+75+87)/7 = ~82% (шаг 9 повысил средний)
- **Средний балл по шагу 2:** 80%
- **Средний балл по шагу 3:** ~93% (лучший в курсе №2)
- **Средний балл по шагу 4:** ~85%
- **Средний балл по шагу 6:** **~83%** (micro-1: 78%, micro-2: REQUIRES_NEW 95%)
- **Средний балл по шагу 7:** **~70%** (V vs R — слабо, подтянуть на собесе)
- **Средний балл по шагу 8:** **~75%** (A vs B vs C — 55%, нужно подтянуть use-cases)
- **Средний балл по шагу 9:** **~87%** (UserDetailsService частота 60%; HttpMethod/порядок/permitAll — 100%)
- **Всего потрачено:** **21.7 ч** (17.5 до + 3.6 (06.09, сессии №16–17) + 0.6 (10.09, сессия №18); время 11.09 будет добавлено при закрытии сессии)

**Что сделано в шаге 9 (11.09.2026, сегодня):**
- ✅ **9-A:** `pom.xml` + `spring-boot-starter-security` (коммит `3174036` — отдельный коммит под 9-A). Дефолтный Security подтверждён: `curl -u "user:<uuid>"` → 200 + JSON, headers видны (`X-Frame-Options: DENY`, `X-Content-Type-Options: nosniff`). Фронт (path C) создан: `src/main/resources/static/{index.html,app.js,style.css}`. Порт 8081 (из `application.properties`).
- ✅ **9-B:** `SecurityConfig.java` (создал ученик) с `@Bean SecurityFilterChain` — `permitAll` для статики, `anyRequest().authenticated()` для API, `httpBasic` для curl, `csrf.disable()` для REST. Фикс: пропустил слэш у `"style.css"` → `pattern must start with a /`.
- ✅ **9-C:** `BCryptPasswordEncoder` + `InMemoryUserDetailsManager` с 2 юзерами: `alice/alice123` (USER), `admin/admin123` (USER+ADMIN). `requestMatchers(HttpMethod.POST, "/messages").hasRole("ADMIN")` — обязательно с `HttpMethod`.
- 🔍 **Бонус-диагностика 1:** `requestMatchers("POST", ...)` со String → Spring парсит оба как URL → ошибка. Фикс: `HttpMethod.POST` (enum).
- 🔍 **Бонус-диагностика 2:** PowerShell splatting `@body.json` ломается (даже в PS 7). Фикс: `-d (Get-Content -Raw body.json)`. Установлен PowerShell 7.4.6, настроен в IDEA как Shell path.
- 🧪 Мини-экзамен (4 вопроса, **~87%**): UserDetailsService (60%) + HttpMethod (100%) + порядок matcher'ов (100%) + permitAll (100%)
- ✅ Коммит `e93bbbe` на main

**Что сделано в шаге 8 (10.09.2026, сессия №18):**
- ✅ **8-A (JPQL):** `MessageLogRepository.findByRecipientOrderByCreatedAtDesc(...)`, `findByCreatedAtBetween(Instant, Instant)` — тип `Instant` после бага совместимости
- ✅ **8-B (native):** `AuditLogRepository.findByEventTypeNative(@Param("eventType") String eventType)` — `SELECT * FROM audit_log WHERE event_type = :eventType ORDER BY created_at DESC`
- ✅ **8-C (Specification API):** 3 static-спецификации в `MessageLogRepository` (`hasRecipient`, `textContains`, `createdAfter`) + `MessageLogController.search()` — `GET /messages/search?recipient=...&q=...&from=...`
- ✅ `H2ServerConfig`: убран `@Profile("dev")` (TCP-сервер нужен в любом профиле)
- ✅ Удалён `TestQueryRunner.java` (временный, задача выполнена)
- 🔍 **Диагностика 1:** `EmailSender bean not found` при `dev` → IDEA кешировала `target/classes/` (`ConsoleEmailSender.class` и `FailingEmailSender.class` отсутствовали). Фикс: `Build → Rebuild Project` + убить старые java-процессы
- 🔍 **Диагностика 2:** `Specification.where(null)` → `IllegalArgumentException: Specification must not be null` (Specification.java:89 ВСЕГДА проверяет на null, не только в Spring Data 3+). Фикс: `Specification.unrestricted()`
- ✅ Все 5 curl'ов на `/messages/search` прошли: 200 + JSON, фильтры работают, ghost → `[]`
- 🧪 Мини-экзамен (3 вопроса, **~75%**): A vs B vs C (55%) + тип `Instant` (100%) + парсинг `LocalDate → Instant` (80%)
- ✅ Коммит `983f8dc` на main

**Что сделано в шаге 7 (финал, 05.09.2026, сессии №13–14):**
- ✅ Реализован Подход #3 — production-like dev (TCP-сервер H2 + файловая БД + `ddl-auto=validate`)
- ✅ `H2ServerConfig`: `@Component` + `BeanFactoryPostProcessor` — TCP-сервер стартует ДО Flyway (через @Component иначе не регистрируется, поймали `Connection refused`)
- ✅ `application.properties`: url=`jdbc:h2:tcp://localhost:9092/file:./data/notificationhub`, `ddl-auto=validate`
- ✅ `application-dev.yml`: убран `ddl-auto:none`
- ✅ pom.xml: +explicit `h2` со `scope=compile` (transitive scope=runtime блокирует `org.h2.tools.Server` в IDE)
- ✅ Верификация: Flyway `Successfully validated 2 migrations`, Hibernate 0 DDL в логе, POST→restart→5 записей на месте
- 🧪 Мини-экзамен (2 вопроса, **3/5**): Flyway checksum mismatch (4/5) + V vs R миграции (2/5)
- ✅ Коммит `7634659` на main

**Что сделано в шаге 7 (старт, 05.09.2026, сессия №13):**
- ✅ Ученик **сам** написал `AuditLog` + `AuditLogRepository` + переписал `auditSend` на сохранение в `audit_log`
- ✅ `pom.xml`: +`flyway-core` (Boot BOM → 11.14.1) + `spring-boot-starter-flyway` (нужен в Boot 4)
- ✅ Миграции `V1__init_messages.sql`, `V2__init_audit_log.sql` (после переименования, двойное `_`)
- 🔍 Flyway применил 2 миграции (`Successfully applied 2 migrations`) — **работает**
- ⚠️ Hibernate всё ещё делал `drop+create` после Flyway из-за `ddl-auto=create-drop` в `application.properties` (`.yml` не перебивает `.properties`)
- 🎯 Решение: переход на production-like dev (Подход #3 — H2 TCP-сервер + файловая БД)

**Что сделано в шаге 6 (micro-2, 04.09.2026):**
- ✅ `NotificationService` дополнен self-injection через `@Lazy` (поле `self`, конструктор с 3-м параметром)
- ✅ Добавлен метод `auditSend(...)` с `@Transactional(propagation = REQUIRES_NEW)` — пишет запись с префиксом `[audit]` в `messages`
- ✅ Ученик **сам** догадался поменять местами `emailSender.send` и `self.auditSend` (иначе audit не успевал закоммититься до исключения на dev-fail)
- ✅ Проверено на `dev-fail`: в БД **1 запись** `[audit]` (от auditSend, REQUIRES_NEW), основная запись из `send` откатилась. Это и есть смысл REQUIRES_NEW — audit живёт независимо от бизнес-TX
- ✅ Теория propagation: REQUIRED / REQUIRES_NEW / NESTED / MANDATORY / SUPPORTS / NOT_SUPPORTED / NEVER
- ✅ Мини-экзамен (1 вопрос, **95%**): «откатится ли audit-запись при падении внешней TX» — ответил «вариант 2, audit выживет» с правильным пониманием, что REQUIRES_NEW-TX уже закоммичена к моменту исключения
- ⚠️ **unchecked в формулировке**: не назвал ключевое «уже закоммиченная REQUIRES_NEW-транзакция не откатывается внешним rollback'ом»

**Что сделано в шаге 6 (micro-1, 04.09.2026):**
- ✅ Архитектура Controller → Service → Repository починена (`HelloController` дёргает `notificationService.send(...)`, не `repository.save` напрямую)
- ✅ `EmailSender` интерфейс + 2 реализации по `@Profile`:
  - `dev` → `ConsoleEmailSender` (печатает в stdout)
  - `dev-fail` → `FailingEmailSender` (кидает `RuntimeException` — для проверки ROLLBACK)
- ✅ `@Transactional` на `NotificationService.send(...)` — обёртка `save + emailSender.send` в одну TX
- ✅ HTTP-файл `requests/messages-rollback.http` для удобного теста
- ✅ Мини-экзамен (2 вопроса): rollback + propagation → 90% + 65% = 78%
- ✅ Коммит `b5e9342` запушен в `origin/main`
- ✅ Старые сервисы удалены: `GreetingService`, `MessageService`, `Dev/ProdMessageService`
- ✅ `pom.xml`: +`spring-boot-starter-actuator` (заготовка к шагу 13)
- ✅ `.gitignore`: +`.git.backup_*/`, `.tmp_boot.*`, `run.err/log` (после инцидента с рекурсивным `git add .` 04.09)

**Что сделано в шаге 5 (целиком):**
- 2 микро-шага ✅
  - **micro-1**: JPA + H2 in-memory DB. Зависимости `spring-boot-starter-data-jpa` + `h2` в `pom.xml`. `entity/MessageLog.java` (JPA-entity), `repository/MessageLogRepository.java` (extends `JpaRepository<MessageLog, Long>`), `service/MessageService.java` (save/findAll), контроллер дёргает сервис. `spring.jpa.hibernate.ddl-auto=create-drop` для dev-режима.
  - **micro-2**: фикс петли `/h2-console`. В `WebConfig.java` было `addViewController("/h2-console", "forward:/h2-console")` — создавало бесконечный forward (Circular view path). Решение: **вариант Б** (рекомендация) — удалить `WebConfig.java` целиком.
- Диагностика contribution graph: коммиты не показывались, потому что `git config user.email = vasilii@local` не совпадал с email GitHub. Решено через `git filter-repo` (rewrite истории всех коммитов: `vasilii@local → vasekbasovv@mail.ru`), force-push в `origin/main`. Новый HEAD = `339f3d0`. Бэкап: `.git.backup_20260902_024500/` (на диске, **не трогать минимум до 09.09.2026**). С 04.09 добавлен в `.gitignore`.

**Известная мелочь:** `HelloController.java` сейчас содержит только `POST /messages` и `GET /messages` — другие методы (`/hello`, `/search`, `/users/{id}/orders/{orderId}`) были утеряны при правках шага 3. Для собеса не нужны, по мере необходимости восстановим.

**Известная проблема (Spring Boot 4.0.8):** `GET /h2-console` даёт 500 + StackOverflowError даже без `WebConfig`. Корень: Spring Boot 4 не содержит автоконфигурации H2 web console (в отличие от Boot 3.x) + DispatcherServlet forward-петля через `InternalResourceView`. Решение: H2 console отключена полностью, отладка через `GET /messages` + `spring.jpa.show-sql=true`. `H2ConsoleRedirectTest` отменён — на шаге 12 (Testing) разберём `ServletRegistrationBean<WebServlet>` и напишем нормальный тест. Подробности в `LEARNING_LOG.md`, раздел «Шаг 5, микро-шаг 2 — ФИНАЛ».

**Что сделано в шаге 3 микро-шаг 1:**
- Создан `controller/HelloController.java` с `@RestController` + `@GetMapping("/hello")` → `Map.of(...)`
- Упрощён `NotificationHubApplication.main()` до стандартного `SpringApplication.run(...)`
- Получен JSON через `http://localhost:8081/hello` → `{"status":"ok","message":"Hello from boot"}`
- **В реальном рантайме поймали** `NoSuchBeanDefinitionException` (0 бинов из-за `@Profile` без активного профиля). Ученик СРАЗУ вспомнил правило из шага 2 — выбрал правильное исклюнение 🎯
- Решено через `--spring.profiles.active=dev` (без рефакторинга)
- **Закрыто правило №1 курса №2** (правильная формулировка про `SpringApplication.run()` — 85%)

## 🧠 Что изучено в курсе №1 (краткий повтор)

См. `COURSE_HANDBOOK.md`, раздел «КУРС №1».

## 📚 Где что лежит

- **`PROGRESS.md`** — навигация, где остановились, что дальше
- **`STATS.md`** — дашборд (время, баллы, процент, мини-экзамены)
- **`LEARNING_LOG.md`** — конспект теории, разборы шагов, копилка фактов + шпаргалки для собеса
- **`OVERALL_STATS.md`** — сводка по всем проектам ученика
- **`COURSE_HANDBOOK.md`** — master-файл с историей курса №1 и программой курса №2

## 🎓 Профиль ученика

См. `COURSE_HANDBOOK.md`, раздел «Профиль обучения». Главное:

- ✅ Заходят длинные разборы с аналогиями, ASCII-схемы, мини-экзамены **без вариантов ответа**
- ❌ НЕ давать готовый код классов целиком — только подсказки и направление
- ❌ В вопросах мини-экзаменов **не утекать ответы**
- ⚠️ Слабые места: путает похожие концепции, доверяет недавней памяти, ошибки в формулировках закрепляются → давать сравнительные вопросы и требовать явные таблицы отличий

## 🚨 Инструкция по стилю ответов (обязательно!)

1. **НЕ ЖДАТЬ команд** типа «продолжим» / «коммить» / «обнови файлы». Ученик дал **директиву** → **сразу** её выполняю. Не спрашиваю «а сейчас делаем X или Y?», если директива однозначна.
2. **НЕ уточнять** после явных инструкций («коммить», «обнови логи», «идём в шаг N»). Делай → отчитывайся о результате.
3. **Не извиняться за косяки дважды.** Один раз признал → исправил → поехали дальше.
4. Уточняющий вопрос **уместен только** когда есть реальная неоднозначность (например, выбор из 2-3 взаимоисключающих вариантов реализации с разными trade-off).
5. **НЕ ПИСАТЬ КОД ЗА УЧЕНИКА.** Давать подсказки, направление, шпаргалки — но финальный код (классы целиком, правки файлов) пишет сам ученик. Исключение: мелкие правки в `*.md` (HANDOFF, LOG, STATS) и `git commit/push`.
