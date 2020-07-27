package net.toshimichi.packetanalyzer.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesLanguage implements Language {

    private final Properties properties;
    private final File file;

    public PropertiesLanguage(Properties properties, File file) {
        this.properties = properties;
        this.file = file;
    }

    public PropertiesLanguage(File file) throws IOException {
        this.properties = new Properties();
        this.file = file;
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            properties.load(reader);
        }
    }

    @Override
    public String get(String key) {
        String result = properties.getProperty(key);
        if (result == null) return key;
        else return result;
    }
}
