package org.stockpredictor.predictengine;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class Classifiers {

	/**
	 * training Logistic Regression Classifier
	 * 
	 * @param train Training set
	 * @return Trained classifier
	 * 
	 * @throws Exception
	 */
	public static Logistic logistic_regression(Instances train) throws Exception {

		System.out.println("Logistic Regression Training.......");

		Logistic logistic = new Logistic();

		logistic.setRidge(0.8);

		logistic.buildClassifier(train);

		System.out.println("Logistic Regression Training End.......");

		return logistic;

	}

	/**
	 * training SVM Classifier
	 * 
	 * @param train Training set
	 * @return Trained classifier
	 * @throws Exception
	 */
	public static LibSVM SVM(Instances train) throws Exception {

		System.out.println("SVM Training.......");

		LibSVM libsvm = new LibSVM();

		libsvm.setCost(100);

		libsvm.buildClassifier(train);

		System.out.println("SVM Training End.......");

		return libsvm;

	}

	/**
	 * Random Forest classifier training
	 * 
	 * @param train Training set
	 * @return Trained classifier
	 * @throws Exception
	 */
	public static RandomForest random_forest(Instances train) throws Exception {

		System.out.println("RandomForest Training.......");

		RandomForest forest = new RandomForest();

		forest.setNumTrees(100);

		// forest.setMaxDepth(10);

		forest.buildClassifier(train);

		System.out.println("RandomForest Training End.......");

		return forest;

	}

	/**
	 * Training naive Bayes classifier
	 * 
	 * @param train Training set
	 * @return Trained classifier
	 * @throws Exception
	 */
	public static NaiveBayes naive_bayes(Instances train) throws Exception {

		System.out.println("Naive Bayes Training.......");

		NaiveBayes bayes = new NaiveBayes();

		bayes.buildClassifier(train);

		System.out.println("Naive Bayes Training End.......");

		return bayes;

	}

	/**
	 * Training BP neural network classifier
	 * 
	 * @param train Training set
	 * @return Trained classifier
	 * @throws Exception
	 */
	public static MultilayerPerceptron BP(Instances train) throws Exception {

		System.out.println("BP Training.......");

		MultilayerPerceptron mlp = new MultilayerPerceptron();

		mlp.buildClassifier(train);

		System.out.println("BP Training End.......");

		return mlp;
	}

	/**
	 * Training C4.5 decision tree classifier
	 * 
	 * @param train Training set
	 * @return Trained classifier
	 * @throws Exception
	 */
	public static J48 decision_tree(Instances train) throws Exception {

		System.out.println("C4.5 Training.......");

		J48 tree = new J48();

		tree.buildClassifier(train);

		System.out.println("C4.5 Training End.......");

		return tree;
	}

	/**
	 * Training Adaboost combination classification
	 * 
	 * @param train Training set
	 *            
	 * @return Trained classifier
	 * @throws Exception
	 */
	public static AdaBoostM1 Ada_boost(Instances train) throws Exception {

		System.out.println("Adaboost Training.......");

		AdaBoostM1 boost = new AdaBoostM1();

		boost.buildClassifier(train);

		System.out.println("Adaboost Training End.......");

		return boost;

	}

}

