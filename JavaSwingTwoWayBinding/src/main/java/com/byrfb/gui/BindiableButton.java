package com.byrfb.gui;

import javax.swing.*;

public class BindiableButton extends  BindiableTextComponent implements ITextChangeable {


    JButton component;

    public BindiableButton(){
        this.component = new JButton();
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
