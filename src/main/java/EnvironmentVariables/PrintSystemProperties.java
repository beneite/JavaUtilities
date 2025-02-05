package EnvironmentVariables;

import org.testng.annotations.Test;

import java.util.Properties;
import java.util.TreeSet;

public class PrintSystemProperties {


    @Test
    public void printSystemProperties() {
        Properties properties = System.getProperties();

        // Use TreeSet to sort property keys
        TreeSet<String> sortedKeys = new TreeSet<>(properties.stringPropertyNames());

        for (String key : sortedKeys) {
            System.out.println(key + "=" + System.getProperty(key));
        }
    }
}
