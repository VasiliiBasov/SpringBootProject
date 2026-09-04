# 📋 Прогресс: контекст и навигация

Здесь — **только** то, что нужно для быстрого входа в работу: где остановились, что дальше, шпаргалка для следующего открытия.

**Цифры, время, баллы — в `STATS.md`.**
**Конспект теории — в `LEARNING_LOG.md`.**
**Краткое резюме (если история слетит) — в `HANDOFF.md`.**
**Сводка по всем проектам — в `OVERALL_STATS.md`.**
**Программа курса + история — в `COURSE_HANDBOOK.md`.**

---

## 📌 Где мы сейчас

**Текущий шаг:** 6 / 15 — Транзакции: `@Transactional` (micro-1 ✅ + micro-2 ✅, мини-экзамены ✅, средний ~83%). Коммит шага 6 micro-2 **не закоммичен** (ученик ушёл на сегодня).
**Следующий шаг:** шаг 7 — Flyway/Liquibase (завтра, по решению ученика).
**Процент:** 40% (6/15) — шаги 1, 2, 3, 4, 5, 6 (micro-1 + micro-2) закрыты

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
