package com.example.pairdd.create;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class pairddfromregularrdd {
	//-Xmx512m

	public static void main(String[] args) throws Exception {
		System.setProperty("hadoop.home.dir", "c:\\winutil\\");

        SparkConf conf = new SparkConf().setAppName("create").setMaster("local[1]");

        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> inputStrings = Arrays.asList("A 23", "B 29", "C 29", "D 8");

        JavaRDD<String> regularRDDs = sc.parallelize(inputStrings);

        JavaPairRDD<String, Integer> pairRDD = regularRDDs.mapToPair(getNameAndAgePair());

        pairRDD.coalesce(1).saveAsTextFile("dataout/pair_rdd_from_regular_rdd");
    }

    private static PairFunction<String, String, Integer> getNameAndAgePair() {
        return (PairFunction<String, String, Integer>) s -> new Tuple2<>(s.split(" ")[0],
                                                                         Integer.valueOf(s.split(" ")[1]));
    }
}
