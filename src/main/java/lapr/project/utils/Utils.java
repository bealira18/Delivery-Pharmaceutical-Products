package lapr.project.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Utils {

    private Utils() {
    }

    public static List<String> readFile(String fileName) {

        if (fileName == null || fileName.trim().isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return Files.lines(Paths.get(fileName)).collect(Collectors.toList());

        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                Files.lines(Paths.get(fileName)).close();

            } catch (IOException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new ArrayList<>();
    }
}
