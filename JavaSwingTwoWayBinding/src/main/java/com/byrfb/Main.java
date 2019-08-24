/******************************************************************************
 * Copyright (c) 2009-2018, Barthelemy Dagenais and individual contributors.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * - The name of the author may not be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *****************************************************************************/
package com.byrfb;

import com.byrfb.gui.BindiableButton;
import com.byrfb.gui.BindiableLabel;
import com.byrfb.gui.BindiableTextField;
import com.byrfb.lang.types.BindiableString;


import javax.swing.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    BindiableString text;

    public BindiableString getValue() {
        return text;
    }

    public Main() {
        text = new BindiableString();
    }


    public <T> void getValue(T val) {
        List<Class> listClass = new ArrayList<>();
        listClass.add(String.class);
        listClass.add(Integer.class);
        listClass.add(Float.class);

        boolean isAvaliable = false;
        for (Class clazz : listClass) {
            if (clazz.equals(val.getClass())) {
                isAvaliable = true;
                break;
            }
        }
        if (isAvaliable) {
            System.out.println("uygun");
        } else
            System.out.println("değil");


    }

    public int addition(int first, int second) {
        return first + second;
    }

    public static InputStream getSystemIn() {
        final InputStream in = System.in;
        return in;
    }


    public static void main(String[] args) {
        Main app = new Main();
        JFrame frame = new JFrame();

        // custom text field
        BindiableTextField area = new BindiableTextField();
        area.bind(app.text);
        BindiableLabel area2 = new BindiableLabel();
        area2.bind(app.text);
        BindiableButton button = new BindiableButton();
        button.bind(app.text);


        JPanel panel = new JPanel();
        panel.setLayout(null);

        area.getComponent().setSize(200, 20);
        area2.getComponent().setBounds(200, 0, 200, 20);
        button.getComponent().setBounds(0, 200, 100, 100);
        panel.add(area.getComponent());
        panel.add(area2.getComponent());
        panel.add(button.getComponent());

        panel.setSize(400, 400);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        frame.setVisible(true);


        /*/

        for two eay binding
         */
        Scanner sc = new Scanner(System.in);
        while (true){
            app.text.setValue(sc.nextLine());
        }



    }

    public Main getObject() {
        return this;
    }

}
