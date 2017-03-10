package presentation.controller;


import presentation.view.panel.MainPanel;
import presentation.view.panel.LoginPanel;

/**
 * Created by Harvey on 2017/3/9.
 * 所有Controller的父类，提供跳转的功能
 */
public class ViewController {

    public void Controller(){

    }

    public void switchView(String panelName){
        switch (panelName){
            case "loginPanel":
                MainPanel.getInstance().add(LoginPanel.getInstance());
        }
    }

}
