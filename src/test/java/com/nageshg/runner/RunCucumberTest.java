package com.nageshg.runner; // Test suite package.
import org.junit.platform.suite.api.*; // Imports JUnit Platform suite annotations.
@Suite // Declares a JUnit Platform suite.
@IncludeEngines("cucumber") // Selects Cucumber as the test engine.
@SelectClasspathResource("features") // Finds feature files under the classpath features folder.
public class RunCucumberTest { } // Maven Surefire discovers this suite.
