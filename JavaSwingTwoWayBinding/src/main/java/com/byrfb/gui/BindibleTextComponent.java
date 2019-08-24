package com.byrfb.gui;

import com.byrfb.lang.DataChangeListener;
import com.byrfb.lang.types.BindiableString;

import javax.swing.text.JTextComponent;

public abstract class BindibleTextComponent extends JTextComponent implements DataChangeListener {

    BindiableString value;

    public void bind(BindiableString data){
        value = data;
        value.addListener(this);

    }

    @Override
    public void onDataChange() {
        setText(value.getValue());

    }
}
