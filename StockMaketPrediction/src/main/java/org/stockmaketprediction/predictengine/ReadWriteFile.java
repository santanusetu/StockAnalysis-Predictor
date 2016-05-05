package org.stockmaketprediction.predictengine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ReadWriteFile {

	/**
	 * The specified content written to the specified file
	 * 
	 * @param file
	 * @param content
	 * @throws IOException
	 */
	public static void writeFile(String file, String content) throws IOException {

		File result = new File(file);
		OutputStream out = new FileOutputStream(result, false);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));

		bw.write(content);

		bw.close();
		out.close();
	}

}
