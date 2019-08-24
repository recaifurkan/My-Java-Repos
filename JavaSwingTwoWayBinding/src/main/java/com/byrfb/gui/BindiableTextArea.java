package com.byrfb.gui;

import javax.swing.*;

public class BindiableTextArea extends BindiableTextComponent implements ITextChangeable {


    JTextArea component;


    public BindiableTextArea() {
        component = new JTextArea();
        super.setComponent(this);
    }

    public JTextArea getComponent() {
        return component;
    }


    @Override
    public void setText(String value) {
        component.setText(value);

    }

    @Override
    public String getText() {
        return component.getText();
    }
}
