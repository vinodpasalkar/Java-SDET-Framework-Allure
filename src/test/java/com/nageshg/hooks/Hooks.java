package com.nageshg.hooks; // Cucumber hooks package.
import com.nageshg.api.MockServerManager; // Manages mock server lifecycle for API tests.
import com.nageshg.driver.DriverFactory; // Imports browser lifecycle methods.
import io.cucumber.java.Before; // Imports before-scenario annotation.
import io.cucumber.java.After; // Imports after-scenario annotation.
import io.cucumber.java.Scenario; // Provides scenario status and attachment support.
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

public class Hooks { // Defines scenario setup and cleanup.
    @Before("not @api") public void beforeUI() { DriverFactory.createDriver(); } // Starts a fresh browser before each UI scenario.
    @Before("@api") public void beforeAPI() { MockServerManager.start(); } // Starts the mock API server before API scenarios.

    @After(value = "not @api", order = 10000) public void afterUI(Scenario scenario) { // Runs cleanup after lower-order hooks and receives scenario details.
        if (scenario.isFailed() && DriverFactory.hasDriver()) { // Captures evidence only when a scenario failed and a browser exists.
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES); // Takes a screenshot as bytes.
            scenario.attach(screenshot, "image/png", "Failure screenshot"); // Adds the screenshot to Cucumber and the Allure result.
        }
        DriverFactory.quitDriver(); // Closes the browser after evidence has been captured.
    }

    @After("@api") public void afterAPI() {
        MockServerManager.reset(); // Resets mock stubs back to defaults after each API scenario.
    }
} // Ends Hooks.
