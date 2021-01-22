/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
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

/**
 *
 * @author Ricardo
 */
public class AssemblyWatcher implements Runnable {

    public static WatchService asmWatchService = null;

    public AssemblyWatcher() {
        try {
            asmWatchService = FileSystems.getDefault().newWatchService();

            Path path = Paths.get(System.getProperty("charger.comm.dir"));

            path.register(asmWatchService, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

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
                        File flag = new File(System.getProperty("charger.comm.dir") + event.context().toString());
                        if (flag.exists())
                            flag.delete();
                    }

                }
                key.reset();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(AssemblyWatcher.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
    }

}
