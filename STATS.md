# 📈 Статистика обучения

Всё, что связано с цифрами: время, баллы, процент, мини-экзамены. Если этот файл потеряется — можно восстановить.

---

## 🏁 Общий прогресс

**Проект:** Notification Hub (Spring Boot), 15 шагов.

```
Шаг:  0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15
       ◉   ✓   ✓   ✓   ✓   ✓   ✓   ✓   ✓   ✓   ◐   ○   ○   ○   ○   ○
       ◉ init  ✓ done  ◐ in progress  ○ ahead
```

**Процент прохождения:**

```
|██████████████████████████████████████░░░░░░░░░░░░░░░░░░░░░░| 60% (9/15)
```

**Текущий шаг:** 9 / 15 — Spring Security basics ✅ (9-A starter, 9-B SecurityFilterChain, 9-C InMemory users + BCrypt + roles; мини-экзамен ~87%; коммит `e93bbbe`)

**Последнее обновление:** 11.09.2026 10:20 (финал шага 9, коммит `e93bbbe`)

---

## ⏱ Затраченное время

**Правила:**
- Перед началом: **«начинаю обучение»**
- При паузе: **«пауза»**
- При окончании: **«заканчиваю обучение на сегодня»**

| # | Дата | Тема | Часов |
|---|------|------|-------|
| 1 | 28.08.2026 | Шаг 1: Старт Spring Boot (теория + мини-экзамен, **без практики**) | 0.7 |
| 2 | 28.08.2026 | Шаг 2: Spring Boot автоконфигурация (4 микро-шага + мини-экзамен, **v2-формат**) | 0.8 |
| 3 | 28.08.2026 | Шаг 3: REST API — старт (только теория микро-шага 1) | 0.1 |
| 4 | 28.08.2026 | Шаг 3: REST API — **микро-шаг 1 пройден** (`/hello` → JSON, разобрали DI-баг профилей) | 0.7 |
| 5 | 30.08.2026 | Шаг 3: REST API — микро-шаги 2-4 (`@PathVariable`, `@RequestParam`, `@PostMapping` + JSON) | 0.6 |
| 6 | 30.08.2026 | Шаг 4: DTO + валидация — микро-шаги 1-2 (`@Valid`, `@NotBlank`, `@RestControllerAdvice`) | 0.5 |
| 7 | 31.08.2026 | Шаг 4: продолжение — мини-экзамен (не ответил, ушёл на паузу) | 1.0 |
| 8 | 31.08.2026 | Шаг 4: расширение GlobalExceptionHandler (malformed JSON + 404 + 500 fallback) + мини-экзамен + коммит | 1.0 |
| 9 | 02.09.2026 | Шаг 5: JPA + H2 in-memory (JpaRepository save/read) + фикс /h2-console loop (forward→redirect) + урок @WebMvcTest vs @SpringBootTest + диагностика contribution graph (rewrite истории git-history с vasilii@local → vasekbasovv@mail.ru, force-push) | 2.5 |
| 10 | 02.09.2026 | Шаг 5 (финал): удалили WebConfig.java целиком, но петля /h2-console осталась — корень в Spring Boot 4.0.8 (нет автоконфига H2 web console, DispatcherServlet forward-петля). H2 console отключена полностью, отладка через GET /messages + show-sql. H2ConsoleRedirectTest отменён (тест не имеет смысла). Начали шаг 6 (теория + план), остановились на выборе use-case | 0.6 |
| 11 | 04.09.2026 | Шаг 6 micro-1: @Transactional граница (Controller → Service → Repository; EmailSender interface + ConsoleEmailSender @Profile("dev") + FailingEmailSender @Profile("dev-fail"); rollback-сценарий в requests/messages-rollback.http; +spring-boot-starter-actuator; чистка .gitignore). Мини-экзамен 2 вопроса (rollback + propagation) → 90% + 65% = 78% | 0.3 |
| 12 | 04.09.2026 | Шаг 6 micro-2: Propagation REQUIRES_NEW. Self-injection через @Lazy в NotificationService (поле `self`, конструктор с 3-м параметром), добавлен метод `auditSend` с `@Transactional(propagation = REQUIRES_NEW)`. Ученик сам догадался поставить `self.auditSend` ДО `emailSender.send` — иначе audit не успевал закоммититься на dev-fail. Мини-экзамен (REQUIRES_NEW + падение внешней TX) → 95% | 3.2 |
| 13 | 05.09.2026 | Шаг 7 (старт): Flyway. Ученик сам написал AuditLog + AuditLogRepository + auditSend на AuditLog (вместо костыля [audit] в messages). pom.xml: +flyway-core. Создал V1/V2 миграции, но **с одним подчёркиванием** в имени (V1_init_messages.sql) — Flyway их НЕ ВИДИТ. Диагностика: Spring Boot 4 требует spring-boot-starter-flyway (добавил). После этого Flyway применил 2 миграции (Migrating v1 → v2 → Successfully applied). **Но** Hibernate всё равно делает drop+create — потому что в application.properties стоит `ddl-auto=create-drop`, а в application-dev.yml `ddl-auto:none` не перебивает. Решили переходить на production-like dev-стенд. Переименованы файлы миграций (V1__init_messages.sql, V2__init_audit_log.sql) | 3.0 |
| 14 | 05.09.2026 | Шаг 7 (финал): реализация Подхода #3. `H2ServerConfig`: `@Component` + `BeanFactoryPostProcessor` (стартует TCP-сервер ДО Flyway). pom.xml: +explicit `h2` со `scope=compile` (transitive scope=runtime блокирует `org.h2.tools.Server` в IDE). `application.properties`: url=`jdbc:h2:tcp://localhost:9092/file:./data/notificationhub`, `ddl-auto=validate`. `application-dev.yml`: убран `ddl-auto:none`. Диагностика: `Connection refused: localhost:9092` — `BeanFactoryPostProcessor` без `@Component` не регистрируется → добавил `@Component`. Ученик сам ответил «почему не @Configuration: нет @Bean, side-effect» — правильно. Верификация: POST /messages → 201, kill app → restart → 5 записей на месте (`./data/notificationhub.mv.db` 45 КБ). Flyway: `Successfully validated 2 migrations`, Hibernate: 0 DDL в логе. Мини-экзамен (2 вопроса, **3/5**): checksum mismatch V3 (4/5) + V vs R миграции (2/5 — R надо подтянуть на собесе). Коммит `7634659` на main | 2.5 |
| 15–17 | 06.09.2026 | Шаги 7 → 8 (пауза, документы, тесты руками): сессия №16 (11:45 → 14:24, 2.6ч) + сессия №17 (15:30 → ~16:30, 1.0ч) = **3.6ч** одной строкой | 3.6 |
| 18 | 10.09.2026 | Шаг 8 (Spring Data JPA queries) — **в процессе**. Сделано: 8-A JPQL (`findByRecipientOrderByCreatedAtDesc`, `findByCreatedAtBetween` тип `Instant` после бага совместимости), 8-B native (`AuditLogRepository.findByEventTypeNative`), 8-C Specification (3 static-spec: `hasRecipient/textContains/createdAfter` + `MessageLogController.search()`). Диагностика: `Specification.where(null)` падает с `IllegalArgumentException` в рантайме (подумали что это Spring Data 3 ambiguous, оказалось — все версии). Фикс: `Specification.unrestricted()`. Поймали кеш IDEA — `target/classes/` не пересобирался, в логе `EmailSender bean not found` хотя `@Component` есть → `Build → Rebuild Project` + убить старые java-процессы. Все 5 curl'ов на `/messages/search` прошли: 200 + JSON, фильтры работают, пустой recipient → `[]`. Мини-экзамен (~75%, см. таблицу) | 0.6 |

