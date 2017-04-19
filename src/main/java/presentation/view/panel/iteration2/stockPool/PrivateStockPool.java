package presentation.view.panel.iteration2.stockPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Byron Dong on 2017/4/19.
 */
public class PrivateStockPool {

    private Map<String ,String> privatePool;

    private static PrivateStockPool privateStockPool;

    private PrivateStockPool()  {
        privatePool = new TreeMap<>();
    }

    public static PrivateStockPool getInstance(){
        if(privateStockPool == null){
            privateStockPool = new PrivateStockPool();
        }
        return privateStockPool;
    }

    public Map<String, String> getPrivatePool() {
        return privatePool;
    }

    public void remove(String code){

        privatePool.remove(code);
    }

    public void add(String code, String name){
        privatePool.put(code,name);
    }

    public void add(Map<String, String> map){
        privatePool.putAll(map);
    }

    public void remove(Map<String, String> map){
        for(Map.Entry<String, String> entry : map.entrySet()){
            privatePool.remove(entry.getKey());
        }
    }

    public List<String> getPrivatePoolCodes() {
        List<String> codes = new ArrayList<>();
        for(Map.Entry<String, String> entry : privatePool.entrySet()){
            codes.add(entry.getKey());
        }
        return codes;
    }
}
