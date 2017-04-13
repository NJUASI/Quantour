package presentation.view.panel;

import presentation.listener.navigationBarListener.CompareListener;
//import presentation.listener.navigationBarListener.FavoritesListener;
import presentation.listener.navigationBarListener.KStringListener;
import presentation.listener.navigationBarListener.StocksTableListener;
import presentation.listener.navigationBarListener.StrategyListener;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.customizedButton.MyCustomizedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
public class NavigationBar extends TemplatePanel {
    int numOfChoosed;
    MyCustomizedButton kString,compare,stock,strategy;
    private static NavigationBar navigationBar;

    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public NavigationBar() {
        numOfChoosed=1;
        setLayout(null);
        setBackground(ColorUtils.divideColor());
        setBounds(adaptScreen(0,0,90,1030));

        ImageIcon bgPicture= new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/logo3.png"));
        JLabel logo1 =new JLabel();
        bgPicture.setImage(bgPicture.getImage().getScaledInstance(80*width/1920, 80*height/1130, Image.SCALE_DEFAULT ));
        logo1.setIcon(bgPicture);
        logo1.setBounds(adaptScreen(5,5,80,80));
        add(logo1);

        //the door of function 4
        stock = new MyCustomizedButton("stock",1);
        stock.setBounds(adaptScreen(3, 100, 84, 110));
        stock.addMouseListener(new StocksTableListener());
        add(stock);

        stock.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(numOfChoosed!=1){
//                    favorites.click2();
                    numOfChoosed=1;
                    whileClicked(numOfChoosed);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(numOfChoosed!=1){
                    stock.moveIn();

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(numOfChoosed!=1){
                    stock.moveOut();
                }
            }
        });

        //the door of function 1
        kString = new MyCustomizedButton("kString",1);
        kString.setBounds(adaptScreen(3, 210, 84, 110));
        kString.addMouseListener(new KStringListener());
        add(kString);
        kString.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(numOfChoosed!=2){
//                    kString.click2();
                    numOfChoosed=2;
                    whileClicked(numOfChoosed);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(numOfChoosed!=2){
                    kString.moveIn();

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(numOfChoosed!=2){
                    kString.moveOut();
                }
            }
        });

        //the door of function 2
        compare = new MyCustomizedButton("compare",1);
        compare.setBounds(adaptScreen(3, 320, 84, 110));
        compare.addMouseListener(new CompareListener());
        add(compare);
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(numOfChoosed!=3){
//                    compare.click2();
                    numOfChoosed=3;
                    whileClicked(numOfChoosed);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(numOfChoosed!=3){
                    compare.moveIn();

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(numOfChoosed!=3){
                    compare.moveOut();
                }
            }
        });
        //the door of function 3
        strategy = new MyCustomizedButton("strategy",1);
        strategy.setBounds(adaptScreen(3, 430, 84, 110));
        strategy.addMouseListener(new StrategyListener());
        add(strategy);
        strategy.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(numOfChoosed!=4){
                    numOfChoosed=4;
                    whileClicked(numOfChoosed);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(numOfChoosed!=4){
                    strategy.moveIn();
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(numOfChoosed!=4){
                    strategy.moveOut();
                }
            }
        });


        //log out
//        JButton logout = new JButton("注销");
//        logout.setBounds(adaptScreen(90, 820, 70, 70));
//        logout.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                MainFrame.getCard().show(MainFrame.getCardPanel(), "loginPanel");
//                KStringPanel.getInstance().refresh();
//                ComparePanel.getInstance().refresh();
//                ThermometerPanel.getInstance().refresh();
//                StocksTablePanel.getInstance().refresh();
//            }
//        });
//        add(logout);
    }
    public static NavigationBar getInstance(){
        if(navigationBar==null){
            navigationBar=new NavigationBar();
        }
        return navigationBar;
    }
    public void whileClicked(int i){
        switch (i){
            case 1:
                stock.click2();
                kString.moveOut();
                strategy.moveOut();
                compare.moveOut();
                TitlePanel.getInstance().setTitle("行情");
                numOfChoosed=1;
                break;
            case 2:
                kString.click2();
                stock.moveOut();
                compare.moveOut();
                strategy.moveOut();
                TitlePanel.getInstance().setTitle("个股");
                numOfChoosed=2;
                break;
            case 3:
                stock.moveOut();
                kString.moveOut();
                compare.click2();
                strategy.moveOut();
                TitlePanel.getInstance().setTitle("对比");
                numOfChoosed=3;
                break;
            case 4:
                strategy.click2();
                stock.moveOut();
                kString.moveOut();
                compare.moveOut();
                TitlePanel.getInstance().setTitle("策略");
                numOfChoosed=4;

                break;

            default:
                break;
        }
    }
}

