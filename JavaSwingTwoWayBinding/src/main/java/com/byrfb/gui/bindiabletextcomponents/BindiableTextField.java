package com.byrfb.gui.bindiabletextcomponents;


import com.byrfb.gui.BindiableTextComponent;

import javax.swing.*;

public class BindiableTextField extends BindiableTextComponent {


    JTextField component;

    /**
     * buarada textfield oluştuturp setcomponent yapılarak settext gettext ve componenitin eklenmesi
     * yapılmakta
     */
    public BindiableTextField() {
        component = new JTextField();
        super.setComponent(component);
    }


}




