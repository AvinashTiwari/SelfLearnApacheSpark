package com.example.pairdd.groupby.airportbycountry;

import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import com.example.*;
import com.example.Utils.Utils;

import scala.Tuple2;

public class AirportsByCountrySolution {
	// -Xmx512m

	public static void main(String[] args) throws Exception {
		System.setProperty("hadoop.home.dir", "c:\\winutil\\");

        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("airports").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("data/airports.text");

        JavaPairRDD<String, String> countryAndAirportNameAndPair =
                lines.mapToPair( airport -> new Tuple2<>(airport.split(Utils.COMMA_DELIMITER)[3],
                                                         airport.split(Utils.COMMA_DELIMITER)[1]));

        JavaPairRDD<String, Iterable<String>> airportsByCountry = countryAndAirportNameAndPair.groupByKey();

        for (Map.Entry<String, Iterable<String>> airports : airportsByCountry.collectAsMap().entrySet()) {
            System.out.println(airports.getKey() + " : " + airports.getValue());
        }
    }
	
}
