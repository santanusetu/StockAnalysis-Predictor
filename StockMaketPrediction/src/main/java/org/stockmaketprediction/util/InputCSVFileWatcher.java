package org.stockmaketprediction.util;

public class InputCSVFileWatcher {

	String[] datapathDTR = {   "data//EOD-AAPL-labled.csv", "data//EOD-CSCO-labled.csv", "data//EOD-FB-labled.csv", "data//EOD-GOOGL-labled.csv",
			"data//EOD-IBM-labled.csv", "data//EOD-INTU-labled.csv", "data//EOD-MSFT-labled.csv", "data//EOD-VAR-labled.csv"};

	String[] compNameDTR = {  "APPLE", "CISCO", "FACEBOOK", "GOOGLE", "IBM", "INTUIT", "MICROSOFT", "VARIAN" };

	public String[] fileNameRF = {  "data//EOD-AAPL.csv", "data//EOD-CSCO.csv", "data//EOD-FB.csv", "data//EOD-GOOGL.csv", "data//EOD-IBM.csv",
			"data//EOD-INTU.csv", "data//EOD-MSFT.csv", "data//EOD-VAR.csv" };

	public String[] compNameRF = { "APPLE", "CISCO", "FACEBOOK", "GOOGLE", "IBM", "INTUIT", "MICROSOFT", "VARIAN" };

	public String[] getDatapathDTR() {
		return datapathDTR;
	}

	public void setDatapathDTR(String[] datapathDTR) {
		this.datapathDTR = datapathDTR;
	}

	public String[] getCompNameDTR() {
		return compNameDTR;
	}

	public void setCompNameDTR(String[] compNameDTR) {
		this.compNameDTR = compNameDTR;
	}

	public String[] getFileNameRF() {
		return fileNameRF;
	}

	public void setFileNameRF(String[] fileNameRF) {
		this.fileNameRF = fileNameRF;
	}

	public String[] getCompNameRF() {
		return compNameRF;
	}

	public void setCompNameRF(String[] compNameRF) {
		this.compNameRF = compNameRF;
	}

}
