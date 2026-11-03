package com.nageshg.api; // API test package.
import org.junit.jupiter.api.*; // Imports JUnit lifecycle and test annotations.
import org.junit.jupiter.api.extension.ExtendWith; // Enables the Allure JUnit 5 extension.
import io.qameta.allure.junit5.AllureJunit5; // Imports Allure integration for JUnit 5.
import io.qameta.allure.restassured.AllureRestAssured; // Attaches HTTP request and response to Allure.
import static io.restassured.RestAssured.given; // Imports REST Assured request syntax.
import static org.hamcrest.Matchers.equalTo; // Imports response matcher.

@ExtendWith(AllureJunit5.class) // Sends JUnit 5 test lifecycle events to Allure.
class UsersApiTest { // Tests a sample API using a local mock.
    @BeforeAll static void start() { // Starts WireMock once before tests.
        MockServerManager.start();
    } // Ends setup.
    @AfterAll static void stop() { MockServerManager.stop(); } // Stops the mock server.
    @Test void getUser() { // Verifies the API response.
        given().filter(new AllureRestAssured()).baseUri(MockServerManager.getBaseUrl()).when().get("/users/42").then().statusCode(200).body("id", equalTo(42)).body("name", equalTo("Nagesh")); // Sends GET and checks status and JSON fields.
    } // Ends test.
} // Ends UsersApiTest.
