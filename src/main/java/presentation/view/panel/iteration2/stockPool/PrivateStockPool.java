package presentation.view.panel.iteration2.stockPool;

import presentation.view.tools.WindowData;
import service.StockService;
import service.serviceImpl.StockService.StockServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.PrivateStockNotFoundException;
import vo.StockVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/4/19.
 */
public class PrivateStockPool {

    private StockService stockService;

    private List<StockVO> privatePool;

    private static PrivateStockPool privateStockPool;

    private PrivateStockPool() throws IOException, PrivateStockNotFoundException {
        stockService = new StockServiceImpl();
        privatePool = stockService.getPrivateStocks(IDReserve.getInstance().getUserID(), WindowData.getInstance().getDate());
    }

    public static PrivateStockPool getInstance() throws IOException, PrivateStockNotFoundException {
        if(privateStockPool == null){
            privateStockPool = new PrivateStockPool();
        }
        return privateStockPool;
    }

    public List<StockVO> getPrivatePool(){
        return privatePool;
    }

    public void remove(String code){
        for(StockVO stockVO : privatePool){
            if(stockVO.code.equals(code)){
                privatePool.remove(stockVO);
                break;
            }
        }
    }

    public void remove(List<String> codes){

        if(codes==null||codes.size()==0){return;}

        for(String code : codes){
            this.remove(code);
        }
    }
}
