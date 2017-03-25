package presentation.view.panel.associatePanel;

import service.StockService;
import service.serviceImpl.StockServiceImpl;
import utilities.exceptions.MatchNothingException;
import vo.StockSearchVO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/3/16.
 */
public class SearchComboBoxModel extends DefaultComboBoxModel<StockSearchVO>{

    public SearchComboBoxModel(String searchString) {

        StockService stockService = new StockServiceImpl();

        List<StockSearchVO> stockSearchVOList = null;
        stockSearchVOList = stockService.searchStock(searchString);


        for(int i = 0; i < stockSearchVOList.size();i++) {
            this.addElement(stockSearchVOList.get(i));
        }

    }

    @Override
    public StockSearchVO getElementAt(int index) {
        return super.getElementAt(index);
    }
}
