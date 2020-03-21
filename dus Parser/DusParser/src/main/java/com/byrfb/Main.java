package com.byrfb;

import com.byrfb.objects.Kontenjan;
import com.byrfb.objects.KontenjanBolum;
import com.byrfb.objects.SortCriteria;
import com.byrfb.parser.ParserMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ParserMain app = new ParserMain();
        app.read();
        System.out.println("Sıralanmasını istediğiniz bölğmğ seçiniz.");
        System.out.println("Sayı Numurasını girebilirsiniz");
        KontenjanBolum[] values = KontenjanBolum.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(i + "-" + values[i].getText());
        }
        Scanner sc = new Scanner(System.in);
        int secilenIndex = sc.nextInt();

        app.setBolum(values[secilenIndex]);
        app.bookParse();
        ArrayList<Kontenjan> reserved = app.reserve();

        System.out.println("Sıralanmasını istediğiniz şekli seçiniz.");
        SortCriteria[] criters = SortCriteria.values();
        for (int i = 0; i < criters.length; i++) {
            System.out.println(i + "-" + criters[i].getText());
        }
        int secilenSortIndex = sc.nextInt();
        Kontenjan.sortCriteria = criters[secilenSortIndex];
        reserved.sort(null);

        app.save(reserved);


    }
}
