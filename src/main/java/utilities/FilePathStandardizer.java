package utilities;

import utilities.enums.DataSourceState;

/**
 * Created by cuihua on 2017/4/22.
 */
public class FilePathStandardizer {

    /**
     * 让软件架包能够跨系统执行
     * @param path windows／maxOS 下要找的路径
     * @return windows下软件使用本机数据源时系统分拣分隔符'\'被置为'/'
     */
    public static String standardize(String path) {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win") && DataSourceStateKeeper.getInstance().getState() == DataSourceState.ORIGINAL) {
            path = path.replace('\\', '/');
        }
        return path;
    }

}
