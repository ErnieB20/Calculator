package utils;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileUtils {

    public static final String FILE_OBJECT_DIRECTORY = "src/test/resources/page_objects/";


    public static String lister(String name) {
        String fileSep = FileSystems.getDefault().getSeparator();
        String location = FILE_OBJECT_DIRECTORY.replace("/", fileSep);
        Path directory = Paths.get(location);
        List<String> files;
        String filePath = null;
        try {
            files = Files.list(directory)
                    .map(Path::toString)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch files from the given path: " + location + ", " + e.getMessage());
        }

        for (String file : files){
            if (file.contains(name)){
                filePath = file;
            }
        }
        if (filePath == null) {
            throw new RuntimeException("File " + name + " not found in the following directory: "+ location);
        }
        return filePath;
    }

}
