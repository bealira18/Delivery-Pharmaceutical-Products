package lapr.project.data;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lapr.project.controller.VehicleParkingController;
import static lapr.project.utils.Constants.CRG_COM_DIR;

/**
 * This class implements a system meant to run as a daemon, watching incoming files
 * from the parking system on a specified directory.
 * 
 * @author lapr3-2020-g052
 */
public class AssemblyWatcher implements Runnable {

    /**
     * WatchService Object, responsible for the directory watch
     */
    private WatchService asmWatchService;

    /**
     * Sets up the Watch service and registers the directory stored on the config
     * file.
     */
    public AssemblyWatcher() {

        try {
            asmWatchService = FileSystems.getDefault().newWatchService();

            Path path = Paths.get(System.getProperty(CRG_COM_DIR));

            path.register(asmWatchService, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * The daemon routine. On the start, it builds the regex pattern to identify
     * only the files pertinent to this operation. Then, as long as the thread is
     * alive, it will watch for any file being created on the directory and interpret
     * it if it matches the regex pattern.
     */
    @Override
    public void run() {

        Pattern pattern = Pattern.compile("^estimate_[0-9]{4}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}[.]data[.]flag$", Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        WatchKey key;
        try {
            while ((key = asmWatchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {

                    matcher = pattern.matcher(event.context().toString());

                    if (matcher.find()) {
                        new VehicleParkingController().interpretChargerInfo(event.context().toString().replace(".flag", ""));
                        Files.delete(FileSystems.getDefault().getPath(System.getProperty(CRG_COM_DIR) + event.context().toString()));
                        Files.delete(FileSystems.getDefault().getPath(System.getProperty(CRG_COM_DIR) + event.context().toString().replace(".flag", "")));
                    }

                }
                key.reset();
            }
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(AssemblyWatcher.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
    }
}
