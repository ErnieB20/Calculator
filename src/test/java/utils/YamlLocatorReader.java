package utils;

import org.slf4j.*;
import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class YamlLocatorReader implements LocatorReader{

    private static final Logger logger = LoggerFactory.getLogger(YamlLocatorReader.class);


    @Override
    public Map<String, String> read(String pageName) {
        // Fetching and checking if file exists
        String filePath = FileUtils.lister(pageName);

        // To store sub keys after getting the rootKey from the original map
        Map<String, String> childMap = new HashMap<>();
        Yaml yaml = new Yaml();

        try (InputStream in = Files.newInputStream(Paths.get(filePath))) {
            // Load YAML file into a map
            Map<String, Object> originalMap = yaml.load(in);

            @SuppressWarnings(value = "unchecked") // getting rootKey from original map
            Map<String, Object> tempMap = (Map<String, Object>) originalMap.get(pageName);

            if (tempMap != null){
                // Iterate over the child map and populate the childMap with String values
                for (Map.Entry<String, Object> entry : tempMap.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        childMap.put(entry.getKey(), (String) entry.getValue());
                    }
                }
            }

        } catch (IOException e) {
            logger.warn("Please check map value");
            throw new RuntimeException("Please provide the correct root key");
        }
        return childMap;
    }

}
