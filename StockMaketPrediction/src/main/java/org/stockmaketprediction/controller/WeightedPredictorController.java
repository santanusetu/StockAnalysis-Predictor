package org.stockmaketprediction.controller;

import java.awt.List;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.stockmaketprediction.model.StockObject;
import org.stockmaketprediction.util.InputCSVFileWatcher;

import scala.collection.immutable.HashMap;

public class WeightedPredictorController {

	//public void weightedPrediction(){
	//	public static void main(String[] args) throws Exception{
	static ArrayList<StockObject> stockList = new ArrayList<>();
	
	static ArrayList<Double> biggestGainer = new ArrayList<>();
	static ArrayList<Double> biggestLoserStock = new ArrayList<>();
	
	static ArrayList<Double> avgModelAuuracy = new ArrayList<>();
	
	public void weightedPrediction() throws Exception{
			//ArrayList<StockObject> stockList = new ArrayList<>();
			
			DecisionTreeRegressorController xy = new DecisionTreeRegressorController();
			xy.decisionTRCGetData();
			
			RandomForestPredictorController rf = new RandomForestPredictorController();
			rf.randomForestCalculation();
			
			
		System.out.println("\n \n @@@@@ DECISION TREE HM "+DecisionTreeRegressorController.decisionTreeHM);
		System.out.println("\n \n @@@@@ RandomForestPredictor HM "+RandomForestPredictorController.randomForestHM);
		
		InputCSVFileWatcher icsvFW = new InputCSVFileWatcher();
		
		String[] compNameDT = new String[icsvFW.getCompNameDTR().length];
		String[] compValueDT = new String[icsvFW.getCompNameDTR().length];
		
		int i = 0;
		Iterator<String> itrDTR = DecisionTreeRegressorController.decisionTreeHM.keySet().iterator();
	    while(itrDTR.hasNext()) {
	        String key=(String)itrDTR.next();
	        String value= DecisionTreeRegressorController.decisionTreeHM.get(key);
	        System.out.println(key + " " + value);
	        
	         compNameDT[i] = key;
	         compValueDT[i] = value;
	         i++;
	    }
	    
	    String[] compNameRF = new String[icsvFW.getCompNameRF().length];
		String[] valueRF = new String[icsvFW.getFileNameRF().length];
		
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
			System.out.println("\n @@@@ compName[i] "+compNameDT[k] + "  DTcompValue[i] "+ compValueDT[k]);
			System.out.println("\n @@@@ compNameRF[j] "+compNameRF[k] + "  valueRF[j] "+ valueRF[k]);
			String[] splitData = valueRF[k].split(" ");
		
			
	        double pa = Double.parseDouble(compValueDT[k]);      
	        DecimalFormat df = new DecimalFormat("#.#####");
	        String predictAmount = df.format(pa);
			
	        double modelaccurecy = Double.parseDouble(splitData[2]);
	       
	        avgModelAuuracy.add(modelaccurecy);
	        
	        DecimalFormat dfmodelaccu = new DecimalFormat("#.#########");
	       // String modelAccu = splitData[2].substring(0);
	        String modelAccu = dfmodelaccu.format(modelaccurecy);
	        
			StockObject so = new StockObject();
	    	so.setCompName(compNameDT[k]);
	    	so.setPrediction(splitData[0]);
	    	so.setPredictAmount(predictAmount+" %");
	    	so.setAccuracy(modelAccu);
	    	
	    	if(splitData[0].equalsIgnoreCase("UP"))
		        biggestGainer.add(pa);
	    	
	    	if(splitData[0].equalsIgnoreCase("DOWN"))
		        biggestLoserStock.add(pa);
	    	
	    	
	    	stockList.add(so);
		}
	    
	}
}
