package com.byrfb.game.desktop;

import java.io.File;

public class ManifestCreator {
    public static void main(String[] args) {
        String libFolder = "libs/";
        File file = new File("F:\\Libraries\\Java\\libgdx\\html3DTest\\out\\artifacts\\desktop_main_jar");
        File[] files = file.listFiles();
        StringBuilder builde = new StringBuilder();
        for(File fil : files){
            if(fil.isFile()){
                builde.append(libFolder + fil.getName() + " ");
            }
        }
        System.out.println(builde.toString());
    }
}
