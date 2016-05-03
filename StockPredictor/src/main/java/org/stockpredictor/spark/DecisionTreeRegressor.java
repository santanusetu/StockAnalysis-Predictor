package org.stockpredictor.spark;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import scala.Tuple2;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DecisionTreeRegressor implements Serializable {
	
	
	public String modelGenerator(String cpName, String datapath) throws IOException
	{
		SparkConf conf = new SparkConf().setAppName("JavaDecisionTreeRegressionExample").setMaster("local[1]");
		JavaSparkContext sc = new JavaSparkContext(conf);
		 
		//String datapath = "H:\\CMPE239\\formattedDataSet\\EOD-GOOGL-labled.csv";
		
		JavaRDD<String> data = sc.textFile(datapath);
	    JavaRDD<LabeledPoint> parsedData = data
	            .map(new Function<String, LabeledPoint>() {
	                public LabeledPoint call(String line) {

	                    String[] features = line.split(",");
	                    double[] v = new double[features.length-1];
	                    for (int i = 1; i < features.length; i++)
	                    {
	                        v[i-1] = Double.valueOf(features[i]);
	                    }
	                    return new LabeledPoint( Double.valueOf(features[0]),
	                            Vectors.dense(v));
	                }
	            });
	    JavaRDD<LabeledPoint>[] splits = parsedData.randomSplit(new double[]{0.7, 0.3});
		JavaRDD<LabeledPoint> trainingData = splits[0];
		JavaRDD<LabeledPoint> testData = splits[1];
		
		// Set parameters.
		// Empty categoricalFeaturesInfo indicates all features are continuous.
		Map<Integer, Integer> categoricalFeaturesInfo = new HashMap<Integer, Integer>();
		String impurity = "variance";
		Integer maxDepth = 5;
		Integer maxBins = 32;

		// Train a DecisionTree model.
		final DecisionTreeModel model = DecisionTree.trainRegressor(trainingData,
		  categoricalFeaturesInfo, impurity, maxDepth, maxBins);

		// Evaluate model on test instances and compute test error
		JavaPairRDD<Double, Double> predictionAndLabel =
		  testData.mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
		  
		  public Tuple2<Double, Double> call(LabeledPoint p) {
		    return new Tuple2<Double, Double>(model.predict(p.features()), p.label());
		  }
		});
		Double testMSE =
		  predictionAndLabel.map(new Function<Tuple2<Double, Double>, Double>() {
		  
		    public Double call(Tuple2<Double, Double> pl) {
		      Double diff = pl._1() - pl._2();
		      return diff * diff;
		    }
		  }).reduce(new Function2<Double, Double, Double>() {
		   
		    public Double call(Double a, Double b) {
		      return a + b;
		    }
		  }) / data.count();
		int a=0;
		
		String returnRes = "";
		System.out.println("Test Mean Squared Error: " + testMSE);
		returnRes = "Test Mean Squared Error: " + testMSE.toString() +"\n";
		
		System.out.println("Learned regression tree model:\n" + model.toDebugString());
		returnRes += "\n Learned regression tree model: " + model.toDebugString();
		
		//double[] result = {20160429,704.12,712.11,703.78,707.88,0,1,704.12,712.11,703.78,707.88,2913898};
	    
		//Vector features = Vectors.dense(result);
		Vector features = Vectors.dense(fileLineReader(datapath));
		System.out.println(model.predict(features));
		
		returnRes += " \n model.predict(features) "+ model.predict(features) +"\n";
		
		sc.stop();
		
		System.out.println("RESULT DTR "+returnRes);
		//return model;
		//return returnRes;
		return ""+model.predict(features);
	}
	

	// reading the files
	public double [] fileLineReader(String filePath) throws IOException
	{
		double [] result;
		String line= Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8).get(0);
		String [] parts =line.split(",");
		int length =parts.length;
		result=new double[length-1];
			for (int i=1;i<length;i++) 
			{
		        result[i-1]=Double.valueOf(parts[i]);
		    }
	
		return result;
		
	}


}

