package org.stockmaketprediction.controller;

import java.text.DecimalFormat;
import java.util.Collections;

import org.apache.xml.resolver.apps.resolver;
import org.stockmaketprediction.model.BiggestGainerStock;
import org.stockmaketprediction.model.BiggestLoserStockPredicted;

public class PredictionOverviewController {

	public BiggestGainerStock biggestGainerPredicted(){
		Double bigGain = Collections.max(WeightedPredictorController.biggestGainer);
		DecimalFormat df = new DecimalFormat("#.#####");
	    String predictAmountBGS = df.format(bigGain);
	    String predictAmountBGSCheck = predictAmountBGS+" %";
	   
		String cNameBGST = "";
		for (int i = 0; i < WeightedPredictorController.stockList.size(); i++) {
			if((WeightedPredictorController.stockList.get(i).getPredictAmount().equalsIgnoreCase(predictAmountBGSCheck))
					&& (WeightedPredictorController.stockList.get(i).getPrediction().equalsIgnoreCase("UP"))){
				cNameBGST = WeightedPredictorController.stockList.get(i).getCompName();
			}
		}
		
		BiggestGainerStock bgSt = new BiggestGainerStock();
		bgSt.setCompNameBGS(cNameBGST);
		bgSt.setPercentGainBGS(predictAmountBGSCheck);
		
		return bgSt;
 	}
	
	
	
	public BiggestLoserStockPredicted biggestLoserStockPredicted(){
		Double bigLoseStock = Collections.max(WeightedPredictorController.biggestLoserStock);
		DecimalFormat df = new DecimalFormat("#.#####");
	    String predictAmountBGS = df.format(bigLoseStock);
	    String predictAmountBGSCheck = predictAmountBGS+" %";
	   
		String cNameBGST = "";
		for (int i = 0; i < WeightedPredictorController.stockList.size(); i++) {
			if((WeightedPredictorController.stockList.get(i).getPredictAmount().equalsIgnoreCase(predictAmountBGSCheck))
					&& (WeightedPredictorController.stockList.get(i).getPrediction().equalsIgnoreCase("DOWN"))){
				cNameBGST = WeightedPredictorController.stockList.get(i).getCompName();
			}
		}
		
		BiggestLoserStockPredicted bgStLose = new BiggestLoserStockPredicted();
		bgStLose.setCompNameBGS(cNameBGST);
		bgStLose.setPercentLoserStockBL(predictAmountBGSCheck);
		
		return bgStLose;
 	}
	
	public String avgModelAccuracy(){
		double sum = 0.0;
		for (int i = 0; i < WeightedPredictorController.avgModelAuuracy.size(); i++) {
			sum += WeightedPredictorController.avgModelAuuracy.get(i);
		}
		
		double res = sum/WeightedPredictorController.avgModelAuuracy.size();
		DecimalFormat dfRes = new DecimalFormat("#.#########");
	    String resAvgAccu = dfRes.format(res);
		return resAvgAccu;
	}
	
}
