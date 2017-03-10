package presentation.view.toos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/10.
 */
public class FavoritesTable extends JScrollPane {
    private JTable jTable;
    WindowData windowData;
    int width;
    int height;

    public FavoritesTable() {
        windowData = WindowData.getInstance();
        width = windowData.getWidth();
        height =windowData.getHeight();
//        Iterator<StockVO> stockList=stockService.getPrivateStocks(IDReserve.getInstance().getUserID(), LocalDate.now());
//        int index=0;
//        while(stockList.hasNext()){
//            index++;
//        }
//        Object[] columnNames = {"记录编号", "开盘指数", "最高指数",
//                "最低指数", "收盘指数", "成交量", "复权后的收盘指数", "股票代码","股票名称","昨日收盘指数","昨日复权收盘指数"};
//        Object[][] rowData=new Object[index][];
//        for(int j=0;stockList.hasNext();j++) {
//            rowDate[j][0]=stockList.next().serial;
//            rowDate[j][1]=stockList.next().open;
//            rowDate[j][2]=stockList.next().high;
//            rowDate[j][3]=stockList.next().low;
//            rowDate[j][4]=stockList.next().close;
//            rowDate[j][5]=stockList.next().volume;
//            rowDate[j][6]=stockList.next().adjClose;
//            rowDate[j][7]=stockList.next().code;
//            rowDate[j][8]=stockList.next().preClose;
//            rowDate[j][9]=stockList.next().preAdjClose;
        //for test
        Object[] columnNames = {"姓名", "性别", "家庭地址",//列名最好用final修饰
                "电话号码", "生日", "工作", "收入", "婚姻状况", "恋爱状况"};

        Object[][] rowData = {
                {"e331", "男", "江苏南京", "1378313210", "03/24/1985", "学生", "寄生中", "未婚", "没"},
                {"eee", "女", "江苏南京", "13645181705", "xx/xx/1985", "家教", "未知", "未婚", "好象没"},
                {"fff", "男", "江苏南京", "13585331486", "12/08/1985", "汽车推销员", "不确定", "未婚", "有"},
                {"ggg", "女", "江苏南京", "81513779", "xx/xx/1986", "宾馆服务员", "确定但未知", "未婚", "有"},
                {"hhh", "男", "江苏南京", "13651545936", "xx/xx/1985", "学生", "流放中", "未婚", "无数次分手后没有"}
        };
//        setLayout(null);
        setSize(1400*width/1920, 800*height/1030);
        //设置不可编辑
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable = new JTable(model);
        jTable.setBounds(0, 0, 1400*width/1920, 900*height/1030);

//            jTable.setRowHeight (30);//设置每行的高度为30
//            jTable.setRowMargin (5);//设置相邻两行单元格的距离
//        table.removeColumn(table.getColumnModel().getColumn(columnIndex));// columnIndex是要删除的列序号
        jTable.setRowSelectionAllowed(true);//设置可否被选择
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.setSelectionBackground(Color.white);//设置所选择行的背景色
        jTable.setSelectionForeground(Color.red);//设置所选择行的前景色
        jTable.setShowVerticalLines (true);//是否显示垂直的网格线

        jTable.setRowSelectionInterval(0,0);
        jTable.setGridColor(Color.blue);
        jTable.getSelectedColumn();
        jTable.setBackground(Color.lightGray);
        setViewportView(jTable);
    }
    public String getCode(){
        String cellValue=(String) jTable.getValueAt(jTable.getSelectedRow(), 5);// 取单元格数据,row是行号,column是列号
        return cellValue;
    }
}