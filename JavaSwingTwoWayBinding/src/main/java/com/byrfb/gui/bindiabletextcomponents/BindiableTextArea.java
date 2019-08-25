package com.byrfb.gui.bindiabletextcomponents;

import com.byrfb.gui.BindiableTextComponent;

import javax.swing.*;

public class BindiableTextArea extends BindiableTextComponent {


    JTextArea component;


    public BindiableTextArea() {
        component = new JTextArea();
        super.setComponent(component);
    }


}
