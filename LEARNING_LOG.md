# 📓 Learning Log — дневник теории

Сюда пишем **всё**: разборы шагов, новые концепции, шпаргалки для собеса, открытия, ошибки.

**Формат (v2, утверждён учеником 28.08.2026):**
- `## Шаг N: <название>` — заголовок шага
- Под ним: **микро-шаги (теория порциями + микро-задания ученика) → мини-экзамен → итог**
- Каждый микро-шаг: 1-2 абзаца теории + 1 микро-задание ученику («создай файл X» / «запусти вот так» / «пришли вывод»)
- Мини-экзамен — **после** всех микро-шагов, проверяет итог
- Шпаргалки для собеса — в конце файла, таблицей

---

## Шаг 0: Служебный — инициализация проекта (сделано 28.08.2026)

### Что сделали

- Разведали состояние `SpringBootProject/`: каталог и `pom.xml` есть, но `pom.xml` пустой, структура содержит артефакты `archetype-resources/`
- Создали 6 файлов-дневника: `HANDOFF.md`, `PROGRESS.md`, `STATS.md`, `LEARNING_LOG.md`, `OVERALL_STATS.md`, `COURSE_HANDBOOK.md`
- Обновили `OVERALL_STATS.md`: курс №2 в статусе «В процессе», старт в 17:10
- Дополнили `.gitignore` правилом `.env`
- Инициализировали git, сделали первый коммит `init: старт курса №2 Spring Boot`

### На что обратить внимание перед шагом 1

