package com.byrfb.utils;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;

public class Utils {
	
	public static void saveModel(MultiLayerNetwork model,File locationToSave) throws IOException {
//		Save the model
           //Where to save the network. Note: the file is in .zip format - can be opened externally
      boolean saveUpdater = true;                                             //Updater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this if you want to train your network more in the future
      ModelSerializer.writeModel(model, locationToSave, saveUpdater);
	}
	
	public static File openFileChooser() {
		JFileChooser chooser = new JFileChooser(new File("C:\\Users\\ByRfb\\Desktop\\sayýlar"));
		int ret = chooser.showOpenDialog(null);
		if(ret == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			return file;
		}
		return null;
	}

}
