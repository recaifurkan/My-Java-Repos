package com.byrfb.gui.bindiablecomponents;


import com.byrfb.gui.BindiableComponent;
import com.byrfb.gui.ITextChangeable;

import javax.swing.*;

public class BindiableLabel extends BindiableComponent implements ITextChangeable {


    JLabel component;

    /**
     * gerekli açıklamlar BindiableButton sınıfnfda yazmakta
     */
    public BindiableLabel() {
        this.component = new JLabel();
        super.setBindinable(this);

    }


    @Override
    public void setText(String value) {
        getComponent().setText(value);
    }

    @Override
    public String getText() {
        return getComponent().getText();
    }

    @Override
    public JLabel getComponent() {
        return component;
    }


}
