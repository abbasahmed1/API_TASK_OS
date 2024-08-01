package utils;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHandling {

    private static Properties initProperties() throws IOException {
        // read application.properties file and return properties
        Properties properties = new Properties();
        FileReader reader=new FileReader("application.properties");
        properties.load(reader);

        return properties;
    }

    // get the property
    public static String getProperty(String text) throws IOException {
        return PropertiesHandling.initProperties().getProperty(text);
    }
}
