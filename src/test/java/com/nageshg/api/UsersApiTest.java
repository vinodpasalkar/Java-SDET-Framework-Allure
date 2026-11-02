package com.nageshg.api; // API test package.
import com.github.tomakehurst.wiremock.WireMockServer; // Provides an in-process mock server.
import org.junit.jupiter.api.*; // Imports JUnit lifecycle and test annotations.
import org.junit.jupiter.api.extension.ExtendWith; // Enables the Allure JUnit 5 extension.
import io.qameta.allure.junit5.AllureJunit5; // Imports Allure integration for JUnit 5.
import static com.github.tomakehurst.wiremock.client.WireMock.*; // Imports WireMock stubbing helpers.
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options; // Imports server options.
import static io.restassured.RestAssured.given; // Imports REST Assured request syntax.
import static org.hamcrest.Matchers.equalTo; // Imports response matcher.

@ExtendWith(AllureJunit5.class) // Sends JUnit 5 test lifecycle events to Allure.
class UsersApiTest { // Tests a sample API using a local mock.
    private static WireMockServer server; // Holds the server for this test class.
    @BeforeAll static void start() { // Starts WireMock once before tests.
        server = new WireMockServer(options().dynamicPort()); // Requests an available port.
        server.start(); // Starts the HTTP listener.
        configureFor("localhost", server.port()); // Points WireMock stubs at this server.
        stubFor(get(urlEqualTo("/users/42")).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody("{\"id\":42,\"name\":\"Nagesh\"}"))); // Stubs the GET response.
    } // Ends setup.
    @AfterAll static void stop() { server.stop(); } // Stops the mock server.
    @Test void getUser() { // Verifies the API response.
        given().baseUri("http://localhost:" + server.port()).when().get("/users/42").then().statusCode(200).body("id", equalTo(42)).body("name", equalTo("Nagesh")); // Sends GET and checks status and JSON fields.
    } // Ends test.
} // Ends UsersApiTest.
