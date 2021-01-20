package lapr.project.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Utils {

    private Utils() {
    }

    public static List<String> readFile(String fileName) {

        if (fileName == null || fileName.trim().isEmpty()) {
            return null;
        }
        try {
            return Files.lines(Paths.get(fileName)).collect(Collectors.toList());

        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean writeFile(String s, String fileName) {

        if (s == null || fileName == null || s.trim().isEmpty() || fileName.trim().isEmpty()) {
            return false;

        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(s);
            return true;

        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
