# 📓 Learning Log — дневник теории

Сюда пишем **всё**: разборы шагов, новые концепции, шпаргалки для собеса, открытия, ошибки.

**Формат:**
- `## Шаг N: <название>` — заголовок шага
- Под ним: теория → мини-экзамен → эксперимент → итог
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
