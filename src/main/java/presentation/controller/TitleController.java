package presentation.controller;

import presentation.view.panel.TitlePanel;

/**
 * Created by Harvey on 2017/3/18.
 */
public class TitleController {

    private TitlePanel titlePanel;

    private static TitleController titleController = new TitleController();

    public TitleController() {
        this.titlePanel = TitlePanel.getInstance();
    }


    public static TitleController getInstance(){
        return titleController;
    }

    public void search() {
    }
}
