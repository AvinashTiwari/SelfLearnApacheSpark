package com.example.set.union;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class UnionLog {
	public static void main(String[] args) throws Exception {
		
		System.setProperty("hadoop.home.dir", "c:\\winutil\\");


        SparkConf conf = new SparkConf().setAppName("unionLogs").setMaster("local[*]");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> julyFirstLogs = sc.textFile("data/nasa_19950701.tsv");
        JavaRDD<String> augustFirstLogs = sc.textFile("data/nasa_19950801.tsv");

        JavaRDD<String> aggregatedLogLines = julyFirstLogs.union(augustFirstLogs);

        JavaRDD<String> cleanLogLines = aggregatedLogLines.filter(line -> isNotHeader(line));

        JavaRDD<String> sample = cleanLogLines.sample(true, 0.1);

        sample.saveAsTextFile("dataout/sample_nasa_logs.csv");
    }

    private static boolean isNotHeader(String line) {
        return !(line.startsWith("host") && line.contains("bytes"));
    }
}
