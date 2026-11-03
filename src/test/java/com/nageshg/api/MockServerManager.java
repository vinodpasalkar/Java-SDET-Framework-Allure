package com.nageshg.api; // API utilities and test package.

import com.github.tomakehurst.wiremock.WireMockServer; // Provides the embedded HTTP server.
import static com.github.tomakehurst.wiremock.client.WireMock.*; // Provides DSL for stubs and responses.
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options; // Configures dynamic port binding.

public final class MockServerManager { // Manages the lifecycle and stubs of the WireMock server.
    private static WireMockServer server; // Singleton instance of WireMock for test execution.

    private MockServerManager() { } // Prevents direct instantiation.

    public static synchronized void start() { // Starts WireMock if not running and registers stubs.
        if (server == null || !server.isRunning()) {
            server = new WireMockServer(options().dynamicPort()); // Selects an available local port.
            server.start(); // Starts the HTTP listener.
            configureFor("localhost", server.port()); // Points the static WireMock client to this instance.
            setupDefaultStubs(); // Registers endpoint stubs for tests.
        }
    } // Ends start.

    public static synchronized int getPort() { // Returns the active WireMock listening port.
        if (server == null || !server.isRunning()) start();
        return server.port();
    } // Ends getPort.

    public static synchronized String getBaseUrl() { // Returns the full base URL for REST Assured requests.
        return "http://localhost:" + getPort();
    } // Ends getBaseUrl.

    public static synchronized void setupDefaultStubs() { // Defines request matching and mock responses.
        // GET /users/42 -> Returns 200 with user JSON.
        stubFor(get(urlEqualTo("/users/42"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":42,\"name\":\"Nagesh\",\"role\":\"SDET\"}")));

        // POST /users -> Returns 201 with created user JSON.
        stubFor(post(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":101,\"name\":\"Vinod\",\"role\":\"SDET\"}")));

        // GET /users/999 -> Returns 404 with error JSON.
        stubFor(get(urlEqualTo("/users/999"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"error\":\"User not found\"}")));
    } // Ends setupDefaultStubs.

    public static synchronized void reset() { // Resets stub mappings back to defaults between scenarios.
        if (server != null && server.isRunning()) {
            server.resetAll();
            setupDefaultStubs();
        }
    } // Ends reset.

    public static synchronized void stop() { // Stops the WireMock server when test suite finishes.
        if (server != null && server.isRunning()) {
            server.stop();
            server = null;
        }
    } // Ends stop.
} // Ends MockServerManager.
