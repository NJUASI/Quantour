package presentation.view.tools;

import java.awt.datatransfer.StringSelection;

/**
 * Created by Harvey on 2017/4/19.
 */
public class DraggedTrasferable extends StringSelection {
    /**
     * Creates a <code>Transferable</code> capable of transferring
     * the specified <code>String</code>.
     *
     * @param data
     */
    public DraggedTrasferable(String data) {
        super(data);
    }

    public DraggedTrasferable(String code, String name){
        super(code+","+name);
    }
}
