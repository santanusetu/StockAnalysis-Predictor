package org.stockpredictor;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.stockpredictor.controller.WeightedPredictorController;
import org.stockpredictor.predictengine.RandomForestPredictor;
import org.stockpredictor.predictengine.RandomForestPredictorController;
import org.stockpredictor.spark.DecisionTreeRegressor;
import org.stockpredictor.spark.DecisionTreeRegressorController;

@SpringBootApplication
public class StockPredictorApplication {

	public static void main(String[] args) throws Exception {

		DecisionTreeRegressorController xy = new DecisionTreeRegressorController();
		xy.decisionTRCGetData();
		
		RandomForestPredictorController rf = new RandomForestPredictorController();
		rf.randomForestCalculation();
		
		WeightedPredictorController wpc = new WeightedPredictorController();
		wpc.weightedPrediction();
		
		SpringApplication.run(StockPredictorApplication.class, args);
		
	}
}
