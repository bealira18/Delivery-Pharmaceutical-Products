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

/**
 * Class responsible for containing utility algorithms such as write/read
 * methods.
 *
 * @author lapr3-2020-g052
 */
public class Utils {

    /**
     * Private constructor to hide the implicit public one.
     */
    private Utils() {

    }

    /**
     * Method responsible for reading and delivering the contents of a file
     * (whose name is received as parameter).
     *
     * @param fileName the file's name.
     * @return the contents of the file by line.
     */
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

    /**
     * Method responsible for writing content into a file (whose name is
     * received as parameter).
     *
     * @param s the content to be written onto the file.
     * @param fileName the file's name.
     * @return true, if the contents have been successfully written, otherwise
     * returns false.
     */
    public static boolean writeFile(String s, String fileName) {

        if (s == null || fileName == null || s.trim().isEmpty() || fileName.trim().isEmpty()) {
            return false;

        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(s);

        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
