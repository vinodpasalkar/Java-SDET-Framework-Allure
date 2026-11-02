package com.nageshg.pages; // Page-object package.
import com.nageshg.driver.DriverFactory; // Gets the active WebDriver.
import org.openqa.selenium.*; // Imports locators and browser APIs.
import org.openqa.selenium.support.ui.*; // Imports explicit-wait utilities.
import java.time.Duration; // Represents timeout duration.

public class LoginPage { // Models the login screen.
    private final WebDriver driver = DriverFactory.getDriver(); // Gets the scenario browser.
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Creates an explicit wait.
    private final By username = By.id("username"); // Locator for username input.
    private final By password = By.id("password"); // Locator for password input.
    private final By submit = By.cssSelector("button[type='submit']"); // Locator for submit button.
    private final By flash = By.id("flash"); // Locator for feedback message.
    public void open(String baseUrl) { driver.get(baseUrl + "/login"); } // Opens the login route.
    public void login(String user, String pass) { // Enters credentials and submits.
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user); // Waits for username and enters it.
        driver.findElement(password).sendKeys(pass); // Enters password.
        driver.findElement(submit).click(); // Submits the form.
    } // Ends login.
    public String message() { return wait.until(ExpectedConditions.visibilityOfElementLocated(flash)).getText(); } // Waits for and returns the result message.
} // Ends LoginPage.
