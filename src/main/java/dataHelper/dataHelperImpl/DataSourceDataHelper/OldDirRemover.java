package dataHelper.dataHelperImpl.DataSourceDataHelper;

import utilities.AttahmentsInitializer;

import java.io.File;
import java.io.IOException;

/**
 * 移除之前的.attachments/stocks，如果之前没有就不操作
 * 同时创建父目录.attachments
 */
public class OldDirRemover {

    private final String separator = System.getProperty("file.separator");

    public boolean myDelete() throws IOException {
        // init the .attachments folder
        AttahmentsInitializer.init();

        final String separator = System.getProperty("file.separator");
        final String parent = System.getProperty("user.dir") + separator + ".attachments" + separator + "stocks";

        File parentFile = new File(parent);
        if (parentFile.exists()) {
            delete(parent);
        }

        return true;
    }

    private boolean delete(String path) {
        File thisFile = new File(path);
        if (thisFile.isDirectory()) {
            String[] parts = thisFile.list();
            for (String eachPath : parts) {
                delete(path + separator + eachPath);
            }
            thisFile.delete();
        } else {
            thisFile.delete();
        }

        return true;
    }

}