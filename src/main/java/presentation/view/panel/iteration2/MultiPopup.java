package presentation.view.panel.iteration2;

import presentation.view.tools.ColorUtils;
import presentation.view.tools.component.MyButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;

public class MultiPopup extends JPopupMenu {

    private List<ActionListener> listeners = new ArrayList<ActionListener>();

    private Object[] values;

    private Object[] defaultValues;

    private List<JCheckBox> checkBoxList = new ArrayList<JCheckBox>();

    private JButton commitButton;

    private JButton cancelButton;

    public static final String COMMIT_EVENT = "commit";

    public static final String CANCEL_EVENT = "cancel";

    public MultiPopup(Object[] value, Object[] defaultValue) {
        super();
        values = value;
        defaultValues = defaultValue;
        initComponent();
    }

    public void addActionListener(ActionListener listener) {
        if (!listeners.contains(listener))
            listeners.add(listener);
    }


    private void initComponent() {

        JPanel checkboxPane = new JPanel();
        JPanel buttonPane = new JPanel();

        this.setLayout(new BorderLayout());

        for (Object v : values) {
            JCheckBox temp = new JCheckBox(v.toString(), selected(v));
            checkBoxList.add(temp);
        }

        if (checkBoxList.get(0).getText().equals("全部"))
            checkBoxList.get(0).addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (checkBoxList.get(0).isSelected())//Select All 被选中
                    {
                        //检查其他的是否被选中乳沟没有就选中他们
                        for (int i = 1; i < checkBoxList.size(); i++) {
                            if (!checkBoxList.get(i).isSelected())
                                checkBoxList.get(i).setSelected(true);
                        }
                    } else {
                        for (int i = 1; i < checkBoxList.size(); i++) {
                            if (checkBoxList.get(i).isSelected())
                                checkBoxList.get(i).setSelected(false);
                        }
                    }
                }
            });


        checkboxPane.setLayout(new GridLayout(checkBoxList.size(), 1, 3, 3));
        for (JCheckBox box : checkBoxList) {
            checkboxPane.add(box);
        }

        commitButton = new MyButton("确定");

        commitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                commit();
            }

        });

        cancelButton = new MyButton("取消");

        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cancel();
            }

        });

        buttonPane.add(commitButton);

        buttonPane.add(cancelButton);

        this.add(checkboxPane, BorderLayout.CENTER);

        this.add(buttonPane, BorderLayout.SOUTH);

    }

    private boolean selected(Object v) {
        for (Object dv : defaultValues) {
            if (dv.equals(v)) {
                return true;
            }
        }
        return false;
    }

    protected void fireActionPerformed(ActionEvent e) {
        for (ActionListener l : listeners) {
            l.actionPerformed(e);
        }
    }

    public Object[] getSelectedValues() {
        List<Object> selectedValues = new ArrayList<Object>();

        if (checkBoxList.get(0).getText().equals("全部")) {
            if (checkBoxList.get(0).isSelected()) {
                for (int i = 1; i < checkBoxList.size(); i++) {
                    selectedValues.add(values[i]);
                }
            } else {
                for (int i = 1; i < checkBoxList.size(); i++) {

                    if (checkBoxList.get(i).isSelected())
                        selectedValues.add(values[i]);
                }
            }
        } else
            for (int i = 0; i < checkBoxList.size(); i++) {

                if (checkBoxList.get(i).isSelected())
                    selectedValues.add(values[i]);
            }


        return selectedValues.toArray(new Object[selectedValues.size()]);
    }

    public void setDefaultValue(Object[] defaultValue) {
        defaultValues = defaultValue;

    }

    public void commit() {
        fireActionPerformed(new ActionEvent(this, 0, COMMIT_EVENT));
    }

    public void cancel() {
        fireActionPerformed(new ActionEvent(this, 0, CANCEL_EVENT));
    }

}