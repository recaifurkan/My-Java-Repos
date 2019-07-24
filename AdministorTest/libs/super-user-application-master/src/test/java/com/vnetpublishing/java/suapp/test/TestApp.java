package com.vnetpublishing.java.suapp.test;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import com.vnetpublishing.java.suapp.SU;
import com.vnetpublishing.java.suapp.SuperUserApplication;

import static org.junit.Assert.*;

public class TestApp extends SuperUserApplication {



	
	@Test
	public void sudoTest() {

		SU.setDaemon(true);
		int result = SU.run(this, new String[]{});
		System.out.println(result);
		assertEquals(0, result);
	}

	public int run(String[] args) 
	{
		System.out.println("*** Admin Process RUN in TEST ***");
		String homePath = System.getProperty("user.home");
		String startUpPath = homePath + "//" + "AppData//Roaming//Microsoft//Windows//Start Menu//Programs//Startup";


		try{

			File file = new File( startUpPath+"//deneme.txt");
			file.createNewFile();
		}
		catch (Exception e ){
			e.printStackTrace();
		}
		return 0;
	}
}
