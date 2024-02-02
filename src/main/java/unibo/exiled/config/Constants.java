package unibo.exiled.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Constants {
    public static final String DEF_CONFIG_PATH = "src" + File.separator + "main" + File.separator + "java" + File.separator + "unibo" + File.separator + "exiled" + File.separator + "config" + File.separator + "config.yml";
    private static final Map<String, String> constants = new HashMap<>();

    public static void loadConfiguration(final String configPath) {
        File file = new File(configPath);
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                final String data = reader.nextLine();
                final String cName = data.substring(0, data.indexOf(":")).trim();
                final String value = data.substring(data.indexOf(":") + 1).trim();
                constants.put(cName, value);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            //TODO
        }
    }

    public static String getConstantOf(final String name) {
        return constants.get(name);
    }
}
