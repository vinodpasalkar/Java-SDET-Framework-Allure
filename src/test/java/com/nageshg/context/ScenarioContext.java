package com.nageshg.context; // Scenario state package.
public class ScenarioContext { // Shares data between step classes within one scenario.
    private String message; // Stores application feedback.
    public void setMessage(String message) { this.message = message; } // Saves the feedback.
    public String getMessage() { return message; } // Returns the saved feedback.
} // Ends ScenarioContext.
