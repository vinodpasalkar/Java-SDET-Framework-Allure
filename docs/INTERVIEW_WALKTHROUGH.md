# Interview walkthrough

## 90-second framework summary
“I would use Java 17, Selenium 4 and Cucumber. Gherkin features describe behaviour; step definitions call page objects. DriverFactory owns browser lifecycle and can use ThreadLocal for parallel sessions. Hooks perform setup and teardown. Configuration and test data are externalized. REST Assured covers APIs, while WireMock keeps tests deterministic. Maven runs the suite locally and in CI, where reports and failure evidence are published.”

## Topics
- Java: OOP, collections, streams, exceptions, generics, immutability, equals/hashCode and concurrency.
- Selenium: locators, explicit waits, stale elements, frames, windows, Grid and headless execution.
- Cucumber: hooks, tags, Scenario Outline, Examples, DataTable, dependency injection and parallel runs.
- API: HTTP methods, status codes, headers, authentication, JSONPath, schema validation and idempotency.
- React: components, props, state, hooks, controlled inputs, accessibility and testability.
- AI: prompt design, evaluation, hallucinations, privacy and nondeterminism.
- CI/CD: Maven lifecycle, Jenkins stages, reports, secrets, Docker and flaky-test management.

## Common questions
**Why POM?** It centralizes locators and interactions, reduces duplication and isolates UI changes.

**Implicit vs explicit wait?** Implicit wait affects element lookup globally; explicit wait polls for a specific condition. Avoid mixing them because timeout behaviour is harder to reason about.

**How do you reduce flaky tests?** Use stable selectors, state-based waits, isolated test data, deterministic dependencies and cleanup. Do not blindly retry real defects.

**How do you test React?** Test user-visible behaviour, accessibility, validation, loading/error states, API integration and critical journeys.

**How do you test AI output?** Define measurable criteria, use representative datasets, evaluate safety and quality, validate structure/grounding, compare against reviewed baselines and avoid exact-string assertions for variable output.
