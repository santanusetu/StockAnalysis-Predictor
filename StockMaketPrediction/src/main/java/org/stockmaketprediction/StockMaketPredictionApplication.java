package org.stockmaketprediction;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.stockmaketprediction.controller.DecisionTreeRegressorController;
import org.stockmaketprediction.controller.RandomForestPredictorController;
import org.stockmaketprediction.controller.WeightedPredictorController;

@SpringBootApplication
public class StockMaketPredictionApplication {

	public static void main(String[] args) throws Exception {
		
		DecisionTreeRegressorController xy = new DecisionTreeRegressorController();
		xy.decisionTRCGetData();
		
		RandomForestPredictorController rf = new RandomForestPredictorController();
		rf.randomForestCalculation();
		
		WeightedPredictorController wpc = new WeightedPredictorController();
		wpc.weightedPrediction();
		
		SpringApplication.run(StockMaketPredictionApplication.class, args);
		
	}
}
