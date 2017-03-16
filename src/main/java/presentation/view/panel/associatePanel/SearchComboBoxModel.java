package presentation.view.panel.associatePanel;

import service.StockService;
import service.serviceImpl.StockServiceImpl;
import utilities.exceptions.MatchNothingException;
import vo.StockSearchVO;

import javax.swing.*;
import java.util.List;

/**
 * Created by Harvey on 2017/3/16.
 */
public class SearchComboBoxModel extends DefaultComboBoxModel<StockSearchVO>{

    public SearchComboBoxModel(String searchString){

        StockService stockService = new StockServiceImpl();

        List<StockSearchVO> stockSearchVOList = null;
        try {
            stockSearchVOList = stockService.searchStock(searchString);
        } catch (MatchNothingException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < stockSearchVOList.size();i++){
            this.addElement(stockSearchVOList.get(i));
        }
    }

}