**Итого:** 21.7 ч (старт курса №2 — 28.08.2026 17:10, последняя активность 11.09.2026 10:20). Итого времени шага 9 будет добавлено в таблицу при закрытии сессии (правило: «закончили на сегодня»).

---

## 📈 Готовность к собеседованию (по темам)

**Легенда:**
- 🟢 80–100% — знаешь твёрдо
- 🟡 50–79% — помнишь суть, есть пробелы
- 🔴 0–49% — пробел, надо разобрать

| ! | Тема | Балл | Комментарий |
|---|------|------|-------------|
| 🟡 | Что такое Spring Boot (отличия от Core) | 60% | Суть понял («делает больше и ждёт»), но назвал `SpringApplication.run` «процессом» — неточно. Поправлено, формулировку учить |
| 🟢 | `@SpringBootApplication` = 3 аннотации | 90% | Сразу сказал правильно: `@Configuration` + `@ComponentScan` + автоконфигурация |
| 🟡 | `SpringApplication.run()` — что делает под капотом | 60% | Связано с предыдущим — путает «метод» и «процесс» |
| 🟢 | `application.properties` / `.yml` — где живут и приоритет | 90% | Сразу понял. Формулировку «иерархия» уточнить → «приоритет / порядок загрузки» |
| 🟢 | Профили `dev`/`prod` — как активировать | 95% | Увидел разницу в логах, ответил правильно с первого раза |
| 🟡 | `@Profile` — что будет если профиль не подходит | 70% | Суть верная (DI упадёт), но сказал «2 бина в контексте» — на самом деле **0**. Исключение `NoSuchBeanDefinitionException`, не `NoUnique...`. Учить |
| 🟢 | `@Transactional` — что произойдёт при исключении из метода | 90% | Сразу сказал «rollback», суть верная. Не хватило деталей про AOP-прокси + `PlatformTransactionManager`. Учить полную формулировку |
| 🟡 | `@Transactional` propagation (REQUIRED vs REQUIRES_NEW) | 65% | Пока не разбирали явно. На собесе любят спрашивать про вложенные вызовы. **Подтянуть на шаге 6/8** |
| 🟢 | `@TransactionalEventListener(AFTER_COMMIT)` — поведение при падении listener'а | 65% | Сказал «не откатится», но не назвал где публикуются события (сервис, не сендер) и outbox-паттерн. **Подтянуть на шаге 8** |

