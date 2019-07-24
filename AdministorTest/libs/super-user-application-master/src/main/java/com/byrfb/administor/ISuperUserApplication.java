package com.byrfb.administor;

public interface ISuperUserApplication extends ISuperUserDetector, ISudo {

	int runAdministor(String[] args);
	int runNotAdministor();

}
