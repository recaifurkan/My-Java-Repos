package com.byrfb.deeplearinigmain;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DeepLearningApp  {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		IrisClassifierNewTrained classifier = new IrisClassifierNewTrained();
		classifier.classify("/resources/iris.csv","/resources/iris-test.csv");
		
		
		
		
		
	}

	
}
