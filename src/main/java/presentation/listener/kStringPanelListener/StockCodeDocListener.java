package presentation.listener.kStringPanelListener;

import presentation.controller.KStringPanelController;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.IOException;

/**
 * Created by Harvey on 2017/3/26.
 */
public class StockCodeDocListener implements DocumentListener {

    /**
     * Gives notification that there was an insert into the document.  The
     * range given by the DocumentEvent bounds the freshly inserted region.
     *
     * @param e the document event
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        callController();
    }

    /**
     * Gives notification that a portion of the document has been
     * removed.  The range is given in terms of what the view last
     * saw (that is, before updating sticky positions).
     *
     * @param e the document event
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        callController();
    }

    /**
     * Gives notification that an attribute or set of attributes changed.
     *
     * @param e the document event
     */
    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    private void callController(){
        try {
            KStringPanelController.getInstance().stockCodeChange();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
