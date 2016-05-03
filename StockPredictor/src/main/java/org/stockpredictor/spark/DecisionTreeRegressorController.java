package org.stockpredictor.spark;

import java.io.IOException;
import java.util.HashMap;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

public class DecisionTreeRegressorController {

	public static HashMap<String, String> decisionTreeHM = new HashMap<>();
	int i = 0;
//	String[] datapath = {"data//EOD-CSCO-labled.csv","data//EOD-FB-labled.csv",
//			"data//EOD-GOOGL-labled.csv", "data//EOD-IBM-labled.csv",
//			"data//EOD-INTU-labled.csv","data//EOD-MSFT.csv","data//EOD-VAR.csv"};
//	String[] compName = {"CISCO","FACEBOOK","GOOGLE", "IBM", "INTUIT",
//			"MICROSOFT", "VARIAN MEDICAL"};
	
	String[] datapath = {"data//EOD-CSCO-labled.csv","data//EOD-FB-labled.csv",
			"data//EOD-GOOGL-labled.csv", "data//EOD-IBM-labled.csv",
			"data//EOD-INTU-labled.csv","data//EOD-MSFT-labled.csv",};
	String[] compName = {"CISCO","FACEBOOK","GOOGLE", "IBM", "INTUIT", "MICROSOFT"};
	
	
	public static void main(String[] args) throws IOException {
		
		DecisionTreeRegressorController xy = new DecisionTreeRegressorController();
		xy.decisionTRCGetData();
		
		System.out.println(decisionTreeHM);
	}
	
	
	public void decisionTRCGetData() throws IOException{
	
		while(i< datapath.length) {
		//String compName = "GOOGLE";
	    //String datapath = "data//EOD-GOOGL-labled.csv";
	    
		DecisionTreeRegressor dtr = new DecisionTreeRegressor();
		String res = dtr.modelGenerator(compName[i], datapath[i]);
	    System.out.println("\n \n Printing res "+res); 
	    decisionTreeHM.put(compName[i], res);
	    i++;
		}
	}
	
	
}
