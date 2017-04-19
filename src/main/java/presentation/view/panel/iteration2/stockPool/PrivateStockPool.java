package presentation.view.panel.iteration2.stockPool;

import presentation.view.tools.WindowData;
import service.StockService;
import service.serviceImpl.StockService.StockServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.PrivatePoolIsNullException;
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
}
