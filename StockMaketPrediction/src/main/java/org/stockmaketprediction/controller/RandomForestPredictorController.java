package org.stockmaketprediction.controller;

import java.util.HashMap;

import org.stockmaketprediction.predictengine.RandomForestPredictor;
import org.stockmaketprediction.util.InputCSVFileWatcher;

public class RandomForestPredictorController {
	public static HashMap<String, String> randomForestHM = new HashMap<>();
	int i = 0;
	int j = 0;
	// static String[] fileNameRF = {"data//EOD-CSCO.csv","data//EOD-FB.csv",
	// "data//EOD-GOOGL.csv", "data//EOD-IBM.csv", "data//EOD-INTU.csv",
	// "data//EOD-MSFT.csv","data//EOD-VAR.csv"};
	// static String[] compNameRF = {"CISCO","FACEBOOK","GOOGLE", "IBM",
	// "INTUIT",
	// "MICROSOFT", "VARIAN MEDICAL"};

//	public static String[] fileNameRF = { "data//EOD-CSCO.csv", "data//EOD-FB.csv", "data//EOD-GOOGL.csv",
//			"data//EOD-IBM.csv", "data//EOD-INTU.csv", "data//EOD-MSFT.csv" };
//	public static String[] compNameRF = { "CISCO", "FACEBOOK", "GOOGLE", "IBM", "INTUIT", "MICROSOFT" };

	// public static void main(String[] args) throws Exception {
	//
	// RandomForestPredictor rfp = new RandomForestPredictor();
	// rfp.predictRandomForest(compNameRF[1], fileNameRF[1]);
	//
	// System.out.println(randomForestHM);
	// }

	public void randomForestCalculation() throws Exception {
		
		InputCSVFileWatcher icsFileWatcher = new InputCSVFileWatcher();
		String[] compNameRF = icsFileWatcher.getCompNameRF();
		String[] fileNameRF = icsFileWatcher.getFileNameRF();
		
		while (j < fileNameRF.length) {
			RandomForestPredictor rfp = new RandomForestPredictor();
			String resRF = rfp.predictRandomForest(compNameRF[j], fileNameRF[j]);
			System.out.println("\n \n Printing res " + resRF);
			randomForestHM.put(compNameRF[j], resRF);
			j++;
		}

		System.out.println(randomForestHM);
	}
}
