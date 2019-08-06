package com.byrfb.program;

public class Utils {
    public static float StringToFloat(String value){

        return isNone(value) ? 0:
                Float.parseFloat(value.replace(",", "."));
    }

    public static boolean isNone(String value){
        return value.equals("---");
    }
}
