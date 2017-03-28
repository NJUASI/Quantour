package presentation.view.panel.iteration2;

import presentation.view.panel.TemplatePanel;

/**
 * Created by day on 17/3zx/28.
 */
public class AnalysePanel extends TemplatePanel {
    private static AnalysePanel analysePanel;
    private AnalysePanel(){

    }

    public static AnalysePanel getInstance(){
        if(analysePanel==null){
            analysePanel=new AnalysePanel();
        }
        return analysePanel;
    }
}
