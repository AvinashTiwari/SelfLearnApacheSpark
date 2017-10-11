package com.example.count;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
//// -Xmx512m

public class CountSample {
	  public static void main(String[] args) throws Exception {
		//// -Xmx512m
			System.setProperty("hadoop.home.dir", "c:\\winutil\\");

	        Logger.getLogger("org").setLevel(Level.ERROR);
	        SparkConf conf = new SparkConf().setAppName("count").setMaster("local[*]");
	        JavaSparkContext sc = new JavaSparkContext(conf);

	        List<String> inputWords = Arrays.asList("spark", "hadoop", "spark", "hive", "pig", "cassandra", "hadoop");
	        JavaRDD<String> wordRdd = sc.parallelize(inputWords);

	        System.out.println("Count: " + wordRdd.count());

	        Map<String, Long> wordCountByValue = wordRdd.countByValue();

	        System.out.println("CountByValue:");

	        for (Map.Entry<String, Long> entry : wordCountByValue.entrySet()) {
	            System.out.println(entry.getKey() + " : " + entry.getValue());
	        }
	    }
}
