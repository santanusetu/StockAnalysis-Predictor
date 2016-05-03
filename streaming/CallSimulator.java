package com.sjsu.MoonShotDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class CallSimulator {

	String[] resp;

	public String[] callSimulatorRestService() {

		String url = "http://localhost:8080/simulator";
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			// add request header
			// con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			// System.out.println("\nSending 'GET' request to URL : " + url);
			// System.out.println("Response Code : " + responseCode);
			String content = con.getContent().toString();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			String response = "";
			while ((inputLine = in.readLine()) != null) {
				response = inputLine;
			}
			in.close();
			resp = response.split(";");
			for (int i = 0; i < resp.length; i++) {
				System.out.println("@@@ resp " + resp[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}