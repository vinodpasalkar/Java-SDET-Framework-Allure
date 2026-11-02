package com.nageshg.hooks; // Cucumber hooks package.
import com.nageshg.driver.DriverFactory; // Imports browser lifecycle methods.
import io.cucumber.java.Before; // Imports before-scenario annotation.
import io.cucumber.java.After; // Imports after-scenario annotation.
import io.cucumber.java.Scenario; // Provides scenario status and attachment support.
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;


public class Hooks { // Defines scenario setup and cleanup.
    private WebDriver driver;
    @Before public void before() { DriverFactory.createDriver(); } // Starts a fresh browser before each scenario.
    @After(order = 10000) public void after(Scenario scenario) { // Runs cleanup after lower-order hooks and receives scenario details.
        if (scenario.isFailed() && DriverFactory.hasDriver()) { // Captures evidence only when a scenario failed and a browser exists.
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES); // Takes a screenshot as bytes.
            scenario.attach(screenshot, "image/png", "Failure screenshot"); // Adds the screenshot to Cucumber and the Allure result.
        }
        DriverFactory.quitDriver(); // Closes the browser after evidence has been captured.
    }
} // Ends Hooks.
