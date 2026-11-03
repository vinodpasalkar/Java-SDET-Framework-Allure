package com.nageshg.context; // Scenario state package.
import io.restassured.response.Response; // Stores REST Assured responses.

public class ScenarioContext { // Shares data between step classes within one scenario.
    private String message; // Stores application UI feedback.
    private Response response; // Stores API responses for validation across steps.

    public void setMessage(String message) { this.message = message; } // Saves the UI feedback.
    public String getMessage() { return message; } // Returns the saved UI feedback.

    public void setResponse(Response response) { this.response = response; } // Saves the API response.
    public Response getResponse() { return response; } // Returns the saved API response.
} // Ends ScenarioContext.
