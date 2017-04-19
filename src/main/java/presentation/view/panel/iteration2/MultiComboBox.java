package presentation.view.panel.iteration2;


import presentation.view.panel.TemplatePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.WindowData;
import presentation.view.tools.component.MyButton;
import presentation.view.tools.component.MyTextField;
import presentation.view.tools.customizedButton.MyCustomizedButton;
import utilities.enums.BlockType;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.plaf.basic.*;

public class MultiComboBox extends TemplatePanel {

    private Object[] values;

    public Object[] defaultValues;

    private List<ActionListener> listeners = new ArrayList<ActionListener>();

    private MultiPopup popup;

    private JTextField editor;

    protected MyCustomizedButton arrowButton;

    private String valueSperator;

    private static final String DEFAULT_VALUE_SPERATOR = ",";

    public MultiComboBox(Object[] value, Object[] defaultValue) {
        this(value, defaultValue, DEFAULT_VALUE_SPERATOR);
    }

    public MultiComboBox(Object[] value, Object[] defaultValue, String valueSperator) {
        values = value;
        defaultValues = defaultValue;
        this.valueSperator = valueSperator;
        initComponent();
    }

    private void initComponent() {
        //暂时使用该布局,后续自己写个布局
        setLayout(null);
        setBackground(new Color(32,36,39));


        popup = new MultiPopup(values, defaultValues);
        popup.addActionListener(new PopupAction());

        editor = new MyTextField();
        editor.setEditable(false);
        editor.setBounds(adaptScreen(0, 0, 195, 35));
        editor.addMouseListener(new EditorHandler());

        arrowButton = new MyCustomizedButton("arrowBt",2);
        arrowButton.setBounds(adaptScreen(195, 0, 35, 35));
        arrowButton.addMouseListener(new EditorHandler());
        add(editor);
        add(arrowButton);
        setText();

        JLabel lb = new JLabel();
        lb.setBounds(adaptScreen(0, 0, 240, 40));
        lb.setBackground(WindowData.getInstance().getColor());
        lb.setOpaque(true);
        add(lb);


    }

    public List<BlockType> getSelectedValues() {
        Object[] block = popup.getSelectedValues();
        List<BlockType> blockTypes = new ArrayList<>();
        if (block.length != 0) {
            for (int i = 0; i < block.length; i++) {
                if (block[i].equals("中小板")) {
                    blockTypes.add(BlockType.ZXB);
                } else if (block[i].equals("主板")) {
                    blockTypes.add(BlockType.ZB);
                } else if (block[i].equals("创业板")) {
                    blockTypes.add(BlockType.CYB);
                }
            }
        }
        return blockTypes;
    }

    protected void fireActionPerformed(ActionEvent e) {
        for (ActionListener l : listeners) {
            l.actionPerformed(e);
        }
    }

    private class PopupAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals(MultiPopup.CANCEL_EVENT)) {

            } else if (e.getActionCommand().equals(MultiPopup.COMMIT_EVENT)) {
                defaultValues = popup.getSelectedValues();
                setText();
                //把事件继续传递出去
                fireActionPerformed(e);
            }

            togglePopup();


        }

    }

    private void togglePopup() {
        if (popup.isVisible()) {
            popup.setVisible(false);
        } else {
            popup.setDefaultValue(defaultValues);
            popup.show(this, 0, getHeight());
        }
    }

    private void setText() {
        StringBuilder builder = new StringBuilder();
        for (Object dv : defaultValues) {
            builder.append(dv);
            builder.append(valueSperator);
        }

        editor.setText(builder.substring(0, builder.length() > 0 ? builder.length() - 1 : 0).toString());
    }

    private class EditorHandler implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            togglePopup();
        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

    }

    public void paintComponent(Graphics g) {
        g.setColor(ColorUtils.backgroundColor());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}