package com.byrfb.gui;

import com.byrfb.lang.DataChangeListener;
import com.byrfb.lang.types.AbstractBindiableData;
import com.byrfb.lang.types.BindiableString;

public abstract class BindiableTextComponent implements DataChangeListener  {

    BindiableString value;
    private ITextChangeable component;




    @Override
    public void bind(AbstractBindiableData data) {
        if (data instanceof BindiableString) {
            value = (BindiableString) data;
            value.addListener(this);
        } else {
            new Exception("Cannot solve Type of : " + data.getClass().getName());
        }

    }



    @Override
    public void onDataChange() {
        component.setText(value.getValue());

    }

    protected void setComponent(ITextChangeable bindiableTextArea) {
        this.component = bindiableTextArea;
    }
}
