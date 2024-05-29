package utils;

import java.util.Map;

public interface LocatorReader {
    Map<String, ?> read(String filePath);
}
