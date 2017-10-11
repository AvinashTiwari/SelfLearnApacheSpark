package com.example.pairdd.create;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

//-Xmx512m

public class pairrddfromtuplelist {
	 public static void main(String[] args) throws Exception {
			System.setProperty("hadoop.home.dir", "c:\\winutil\\");
	        SparkConf conf = new SparkConf().setAppName("create").setMaster("local[1]");

	        JavaSparkContext sc = new JavaSparkContext(conf);

	        List<Tuple2<String, Integer>> tuple = Arrays.asList(new Tuple2<>("A", 23),
	                                                            new Tuple2<>("B", 29),
	                                                            new Tuple2<>("C", 29),
	                                                            new Tuple2<>("D",8));

	        JavaPairRDD<String, Integer> pairRDD = sc.parallelizePairs(tuple);

	        pairRDD.coalesce(1).saveAsTextFile("dataout/pairrddfromtuplelist");
	    }

}
