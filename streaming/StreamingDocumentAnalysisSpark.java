package com.sjsu.MoonShotDemo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import com.google.common.collect.Lists;

import kafka.serializer.StringDecoder;
import scala.Tuple2;

public class StreamingDocumentAnalysisSpark {
	private static final Pattern BLANK_SPACE = Pattern.compile("");
	public static void main(String[] args){
		System.out.println("Spark reads data");
		
		
		String broker_name = "localhost:9092";
		String kafka_topic = kafkaexample;
		
		SparkConf spark_Config = new SparkConf().setAppName("StreamingAnalysisSpark").setMaster("local");
		
		JavaStreamingContext javaStreamContext = new JavaStreamingContext(spark_Config, Durations.seconds(1));
		Set<String> topics_List = new HashSet<String>(Arrays.asList(kafka_topic.split(",")));
		Map<String, String> kafka_Parameters = new HashMap<String, String>();
		
		kafka_Parameters.put("metadata.broker.list", broker_name);
		JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(javaStreamContext,
				String.class, String.class, StringDecoder.class, StringDecoder.class, kafka_Parameters, topics_List);
		
		JavaDStream<String> lines = messages.map(new Function<Tuple2<String,String>, String>() {
			public String call(Tuple2<String, String>tuple2){
				return tuple2._2();
			}
		});
		
		JavaDStream<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
			public Iterable<String> call(String inputStr){
				return Lists.newArrayList(BLANK_SPACE.split(inputStr));
			}
		});
		
		JavaPairDStream<String, Integer> wordCounts = words.mapToPair(new PairFunction<String, String, Integer>() {
			public Tuple2<String, Integer> call(String str){
			return new Tuple2<String, Integer>(str, 1);	
			}
		}).reduceByKey(new Function2<Integer, Integer, Integer>(){
			public Integer call(Integer int1, Integer int2){
				return int1 + int2;
			}
		});
		
		System.out.println("Streaming Starts");
		wordCounts.print();
		javaStreamContext.start();
		javaStreamContext.awaitTermination();
		
		
	}

}
