package com.edu.nju.asi.crawler;

import org.python.core.PyFunction;
import org.python.core.PyList;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public class CrawingHistory {

    //python主控对象
    private PythonInterpreter pythonInterpreter;

    //初始化
    public CrawingHistory() {
        Properties properties = new Properties();
        properties.put("python.console.encoding", "UTF-8");
        properties.put("python.security.respectJavaAccessibility", "false");
        properties.put("python.import.site", "false");
        Properties pre = System.getProperties();

        PythonInterpreter.initialize(pre,properties,new String[0]);
        pythonInterpreter = new PythonInterpreter();
        PySystemState pySystemState =pythonInterpreter.getSystemState();
        System.out.println(pySystemState.path.toString());
        pySystemState.path.add("C:\\Users\\Byron Dong\\AppData\\Local\\Programs\\Python\\Python36-32\\DLLs");
        pySystemState.path.add("C:\\Users\\Byron Dong\\AppData\\Local\\Programs\\Python\\Python36-32\\Lib");
        pySystemState.path.add("C:\\Users\\Byron Dong\\AppData\\Local\\Programs\\Python\\Python36-32\\Lib\\site-packages");
        System.out.println(pySystemState.path.toString());
        pythonInterpreter.exec("import tushare as ts");
        pythonInterpreter.execfile(getClass().getClassLoader().getResource("python/CrawingHistory.py").getPath());
    }

    /**
     * 爬取前复权数据（指定的时间段）
     *
     * @param codes code列表
     * @param start 开始时间
     * @param end 结束时间
     * @param path 存放CSV的路径
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    public void crawHistoryFront(List<String> codes, LocalDate start,LocalDate end,String path){
       crawingHistory("_crawing_history_front",codes,start,end,path);
    }

    /**
     * 爬取股指前复权数据（指定的时间段）
     *
     * @param codes code列表
     * @param start 开始时间
     * @param end 结束时间
     * @param path 存放CSV的路径
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    public void crawHistoryFrontBase(List<String> codes, LocalDate start,LocalDate end,String path){
        crawingHistory("_crawing_history_front_base",codes,start,end,path);
    }

    /**
     * 爬取后复权数据（指定的时间段）
     *
     * @param codes code列表
     * @param start 开始时间
     * @param end 结束时间
     * @param path 存放CSV的路径
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    public void crawHistoryAfter(List<String> codes, LocalDate start,LocalDate end,String path){
        crawingHistory("_crawing_history_after",codes,start,end,path);
    }

    /**
     * 爬取股指后复权数据（指定的时间段）
     *
     * @param codes code列表
     * @param start 开始时间
     * @param end 结束时间
     * @param path 存放CSV的路径
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    public void crawHistoryAfterBase(List<String> codes, LocalDate start,LocalDate end,String path){
        crawingHistory("_crawing_history_after_base",codes,start,end,path);
    }

    /**
     * 关闭主控对象
     *
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    public void close(){
        pythonInterpreter.close();
    }

    /**
     * 爬取复权数据（指定的时间段）
     *
     * @param codes code列表
     * @param start 开始时间
     * @param end 结束时间
     * @param path 存放CSV的路径
     * @author ByronDong
     * @updateTime 2017/6/2
     */
    private void crawingHistory(String name,List<String> codes, LocalDate start,LocalDate end,String path){
        PyFunction pyFunction = (PyFunction)pythonInterpreter.get(name,PyFunction.class);
        pyFunction.__call__(new PyList(codes),new PyString(start.toString()),new PyString(end.toString()),new PyString(path));
    }
}
