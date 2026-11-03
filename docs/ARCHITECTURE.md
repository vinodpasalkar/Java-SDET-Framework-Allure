# Framework architecture

1. Maven Surefire discovers `RunCucumberTest` and JUnit 5 test classes.
2. JUnit Platform starts the Cucumber engine and JUnit Jupiter engine.
3. Cucumber reads feature files (`login.feature`, `users_api.feature`) and maps Gherkin to `LoginSteps` and `ApiSteps`.
4. Hooks manage test lifecycles dynamically:
   - UI scenarios (`not @api`): `DriverFactory` creates and quits a ThreadLocal browser and attaches failure screenshots.
   - API scenarios (`@api`): `MockServerManager` spins up and manages in-memory WireMock stubs without starting a browser.
5. `DriverFactory` stores one WebDriver per thread for safe parallel execution.
6. `LoginPage` hides locators and UI actions behind page-object methods.
7. `MockServerManager` centralizes deterministic API stubbing for WireMock.
8. PicoContainer injects `ScenarioContext` for scenario-scoped state sharing (UI messages, REST Assured HTTP responses).
9. Allure automatically records Cucumber scenarios, REST Assured HTTP requests/responses, and failure attachments under `target/allure-results`.

Discuss POM, explicit waits, ThreadLocal parallelism, test-data isolation, external configuration, deterministic API mocks, CI reports and failure evidence.
