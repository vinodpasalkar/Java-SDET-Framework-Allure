package com.nageshg.steps; // Step-definition package.
import com.nageshg.config.Config; // Reads the app URL.
import com.nageshg.context.ScenarioContext; // Shares scenario data.
import com.nageshg.pages.LoginPage; // Uses the login page object.
import io.cucumber.java.en.*; // Imports Given, When and Then annotations.
import org.junit.jupiter.api.Assertions; // Provides assertions.

public class LoginSteps { // Connects Gherkin steps to Java actions.
    private final ScenarioContext context; // Holds scenario-scoped data.
    private LoginPage page; // Holds the page object.
    public LoginSteps(ScenarioContext context) { this.context = context; } // PicoContainer injects the scenario context.
    @Given("the login page is open") // Matches the Given feature step.
    public void openLogin() { page = new LoginPage(); page.open(Config.get("base.url")); } // Creates page object and navigates to login.
    @When("I sign in with username {string} and password {string}") // Captures values from the Gherkin step.
    public void signIn(String user, String pass) { page.login(user, pass); context.setMessage(page.message()); } // Logs in and stores feedback.
    @Then("I should see a successful login message") // Matches the expected-result step.
    public void verify() { Assertions.assertTrue(context.getMessage().contains("You logged into a secure area!"), "Expected successful login message"); } // Asserts the successful login text.
} // Ends LoginSteps.
