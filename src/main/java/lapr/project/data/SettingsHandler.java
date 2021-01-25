package lapr.project.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsHandler {

    public static final String SETTINGS_FILE = "target/classes/application.properties";

    public void loadSettings(String filePath) {

        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream(filePath);
            properties.load(input);

            System.setProperties(properties);

        } catch (IOException ex) {
            Logger.getLogger(SettingsHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean saveSettings(String filePath) {

        try {
            FileOutputStream output = new FileOutputStream(filePath);
            System.getProperties().store(output, null);
            output.close();

        } catch (IOException ex) {
            Logger.getLogger(SettingsHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
