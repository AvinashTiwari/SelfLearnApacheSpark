package com.example.pairdd.join;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;

import scala.Tuple2;

public class JoinOperation {

    public static void main(String[] args) throws Exception {
    	// -Xmx512m
		System.setProperty("hadoop.home.dir", "c:\\winutil\\");

        SparkConf conf = new SparkConf().setAppName("JoinOperations").setMaster("local[1]");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaPairRDD<String, Integer> ages = sc.parallelizePairs(Arrays.asList(new Tuple2<>("Tom", 29),
                                                                              new Tuple2<>("John", 22)));

        JavaPairRDD<String, String> addresses = sc.parallelizePairs(Arrays.asList(new Tuple2<>("James", "USA"),
                                                                                  new Tuple2<>("John", "UK")));

        JavaPairRDD<String, Tuple2<Integer, String>> join = ages.join(addresses);

        join.saveAsTextFile("data/age_address_join.text");

        JavaPairRDD<String, Tuple2<Integer, Optional<String>>> leftOuterJoin = ages.leftOuterJoin(addresses);

        leftOuterJoin.saveAsTextFile("data/age_address_left_out_join.text");

        JavaPairRDD<String, Tuple2<Optional<Integer>, String>> rightOuterJoin = ages.rightOuterJoin(addresses);

        rightOuterJoin.saveAsTextFile("data/age_address_right_out_join.text");

        JavaPairRDD<String, Tuple2<Optional<Integer>, Optional<String>>> fullOuterJoin = ages.fullOuterJoin(addresses);

        fullOuterJoin.saveAsTextFile("data/age_address_full_out_join.text");


    }
}
