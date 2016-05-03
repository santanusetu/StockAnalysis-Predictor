package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreviousPages {
	
	
	@RequestMapping(value = "/v1/api/stockhistory", method ={ RequestMethod.GET})
	public Object History(@RequestParam(value="company", defaultValue="GOOGL") String company) throws IOException
	//public Object History() throws IOException
	{
		String path="H:\\CMPE239\\formattedDataSet\\labled\\EOD-"+company+"-labled.csv";
		List<String> lines= Files.readAllLines(Paths.get(path));
		int length  =lines.size()>100?100:lines.size();
		StockHistory [] st =new StockHistory[length]; 
		for (int i=0;i<length;i++) 
		{
			 st[i] =new StockHistory(); 
			st[i].date= Integer.parseInt (lines.get(i).split(",")[1]);
			st[i].value= Double.valueOf(lines.get(i).split(",")[2]);
		}
		return st;
	}
}
