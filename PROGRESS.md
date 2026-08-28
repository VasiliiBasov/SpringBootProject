# 📋 Прогресс: контекст и навигация

Здесь — **только** то, что нужно для быстрого входа в работу: где остановились, что дальше, шпаргалка для следующего открытия.

**Цифры, время, баллы — в `STATS.md`.**
**Конспект теории — в `LEARNING_LOG.md`.**
**Краткое резюме (если история слетит) — в `HANDOFF.md`.**

---

## 📌 Где мы сейчас

**Текущий шаг:** 1 / 15 — Старт Spring Boot 🟡 (теория + мини-экзамен, без практики)
**Следующий шаг:** 2 — Spring Boot автоконфигурация (application.properties/yml, профили dev/prod, @Profile). **Включая практику** — ученик сам конфигурит и запускает.
**Процент:** 7% (1/15)

## 🔑 Шпаргалка для следующего открытия (ПРОЧИТАТЬ ПЕРВЫМ ДЕЛОМ)

**Если ученик написал «прочитай прогресс» — прочитай ОБЯЗАТЕЛЬНО все 6 файлов в таком порядке:**

1. **`COURSE_HANDBOOK.md`** — master-файл с историей
2. **`OVERALL_STATS.md`** — сводка по всем проектам
3. **`HANDOFF.md`** — краткое резюме
4. **`PROGRESS.md`** (этот файл) — где остановились
5. **`STATS.md`** — баллы, время, прогресс
6. **`LEARNING_LOG.md`** — конспект теории

### Суть

- **Ученик:** Василий. Готовится к собеседованию по Spring Boot. См. `COURSE_HANDBOOK.md`.
- **Профиль:** ценит глубокие разборы с аналогиями, ASCII-схемы, мини-экзамены без вариантов. **Не любит готовый код классов целиком** — давай только подсказки и направление.

### Главное правило про время
**Не угадывай время — всегда `Get-Date`.**

### Главное правило про git
**После каждого завершённого шага — `git add . && git commit -m "step N: <название>" && git push`.** Ученик явно попросил 28.08.2026.

## 📋 План курса (15 шагов)

### Блок 1: Spring Boot Basics (шаги 1–4)
1. **Старт Spring Boot проекта** — `@SpringBootApplication`, `SpringApplication.run()`, embedded Tomcat
2. **Автоконфигурация** — `application.properties`/`yml`, профили (`dev`/`prod`), `@Profile`
3. **REST API: `@RestController`, `@GetMapping`**
4. **DTO + валидация** (`@Valid`, `@NotNull`)

### Блок 2: Spring Data JPA (шаги 5–8)
5. **Spring Data JPA: `@Entity`, `JpaRepository`** (H2 in-memory)
6. **Транзакции: `@Transactional`** (связь с AOP из курса №1)
7. **Миграции: Flyway/Liquibase**
8. **Query: JPQL, native, Specification**

### Блок 3: Spring Security (шаги 9–11)
9. **Spring Security basics** — `SecurityFilterChain`, `BCryptPasswordEncoder`
10. **JWT / OAuth2**
11. **Method Security: `@PreAuthorize`, `@Secured`**

### Блок 4: Testing + Production (шаги 12–14)
12. **Тестирование** — `@SpringBootTest`, `MockMvc`, `Testcontainers`
13. **Логирование + мониторинг** — SLF4J, Micrometer, Actuator
14. **Кэширование** — `@Cacheable`, `@CacheEvict`

### Блок 5: Финал (шаг 15)
15. **Ревью курса, финальная шпаргалка, чеклист «готов к собесу»**
