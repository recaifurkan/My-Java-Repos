package com.byrfb.gui;

import com.byrfb.lang.DataChangeListener;
import com.byrfb.lang.types.AbstractBindiableData;
import com.byrfb.lang.types.BindiableString;

import javax.swing.*;

/*
JComponent sınınfı settext ve gettext metodu içermediği için bunları için ayrı sınıf oluşturulmuştur
implemente eden yerde settext ve gettext metotları bulunm alı

 */
public abstract class BindiableComponent implements DataChangeListener, ITextChangeable {

    BindiableString value;
    protected ITextChangeable component;

    public void bind(AbstractBindiableData data) {
        if (data instanceof BindiableString) {
            value = (BindiableString) data;
            value.addListener(this);
        } else {
            new Exception("Cannot solve Type of : " + data.getClass().getName());
        }

    }

    /*
    Bind edilen değer eğer değiştirlmişse burdaki metod çalıır

     */
    @Override
    public void onDataChange() {
        component.setText(value.getValue());

    }

    /**
     * değeri değiştilebilir alan buraya gönderilmekte
     * @param bindiableTextArea
     */
    protected void setBindinable(ITextChangeable bindiableTextArea) {
        this.component = bindiableTextArea;
    }

    /**
     * java swinge eklenmesi için componentin get edilmesi gerekmekte
     * @return
     */

    @Override
    public JComponent getComponent() {
        return component.getComponent();
    }
}
