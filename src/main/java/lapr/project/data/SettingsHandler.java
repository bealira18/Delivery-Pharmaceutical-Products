/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Ricardo
 */
public class SettingsHandler {

    public static final String SETTINGS_FILE = "target/classes/application.properties";
    
    public void loadSettings(String filePath)
    {
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream(filePath);
            properties.load(input);
            
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void saveSettings(String filePath)
    {
        try {
            Properties properties = new Properties(System.getProperties());
            OutputStream output = new FileOutputStream(filePath);
            properties.store(output, null);
            output.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
