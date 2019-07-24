package com.byrfb.administor;

import java.util.logging.Logger;

import com.byrfb.administor.linux.LinuxSudo;
import com.byrfb.administor.mac.MacSudo;
import com.byrfb.administor.mac.MacSuperUserDetector;
import com.byrfb.administor.posix.PosixSudo;
import com.byrfb.administor.posix.PosixSuperUserDetector;
import com.byrfb.administor.win.WinSudo;
import com.byrfb.administor.win.WinSuperUserDetector;

public abstract class SuperUserApplication 
 implements ISuperUserApplication 
{
	private final static Logger logger = Logger.getLogger(SuperUserApplication.class.getName());
	
	public final int sudo(String[] args) 
	{
		String os = SUDO.getOS();
		
		ISudo sudo = null;
		
		if ("windows".equals(os)) {
			sudo = new WinSudo();
		} else if ("linux".equals(os)) {
			sudo = new LinuxSudo();
		} else if ("mac".equals(os)) {
			sudo = new MacSudo();
		} else {
			logger.warning(String.format("Unsupported platform '%s, falling back to posix'",os));
			sudo = new PosixSudo();
		}
		
		return sudo.sudo(args);
	}
	
	public final int sudo() 
	{
		return sudo(new String[]{});
	}
	
	public final boolean isSuperUser() 
	{
		
		String os = SUDO.getOS();
		
		ISuperUserDetector detector = null;
		
		if ("windows".equals(os)) {
			detector = new WinSuperUserDetector();
		} else if ("linux".equals(os)) {
			detector = new PosixSuperUserDetector();
		} else if ("mac".equals(os)) {
			detector = new MacSuperUserDetector();
		}
		
		if (null == detector) {
			throw new IllegalStateException(String.format("Unsupported operating system: %s",os));
		}
		
		return detector.isSuperUser();
	}
}
