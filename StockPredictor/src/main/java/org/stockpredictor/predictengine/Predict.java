package org.stockpredictor.predictengine;

import java.io.File;
import java.io.IOException;
import java.util.List;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.classifiers.Classifier;
import weka.core.Instance;


public class Predict {

	//public static void main(String[] args) throws Exception {

	String resToReturn = "";
	
	public String predictRandomForest() throws Exception{
		//String filename = "data//szzs_new.csv";
		//String filename = "data//300431.csv";
		String filename = "data//EOD-AAPL.csv";

		List<List<Double>> price_list = Price.getPriceInFileByDay(filename);
		int[] label = RiseFallLabel.getLabelList(price_list);

		double[][] data_feature = Feature.simpleFeature(price_list);
		String weka_file_content = getDataSet(data_feature, label);
		String weka_file_name = "data//feature//szzs_simple120_3_day.arff";

		ReadWriteFile.writeFile(weka_file_name, weka_file_content);
		// Read data randomly divided into a training set , testing set

		File file = new File(weka_file_name);
		ArffLoader loader = new ArffLoader();
		loader.setFile(file);

		Instances ins = loader.getDataSet();
		ins.setClassIndex(ins.numAttributes() - 1);

		Instances train = new Instances(ins);
		train.delete();

		Instances test = new Instances(ins);
		test.delete();

		int data_size = ins.numInstances();
		for (int i = 0; i < data_size; i++) {
			if (Math.random() < 0.80)
				train.add(ins.instance(i));
			else
				test.add(ins.instance(i));
		}

		
		
		Classifier random = Classifiers.random_forest(train);
		int test_num = test.numInstances();
		int predict_true_count = 0;

		for (int i = 0; i < test_num; i++) {
			Instance test_instance = test.instance(i);
			int real_label = (int) test_instance.classValue();
			double class_value = random.classifyInstance(test_instance);
			
			int predict_result = (int) class_value;
			if (predict_result == real_label)
				predict_true_count++;
		}

		double accuracy = (double) predict_true_count / test_num;
		System.out.println("predict_true_count： " + predict_true_count + "\n Total forecast： " + test_num + "\n Accuracy： " + accuracy);

		resToReturn += "predict_true_count： " + predict_true_count + "\n Total forecast： " + test_num + "\n Accuracy： " + accuracy;
		/*
		 * Predict the future three days
		 */

		random = Classifiers.random_forest(ins);// Throughout the training data set

		double[] current = getCurrentInstance(price_list);

		test = new Instances(ins);

		test.delete();

		Instance test_instance = new Instance(1.0, current);

		test.add(test_instance);

		double class_value = random.classifyInstance(test.instance(0));

		int predict_result = (int) class_value;

		if (predict_result == 1){
			resToReturn += "\n @@@@@@@ Have the opportunity to Go Up";
			System.out.println("@@@@@@@ Have the opportunity to Go Up");
		}
		else{
			resToReturn += "\n @@@@@@@ No Chance of Going Up";
			System.out.println("@@@@@@@ No Chance of Going Up");
		}
		return resToReturn;
		
//		Instance test_instance_svm = new Instance(1.0, current);
//		//Santanu -- new try
//		int predict_true_count_svm = 0;
//		Classifier rj = Classifiers.SVM(train);
//		for (int i = 0; i < test_num; i++) {
//			//Instance test_instance_svm = test.instance(i);
//
//			int real_label_svm = (int) test_instance_svm.classValue();
//
//			double class_value_svm = rj.classifyInstance(test_instance);
//
//			int predict_result_svm = (int) class_value_svm;
//
//			if (predict_result_svm == real_label_svm)
//				predict_true_count_svm++;
//		}
//		
//		double accuracy_svm = (double) predict_true_count_svm / test_num;
//		System.out.println("predict_true_count_svm ： " + predict_true_count_svm + "\n Total forecast： " + test_num + "\n Accuracy SVM： " + accuracy_svm);

	}

	/**
	 * Configuration data set , as a string , write file
	 * 
	 * @param feature
	 *            Data features
	 * @param label
	 *            datalabel
	 * @return
	 */
	public static String getDataSet(double[][] feature, int[] label) {

		StringBuilder sb = new StringBuilder("@relation Stocks\n");

		for (int i = 0; i < feature[0].length; i++)
			sb.append("@attribute feature_" + i + " numeric\n");
		sb.append("@attribute 'class' {0, 1}\n@data\n");

		for (int i = 0; i < feature.length; i++) {

			for (int j = 0; j < feature[0].length; j++) {
				sb.append(feature[i][j] + ",");
			}
			sb.append(label[i] + "\n");
		}

		return sb.toString();
	}

	/**
	 * Returns the last 50 days the information to predict future
	 * 
	 * @param price_list
	 * @param dim
	 * @return
	 */
	public static double[] getCurrentInstance(List<List<Double>> price_list) {

		int list_size = price_list.size();

		double[] instance = new double[50 * 5 + 1];

		for (int j = 0; j < 50; j++) {

			List<Double> price = price_list.get(list_size - 1 - j);

			int price_kind = price.size();

			for (int k = 0; k < price_kind; k++) {

				instance[5 * j + k] = price.get(k);

			}

		}

		instance[250] = 0;

		return instance;
	}

}