**Средний балл:** **71%** (12 тем, сумма 850/12 ≈ 70.8). Подрос с 68% после шага 8 (~75%). Propagation и outbox ещё подтягивать.
**Средний балл по шагам (со средними по мини-экзаменам внутри шага):** шаг 2 — 80%, шаг 3 — 93%, шаг 4 — 85%, шаг 6 — **~83%**, шаг 7 — **~70%**, шаг 8 — **~75%** → общий **(80+93+85+83+70+75)/6 = 81%**. Этот показатель точнее отражает прогресс, потому что один шаг = одна тема с весом.

**Приоритеты на подтяжку:**
- 🔁 **ПРОПУЩЕНО 28.08.2026:** должен был спросить правильную формулировку `SpringApplication.run()` в естественном контексте шага 3. Не спросил. **Спросить на шаге 6/7**, если `run` снова встретится. Тон — «а напомни, как там было...», без «ты ошибся». См. `LEARNING_LOG.md`, шаг 1.
- 🟡 **Шаг 1 без практики** — НЕ наверстывается отдельно. Уже отработано в шаге 2 (4 микро-шага = полноценная практика). Статус шага 1 остаётся 🟡 исторически — как напоминание, что без практики шаг не «полный».
- 🟡 **Путаница «нет бинов» vs «несколько бинов»** — DI-исключения. Учить разницу `NoSuchBeanDefinitionException` vs `NoUniqueBeanDefinitionException`. На собесе любят спрашивать.
- 🟡 **Где публикуются события** — сервис, не сендер. Outbox-паттерн. **Не разобрали на шаге 8** (шаг 8 был про queries). Подтянуть отдельно.

**Цель:** к шагу 15 — 80%+ по всем темам.

---

## 🧪 Мини-экзамены (история)

