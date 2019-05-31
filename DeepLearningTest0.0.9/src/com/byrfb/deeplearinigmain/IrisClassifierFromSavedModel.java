package com.byrfb.deeplearinigmain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class IrisClassifierFromSavedModel {

	private static Map<Integer, String> classifiers;

	public IrisClassifierFromSavedModel() {
		classifiers = new HashMap<>();
		classifiers.put(0, "Iris-setosa");
		classifiers.put(1, "Iris-versicolor");
		classifiers.put(2, "Iris-virginica");
	}

	public void classify(String irisDataTrainFile, String irisDataTestFile)
			throws FileNotFoundException, IOException, InterruptedException {

		int labelIndex = 4;
		int numClasses = 3;
//
		int batchSizeTraining = 147;

//
		int batchSizeTest = 3;
		DataSet testData = readCSVDataset(irisDataTestFile, batchSizeTest, labelIndex, numClasses);
//
		Map<Integer, Iris> flowers = objectify(testData);
//		
		File locationToSave = new File("IrisClassifier");
		MultiLayerNetwork model = loadModel(locationToSave);


		// evaluate the model on the test set
		Evaluation eval = new Evaluation(3);
		INDArray output = model.output(testData.getFeatureMatrix());
		
		eval.eval(testData.getLabels(), output);
		
		System.out.println(eval.stats());
		
		System.out.println(output);
		
		

        //Load the model
//        
		
		classify(output,flowers);
		
		flowers.forEach((k, v) -> System.out.println("Index:" + k + " -> " + v));

	}
	
	public MultiLayerNetwork loadModel(File locationToSave) throws IOException {
		return ModelSerializer.restoreMultiLayerNetwork(locationToSave);
	}
	
	public void saveModel(MultiLayerNetwork model) throws IOException {
//		Save the model
      File locationToSave = new File("IrisClassifier");      //Where to save the network. Note: the file is in .zip format - can be opened externally
      boolean saveUpdater = true;                                             //Updater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this if you want to train your network more in the future
      ModelSerializer.writeModel(model, locationToSave, saveUpdater);
	}

	public DataSet readCSVDataset(String csvFileClasspath, int batchSize, int labelIndex, int numClasses)
			throws IOException, InterruptedException {

		RecordReader rr = new CSVRecordReader();
		rr.initialize(new FileSplit(new ClassPathResource(csvFileClasspath).getFile()));
		DataSetIterator iterator = new RecordReaderDataSetIterator(rr, batchSize, labelIndex, numClasses);
		return iterator.next();
	}

	private Map<Integer, Iris> objectify(DataSet testData) {
		Map<Integer, Iris> iFlowers = new HashMap<>();
		INDArray features = testData.getFeatureMatrix();
		for (int i = 0; i < features.rows(); i++) {
			INDArray slice = features.slice(i);
			Iris irs = new Iris(slice.getDouble(0), slice.getDouble(1), slice.getDouble(2), slice.getDouble(3));
			iFlowers.put(i, irs);
		}
		return iFlowers;
	}

	private void classify(INDArray output, Map<Integer, Iris> flowers) {
		for (int i = 0; i < output.rows(); i++) {
			Iris irs = flowers.get(i);
			// set the classification from the fitted results
			irs.setIrisClass(classifiers.get(maxIndex(getFloatArrayFromSlice(output.slice(i)))));
		}
	}

	private float[] getFloatArrayFromSlice(INDArray rowSlice) {
		float[] result = new float[rowSlice.columns()];
		for (int i = 0; i < rowSlice.columns(); i++) {
			result[i] = rowSlice.getFloat(i);
		}
		return result;
	}

	private static int maxIndex(float[] vals) {
		int maxIndex = 0;
		for (int i = 1; i < vals.length; i++) {
			float newnumber = vals[i];
			if ((newnumber > vals[maxIndex])) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}

}
