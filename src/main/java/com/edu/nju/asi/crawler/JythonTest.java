package com.edu.nju.asi.crawler;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Byron Dong on 2017/5/26.
 */
public class JythonTest {

    public static void main(String[] args) {
        System.out.println("------------start python--------------------");
        Properties properties = new Properties();
        properties.put("python.console.encoding", "UTF-8");
        properties.put("python.security.respectJavaAccessibility", "false");
        properties.put("python.import.site", "false");
        Properties pre = System.getProperties();

        PythonInterpreter.initialize(pre,properties,new String[0]);
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        System.out.println("------------------test1 -------------------");
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("HelloWorld.py").getPath());
        pythonInterpreter.execfile("D:/Python_Workspace/HelloWorld.py");
        System.out.println("------------------Hello end -------------------");

        PyFunction function = (PyFunction)pythonInterpreter.get("test1",PyFunction.class);
        List<String> list =  new ArrayList<>();
        list.add("Dong");
        list.add("Gong");
        list.add("Feng");
        list.add("Ling");
        PyObject pyObject = function.__call__(new PyList(list));
        double result = pyObject.asDouble();
        String ret = "a+b = "+pyObject.toString();
        System.out.println(ret);
        System.out.println("number:"+result);
        System.out.println("-----------------func end---------------------");
        pythonInterpreter.close();
        System.out.println("------------end python--------------------");
    }

}