- `pom.xml` нужно переделать в реальный Spring Boot проект (добавить `spring-boot-starter-parent`, starter'ы)
- `src/main/resources/archetype-resources/` — артефакт, на первом шаге решим: удалить или оставить
- Java 21 уже настроена (предположительно, проверим в `mvn -v`)

---

## 📌 Педагогические правила курса №2 (обновлено 28.08.2026 после шага 1)

> Уточнения ученика, **обязательные к исполнению** всеми ассистентами курса №2.

### 1. Переэкзаменовка — НЕ сразу после теории

❌ **Неправильно:** «Сейчас же повтори правильную формулировку, ты же ошибся».

✅ **Правильно:** Спросить **в естественном контексте**, когда тема встретится в следующих шагах. Например, на шаге 2 (когда речь про `application.yml` и `run-args`) или шаге 3 (когда пишем `@RestController` и `SpringApplication.run` упоминается снова). Тон — «а напомни, как там было...», без давления и без «ты ошибся».

### 2. Не угадывать, устал ли ученик — ученик сам говорит «пауза» / «закончили»

❌ **Неправильно:** «Вижу, что ты устал, на сегодня хватит» / «Давай сделаем паузу, ты подустал» / любые догадки про усталость.

✅ **Правильно:** Ждать явных команд. Ученик **сам** скажет «пауза» или «закончили на сегодня» — тогда реагируем. До этого момента продолжаем по плану шага.

> ⚠️ Это **отдельное** правило, не путать с правилом про `Get-Date` для активного времени (см. `COURSE_HANDBOOK.md`, «Главное правило про время»). Тут речь только про усталость.

### 3. Практика обязательна на каждом шаге (v2 — теория вплетена в практику)

❌ **Неправильно v1:** «Теория блоком → ученик читает → мини-экзамен → коммит».
Проблема: теория оторвана от кода, ученик не понимает, **зачем** каждое утверждение, не учится применять.

✅ **Правильно v2 (уточнение ученика 28.08.2026):** **Теория и практика вплетены**. Каждый кусок теории сопровождается микро-заданием ученику: «впиши вот это в файл, запусти, увидишь вот это в логе». Мини-экзамен — **после** прохождения всех микро-заданий, проверяет итоговое понимание.

Структура шага v2:
1. **Микро-шаг A** — ассистент даёт 1-2 абзаца теории + микро-задание ученику («создай файл X»)
2. **Микро-шаг B** — следующий кусок теории + следующее задание
3. **...** — пока не закроется вся тема шага
4. **Мини-экзамен** — 1-2 вопроса на итоговое понимание
5. **Коммит** — ученик прислал все логи/скриншоты, шаг закрыт

> ⚠️ Слабые примеры для истории:
> - Шаг 1: теория блоком + мини-экзамен, практики вообще не было. Ученик поправил («экзамен по чему ты проводишь?»)
> - Шаг 2 (первая попытка): теория блоком + большое задание из 8 пунктов разом. Ученик поправил («давай совместим»)
> - Шаг 2 (эта попытка): теория порциями, каждая порция = 1 микро-задание ученика
> - На шаге 3 и далее — сразу применять v2

Что считается практикой (v2):
- Ученик **сам** пишет код (хотя бы частично) и запускает
- Ученик **сам** делает запрос/эксперимент и видит результат
- Ученик присылает **свой** лог/скриншот/вывод, а не ассистент дёргает утилиты

Что **не** считается практикой:
- Ассистент сгенерировал проект через start.spring.io / API
- Ассистент запустил `mvn spring-boot:run` в фоне
- Ассистент сделал `curl` / `Invoke-WebRequest` и прислал ответ ученику

### 4. Команды паузы / окончания / продолжения — проверять время через `Get-Date` (добавлено 31.08.2026)

Ученик устал от того, что ассистент **путает даты** и **записывает время не туда**. Правила обязательные:

| Команда ученика | Что делать ассистенту |
|---|---|
| **«продолжим»** | Зафиксировать время (`Get-Date`), продолжить с места остановки |
| **«пауза»** | Проверить время, **не коммитить**, ждать следующей команды |
| **«закончили на сегодня»** / **«заканчиваем»** | Проверить время, записать строку в `STATS.md`, синхронизировать 4 файла-дневника, **закоммитить и запушить** |
| **«коммитим как есть»** | То же что «закончили», но без прощания с сессией |

**Каждая сессия = отдельная строка в `STATS.md` с датой.** Не путать «сегодня» и «вчера». Перед любой записью времени вызывать `Get-Date -Format "dd.MM.yyyy HH:mm:ss"`.

История косяков ассистента:
- 30.08.2026 вечером: написал «23:50» когда было 23:49
- 31.08.2026: написал «30.08.2026 17:30» когда было 31.08.2026 11:06 (записал вчерашний день как сегодня)
- 31.08.2026: забыл проверить время перед ответом на «продолжим»

---

## Шаг 1: Старт Spring Boot (28.08.2026)

### Теория (разобрано 28.08.2026)

**Spring Boot = Spring Core + embedded server + auto-config + starters.**

Ключевые отличия от Core (курс №1):
- Контекст создаёт `SpringApplication.run(...)` сам, не `new AnnotationConfigApplicationContext(...)`
- Конфиг = `@SpringBootApplication` (включает `@Configuration` + `@ComponentScan` + `@EnableAutoConfiguration`)
- В classpath лежит embedded Tomcat — Boot его поднимает автоматически
- `main()` не завершается — приложение блокирует поток и работает до явной остановки

**`@SpringBootApplication` расщепляется на 3 аннотации:**
1. `@SpringBootConfiguration` (= `@Configuration` — класс содержит `@Bean`-методы)
2. `@EnableAutoConfiguration` — «магия»: Boot смотрит в classpath и сам регистрирует бины (Tomcat, DataSource, Jackson) по условиям
3. `@ComponentScan` — сканирует пакет главного класса и подпакеты, регистрирует `@Component`/`@Service`/`@Repository`/`@Controller`

**`SpringApplication.run(class, args)` — обычный Java-метод**, делает под капотом:
1. Создаёт `ApplicationContext` (со всеми автоконфигурациями)
2. Запускает `CommandLineRunner` / `ApplicationRunner` (хуки при старте — разберём на шаге 3)
3. Поднимает embedded Tomcat, регистрирует `DispatcherServlet`
4. **Не возвращает управление в main** — поток блокируется, приложение работает

**Что НЕ меняется** (всё, что было в Core — работает):
- `@Component`, `@Service`, `@Repository`, `@Bean`, `@Configuration`
- DI через конструктор / сеттер / поле
- Scope (singleton / prototype)
- AOP через `ProxyFactory`
- `BeanPostProcessor`, `@PostConstruct`, `@PreDestroy`

### Практика

⚠️ **Практики на шаге 1 не было** (нарушение правила №3 курса №2).

Правильный порядок — **теория → практика → мини-экзамен**. На шаге 1 ассистент сам сгенерил скелет через Initializr API и сам его запустил — ученик верно поправил («экзамен по чему ты проводишь?»). Скелет принят как **baseline**, но считать шаг полностью пройденным без рук ученика нельзя.

Компенсация запланирована на шаге 2: ученик **сам** правит `application.yml`, запускает `mvn spring-boot:run` и делает запрос (curl / браузер) — и присылает свой лог.

### Мини-экзамен

**Вопрос:** что общего и в чём разница между `new AnnotationConfigApplicationContext(AppConfig.class)` (Core) и `SpringApplication.run(NotificationHubApplication.class, args)` (Boot)?

**Ответ ученика (первая попытка):**
> «в SpringBoot это процесс. Т.е. эта строка говорит не просто посмотри файл конфигурации и открой контекст, а запусти еще такие то процессы и дальше жди.»

**Что правильно:**
- ✅ «Не просто открой контекст, а запусти ещё такие-то процессы» — `SpringApplication.run` действительно делает больше шагов (контекст + Tomcat + сервлеты + runner'ы)
- ✅ «И дальше жди» — main-поток блокируется, приложение не завершается

**Что неточно (поправлено):**
- ⚠️ «Это процесс» — `SpringApplication.run` это **обычный Java-метод**, не «процесс». Просто он внутри делает много шагов и блокирует main-поток через Tomcat lifecycle
- Гипотеза: путаница могла прийти из-за embedded Tomcat, который реально работает в пуле потоков, но это **другая** тема

**Правильная формулировка (выучить):**
> «`SpringApplication.run()` — это обычный Java-метод, такой же как `new AnnotationConfigApplicationContext()`. Только он делает больше шагов (контекст + автоконфигурация + embedded Tomcat + runner'ы) и не возвращает управление в main, поэтому приложение работает до явной остановки.»

**Оценка:** 60% (суть ухвачена — «делает больше и ждёт», но формулировка «процесс» неточна и может закрепиться).

### Правильная формулировка (повторить в начале следующей сессии)

> `SpringApplication.run(...)` — **обычный метод**, который внутри создаёт контекст, поднимает embedded Tomcat, регистрирует сервлеты, запускает runner'ы — и **не возвращает управление** в main, поэтому приложение работает, пока не остановишь.

### Шпаргалка для собеса (с этого шага)

1. **`@SpringBootApplication` = `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan`**
2. **`@EnableAutoConfiguration` — магия**: фреймворк смотрит в classpath и сам регистрирует бины (Tomcat, DataSource, Jackson)
3. **`SpringApplication.run()` — обычный Java-метод**, делает несколько шагов и блокирует main-поток
4. **В Boot всё, что было в Core, работает**: DI, scope, AOP, BPP, lifecycle

---

## Шаг 2: Spring Boot автоконфигурация (28.08.2026) ✅

### Микро-шаг A — `application.properties` меняет поведение без правки кода

**Теория:** Boot читает `application.properties` (или `.yml`) из `src/main/resources/` до старта контекста. Свойства типа `server.port`, `spring.application.name` влияют на сам процесс подъёма (порт Tomcat, имя приложения в логах).

**Практика ученика:**
- Добавил `server.port=8081` и `spring.application.name=notification-hub` в `application.properties`
- В логе старта увидел `Tomcat initialized with port 8081 (http)` и префикс `[notification-hub]`
- Заметка: IDEA подхватила JDK **23.0.1** (не 24.0.2 как было в системе ранее) — Boot 4 совместим

### Микро-шаг B — `@Value` подхватывает кастомное свойство

**Теория:** `@Value("${имя.свойства}")` инжектит значение из конфигурации в поле бина. Также в main показал, что `SpringApplication.run(...)` возвращает `ConfigurableApplicationContext`, который можно закрыть через `ctx.close()` — это идиома интеграционных тестов.

**Практика ученика:**
- Создал `GreetingService` с `@Value("${app.greeting}")`
- В `application.properties`: `app.greeting=Hello from default profile`
- Переписал `main` чтобы сохранить `ctx` из `run` и вызвать `ctx.close()` после `greet()`
- В логе: `>>> Hello from default profile` + `Process finished with exit code 0` — контекст закрылся, JVM вышла

### Микро-шаг C — профили `dev`/`prod`

**Теория:** Boot грузит `application-{profile}.yml` поверх базового, если профиль активен. Активация через `--spring.profiles.active=dev` в Program arguments IDEA. Если профиль не задан — Boot фоллбэчит на `"default"`.

**Практика ученика:**
- Создал `application-dev.yml` с `app.greeting: Hello from DEV profile` + `logging.level.com.vasilii.notificationhub: DEBUG`
- Создал `application-prod.yml` с `app.greeting: Hello from PROD profile` + `logging.level...: ERROR`
- Запустил под `dev` → `>>> Hello from DEV profile`
- Запустил под `prod` → `>>> Hello from PROD profile`
- В обоих логах увидел `The following 1 profile is active: "dev"` / `"prod"`

### Микро-шаг D — `@Profile` подменяет реализацию бина

**Теория:** `@Profile("dev")` на `@Service` = бин создаётся только при активном `dev`. Не подходит профиль → бина в контексте **нет вообще**. Один интерфейс — разные реализации под разные профили (паттерн «strategy by profile»).

**Практика ученика:**
- Создал интерфейс `MessageService` и 2 реализации: `DevMessageService` (`@Profile("dev")`) и `ProdMessageService` (`@Profile("prod")`)
- Обновил `GreetingService` — конструкторный инжект `MessageService`, вызов `send` после `greet()`
- Запустил под `dev` → `[DEV-MOCK] to=user@example.com text=Hello from DEV profile`
- Запустил под `prod` → `[PROD-SMTP] to=user@example.com text=Hello from PROD profile`
- Ответ ученика на вопрос «почему не падает DI»: «подтягиваются только бины с тем профилем в котором мы работаем» ✅

### Мини-экзамен

**Вопрос 1:** Забыли `--spring.profiles.active=prod` в проде. Активен `default`. Что произойдёт при `messageService.send(...)`?

**Ответ ученика:** «приложение упадёт, потому что т.к. не выбран профиль у нас попало в контекст 2 бина мессаджесервис и springboot не знает к какому обратиться.»

**Оценка:** 🟡 70%. **Суть верная (DI упадёт), но механизм неверный:**
- Бинов в контексте **0**, не 2. `@Profile("dev")` и `@Profile("prod")` не подходят под активный `default` → бины не создаются вообще
- Исключение: `NoSuchBeanDefinitionException` (не нашли ни одного), **не** `NoUniqueBeanDefinitionException` (нашли несколько, не знаем какой)
- **Поправка (выучить):** «Если активный профиль не подходит ни под `@Profile` ни одного бина-кандидата, то бина в контексте нет. DI упадёт с `NoSuchBeanDefinitionException`»

**Вопрос 2:** В `application.properties` написано `app.greeting=Hi from .properties`, в `application.yml` — `app.greeting: Hi from .yml`. Что в логе?

**Ответ ученика:** «properties, потому что они выше в иерархии.»

**Оценка:** 🟢 90%. Факт правильный, формулировку уточнить:
- Лучше говорить «приоритет» / «порядок загрузки», а не «иерархия» (звучит размыто)
- **Поправка:** «При конфликте побеждает `.properties`, потому что грузится **позже** `.yml` (приоритет выше)»

**Средний балл шага 2: 80%** 🎯 (по 3 мини-вопросам шага 2)

> 💡 **80% vs 77% — не путать:**
> - **80%** — средний балл **по шагу 2** (3 мини-вопроса этого шага: 90+95+70 = 85%, оценка 80% после учёта поправок)
> - **77%** — средний балл **по курсу №2** (все 6 тем из таблицы `STATS.md`: 60+90+60+90+95+70 = 465/6 = 77.5%, включая шаг 1 с 70%)

> 💡 Это **первый шаг с полноценной практикой** в формате v2 (микро-шаги). Прогресс с шага 1 (70%) — заметный. Главный пробел: путает «нет бинов» и «несколько бинов» — это разные исключения в Spring, на собесе любят спрашивать.

---

## Шаг 3: REST API — старт (28.08.2026 + 30.08.2026) ✅

**Микро-шаг 1:** `@RestController` + `@GetMapping("/hello")` → JSON.
**Микро-шаг 2:** `@PathVariable` (Long авто-конверсия) — `GET /users/{id}/orders/{orderId}`.
**Микро-шаг 3:** `@RequestParam` + `defaultValue` — `GET /search?q=hello&page=5`.
**Микро-шаг 4:** `@PostMapping` + `@RequestBody` + JSON DTO — `POST /messages`.

**Что делали:**
1. Создан `controller/HelloController.java` с тремя методами (`/hello`, `/users/{id}/orders/{orderId}`, `/search`, `/messages`)
2. Создан `dto/MessageRequest.java` (геттеры/сеттеры/пустой конструктор для Jackson)
3. Получены 3 разных JSON-ответа через GET + 1 через POST

**Проблемы в реальном рантайме (и это золото!):**

1. **28.08.2026 22:36** — `NoSuchBeanDefinitionException` из-за `@Profile` без активного профиля. Ученик СРАЗУ вспомнил правило из шага 2 (0 бинов ≠ несколько бинов). Решено через `--spring.profiles.active=dev`. **Связка шага 2 и шага 3 в реальности.**
2. **30.08.2026 17:17** — POST вернул **405** из-за перепутанной аннотации (`@GetMapping` вместо `@PostMapping`). Ученик сам заметил — исправил. **Урок: 405 ≠ 404.** 405 = endpoint есть, метод не тот.
3. **30.08.2026 17:18** — POST с пустым `text` вернул **500** из-за `NullPointerException` в `req.getText().length()`. Ученик сам предложил решение: **валидация через аннотации на DTO + `@Valid` в контроллере** — Separation of Concerns. **100% на мини-экзамене.** Это вход в шаг 4.

**Оценки по микро-экзаменам шага 3:**
- Вопрос про `Long` авто-конверсию: **90%** (тип в сигнатуре + ConversionService)
- Вопрос про `?param=` vs отсутствие: **90%** (пустая строка ≠ null)
- Вопрос про уровни валидации: **100%** (выбрал `@Valid` + аннотации, объяснил почему)

**Средний балл шага 3: ~93%** 🎯 (лучший в курсе №2)

**Известное ограничение:** `POST /messages` без `text` → 500 (NPE). Это **не баг**, а **точка входа в шаг 4** (валидация через `@Valid`). Ученик подтвердил коммит «ка есть» — фиксируем поведение, валидация на следующем шаге.

---

## Шаг 4: DTO + валидация (30.08.2026 + 31.08.2026) ✅

**Микро-шаг 1:** Аннотации на DTO (`@NotBlank`, `@Email`, `@Size`) + `@Valid` в контроллере.
**Микро-шаг 2:** `@RestControllerAdvice` + `@ExceptionHandler(MethodArgumentNotValidException.class)` → 400.
**Микро-шаг 3:** Мини-экзамен (70% — ответил про 400 и 404).
**Микро-шаг 4:** Расширение: `HttpMessageNotReadableException` (400 malformed JSON) + `NoResourceFoundException` (404) + catch-all `Exception` (500 + `log.error`).

**Что делали:**
1. Добавлена зависимость `spring-boot-starter-validation` в `pom.xml` (Boot 4 **не подтягивает** её автоматически — урок: всегда проверять `pom.xml`)
2. Добавлены аннотации на `MessageRequest`
3. Написан `exception/GlobalExceptionHandler.java` — 4 `@ExceptionHandler` метода + SLF4J логгер
4. **Импорт-баг:** ученик импортировал `java.util.logging.Logger` вместо `org.slf4j.Logger` — поправили, объяснил разницу JUL vs SLF4J
5. Проверены 3 кейса: валидный (200), невалидный DTO (400), кривой JSON (400), несуществующий endpoint (404)

**Проблема №2 (поймали в реальном рантайме!):** при попытке проверить type mismatch (`@PathVariable Long id` + `"abc"`) → 404 вместо 400. Причина: в `HelloController.java` остался только `POST /messages`, остальные методы были утеряны при предыдущих правках. Не восстанавливали — урок про шум в коде и важность явных коммитов.

**Проблема №3 (ученик запутался в конце):** сказал «type mismatch → 404» вместо 400. Поправил. Имеет значение для собеса: 404 = «не нашёл ресурс», 400 = «запрос некорректный». Type mismatch — это 400, потому что URL совпал, но значение параметра кривое.

**Оценки по микро-экзаменам шага 4:**
- Какие ещё типы исключений ловить: **70%** (назвал 400 и 404, без деталей)
- Почему Boot 4 убрал встроенный 404: **70%** (уловил суть про stacktrace, не назвал про сокрытие багов клиента)
- type mismatch → какой код: **100%** (после правки)

**Средний балл шага 4: ~85%** 🎯

**Сессия 31.08.2026 (12:37–13:36, ~1.0 ч):** микро-шаг 4 — `GlobalExceptionHandler` расширен до 4-х хэндлеров (validation 400, malformed JSON 400, NoResourceFoundException 404, catch-all 500 + log.error). Мини-экзамен по шпаргалке ошибок (400 vs 404, типы исключений). Поправлен баг понимания ученика (type mismatch = 400, не 404). Коммит `9625451`. Ученик сказал «пауза» → **STOP**. Состояние проекта чистое (working tree clean).

**Шпаргалка (новое):**
- **`@Valid`** включает валидацию DTO через Hibernate Validator (Jakarta Bean Validation)
- **`@RestControllerAdvice`** + **`@ExceptionHandler`** — глобальный обработчик ошибок для всего API
- **`MethodArgumentNotValidException`** (400) → ошибки валидации полей DTO
- **`HttpMessageNotReadableException`** (400) → кривой JSON / неверный Content-Type
- **`MethodArgumentTypeMismatchException`** (400) → `@PathVariable Long` а пришёл `"abc"`
- **`NoResourceFoundException`** (404) → endpoint не существует (в Boot 4 — 500 если не обработать явно!)
- **Catch-all `Exception`** (500) + `log.error("...", ex)` — ОБЯЗАТЕЛЬНО логировать stacktrace
- **SLF4J** — стандарт логирования в Spring, не `java.util.logging`

### Шпаргалка для собеса (обновляется)

1. **`@SpringBootApplication` = `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan`**
2. **`@EnableAutoConfiguration` — магия**: фреймворк смотрит в classpath и сам регистрирует бины (Tomcat, DataSource, Jackson)
3. **`SpringApplication.run()` — обычный Java-метод**, делает несколько шагов и блокирует main-поток
4. **В Boot всё, что было в Core, работает**: DI, scope, AOP, BPP, lifecycle
5. **Конфиг-файлы:** `application.properties` ИЛИ `application.yml` (`.properties` приоритетнее при конфликте)
6. **Кастомные свойства:** `@Value("${app.foo}")` для простых, `@ConfigurationProperties` для сложных (вернёмся на шаге 4 как связку с DTO)
7. **Профили:** `application-{profile}.yml` + `--spring.profiles.active=dev` (или в `application.yml`)
8. **`@Profile`:** бин создаётся только если активен указанный профиль. Не подошёл → бина **нет** → `NoSuchBeanDefinitionException`
9. **`ctx.close()`** идиома для тестов: закрывает контекст, дёргает `@PreDestroy`, JVM выходит с кодом 0
10. **`@RestController = @Controller + @ResponseBody`** — каждый метод сразу сериализует возвращаемое значение в HTTP-ответ
11. **`@PathVariable` + тип в сигнатуре** → Spring сам конвертирует через `ConversionService` (String → Long и т.д.)
12. **`@RequestParam(defaultValue=...)`** подставляет дефолт при **отсутствии** параметра (не при пустом значении `?x=` — там будет пустая строка `""`)
13. **`@RequestBody` + Jackson** — Spring сам читает тело запроса, парсит JSON в DTO. **Нужен `Content-Type: application/json`** иначе 415
14. **HTTP-ошибки**: 400 (битый запрос / валидация), 404 (endpoint нет), 405 (метод не тот), 415 (Content-Type неверный), 500 (баг в коде)
15. **Tomcat thread pool** — HTTP-запросы обрабатываются в worker-потоках `[nio-8081-exec-N]`, не в main. По умолчанию 200 потоков
16. **Separation of Concerns:** валидация — на DTO аннотациями + `@Valid` на границе (контроллер), бизнес-логика — в методе без `if (x == null)`
17. **Глобальный error handling:** `@RestControllerAdvice` + `@ExceptionHandler(MethodArgumentNotValidException.class)` для 400 (валидация). Аналогично для malformed JSON, type mismatch, not found, catch-all
18. **Spring Boot 4** не обрабатывает 404 на несуществующий endpoint встроенно → возвращает 500, чтобы не маскировать баги клиента. Решение: явный `@ExceptionHandler(NoResourceFoundException.class)`
19. **SLF4J** — стандарт логирования в Spring Boot, не `java.util.logging`. Logback — реализация по умолчанию
20. **Catch-all `Exception` всегда с `log.error("...", ex)`** — иначе stacktrace теряется в проде

---

---

## Шаг 5: JPA + H2 in-memory + фикс /h2-console loop (сделан 02.09.2026, ~2.5 ч)

### Микро-шаг 1: JPA + H2 in-memory DB, save/read via JpaRepository

**Что сделано:**
- Добавлены зависимости в `pom.xml`: `spring-boot-starter-data-jpa` + `h2` (runtime scope)
- `entity/MessageLog.java` — JPA-entity (`@Entity`, `@Id @GeneratedValue`, поля для логов)
- `repository/MessageLogRepository.java` — extends `JpaRepository<MessageLog, Long>` (готовые CRUD из коробки)
- `service/MessageService.java` — бизнес-слой с методами `save(...)` и `findAll()`, инжектит `MessageLogRepository`
- Контроллер дёргает сервис (не репозиторий напрямую — соблюдаем слои)
- `application.properties`: `spring.datasource.url=jdbc:h2:mem:notificationhub`, `spring.jpa.hibernate.ddl-auto=create-drop` (схема создаётся из entity при старте, дропается при остановке)

**Теория (короткая):**
- **JPA (Jakarta Persistence API)** — спецификация для маппинга Java-объектов на таблицы БД. Реализация в Spring Boot — **Hibernate**
- **`JpaRepository<T, ID>`** даёт готовые методы: `save()`, `findById()`, `findAll()`, `deleteById()`, `count()`, ... — без реализации
- **`ddl-auto=create-drop`** удобно для dev/test, в проде использовать `validate` или `none` (миграции — отдельная тема, шаг 7+)
- **H2 in-memory** — БД живёт в RAM, исчезает при остановке JVM. Идеально для разработки и тестов

### Микро-шаг 2: фикс петли `/h2-console`

**Проблема (поймана в реальном рантайме):**
`WebConfig.java` содержал:
```java
registry.addViewController("/h2-console").setViewName("forward:/h2-console");
```
Это **бесконечная петля**: запрос `GET /h2-console` → forward на тот же `/h2-console` → forward опять → ∞. В логах Tomcat:
```
Circular view path [h2-console]: would dispatch back to the current handler URL [/h2-console] again
```
В браузере — пустая страница или `ERR_TOO_MANY_REDIRECTS`.

**Решение — вариант А (минимальный):**
```java
registry.addViewController("/h2-console").setViewName("redirect:/h2-console/");
```
Одна строка изменена. `redirect:` — это HTTP-redirect (новый запрос от клиента), а не внутренний forward → петли нет.

**Теория — `forward:` vs `redirect:` (КЛЮЧЕВАЯ для собеса):**

| | `forward:` | `redirect:` |
|---|---|---|
| Тип | Внутренний forward на стороне сервера | HTTP-redirect (302) клиенту |
| Новый HTTP-запрос? | ❌ нет | ✅ да (клиент делает GET) |
| URL в браузере | не меняется | меняется на новый |
| Данные запроса (атрибуты, параметры) | сохраняются | теряются (это новый запрос) |
| Когда использовать | внутренняя маршрутизация в рамках одного handler | «перенаправить пользователя на другой URL» (PRG-паттерн после POST) |
| Может создать петлю? | ✅ да (если forward на свой же URL) | ❌ нет (каждый redirect — новый запрос, браузер видит смену URL) |

**Ключевое правило:** `forward` внутри одного handler URL — это петля. `redirect` — безопасен по построению.

**Урок про тесты — `@WebMvcTest` vs `@SpringBootTest` (для собеса):**

| | `@WebMvcTest` | `@SpringBootTest` |
|---|---|---|
| Что поднимает | только web-слой (контроллеры, `WebMvcConfigurer`, фильтры) | весь контекст (web + JPA + H2 + бины) |
| Скорость | быстро (~0.5 сек) | медленно (2-5 сек) |
| Что доступно в `@Autowired` | только web-бины | бины всех слоёв |
| Когда использовать | unit-тест на HTTP-роутинг / redirect | интеграционный тест с реальной БД / внешними сервисами |
| Аннотация для MockMvc | идёт в комплекте | нужен ещё `@AutoConfigureMockMvc` |

**Правило выбора:** если тест падает из-за БД, но ты проверяешь только URL → `@WebMvcTest`. Если нужно проверить реальное взаимодействие с БД → `@SpringBootTest`.

**Альтернативный вариант Б (для реального проекта):** удалить `WebConfig` целиком, потому что H2 Console сам обрабатывает `/h2-console`. Решение **А** лучше для учебного проекта (полезный опыт с forward/redirect), **Б** — для продакшна (минимализм).

### Параллельная задача: диагностика contribution graph

**Симптом:** коммиты на GitHub есть, но contribution graph их не показывает.

**Причина:** `git config user.email = vasilii@local` — это не email, GitHub не может сопоставить с аккаунтом → коммиты не засчитываются в contributions.

**Решение:**
1. Email изменён в локальном и глобальном git-конфиге на `vasekbasovv@mail.ru` (привязан к GitHub-аккаунту)
2. История переписана через `git filter-repo`: `vasilii@local → vasekbasovv@mail.ru` во всех author и committer email во всех коммитах
3. Сделал `git push --force` в `origin/main` (новый HEAD = `339f3d0`, старый `9559ec4` ушёл в небытие)
4. Бэкап `.git.backup_20260902_024500/` лежит на диске — не трогать минимум неделю

**Теория для собеса:**
- GitHub contribution graph считает коммиты только если author email совпадает с одним из привязанных к аккаунту (или `username@users.noreply.github.com`)
- 3 типичные причины "no activity": (1) push в org / чужой аккаунт, (2) коммиты в форке чужого репо, (3) email mismatch
- `git filter-repo` — современная замена `filter-branch`, рекомендована самим git-ом для массовых операций на историей (переименование email, удаление файла из всей истории, и т.д.)
- `--force-with-lease` — защищённый force-push: отказывается пушить, если на remote кто-то успел запушить что-то поверх. Безопаснее обычного `--force`

### Шпаргалка (новое):

- **JPA = спецификация**, Hibernate = реализация в Spring Boot по умолчанию
- **`JpaRepository<T, ID>`** даёт готовые CRUD-методы из коробки (`save`, `findById`, `findAll`, `deleteById`, `count`, ...)
- **Слои:** Controller → Service → Repository → DB. Контроллер НЕ дёргает репозиторий напрямую
- **H2 in-memory** (`jdbc:h2:mem:<name>`) — БД в RAM, исчезает при остановке JVM. Для dev/test
- **`ddl-auto=create-drop`** — Hibernate создаёт схему из entity при старте, дропает при остановке. **Только для dev**, в проде `validate` или миграции (Flyway/Liquibase, шаг 7+)
- **`forward:` vs `redirect:`** — forward внутри одного handler URL = петля, redirect = безопасный HTTP-302
- **`@WebMvcTest`** — только web-слой, быстро. **`@SpringBootTest`** + `@AutoConfigureMockMvc` — весь контекст, медленно
- **`git filter-repo`** — современная замена `filter-branch`. Уже установлен в системе (Python 3.13 + `pip install git-filter-repo`)
- **`git push --force-with-lease`** — безопасный force-push (отказывается, если remote сдвинулся)
- **Contribution graph** считает коммиты только если author email привязан к GitHub-аккаунту

### Шпаргалка для собеса (обновляется)

1-20. [прежние пункты без изменений]

21. **`@WebMvcTest`** — slice-тест только web-слоя (контроллеры, `WebMvcConfigurer`). Быстрый, без БД
22. **`@SpringBootTest` + `@AutoConfigureMockMvc`** — интеграционный тест всего контекста + MockMvc. Медленный, но реалистичный
23. **`JpaRepository<T, ID>`** — готовые CRUD из коробки (`save`, `findById`, `findAll`, `deleteById`, `count`). Кастомные методы — через имя метода (`findByName`) или `@Query`
24. **JPA = спецификация** (Jakarta Persistence), **Hibernate** = реализация по умолчанию в Spring Boot
25. **`forward:` vs `redirect:`** — forward = серверная маршрутизация, сохраняет request attributes. Redirect = HTTP-302, новый запрос от клиента, атрибуты теряются. **Forward внутри одного handler URL = бесконечная петля**
26. **`git filter-repo`** — рекомендованная замена `filter-branch` для массовых операций с историей (переименование email, удаление секрета из всей истории)
27. **`--force-with-lease`** — защищённый force-push. Отказывается пушить, если на remote кто-то успел запушить поверх
28. **Contribution graph** засчитывает коммит только если author/committer email привязан к GitHub-аккаунту (или используется `username@users.noreply.github.com`)

---

## Шаг 5, микро-шаг 2 — ФИНАЛ (02.09.2026, ~0.5 ч)

### Что произошло

Приняли зависшую задачу шага 5. Изначально в `WebConfig.java` было `addViewController("/h2-console", "forward:/h2-console")` — создавало бесконечный forward (`Circular view path`).

Сначала я предложил **вариант А** (`redirect:/h2-console/`), но Василий усомнился: «браузер выдаёт 500 и есть зацикленность». Решил проверить проект целиком.

### Что нашли в проекте (разбор)

Прочитали все 11 java-файлов + `pom.xml` + `application*.properties/yml`. Обнаружили **три проблемы**, не одну:

**Проблема 1 — `forward:` петля** (та, что уже знали):
`forward:/h2-console` → запрос на тот же URL → бесконечная петля.

**Проблема 2 — `addViewController("/h2-console/**")` перехватывает всё под `/h2-console`**:
Даже подпути H2 servlet (`/h2-console/login.do`, `/h2-console/css/...`) никогда не доходят до самого H2 servlet — наш `addViewController` их перехватывает и форвардит обратно.

**Проблема 3 — `redirect:/h2-console/` НЕ решает проблему полностью** (та, что обнаружил Василий):
Даже если заменить forward на redirect, всё равно хрупко: правило для `/h2-console` ловит запрос, redirect на `/h2-console/` — следующий запрос может опять попасть в правило для `/h2-console/`.

### Что сделали

Выбрали **вариант Б** (рекомендация из `LEARNING_LOG.md`): **удалить `WebConfig.java` целиком**. Идея: Spring Boot 4 сам регистрирует H2 web servlet через автоконфигурацию, ничего ручного не надо.

### Что выяснилось вживую

Запустили Boot, проверили HTTP:
- `WebConfig.java` удалён ✅
- `GET /messages` → 200 ✅
- `GET /h2-console` → **500** ❌ (петля осталась!)
- `GET /totally-unknown` → 404 ✅

То есть **`WebConfig` действительно не при чём** — петля живёт и без него.

### Истинный корень (для собеса!)

В Spring Boot **4.0.8 + Tomcat 11** автоконфигурация H2 web console **не подтягивается автоматически** (в логе старта нет ни одной строки про регистрацию H2 servlet). При этом DispatcherServlet всё равно ловит запрос на `/h2-console` (видимо, через `WelcomePageHandlerMapping` или похожую логику), не находит mapping'а, пытается отрендерить через `InternalResourceView` → forward на `/` → снова DispatcherServlet → рекурсия в `ApplicationHttpRequest.getSession()` → `StackOverflowError` на глубине ~400 стек-фреймов.

Стек (верхушка):
```
DispatcherServlet.processDispatchResult
  → InternalResourceView.renderMergedOutputModel
    → ApplicationDispatcher.forward
      → HttpServletRequestWrapper.getSession  ← рекурсия
        → ApplicationHttpRequest.getSession
          → HttpServletRequestWrapper.getSession
            → ... ∞
```

### Финальное решение

H2 web console в этом проекте **отключена полностью**:
- `application.properties`: `spring.h2.console.enabled` закомментирован
- `application-dev.yml`: комментарий с пояснением, что H2 console в Boot 4 не работает из коробки

Для отладки используем:
- `GET /messages` через REST API
- `spring.jpa.show-sql=true` — Hibernate логирует все SQL-запросы

### Что НЕ сделали и почему

- **`H2ConsoleRedirectTest` — отменён.** Тест проверял бы, что `/h2-console` даёт 302. Но H2 console не работает в Boot 4 → тест не имеет смысла. Вернёмся к идее тестов на шаге 12 (Testing), там будем писать `ServletRegistrationBean<WebServlet>` для H2 и тестировать его — это и полезнее, и более «в реальном проекте»-стиле.

### Шпаргалка (добавлено)

- **В Spring Boot 4 H2 web console НЕ работает из коробки** (автоконфиг убран). Включение через `spring.h2.console.enabled=true` даёт StackOverflowError, без него — 404
- **Шаблон forward-петли** в Spring MVC: если DispatcherServlet ловит URL без mapping'а и пытается отдать через `InternalResourceView` (forward на `/`), возникает бесконечная рекурсия. Симптом: `StackOverflowError` в `HttpServletRequestWrapper.getSession()`
- **Диагностический приём**: всегда проверять `/totally-unknown` рядом с проблемным URL — если он даёт 404, а проблемный даёт 500, петля специфична именно для проблемного URL (значит, кто-то на него отзывается)
- **Для реального проекта H2 console почти не нужна**: в проде категорически нет, в dev хватает логов Hibernate + REST API. Если нужна — `ServletRegistrationBean<WebServlet>` с `org.h2.server.web.WebServlet`

29. **Spring Boot 4 не содержит автоконфигурации H2 web console** (в отличие от Boot 3.x). Если нужен GUI — регистрировать `ServletRegistrationBean<WebServlet>` вручную
30. **Forward-петля в Spring MVC** — симптом: `StackOverflowError` в `getSession()`. Причина: DispatcherServlet не находит mapping'а и пытается отдать через `InternalResourceView` → forward → снова DispatcherServlet → ∞
31. **`GET /totally-unknown` рядом с проблемным URL** — диагностический приём для локализации forward-петли
32. **В реальном проекте H2 console обычно не нужна** — для dev хватает `spring.jpa.show-sql=true` + REST API

---

## Шаг 6, micro-1: @Transactional граница (04.09.2026, ~0.3 ч)

### Что сделали

- **Архитектура Controller → Service → Repository** починена: `HelloController` теперь дёргает `notificationService.send(...)`, а не `repository.save(...)` напрямую. До этого контроллер лез в репозиторий в обход — нарушение слоистой архитектуры.
- **`EmailSender` интерфейс + 2 реализации по `@Profile`**:
  - `dev` → `ConsoleEmailSender` (печатает в stdout)
  - `dev-fail` → `FailingEmailSender` (кидает `RuntimeException` — для проверки ROLLBACK)
- **`@Transactional` на `NotificationService.send(...)`** — метод делает `repository.save(...)` + `emailSender.send(...)` в одной транзакции. Если `emailSender` падает — ROLLBACK.
- **HTTP-файл `requests/messages-rollback.http`** для удобного теста (POST + GET в одном файле).
- **Старые сервисы удалены**: `GreetingService`, `MessageService`, `DevMessageService`, `ProdMessageService` (переход к `@Profile`-стратегии по интерфейсу `EmailSender`).
- **`pom.xml`:** +`spring-boot-starter-actuator` (заготовка к шагу 13).
- **`.gitignore`:** +`.git.backup_*/`, `.tmp_boot.*`, `run.err/log` (после инцидента с рекурсивным `git add .` — бэкап `.git/` попал в индекс, ~600 файлов, откатил через `git rm -r --cached`).
- **Коммит:** `b5e9342`, запушен в `origin/main`.

### Мини-экзамен (2 вопроса, средний 🟢 78%)

**Вопрос 1:** При `RuntimeException` из `emailSender.send(...)` внутри `@Transactional`-метода — окажется ли строка в таблице `messages`?

**Ответ ученика:** «не окажется, т.к. сработает rollback».

**Оценка:** 🟢 **90%**. Суть верная, но не хватило деталей:
- Механизм: AOP-прокси (связь с курсом №1, BPP.after) → `PlatformTransactionManager` → `getTransaction()` → биндит `EntityManager` к потоку → `repository.save()` пишет SQL, но **не коммитит** → исключение → прокси ловит RuntimeException → `rollback()` → `ROLLBACK` в БД.
- **Выучить формулировку:** «`@Transactional` создаёт AOP-прокси, который открывает JDBC-транзакцию через `PlatformTransactionManager`. RuntimeException по умолчанию → ROLLBACK, checked (не отмеченные) — нет (если не настроен `rollbackFor`).»

**Вопрос 2:** `@TransactionalEventListener(AFTER_COMMIT)` — где публикуется и что будет если listener упадёт после коммита?

**Ответ ученика:** «должен жить в сендере (типа `ConsoleEmailSender`). Транзакция не откатится, но что в логах — не знаю».

**Оценка:** 🟡 **65%**. Две неточности:
- ❌ Где: событие должно публиковаться из **сервиса** (`NotificationService`), а не из сендера. Сендер — про внешний I/O (можно подменить в тестах на mock).
- ✅ Что не откатится — верно (listener крутится ПОСЛЕ коммита основной TX).
- ⚠️ Логи — `ERROR` со стектрейсом, **Spring сам не ретраит**.
- ⚠️ Поведение для пользователя: HTTP 201 уже ушёл до падения listener'а, клиент думает «отправлено».
- ❌ На проде для надёжности — **outbox-паттерн** (отдельная таблица `outbox_events` + polling).

**Выучить формулировку:** «Событие публикуется из `@Transactional`-метода сервиса через `ApplicationEventPublisher`. Listener с `@TransactionalEventListener(AFTER_COMMIT)` вызывается после коммита. Если listener падает — основная запись сохранена, 201 ушёл, в логах ERROR, **тихая потеря данных**. Решение: outbox-паттерн.»

### Шпаргалка (новое)

29. **`@Transactional` — это AOP-прокси** с `TransactionInterceptor`. Сам метод — обычный Java, «магия» в прокси (как `BPP.after` из курса №1)
30. **`PlatformTransactionManager`** — то, что реально открывает/коммитит/откатывает JDBC-транзакцию. `@Transactional` сам ничего не делает
31. **RuntimeException по умолчанию → ROLLBACK**, checked (не отмеченные `@ExceptionHandler`) — нет. Настраивается через `@Transactional(rollbackFor = ...)`
32. **`AFTER_COMMIT`** listener крутится **после** успешного коммита основной TX. Если listener падает — откатывать нечего, но данные в БД могут быть неконсистентны
33. **События публикуются из сервиса** (`@Transactional`-метод), не из сендера/репозитория. Иначе listener может не сработать (нет транзакции)
34. **Outbox-паттерн** — для надёжной доставки событий после коммита. Отдельная таблица `outbox_events` + фоновый polling/CDC. Без него — тихая потеря
35. **`git add .` подхватывает всё**, включая `.git.backup_*/`. Если это копия `.git/` — рекурсивно затащит всю историю в новый коммит. **Всегда** проверять `git status --short` перед `commit`
36. **`git rm -r --cached`** — убирает из индекса, не удаляя файлы с диска. Безопасный способ отката рекурсивного `add`
37. **`index.lock`** — git создаёт при операциях с индексом. Если упал — можно безопасно удалить вручную

### Шпаргалка для собеса (обновляется)

1-28. [прежние пункты без изменений]

29. **`@Transactional` = AOP-прокси с `TransactionInterceptor`** (связь с курсом №1, BPP.after)
30. **`PlatformTransactionManager`** — реально управляет JDBC-TX. `@Transactional` — только аннотация-маркер
31. **RuntimeException → ROLLBACK по умолчанию**, checked — нет. Настраивается через `rollbackFor`/`noRollbackFor`
32. **`@TransactionalEventListener(phase=AFTER_COMMIT)`** — крутится после коммита основной TX. Падение listener'а → основная запись в БД есть, но логика после коммита потеряна
33. **Outbox-паттерн** — `outbox_events` + polling/CDC. Решает проблему «тихой потери» при падении listener'а
34. **`.gitignore` должен включать все локальные артефакты** (бэкапы, tmp-файлы, логи). `git add .` не проверяет — он подхватывает всё не-игнорируемое

---
## Шаг 6, micro-2: Propagation (REQUIRES_NEW + self-injection, 04.09.2026, ~3.2 ч)

### Что сделали

- **`NotificationService`** дополнен:
  - Поле `self` + конструктор с 3-м параметром `@Lazy NotificationService self`
  - Метод `auditSend(...)` с `@Transactional(propagation = Propagation.REQUIRES_NEW)`
  - Внутри `send(...)` вызов переставлен: **сначала `self.auditSend(...)`, потом `emailSender.send(...)`** — иначе audit не успевал закоммититься до исключения на dev-fail (ученик сам догадался об этом переставить)
- **Импорты добавлены:** `org.springframework.context.annotation.Lazy`, `org.springframework.transaction.annotation.Propagation`
- **Проверено на dev-fail:** после POST /messages и GET /messages в БД **1 запись** (только `[audit]` от auditSend). Основная запись откатилась. Это и есть смысл REQUIRES_NEW — изолированная TX, переживает откат внешней
- **Коммит не сделан** — ученик ушёл на сегодня после проверки в логе

### Мини-экзамен (1 вопрос, 🟢 95%)

**Вопрос:** `OrderService.placeOrder(...)` помечен `@Transactional(REQUIRES_NEW)`, внутри вызывает `auditService.log(...)` (тоже `@Transactional(REQUIRES_NEW)`). После `auditService.log(...)` кидаем `RuntimeException`. Что в `orders` и `audit_entries`?

**Ответ ученика:** «сработает транзакция аудита, а внешняя упадёт из-за ошибки. Вариант 2. Видимо это тот вариант аудита, который я хотел увидеть у меня в начале?»

**Оценка:** 🟢 **95%**. Логика — идеально, ответ верный, плюс бонус — сам заметил связь с продакшен-паттерном (audit живёт отдельным бином). **Минус 5% за формулировку:** не назвал ключевое «REQUIRES_NEW-TX уже закоммичена до того, как внешний код бросил исключение, поэтому внешний rollback на неё не действует». На собесе добавь именно эту фразу.

### Шпаргалка (новое)

38. **`Propagation.REQUIRED`** (по умолчанию) — если TX есть, присоединиться; если нет — создать новую. 95% случаев
39. **`Propagation.REQUIRES_NEW`** — **всегда** новая TX, текущая приостановлена. Применяется для аудита, логирования, outbox-events — факт «попытались» должен записаться независимо от результата бизнес-операции
40. **REQUIRES_NEW-TX коммитится сразу** — внешний rollback её **не трогает**. Именно поэтому audit живёт, а бизнес-запись откатывается
41. **`Propagation.NESTED`** — savepoint внутри текущей TX, можно откатить локально без отката всей TX. Используется редко (JDBC savepoint нужен, не все БД поддерживают)
42. **`Propagation.MANDATORY`** — требует существующую TX, иначе исключение. «Я работаю только в TX, но сам её не открываю»
43. **`Propagation.SUPPORTS`** — если TX есть, присоединиться, иначе работать без. Для чтения
44. **`Propagation.NOT_SUPPORTED`** — приостановить текущую TX, выполнить без неё. Для долгих операций вне TX
45. **`Propagation.NEVER`** — кинуть исключение, если TX есть. «Я не должен работать в TX»
46. **AOP-прокси и self-invocation**: вызов `this.method()` **внутри того же бина** идёт мимо прокси → аннотации `@Transactional`, `@Async`, `@Cacheable` **не работают**. Чтобы advice сработал — вызов должен идти через DI-ссылку или через `ApplicationContext.getBean()`
47. **`@Lazy` на self-injection** — разрывает цикл: контейнер внедряет прокси-заглушку, реальный бин подставляется при первом вызове. Без `@Lazy` будет `BeanCurrentlyInCreationException`
48. **Порядок вызовов в `@Transactional`-методе имеет значение**: если REQUIRES_NEW-метод стоит ПОСЛЕ возможного исключения — он никогда не запустится на сценарии падения. Для надёжного аудита его надо ставить **до** рискованной операции или выносить в `@TransactionalEventListener` / outbox
49. **На собесе формулировка про self-invocation**: «Вызов через `this` минует прокси, поэтому AOP-advice не применяется. Чтобы advice сработал, вызов должен идти через DI-ссылку или через `ApplicationContext.getBean()`»

### Шпаргалка для собеса (обновляется)

1-37. [прежние пункты без изменений]

38. **`Propagation.REQUIRED`** (по умолчанию): если TX есть — присоединиться, если нет — создать
39. **`Propagation.REQUIRES_NEW`**: всегда новая TX, внешняя приостановлена. REQUIRES_NEW-TX коммитится сразу и **не откатывается внешним rollback'ом**
40. **`Propagation.NESTED`** редкий — JDBC savepoint внутри текущей TX
41. **AOP-прокси и self-invocation**: `this.method()` минует прокси → `@Transactional`/`@Async`/`@Cacheable` не работают. Только через DI-ссылку
42. **Self-injection через `@Lazy`** разрывает цикл и позволяет вызвать свой же `@Transactional`-метод через прокси
43. **Audit / outbox через REQUIRES_NEW-бин**: типичный продакшен-паттерн. AuditService живёт отдельно, бизнес-сервис инжектит его, факт «попытались» фиксируется независимо от результата
44. **Порядок вызовов важен**: REQUIRES_NEW-метод ПОСЛЕ возможного исключения никогда не запустится на сценарии падения. Для надёжного аудита — до операции, или listener, или outbox-таблица

---
---

## Шаг 7: Flyway/Liquibase (старт 05.09.2026, ~3.0 ч в сессии №13)

### Микро-шаг 1: первая попытка — что пошло не так

- Ученик добавил `AuditLog` entity + `AuditLogRepository` + переписал `auditSend` на сохранение в `audit_log` — самостоятельно, до моих подсказок
- `pom.xml`: +`flyway-core` (Boot BOM → 11.14.1)
- Создал `V1_init_messages.sql` + `V2_init_messages.sql` (названия оба про messages — копипаста)

**Две ошибки найдены при первом запуске:**

1. **Формат имени файлов Flyway.** Должно быть `V<номер>__<описание>.sql` — **два** подчёркивания. У ученика одно. Flyway такие файлы **игнорирует молча** (с предупреждением `validateMigrationNaming`). Решение: `git mv V1_init_messages.sql V1__init_messages.sql` + переименование V2 в `V2__init_audit_log.sql` (второй файл — про audit, а не messages).

2. **Spring Boot 4 требует `spring-boot-starter-flyway`.** Только `flyway-core` недостаточно — нет автоконфигурации. Добавил starter → Flyway заработал, лог показывает:
   ```
   Migrating schema "PUBLIC" to version "1 - init messages"
   Migrating schema "PUBLIC" to version "2 - init audit log"
   Successfully applied 2 migrations to schema "PUBLIC", now at version v2
   ```

### Микро-шаг 2: дьявол в application.properties

После исправления выше Flyway отработал, но Hibernate всё равно сделал `drop table + create table` **после** миграций. Причина:

- В `application.properties` стоит `spring.jpa.hibernate.ddl-auto=create-drop`
- В `application-dev.yml` ученик добавил `ddl-auto:none`
- В Spring Boot `application.properties` загружается как **отдельный PropertySource** с высоким приоритетом → `none` из dev-yml не перебивает `create-drop` из properties

**Урок для собеса:** при конфликте `.properties` vs `.yml` — `.properties` обычно выигрывает по приоритету. Решение в нашем случае — поменять `ddl-auto` **глобально** в `application.properties` (или конвертировать всё в один формат).

### Микро-шаг 3: от in-memory к production-like dev-стенду

`jdbc:h2:mem:notificationhub` — это **in-memory** база. Живёт только в JVM-процессе, при рестарте — пустая Flyway заново создаёт схему, данные потеряны. На реальной работе так не делают.

**Три подхода для persistent H2 в dev:**

| # | URL | Persistent | IDEA одновременно | Production-like |
|---|---|---|---|---|
| 1 | `jdbc:h2:file:./data/notificationhub;AUTO_SERVER=TRUE` | ✅ | ❌ блокировка | Частично |
| 2 | `jdbc:h2:tcp://localhost:9092/mem:notificationhub` | ❌ | ✅ | Нет |
| 3 | `jdbc:h2:tcp://localhost:9092/file:./data/notificationhub` | ✅ | ✅ | ✅ |

**Ученик выбрал #3** — TCP-сервер + файл. Это даёт:
- Данные переживают рестарт (`file:`)
- IDEA подключается параллельно через тот же TCP (`tcp://`)
- На проде тот же паттерн: реальная БД (PostgreSQL/MySQL) крутится как отдельный процесс, приложение подключается по TCP

**Что нужно сделать дальше (план ученика):**
1. Зарегистрировать H2 TCP-сервер через `@Bean` в конфигурации (порт 9092)
2. Поменять `spring.datasource.url` на `jdbc:h2:tcp://localhost:9092/file:./data/notificationhub`
3. Поменять `ddl-auto` с `create-drop` на `validate` — Hibernate будет **проверять** соответствие entity ↔ схеме, но не менять её. Source of truth = Flyway
4. Сверить entity ↔ миграции (особенно `event_type` nullable в entity, длина `message VARCHAR(10017)`)
5. `.gitignore` для `./data/`
6. Подключение из IDEA Database: драйвер H2, URL `jdbc:h2:tcp://localhost:9092/file:./data/notificationhub`, user `sa`, пароль пустой
7. Проверка: рестарт → `flyway_schema_history` на месте → данные на месте → Hibernate НЕ делает drop+create

### Teachable moment для собеса

**Почему `ddl-auto=validate` лучше чем `none` для dev:**

| Режим | Hibernate делает | Когда использовать |
|---|---|---|
| `create-drop` | Создаёт при старте, дропает при остановке | Только ad-hoc тесты, не для серьёзного dev |
| `create` | Создаёт при старте | Не использовать — перезатирает данные |
| `update` | Сравнивает и добавляет новые колонки | Опасно — может сделать непредсказуемые миграции |
| `validate` | Только проверяет что entity ↔ DB совпадают, **не меняет ничего** | **Лучший выбор для dev с миграциями** |
| `none` | Ничего не делает, даже не валидирует | Когда уверен что entity 100% матчит БД, или БД чужая (legacy) |

`validate` **ловит рассинхрон** между entity и схемой на старте (упадёт с ошибкой если добавил `@Column` в entity, но забыл миграцию). Это **защита** от багов в dev.

(см. `PROGRESS.md`, сессия №14) При команде «пауза» / «закончили» / «продолжим» — **обязательно** обновить 4 файла: `STATS.md`, `PROGRESS.md`, `OVERALL_STATS.md`, `HANDOFF.md`. Без напоминания от ученика.
## Шаг 7: Flyway + production-like dev-стенд (05.09.2026, сессии №13–14, ~5.5 ч)

### Что сделали

**Старт (сессия №13):**
- Ученик **сам** написал `AuditLog` entity + `AuditLogRepository extends JpaRepository`, переписал `auditSend` на сохранение в `audit_log` (вместо костыля `[audit]` в messages)
- `pom.xml`: `+flyway-core` (Boot BOM → 11.14.1), `+spring-boot-starter-flyway` (нужен в Boot 4 — Boot 4 изменил autoconfig)
- Миграции `V1__init_messages.sql`, `V2__init_audit_log.sql` (двойное `_` по спеке Flyway). Фикс `V2`: `event_type VARCHAR(100) NOT NULL` (изначально был nullable — расходилось с entity, упало бы на `ddl-auto=validate`)
- Hibernate SQL захвачен для обеих таблиц (H2: `BIGINT GENERATED BY DEFAULT AS IDENTITY`)

**Финал (сессия №14):**
- **`H2ServerConfig`**: `@Component` + `BeanFactoryPostProcessor`. Стартует TCP-сервер на 9092 **до** `DataSource`/`Flyway`
- `application.properties`: `url=jdbc:h2:tcp://localhost:9092/file:./data/notificationhub`, `ddl-auto=validate`
- `application-dev.yml`: убран `ddl-auto:none` (всё равно не перебивал `.properties`)
- `pom.xml`: `+h2` explicit со `scope=compile` (transitive scope=runtime блокирует `org.h2.tools.Server` в IDE)
- `.gitignore`: `+data/` (файл `notificationhub.mv.db` НЕ в git)
- Диагностика: `Connection refused: localhost:9092` → `BeanFactoryPostProcessor` без `@Component` не регистрируется → добавил `@Component`. Ученик **сам** ответил «почему не @Configuration: нет @Bean, side-effect» — правильно
- Верификация: POST /messages → 201, kill app → restart → 5 записей на месте (`./data/notificationhub.mv.db` 45 КБ). Flyway: `Successfully validated 2 migrations`. Hibernate: 0 DDL в логе

### Мини-экзамен (2 вопроса, 🟡 3/5)

**Вопрос 1 (🟢 4/5):** Кто-то удалил строку из `V3__add_user_email.sql`, БД уже на v3. Что будет и как чинить?

**Ответ ученика:** «можно в dev профиле включить `show-sql:true`, подключить БД из памяти, поставить `create-drop` и увидим SQL запрос для создания базы. добавить его в V3. Либо ручками написать sql запрос»

**Оценка:** Идея верная — увидеть SQL и вставить в миграцию. Но на собесе добавь:
- Главное: Flyway падает на **checksum mismatch ДО любого DDL**. Не важно что у тебя в БД — Flyway сравнивает **checksum файла** с **checksum в `flyway_schema_history`**
- Чинить **через файл** (вернуть строку), не через БД
- `ddl-auto:create` **только в dev**, на проде нельзя (грохнет данные)

**Вопрос 2 (🟡 2/5):** Разница между `V` и `R` миграциями?

**Ответ ученика:** «не знаю. может репитабл можно использовать для разных entity?»

**Оценка:** Уловил главное — `R` можно применять много раз. Но детали мимо. **R-миграции для entity использовать НЕЛЬЗЯ** — `R` пересоздаётся каждый раз при изменении файла, `CREATE TABLE` упадёт. `R` только для идемпотентных штук: views, functions, справочники.

### Шпаргалка (новое для шага 7)

49. **`V<номер>__описание.sql`** — двойное подчёркивание по спеке Flyway. Один `_` → файл тихо игнорируется
50. **`spring-boot-starter-flyway`** обязателен в Boot 4 (не только `flyway-core`). Boot 4 изменил autoconfig
51. **`BeanFactoryPostProcessor`** — точка расширения ДО создания бинов. Используется для запуска side-effect (TCP-сервер H2, загрузка настроек из внешнего источника)
52. **`@Component` vs `@Configuration` для `BeanFactoryPostProcessor`** — выбирай `@Component` если класс не определяет `@Bean`. CGLIB-прокси из `@Configuration` тут лишний
53. **TCP + файл = production-like dev**: PostgreSQL/MySQL на проде — отдельный процесс, app подключается по TCP. H2 в режиме `tcp://...file:...` эмулирует это в dev
54. **`flyway_schema_history`** — таблица с метаданными: version, description, type, script, checksum, installed_on, execution_time, success. Если checksum файла ≠ checksum в истории → Flyway падает
55. **`ddl-auto: validate`** — лучший выбор для dev с миграциями: ловит entity↔schema drift на старте, но НЕ мутирует БД
56. **V vs R**: `V` = одноразовая (структурные изменения), `R` = многоразовая (views/functions/справочники). Для entity — только `V`

### Архитектурное решение: почему TCP+файл, а не in-memory

| Вариант | Плюсы | Минусы |
|---|---|---|
| `mem:` (in-memory) | Быстро, чисто | Данные умирают с JVM, нельзя подключиться из IDEA Database одновременно |
| `file:` (embedded) | Persistent, переживает рестарт | К БД можно подключиться только из этого же процесса (file lock) |
| `tcp:// + file:` (production-like) | Persistent + параллельный доступ из IDEA + паттерн прод-конфига | Надо поднять TCP-сервер (наш `H2ServerConfig`) |

**Вывод для собеса:** на проде у тебя **всегда** отдельный процесс БД + TCP. H2 в режиме `tcp+file` в dev — это **честная** эмуляция прода, а не игрушка.

---


### Зафиксированное правило: фиксация времени

(см. `PROGRESS.md`, сессия №14) При команде «пауза» / «закончили» / «продолжим» — **обязательно** обновить 4 файла: `STATS.md`, `PROGRESS.md`, `OVERALL_STATS.md`, `HANDOFF.md`. Без напоминания от ученика.
