package presentation.view.tools;

import vo.StockSearchVO;

import javax.swing.*;

/**
 * Created by Harvey on 2017/3/18.
 */
public class SearchPopupMenu extends JPopupMenu{

    //自动补全列表
    JList list;

    ListModel<StockSearchVO> listModel;

    public SearchPopupMenu(){

        list = new JList(new Object[]{"1231231232","132132456","4654564","123","456","123","789","779"});
        list.setLocation(140,45);
        list.setSize(200,400);
        add(list);
        setSize(300,400);
    }

}
