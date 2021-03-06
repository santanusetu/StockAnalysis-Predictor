package org.stockmaketprediction.controller;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.stockmaketprediction.model.StockHistory;
import org.stockmaketprediction.predictengine.Predict;


@RestController
public class StockMarketPredictorRestEndPointProvider {

	/**
	 * REST END POINT for stock data
	 * 
	 * @return JSON of STOCK DATA 
	 * { "compName": "GOOGLE", "prediction": "UP", "predictAmount": "0.52893 %","accuracy": "0.59154929" }
	 * 
	 */
	@RequestMapping(value="/v1/api/stockdata", method=  RequestMethod.GET)
	public Object stockData(){
		System.out.println("@@@@@@@ Request for STICKDATA api ");
		return WeightedPredictorController.stockList; 
	}
	
	
	@RequestMapping(value="/v1/api/biggestgainer", method=  RequestMethod.GET)
	public Object biggestGainer(){
		System.out.println("@@@@@@@ Accessing BIGGEST GAINER api ");
		PredictionOverviewController poc = new PredictionOverviewController();
		return poc.biggestGainerPredicted(); 
	}
	
	@RequestMapping(value="/v1/api/biggestloser", method=  RequestMethod.GET)
	public Object biggestLoserStock(){
		System.out.println("@@@@@@@ Accessing BIGGEST Loser Stock api ");
		PredictionOverviewController poc = new PredictionOverviewController();
		return poc.biggestLoserStockPredicted(); 
	}
	
	@RequestMapping(value="/v1/api/avgmodelaccuracy", method=  RequestMethod.GET)
	public Object averageModelAccuracy(){
		System.out.println("@@@@@@@ Accessing Average Model Accuracy api ");
		PredictionOverviewController poc = new PredictionOverviewController();
		return poc.avgModelAccuracy(); 
	}
	

	
	
	@RequestMapping(value="/v1/api/stockoverview", method=  RequestMethod.GET)
	public Object stockOverview(){
		return DecisionTreeRegressorController.decisionTreeHM;
	}
	
	@RequestMapping(value="/v1/api/stockoverviewrandomforest", method=  RequestMethod.GET)
	public Object stockOverviewRandomForest(){
		return RandomForestPredictorController.randomForestHM;
	}
	
	@RequestMapping(value="/v1/api/stockoverviewaggregate", method=  RequestMethod.GET)
	public Object stockOverviewAggregate(){
		
		return RandomForestPredictorController.randomForestHM;
	}
	
	
	
	
	
	@RequestMapping(value = "/v1/api/stockhistory", method ={ RequestMethod.GET})
	public Object History(@RequestParam(value="company", defaultValue="GOOGL") String company) throws IOException
	{
		//data//feature//szzs_simple120_3_day.arff
		String path="data//EOD-"+company+"-labled.csv";
		List<String> lines= Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
		int length  =lines.size()>366?366:lines.size();
		
		StockHistory[] st =new StockHistory[length]; 
		for (int i=0;i<length;i++) 
		{
			st[i] =new StockHistory(); 
			st[i].date= Integer.parseInt (lines.get(i).split(",")[1]);
			st[i].value= Double.valueOf(lines.get(i).split(",")[2]);
		}
		return st;
	}
	
	
	
	// Decision Tress Regresson Results
	@RequestMapping(value="dtregressor", method= RequestMethod.GET)
	public String dtregressor(){
//		String datapath = "data//EOD-GOOGL-labled.csv";
//		
//		DecisionTreeRegressor dtr = new DecisionTreeRegressor();
//		//DecisionTreeModel model =dtr.modelGenerator(datapath);
//		String res = dtr.modelGenerator(datapath);
//		
//		double[] result = {20160429,704.12,712.11,703.78,707.88,0,1,704.12,712.11,703.78,707.88,2913898};
//	    Vector features = Vectors.dense(result);
//		//System.out.println(model.predict(features));
//	    System.out.println("\n \n Printing res "+res);
		//return res;
		return null;
	}
	
	//Random Forest Algorithm Return Results
	@RequestMapping(value="randomforest", method= RequestMethod.GET)
	public String randomforest() throws Exception{
		Predict pdt = new Predict();
		String resRF = pdt.predictRandomForest();
		return resRF;
	}	
	
}

