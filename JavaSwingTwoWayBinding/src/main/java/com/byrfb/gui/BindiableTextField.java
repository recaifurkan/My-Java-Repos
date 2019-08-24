package com.byrfb.gui;


import javax.swing.*;

public class BindiableTextField extends BindiableTextComponent implements ITextChangeable {


    JTextField component;

    public BindiableTextField() {
        component = new JTextField();
        super.setComponent(this);
    }


    @Override
    public void setText(String value) {
        component.setText(value);
    }

    @Override
    public String getText() {
        return component.getText();
    }

    @Override
    public JComponent getComponent() {
        return component;
    }
}




