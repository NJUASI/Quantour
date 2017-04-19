package presentation.view.panel.iteration2.stockPool;

import presentation.view.tools.WindowData;
import service.StockService;
import service.serviceImpl.StockService.StockServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.PrivatePoolIsNullException;
import utilities.exceptions.PrivateStockNotFoundException;
import vo.StockVO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/4/19.
 */
public class PrivateStockPool {

    private Map<String ,String> privatePool;

    private static PrivateStockPool privateStockPool;

    private PrivateStockPool()  {
        privatePool = new HashMap<>();
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

    public void add(Map<String,String> map){
      privatePool.putAll(map);
    }

    public void remove(Map<String, String> map){

        if(map==null){return;}

        if(map.size()==0){return;}

        for(String code:map.keySet()){
            privatePool.remove(code,map.get(code));
        }
    }
}
