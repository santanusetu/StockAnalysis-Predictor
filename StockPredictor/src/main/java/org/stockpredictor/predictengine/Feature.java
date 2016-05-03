package org.stockpredictor.predictengine;

import java.util.List;

public class Feature {

	/**
	 * Back dataset list feature, assume that the problem
	 * is there is no chance three days before to 30 days 
	 * after the information to predict price ( average 
	 * closing price for the first three days is greater than 30 days )
	 * 
	 * @param price_list Four daily price
	 * @return
	 */
	public static double[][] simpleFeature(List<List<Double>> price_list) {

		int data_size = price_list.size() - 50 - 2;

		double[][] data_feature = new double[data_size][250];

		int list_size = price_list.size();

		for (int i = 50; i < list_size - 2; i++) {

			for (int j = 0; j < 50; j++) {

				List<Double> price = price_list.get(i - 1 - j);

				int price_kind = price.size();

				for (int k = 0; k < price_kind; k++)
					data_feature[i - 50][j * 5 + k] = price.get(k);

			}

		}

		return data_feature;

	}

}

