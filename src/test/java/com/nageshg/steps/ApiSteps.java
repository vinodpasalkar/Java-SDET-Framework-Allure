package com.nageshg.steps; // Step-definition package.

import com.nageshg.api.MockServerManager; // Provides the mock server endpoint.
import com.nageshg.context.ScenarioContext; // Shares scenario-scoped data.
import io.cucumber.java.en.*; // Imports Given, When and Then annotations.
import io.qameta.allure.restassured.AllureRestAssured; // Attaches HTTP requests and responses to Allure report.
import io.restassured.http.ContentType; // Sets standard JSON MIME type.
import io.restassured.response.Response; // Stores REST Assured response object.
import org.junit.jupiter.api.Assertions; // Provides JUnit 5 assertions.

import static io.restassured.RestAssured.given; // Imports fluent REST Assured DSL.
import static org.hamcrest.Matchers.equalTo; // Imports Hamcrest matcher for JSON body validation.

public class ApiSteps { // Connects Gherkin API steps to REST Assured actions.
    private final ScenarioContext context; // Holds scenario state across steps.

    public ApiSteps(ScenarioContext context) { // PicoContainer injects the shared scenario context.
        this.context = context;
    } // Ends constructor.

    @Given("the user API service is available") // Ensures the WireMock service is up.
    public void apiServiceIsAvailable() {
        MockServerManager.start(); // Starts the mock server if not already running.
    } // Ends apiServiceIsAvailable.

    @When("I send a GET request to {string}") // Sends an HTTP GET request to the specified path.
    public void sendGetRequest(String endpoint) {
        Response response = given()
                .filter(new AllureRestAssured()) // Logs request and response details into Allure report.
                .baseUri(MockServerManager.getBaseUrl()) // Points to the active WireMock port.
                .when()
                .get(endpoint); // Executes HTTP GET.
        context.setResponse(response); // Stores the response in ScenarioContext.
    } // Ends sendGetRequest.

    @When("I send a POST request to {string} with payload:") // Sends an HTTP POST request with a JSON body.
    public void sendPostRequest(String endpoint, String payload) {
        Response response = given()
                .filter(new AllureRestAssured()) // Logs request and response details into Allure report.
                .baseUri(MockServerManager.getBaseUrl()) // Points to the active WireMock port.
                .contentType(ContentType.JSON) // Sets application/json header.
                .body(payload) // Sets the JSON request body.
                .when()
                .post(endpoint); // Executes HTTP POST.
        context.setResponse(response); // Stores the response in ScenarioContext.
    } // Ends sendPostRequest.

    @Then("the response status code should be {int}") // Validates HTTP response status code.
    public void verifyStatusCode(int expectedStatusCode) {
        Response response = context.getResponse();
        Assertions.assertNotNull(response, "Expected an HTTP response, but none was recorded");
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode(), "HTTP status code mismatch");
    } // Ends verifyStatusCode.

    @Then("the response header {string} should contain {string}") // Validates response header content.
    public void verifyHeader(String headerName, String expectedValue) {
        Response response = context.getResponse();
        String actualHeader = response.getHeader(headerName);
        Assertions.assertNotNull(actualHeader, "Header '" + headerName + "' was not found in response");
        Assertions.assertTrue(actualHeader.contains(expectedValue),
                "Expected header '" + headerName + "' to contain '" + expectedValue + "' but was '" + actualHeader + "'");
    } // Ends verifyHeader.

    @Then("the response JSON should contain {string} equal to {string}") // Validates string field in JSON.
    public void verifyJsonStringField(String jsonPath, String expectedValue) {
        Response response = context.getResponse();
        response.then().body(jsonPath, equalTo(expectedValue)); // Asserts JSON path value using Hamcrest.
    } // Ends verifyJsonStringField.

    @Then("the response JSON should contain {string} equal to {int}") // Validates integer field in JSON.
    public void verifyJsonIntField(String jsonPath, int expectedValue) {
        Response response = context.getResponse();
        response.then().body(jsonPath, equalTo(expectedValue)); // Asserts JSON path value using Hamcrest.
    } // Ends verifyJsonIntField.
} // Ends ApiSteps.
