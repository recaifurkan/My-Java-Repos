package com.vnetpublishing.java.suapp;

public interface ISuperUserApplication extends ISuperUserDetector, ISudo {

	int runAdministor(String[] args);
	int runNotAdministor();

}
