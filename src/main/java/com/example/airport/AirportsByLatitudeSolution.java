package com.example.airport;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.example.Utils.Utils;



public class AirportsByLatitudeSolution {
	 public static void main(String[] args) throws Exception {
			System.setProperty("hadoop.home.dir", "c:\\winutil\\");

	        SparkConf conf = new SparkConf().setAppName("airports").setMaster("local[2]");

	        JavaSparkContext sc = new JavaSparkContext(conf);

	        JavaRDD<String> airports = sc.textFile("data/airports.text");

	        JavaRDD<String> airportsInUSA = airports.filter(line -> Float.valueOf(line.split(Utils.COMMA_DELIMITER)[6]) > 40);

	        JavaRDD<String> airportsNameAndCityNames = airportsInUSA.map(line -> {
	                    String[] splits = line.split(Utils.COMMA_DELIMITER);
	                    return StringUtils.join(new String[]{splits[1], splits[6]}, ",");
	                }
	        );
	        airportsNameAndCityNames.saveAsTextFile("dataout/airports_by_latitude.text");
	    }

}
