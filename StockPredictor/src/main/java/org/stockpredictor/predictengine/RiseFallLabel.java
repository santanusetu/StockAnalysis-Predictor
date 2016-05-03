package org.stockpredictor.predictengine;

import java.util.List;

public class RiseFallLabel {

	/**
	 * 
	 * Returns a list of the data set label,
	 * assume that the problem is there is no chance three days
	 *  before to 30 days after the information to predict price 
	 *  ( average closing price for the first three days is greater than 30 days )
	 * 
	 * @param price_list
	 *           Four daily price
	 * @return
	 */
	public static int[] getLabelList(List<List<Double>> price_list) {

		int[] label = new int[price_list.size() - 50 - 2];

		int list_size = price_list.size();

		for (int i = 50; i < list_size - 2; i++) {

			List<Double> current_price_1 = price_list.get(i);
			List<Double> current_price_2 = price_list.get(i + 1);
			List<Double> current_price_3 = price_list.get(i + 2);

			double close_price_1 = current_price_1.get(3);
			double close_price_2 = current_price_2.get(3);
			double close_price_3 = current_price_3.get(3);

			double average = (close_price_1 + close_price_2 + close_price_3) / 3;

			List<Double> last_day_price = price_list.get(i - 1);

			double last_day_close = last_day_price.get(3);

			if (average > last_day_close) {

				label[i - 50] = 1;

			}

		}

		return label;
	}
}
