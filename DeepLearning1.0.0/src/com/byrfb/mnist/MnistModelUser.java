package com.byrfb.mnist;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;

import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.factory.Nd4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MnistModelUser {
	
	public static Logger log = LoggerFactory.getLogger(MnistModelUser.class);
	
	static List<Integer> labelList = Arrays.asList(2,3,7,1,6,4,0,5,8,9);

	public static void main(String[] args) throws IOException {
	
		
		
		
		log.info("Model Loading");
		MultiLayerNetwork model = loadModel(new File("mnist.zip"));
		model.init();
		//alttaki agirlikari ve bias deðerlerini  verir

	    Map<String, INDArray> paramTable = model.paramTable();
	    Set<String> keys = paramTable.keySet();
	    Iterator<String> it = keys.iterator();

	    while (it.hasNext()) {
	        String key = it.next();
	        INDArray values = paramTable.get(key);
	        System.out.print(key+" ");//print keys
	        System.out.println(Arrays.toString(values.shape()));//print shape of INDArray
	        System.out.println(values);
//	        model.setParam(key, Nd4j.rand(values.shape()));//set some random values
	    }
		
//		File fileChoose = openFileChooser();
		log.info("Image Loading");
		testEt(model);
		
		
		
		
	}
	
	
	public static void testEt(MultiLayerNetwork model) throws IOException {
		
		int width =28;
		int height = 28;
		int channels = 1;
	
		log.info("Image Loading");
		NativeImageLoader loader = new NativeImageLoader(width,height,channels);
		File resimlerFile = new File("C:\\Users\\ByRfb\\Desktop\\sayýlar");
		File resimler[] = resimlerFile.listFiles();
		
		for(File resim : resimler) {
			
			INDArray image = loader.asMatrix(resim);
			
			DataNormalization scaler = new ImagePreProcessingScaler(0,1);
			scaler.transform(image);
//			log.info(image.toString());
			
			INDArray output = model.output(image);
			
			log.info(output.toString());
			log.info(labelList.toString());
			log.info(resim.getName());
		}
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
	
	public static MultiLayerNetwork loadModel(File locationToSave) throws IOException {
		return ModelSerializer.restoreMultiLayerNetwork(locationToSave);
	}

}
