package org.stockpredictor.controller;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.stockpredictor.model.StockObject;
import org.stockpredictor.predictengine.RandomForestPredictor;
import org.stockpredictor.predictengine.RandomForestPredictorController;
import org.stockpredictor.spark.DecisionTreeRegressorController;

import scala.collection.immutable.HashMap;

public class WeightedPredictorController {

	//public void weightedPrediction(){
	//	public static void main(String[] args) throws Exception{
	static ArrayList<StockObject> stockList = new ArrayList<>();
	
	public void weightedPrediction() throws Exception{
			//ArrayList<StockObject> stockList = new ArrayList<>();
			
			DecisionTreeRegressorController xy = new DecisionTreeRegressorController();
			xy.decisionTRCGetData();
			
			RandomForestPredictorController rf = new RandomForestPredictorController();
			rf.randomForestCalculation();
			
			
		System.out.println("\n \n @@@@@ DECISION TREE HM "+DecisionTreeRegressorController.decisionTreeHM);
		System.out.println("\n \n @@@@@ RandomForestPredictor HM "+RandomForestPredictorController.randomForestHM);
		
		String[] compName = new String[RandomForestPredictorController.fileNameRF.length];
		String[] DTcompValue = new String[RandomForestPredictorController.fileNameRF.length];
		
		int i = 0;
		Iterator<String> itrDTR = DecisionTreeRegressorController.decisionTreeHM.keySet().iterator();
	    while(itrDTR.hasNext()) {
	        String key=(String)itrDTR.next();
	        String value= DecisionTreeRegressorController.decisionTreeHM.get(key);
	        System.out.println(key + " " + value);
	        
	         compName[i] = key;
	         DTcompValue[i] = value;
	         i++;
	    }
	    
	    String[] compNameRF = new String[RandomForestPredictorController.fileNameRF.length];
		String[] valueRF = new String[RandomForestPredictorController.fileNameRF.length];
		
	    int j = 0;
		Iterator<String> itrRF = RandomForestPredictorController.randomForestHM.keySet().iterator();
	    while(itrRF.hasNext()) {
	        String key=(String)itrRF.next();
	        String value= RandomForestPredictorController.randomForestHM.get(key);
	        System.out.println(key + " " + value);
	        
	         compNameRF[j] = key;
	         valueRF[j] = value;
	         j++;
	    }
	   
	    for (int k = 0; k < valueRF.length; k++) {
			System.out.println("\n @@@@ compName[i] "+compName[k] + "  DTcompValue[i] "+ DTcompValue[k]);
			System.out.println("\n @@@@ compNameRF[j] "+compNameRF[k] + "  valueRF[j] "+ valueRF[k]);
			String[] splitData = valueRF[k].split(" ");
			
			StockObject so = new StockObject();
	    	so.setCompName(compName[k]);
	    	so.setPrediction(splitData[0]);
	    	so.setPredictAmount(DTcompValue[k]);
	    	so.setAccuracy(splitData[2]);
	    	
	    	stockList.add(so);
		}
	    
	}
}
