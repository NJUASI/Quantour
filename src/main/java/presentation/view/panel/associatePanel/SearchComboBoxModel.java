package presentation.view.panel.associatePanel;

import service.StockService;
import service.serviceImpl.StockService.StockServiceImpl;
import vo.StockSearchVO;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by Harvey on 2017/3/16.
 */
public class SearchComboBoxModel extends DefaultComboBoxModel<StockSearchVO>{

    public SearchComboBoxModel(String searchString) throws IOException {

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
