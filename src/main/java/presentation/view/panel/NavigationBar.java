package presentation.view.panel;

import presentation.listener.navigationBarListener.CompareListener;
//import presentation.listener.navigationBarListener.FavoritesListener;
import presentation.listener.navigationBarListener.KStringListener;
import presentation.listener.navigationBarListener.StocksTableListener;
import presentation.listener.navigationBarListener.ThermometerListener;
import presentation.view.tools.customizedButton.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
public class NavigationBar extends TemplatePanel {
    int numOfChoosed;
    MyButton kString,compare,thermometer,favorites;
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
        setBackground(new Color(19,22,24));
        setBounds(adaptScreen(0,0,100,1030));

        ImageIcon bgPicture= new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/logo2.png"));
        JLabel logo1 =new JLabel();
        bgPicture.setImage(bgPicture.getImage().getScaledInstance(90*width/1920, 90*height/1130, Image.SCALE_DEFAULT ));
        logo1.setIcon(bgPicture);
        logo1.setBounds(adaptScreen(5,5,90,90));
        add(logo1);

        //the door of function 4
        favorites = new MyButton("stock",1);
        favorites.setBounds(adaptScreen(3, 100, 94, 120));
        favorites.addMouseListener(new StocksTableListener());
        add(favorites);

        favorites.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(numOfChoosed!=1){
//                    favorites.click2();
                    numOfChoosed=1;
                    whileClicked(numOfChoosed);
                    TitlePanel.getInstance().setTitle("行情");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(numOfChoosed!=1){
                    favorites.moveIn();

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(numOfChoosed!=1){
                    favorites.moveOut();
                }
            }
        });

        //the door of function 1
        kString = new MyButton("kString",1);
        kString.setBounds(adaptScreen(3, 220, 94, 120));
        kString.addMouseListener(new KStringListener());
        add(kString);
        kString.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(numOfChoosed!=2){
//                    kString.click2();
                    numOfChoosed=2;
                    whileClicked(numOfChoosed);
                    TitlePanel.getInstance().setTitle("个股");
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
        compare = new MyButton("compare",1);
        compare.setBounds(adaptScreen(3, 340, 94, 120));
        compare.addMouseListener(new CompareListener());
        add(compare);
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(numOfChoosed!=3){
//                    compare.click2();
                    numOfChoosed=3;
                    whileClicked(numOfChoosed);
                    TitlePanel.getInstance().setTitle("对比");
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
        thermometer = new MyButton("market",1);
        thermometer.setBounds(adaptScreen(3, 460, 94, 120));
        thermometer.addMouseListener(new ThermometerListener());
        add(thermometer);
        thermometer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(numOfChoosed!=4){
//                    thermometer.click2();
                    numOfChoosed=4;
                    whileClicked(numOfChoosed);
                    TitlePanel.getInstance().setTitle("市场");
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(numOfChoosed!=4){
                    thermometer.moveIn();
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(numOfChoosed!=4){
                    thermometer.moveOut();
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
                favorites.click2();
                kString.moveOut();
                compare.moveOut();
                thermometer.moveOut();
                TitlePanel.getInstance().setTitle("行情");
                numOfChoosed=1;
                break;
            case 2:
                kString.click2();
                favorites.moveOut();
                compare.moveOut();
                thermometer.moveOut();
                TitlePanel.getInstance().setTitle("个股");
                numOfChoosed=2;
                break;
            case 3:
                favorites.moveOut();
                kString.moveOut();
                compare.click2();
                thermometer.moveOut();
                TitlePanel.getInstance().setTitle("对比");
                numOfChoosed=3;
                break;
            case 4:
                thermometer.click2();
                favorites.moveOut();
                kString.moveOut();
                compare.moveOut();
                TitlePanel.getInstance().setTitle("市场");
                numOfChoosed=4;

                break;
            default:
                break;
        }
    }
}

