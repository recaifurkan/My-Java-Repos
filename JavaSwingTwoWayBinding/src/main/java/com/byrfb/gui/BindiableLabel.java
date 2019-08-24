package com.byrfb.gui;


import javax.swing.*;

public class BindiableLabel extends BindiableTextComponent implements ITextChangeable {


    JLabel component;

    public BindiableLabel() {
        this.component = new JLabel();
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
