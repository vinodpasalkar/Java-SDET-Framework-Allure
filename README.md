# NageshG Java SDET Framework — commented learning project

Interview-preparation starter covering Java 17, Selenium, Cucumber, REST Assured, React, AI-assisted testing and CI.

## Prerequisites
- Java 17+, Maven 3.9+
- Node.js 20+ for the React demo
- Chrome for the browser scenario

## Run
- Java tests: `mvn clean test`
- Allure results are written to `target/allure-results`.
- Generate and open the interactive report after tests: `allure serve target/allure-results` (requires the Allure command-line tool; see `docs/ALLURE_REPORTING.md`).
- Generate a static report folder: `allure generate target/allure-results --clean -o target/allure-report`
- React demo: `cd react-demo && npm install && npm run dev`

The UI scenario uses the public demo site `the-internet.herokuapp.com` and needs internet access. The API test uses a local WireMock server.

## Contents
- `src/test/java`: framework classes, UI steps, API test, coding examples and AI API sample.
- `src/test/resources`: Cucumber feature, config and sample data.
- `react-demo`: React login page.
- `docs`: architecture, interview, AI testing and SQL/JDBC notes.
- `Jenkinsfile`, `.github/workflows/ci.yml`: CI examples.
- Allure reporting: scenario-level results, failure screenshots and CI artifact upload.

This is an educational starter, not production-ready. JSON does not support comments, so JSON examples use descriptive keys. Source and configuration files include explanatory comments.
