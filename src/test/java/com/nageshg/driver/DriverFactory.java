package com.nageshg.driver; // Browser lifecycle package.
import com.nageshg.config.Config; // Reads browser configuration.
import org.openqa.selenium.*; // Imports WebDriver API.
import org.openqa.selenium.chrome.*; // Imports Chrome driver and options.

public final class DriverFactory { // Centralizes WebDriver management.
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>(); // Gives each test thread its own driver.
    private DriverFactory() { } // Prevents utility-class instances.
    public static void createDriver() { // Starts a browser for the scenario.
        if (!"chrome".equalsIgnoreCase(Config.get("browser"))) throw new IllegalArgumentException("Only Chrome is supported in this sample"); // Checks browser selection.
        ChromeOptions options = new ChromeOptions(); // Creates Chrome startup options.
        options.addArguments("--start-maximized"); // Maximizes the browser.
        options.addArguments("--disable-notifications"); // Suppresses browser notifications.
        DRIVER.set(new ChromeDriver(options)); // Starts Chrome and stores it for this thread.
    } // Ends createDriver.
    public static boolean hasDriver() { return DRIVER.get() != null; } // Checks whether this thread has an active browser.
    public static WebDriver getDriver() { // Returns the current thread's driver.
        WebDriver driver = DRIVER.get(); // Reads the ThreadLocal value.
        if (driver == null) throw new IllegalStateException("Driver has not been created"); // Prevents use before setup.
        return driver; // Returns the active browser.
    } // Ends getDriver.
    public static void quitDriver() { // Closes the scenario browser.
        WebDriver driver = DRIVER.get(); // Reads the current browser.
        if (driver != null) { driver.quit(); DRIVER.remove(); } // Quits Chrome and clears thread state.
    } // Ends quitDriver.
} // Ends DriverFactory.
