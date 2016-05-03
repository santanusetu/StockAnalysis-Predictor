package com.sjsu.MoonShotDemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer {

	public static void main(String[] args) {
		
		//String sampledata = "/Users/santanuchakraborty/Documents/239-moonshot-screenshots/data/data.csv";
	
		Properties props = new Properties();
		// Asmath changed the host number
		// props.put("metadata.broker.list", "host2:9092,host3:9092");
		props.put("metadata.broker.list", "localhost:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		// props.put("partitioner.class", "example.producer.SimplePartitioner");
		//props.put("request.required.acks", "1");
		props.put("request.required.acks", "1");

		ProducerConfig config = new ProducerConfig(props);

		Producer<String, String> producer = new Producer<String, String>(config);

		String topic = "kafkaexample";
		BufferedReader br = null;
		String line = "";
		int i = 0;
		
		try {
			while(true){
		//	br = new BufferedReader(new FileReader(sampledata));
		//	while ((line = br.readLine()) != null) {
		//		System.out.println(line);

			    CallSimulator cs = new CallSimulator();
			    String[] responseRest = cs.callSimulatorRestService();
			    
			    
			//	KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, String.valueOf(i), line);
				KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, responseRest[0], responseRest[1]);

				producer.send(data);
				i++;
			//}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		/*
		 * for (int i = 1 ; i <= 1000 ; i++) {
		 * 
		 * String msg = " This is message " + i ; System.out.println(msg) ;
		 * 
		 * KeyedMessage<String, String> data = new KeyedMessage<String,
		 * String>(topic, String.valueOf(i), msg);
		 * 
		 * producer.send(data);
		 * 
		 * 
		 * }
		 */
		
		producer.close();
		
	}

}