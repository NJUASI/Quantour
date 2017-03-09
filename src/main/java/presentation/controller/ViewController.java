package presentation.controller;


import presentation.view.MainPanel;

/**
 * Created by Harvey on 2017/3/9.
 * 所有Controller的父类，提供跳转的功能
 */
public class ViewController {

    public void Controller(){

    }

    public void switchView(String panelName){
        MainPanel.getCard().show(MainPanel.getCardPanel(), panelName);
    }

}
