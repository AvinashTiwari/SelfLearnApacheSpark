package com.example.redcue;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class ReduceSample {
	  public static void main(String[] args) throws Exception {
			//// -Xmx512m
			System.setProperty("hadoop.home.dir", "c:\\winutil\\");


	        Logger.getLogger("org").setLevel(Level.OFF);
	        SparkConf conf = new SparkConf().setAppName("reduce").setMaster("local[*]");
	        JavaSparkContext sc = new JavaSparkContext(conf);

	        List<Integer> inputIntegers = Arrays.asList(1, 2, 3, 4, 5);
	        JavaRDD<Integer> integerRdd = sc.parallelize(inputIntegers);

	        Integer product = integerRdd.reduce((x, y) -> x * y);

	        System.out.println("product is :" + product);
	    }
}
