package presentation.view.panel.iteration2.stockPool;

import presentation.view.tools.WindowData;
import service.StockService;
import service.serviceImpl.StockService.StockServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.PrivatePoolIsNullException;
import utilities.exceptions.PrivateStockNotFoundException;
import vo.StockVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/4/19.
 */
public class PrivateStockPool {

    private Map<String ,String> privatePool;

    private static PrivateStockPool privateStockPool;

    private PrivateStockPool() throws IOException, PrivateStockNotFoundException {
        privatePool = new HashMap<>();
        privatePool.put("000001","1231");
    }

    public static PrivateStockPool getInstance() throws IOException, PrivateStockNotFoundException {
        if(privateStockPool == null){
            privateStockPool = new PrivateStockPool();
        }
        return privateStockPool;
    }

    public Map<String, String> getPrivatePool() throws PrivatePoolIsNullException {
        if(privatePool.size()==0){
            throw new PrivatePoolIsNullException();
        }

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
