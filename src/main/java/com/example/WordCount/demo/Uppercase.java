package com.example.WordCount.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
// -Xmx512m
public class Uppercase {
	  public static void main(String[] args) throws Exception {
			System.setProperty("hadoop.home.dir", "c:\\winutil\\");

	        // Create a Java Spark Context.
	        SparkConf conf = new SparkConf().setAppName("uppercase").setMaster("local[*]");

	        JavaSparkContext sc = new JavaSparkContext(conf);

	        JavaRDD<String> lines = sc.textFile("data/uppercase.text");
	        JavaRDD<String> lowerCaseLines = lines.map(line -> line.toUpperCase());

	        lowerCaseLines.saveAsTextFile("dataout/uppercase.text");
	    }
}
