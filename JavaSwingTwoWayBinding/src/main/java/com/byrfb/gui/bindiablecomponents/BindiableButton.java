package com.byrfb.gui.bindiablecomponents;


import com.byrfb.gui.BindiableComponent;
import com.byrfb.gui.ITextChangeable;

import javax.swing.*;

public class BindiableButton extends BindiableComponent implements ITextChangeable {


    JButton component;

    /**
     * burada buton newlwnip
     * settext ve gettext metodları Jcomponentte olmadı için settezxt ve gettex
     * değerleri bu sııfta değiştiriliğ işlem görülmekte
     */
    public BindiableButton() {
        this.component = new JButton();
        super.setBindinable(this);
    }


    @Override
    public JButton getComponent() {
        return component;
    }

    @Override
    public String getText() {
        return getComponent().getText();
    }

    @Override
    public void setText(String value) {
        getComponent().setText(value);
    }


}

