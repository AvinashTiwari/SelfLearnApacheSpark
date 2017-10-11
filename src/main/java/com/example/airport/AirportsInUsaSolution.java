package com.example.airport;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.example.Utils.Utils;

public class AirportsInUsaSolution {

	public static void main(String[] args) throws Exception {
		System.setProperty("hadoop.home.dir", "c:\\winutil\\");
		SparkConf conf = new SparkConf().setAppName("airports").setMaster("local[2]");

		JavaSparkContext sc = new JavaSparkContext(conf);

		JavaRDD<String> airports = sc.textFile("data/airports.text");

		JavaRDD<String> airportsInUSA = airports
				.filter(line -> line.split(Utils.COMMA_DELIMITER)[3].equals("\"United States\""));

		JavaRDD<String> airportsNameAndCityNames = airportsInUSA.map(line -> {
			String[] splits = line.split(Utils.COMMA_DELIMITER);
			System.out.println(splits[1]);
			return StringUtils.join(new String[] { splits[1], splits[2] }, ",");
		});
		
		
		airportsNameAndCityNames.saveAsTextFile("dataout/airports_in_usa.text");

	}
}