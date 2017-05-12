package com.edu.nju.asi.utilities;

import java.io.File;
import java.io.IOException;

/**
 * Created by cuihua on 2017/4/15.
 */
public class AttahmentsInitializer {

    public static boolean init() throws IOException {
        final String separator = System.getProperty("file.separator");
        final String parent = System.getProperty("user.dir") + separator + ".attachments";
        File parentFile = new File(parent);

        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            parentFile.mkdirs();
            String sets = "attrib +H \"" + parentFile.getAbsolutePath() + "\"";
            Runtime.getRuntime().exec(sets);
        } else if (osName.contains("mac")) {
            parentFile.mkdirs();
        }

        return true;
    }
}
