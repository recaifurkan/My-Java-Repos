package com.byrfb;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Tests {

    @Test
    public void getHomePath(){
        String homePath = System.getProperty("user.home");
        System.out.println(homePath);
    }

    @Test
    public void getStartUpFolderPath(){
        String startUpPathath = "AppData//Roaming//Microsoft//Windows//Start Menu//Programs//Startup";
    }
    @Test
    public void getJarPath() throws UnsupportedEncodingException {
        String jarPath = AdministorTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(jarPath, "UTF-8");}

    /*


     */
}
