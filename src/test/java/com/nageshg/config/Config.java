package com.nageshg.config; // Configuration package.
import java.io.*; // Provides classpath stream and I/O exception types.
import java.util.Properties; // Stores key-value settings.

public final class Config { // Utility for reading framework configuration.
    private static final Properties VALUES = new Properties(); // Holds loaded property values.
    static { // Executes once when Config is loaded.
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) { // Opens config and closes the stream automatically.
            if (in == null) throw new IllegalStateException("config.properties missing"); // Fails clearly when the file is absent.
            VALUES.load(in); // Loads the properties into memory.
        } catch (IOException ex) { throw new ExceptionInInitializerError(ex); } // Stops initialization if reading fails.
    } // Ends static initialization.
    private Config() { } // Prevents instances of this utility class.
    public static String get(String key) { // Reads a required property.
        String value = VALUES.getProperty(key); // Looks up the key.
        if (value == null || value.isBlank()) throw new IllegalArgumentException("Missing setting: " + key); // Validates the setting.
        return value.trim(); // Returns the value without surrounding whitespace.
    } // Ends get.
} // Ends Config.
