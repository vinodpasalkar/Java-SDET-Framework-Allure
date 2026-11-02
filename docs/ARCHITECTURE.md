# Framework architecture

1. Maven Surefire discovers `RunCucumberTest`.
2. JUnit Platform starts the Cucumber engine.
3. Cucumber reads features and maps Gherkin to `LoginSteps`.
4. Hooks create and quit a browser around each scenario.
5. DriverFactory stores one WebDriver per thread.
6. LoginPage hides locators and UI actions behind page-object methods.
7. PicoContainer injects ScenarioContext for scenario-scoped shared data.

Discuss POM, explicit waits, ThreadLocal parallelism, test-data isolation, external configuration, deterministic API mocks, CI reports and failure evidence.
