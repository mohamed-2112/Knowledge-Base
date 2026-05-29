# Repository Guidelines

## Project Structure & Module Organization
This is a Spring Boot backend (`com.mrm.knowledge_base`) built with Maven Wrapper.

- Source: `src/main/java/com/mrm/knowledge_base`
- Tests: `src/test/java/com/mrm/knowledge_base`
- Main domains:
  - `auth/` (OAuth2 login, JWT, CSRF helpers)
  - `knowledge/` (knowledge item entity, service, controller, DTOs)
  - `user/` (user entity, repository, service, DTOs)
  - `configuration/` and `util/` for shared setup/helpers

Keep new code in the closest existing package boundary; add new top-level packages only for genuinely new domains.

## Build, Test, and Development Commands
Use the Maven wrapper so local Maven version does not matter.

- `./mvnw spring-boot:run` — run the API locally
- `./mvnw test` — run unit/integration tests
- `./mvnw clean verify` — full validation lifecycle
- `./mvnw clean package` — build a runnable JAR in `target/`

Prerequisite: JDK 25 (see `pom.xml` `java.version`).

## Coding Style & Naming Conventions
- Java style: 4-space indentation, UTF-8 source, no wildcard imports.
- Class names: `PascalCase`; methods/fields: `camelCase`; constants: `UPPER_SNAKE_CASE`.
- Spring components use explicit suffixes: `*Controller`, `*Service`, `*Repository`, `*Request`, `*Response`.
- Prefer constructor injection (current project pattern).
- Keep controllers thin; place business logic in services.

No dedicated formatter/linter plugin is configured; keep formatting consistent with existing files.

## Testing Guidelines
- Framework: JUnit 5 with Spring Boot test starters.
- Test class naming: `*Tests` (example: `KnowledgeBaseApplicationTests`).
- Add tests for new service logic and controller behavior, not only context-load tests.
- Run `./mvnw test` before opening a PR.

## Commit & Pull Request Guidelines
Recent commits are feature-focused, descriptive subject lines. Continue that pattern with concise, single-purpose commits.

- Commit message format: short subject line in imperative style (e.g., `Add knowledge item update validation`).
- PRs should include:
  - what changed and why
  - linked issue/task (if available)
  - API impact (new/changed endpoints, request/response fields)
  - test evidence (`./mvnw test` output summary)

## Security & Configuration Notes
This project uses OAuth2 login and token/cookie-based auth components. Do not commit secrets, client credentials, or environment-specific tokens. Keep security-related changes in `auth/` and `configuration/` small and reviewable.
