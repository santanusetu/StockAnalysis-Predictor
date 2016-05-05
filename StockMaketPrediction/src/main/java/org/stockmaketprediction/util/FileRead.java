package org.stockmaketprediction.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public double [] fileLineReader(String filePath) throws IOException
	{
		double [] result;
		String line= Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8).get(0);
		String [] parts =line.split(",");
		int length =parts.length;
		result=new double[length];
			for (int i=0;i<length;i++) 
			{
		        result[i]=Double.valueOf(parts[i]);
		    }
	
		return result;
		
	}

}