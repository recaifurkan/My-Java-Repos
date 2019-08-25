package com.byrfb.gui;

import javax.swing.text.JTextComponent;
/*

bu kend içerisinde set text ve gettext içerdiği için component olarak direk swing gönderilmekte
 */
public abstract class BindiableTextComponent extends BindiableComponent  {


    private JTextComponent component;

    public BindiableTextComponent(){
        super.setBindinable(this);
    }


    @Override
    public void onDataChange() {
        setText(value.getValue());

    }

    protected void setComponent(JTextComponent bindiableTextArea) {
        this.component = bindiableTextArea;
    }

    @Override
    public String getText() {
        return component.getText();
    }

    @Override
    public void setText(String value) {
        component.setText(value);
    }

    public JTextComponent getComponent() {
        return component;
    }
}
