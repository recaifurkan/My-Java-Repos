package com.byrfb.iris;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.transform.schema.Schema;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.io.ClassPathResource;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.byrfb.utils.Utils;

public class Iris {

	
	 private static Logger log = LoggerFactory.getLogger(Iris.class);
	public static void main(String[] args) throws IOException, InterruptedException {
		
		 int numLinesToSkip = 1;
	        char delimiter = ',';
	        RecordReader recordReader = new CSVRecordReader(numLinesToSkip,delimiter);
	        recordReader.initialize(
			new FileSplit(
					new ClassPathResource("/resource/iris.csv").getFile()));
	        
	        Schema inputDataSchema = new Schema.Builder()	        	  
	        	    .addColumnsInteger("sepal_length","sepal_width","petal_length","petal_width")
	        	    .addColumnString("species")
	        	    .build();
	        log.info(inputDataSchema.toString());

	        
	        int labelIndex = 4;     //5 values in each row of the iris.txt CSV: 4 input features followed by an integer label (class) index. Labels are the 5th value (index 4) in each row
	        int numClasses = 3;     //3 classes (types of iris flowers) in the iris data set. Classes have integer values 0, 1 or 2
	        int batchSize = 150;    //Iris data set: 150 examples total. We are loading all of them into one DataSet (not recommended for large data sets)
	    	double fractionTrain = 0.70;
	        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader,batchSize,labelIndex,numClasses);
	        DataSet allData = iterator.next();
	        allData.shuffle();

	SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(fractionTrain); //Use 65% of data for training
	DataSet trainingData = testAndTrain.getTrain();
    DataSet testData = testAndTrain.getTest();

    //We need to normalize our data. We'll use NormalizeStandardize (which gives us mean 0, unit variance):
    DataNormalization normalizer = new NormalizerStandardize();
    normalizer.fit(trainingData);           //Collect the statistics (mean/stdev) from the training data. This does not modify the input data
    normalizer.transform(trainingData);     //Apply normalization to the training data
    normalizer.transform(testData);         //Apply normalization to the test data. This is using statistics calculated from the *training* set

    
   
    long seed = 10;
    final int numInputs = 4;
    int ara1 = 4;
    int ara2 = 3;
    int outputNum = 3;

    log.info("Build model....");
    MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
        .seed(seed)
        .activation(Activation.SIGMOID)
        .weightInit(WeightInit.XAVIER)
        .updater(new Sgd(0.25))
        .l2(0.001)
        .list()
        .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(ara1)
            .build())
        .layer(1, new DenseLayer.Builder().nIn(ara1).nOut(ara2)
            .build())
        .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
            .activation(Activation.SOFTMAX)
            .nIn(ara2).nOut(outputNum).build())
        .backprop(true).pretrain(false)
        .build();

    //run the model
    MultiLayerNetwork model = new MultiLayerNetwork(conf);
    model.init();
    model.setListeners(new ScoreIterationListener(100));

    int epoch = 5000;
	for(int i=0; i<epoch; i++ ) {
        model.fit(trainingData);
        Evaluation eval = new Evaluation(3);
        INDArray output = model.output(testData.getFeatures());
        eval.eval(testData.getLabels(), output);
    log.info(eval.stats());
    }
	
	Map<String, INDArray> paramTable = model.paramTable();
    Set<String> keys = paramTable.keySet();
    Iterator<String> it = keys.iterator();

    while (it.hasNext()) {
        String key = it.next();
        INDArray values = paramTable.get(key);
        System.out.print(key+" ");//print keys
        System.out.println(Arrays.toString(values.shape()));//print shape of INDArray
        System.out.println(values);
//        model.setParam(key, Nd4j.rand(values.shape()));//set some random values
    }
	
	Utils.saveModel(model, new File("iris.zip"));

    //evaluate the model on the test set
   


	}

}