| # | Шаг | Тема мини-экзамена | Балл | Дата |
|---|-----|---------------------|------|------|
| 1 | 1   | `@SpringBootApplication` = 3 аннотации | 🟢 90% | 28.08.2026 |
| 1 | 1   | Что делает `SpringApplication.run` (отличия от Core) | 🟡 60% | 28.08.2026 (поправлено, формулировку учить) |
| 2 | 2   | `application.properties`/`.yml` — где живут и приоритет | 🟢 90% | 28.08.2026 |
| 2 | 2   | Профили `dev`/`prod` — как активировать и переопределяют ли базовый конфиг | 🟢 95% | 28.08.2026 |
| 2 | 2   | `@Profile` — что будет если профиль не подходит ни одному бину | 🟡 70% | 28.08.2026 (поправлено про `NoSuchBean` vs `NoUnique`) |
| 6 | 6   | `@Transactional` — что будет при RuntimeException из метода | 🟢 90% | 04.09.2026 (поправлено про AOP-прокси + `PlatformTransactionManager`) |
| 6 | 6   | `@TransactionalEventListener(AFTER_COMMIT)` + propagation | 🟡 65% | 04.09.2026 (поправлено: где публиковать события, outbox-паттерн) |
| 6 | 6   | REQUIRES_NEW + падение внешней TX — откатится ли audit-запись? | 🟢 95% | 04.09.2026 (ответил правильно — вариант 2; не хватило формулировки «уже закоммиченная REQUIRES_NEW-TX не откатывается») |
| 7 | 7   | Flyway checksum mismatch: что будет и как чинить | 🟢 80% | 05.09.2026 (идея верная — увидеть SQL через `ddl-auto:create` и вставить; но забыл главное: Flyway падает на checksum mismatch ДО любого DDL, чинить через возврат файла, не через БД) |
| 7 | 7   | Flyway V vs R миграции | 🟡 40% | 05.09.2026 (направление верное — repeatable можно много раз; не знал про checksum и порядок применения) |
| 8 | 8   | Когда A vs B vs C (именованный / `@Query` JPQL / Specification) | 🟡 55% | 10.09.2026 (A — ок; B «для join'ов» — не то, B для проекций/агрегаций; C «для подстрок + времени» — не то, C для динамических опциональных фильтров. Ключевые use-cases не захвачены) |
| 8 | 8   | Тип параметра `Instant` для `findByCreatedAtBetween` | 🟢 100% | 10.09.2026 (сразу: «Instant» — Hibernate 6+ требует совпадения типа параметра с типом поля entity) |
| 8 | 8   | Парсинг ISO-даты `?from=2026-09-01` → `Instant` для BETWEEN | 🟢 80% | 10.09.2026 (идея верная: `LocalDate.parse().atStartOfDay().toInstant(ZoneOffset.UTC)`; перепутал `atStartOfDay()` со static — синтаксис мимо) |
| 9 | 9   | Когда `UserDetailsService` вызывается: на каждый запрос или один раз | 🟡 60% | 11.09.2026 (ответил «на каждый запрос, где нужна роль» — неверно. На самом деле **один раз на логин**, дальше роль берётся из `SecurityContext`. Пароль сверяется через `PasswordEncoder.matches()`, после чего `Authentication` кладётся в сессию и больше не вызывает `UserDetailsService` на этот запрос) |
| 9 | 9   | `requestMatchers` без `HttpMethod` — какие методы попадают | 🟢 100% | 11.09.2026 (сразу: «любые». `requestMatchers("/messages").hasRole("ADMIN")` без метода = и GET, и POST требуют ADMIN. Поэтому в 9-C обязательно `HttpMethod.POST`) |
| 9 | 9   | Порядок matcher'ов: что если `anyRequest().authenticated()` поставить первым | 🟢 100% | 11.09.2026 (сходу: «до проверки ролей не дойдёт, anyRequest перекроет всё». Главный нюанс Security — порядок сверху вниз) |
| 9 | 9   | `permitAll` vs `authenticated()` для статики и API | 🟢 100% | 11.09.2026 (верно: `permitAll` пускает всех анонимов, `authenticated` требует залогиниться. Понял разницу между «GET /app.js от анонима» (permitAll → 200) и «GET /api/users от анонима» (anyRequest → 401)) |

**Средний балл по мини-экзаменам шага 8:** (55 + 100 + 80) / 3 = **~78%** → округлено до **~75%** (минус за то что ключевая идея Q1 не схвачена).
**Средний балл по мини-экзаменам шага 9:** (60 + 100 + 100 + 100) / 4 = **90%** → взвешенно с порядком matcher'ов (это самая частая ошибка новичков) → **~87%**.

---

*Файл создан 28.08.2026 17:10 при старте курса №2.*
