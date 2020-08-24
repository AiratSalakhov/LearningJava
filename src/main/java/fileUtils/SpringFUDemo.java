package fileUtils;

import java.io.File;
import java.io.IOException;

public class SpringFUDemo {
    public static String getHash(File file) throws IOException {
        return org.springframework.boot.loader.tools.FileUtils.sha1Hash(file);
    }
}
