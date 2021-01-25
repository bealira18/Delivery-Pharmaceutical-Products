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
    
    /**
     * Settings list
     */
    private static final String CONFIG_PROP_LIST[] = 
    {
        "database.url",
        "database.username",
        "database.password",
        "purchase.order.delivery.fee",
        "client.credits.purchase.ratio",
        "client.credits.delivery.fee.payment",
        "charger.comm.dir",
        "drone.max.payload",
        "scooter.max.payload",
        "invoice.iva",
        "mail.username",
        "mail.password",
        "mail.smtp.host",
        "mail.smtp.port",
        "mail.smtp.auth",
        "mail.smtp.starttls.enable",
        "mail.smtp.ssl.trust"
    };

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
            Properties props = new Properties();
            String propInfo;
            for (String prop : CONFIG_PROP_LIST) 
            {
                propInfo = System.getProperty(prop);
                if (propInfo == null)
                {
                    continue;
                }
                
                props.setProperty(prop, propInfo);
            }
            props.store(output, null);
            output.close();

        } catch (IOException ex) {
            Logger.getLogger(SettingsHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
