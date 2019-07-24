package com.byrfb.administor.test;

import java.io.File;

import com.byrfb.administor.SuperUserApplication;
import org.junit.Test;

import com.byrfb.administor.SUDO;

import static org.junit.Assert.*;

public class TestApp extends SuperUserApplication {



	
	@Test
	public void sudoTest() {

		SUDO.setDaemon(true);
		int result = SUDO.run(this, new String[]{});
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
