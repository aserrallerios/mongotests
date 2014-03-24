package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkMain {
	public static void main(String[] args) {
		Spark.get(new Route("/") {

			@Override
			public Object handle(Request request, Response response) {
				return "Hello world from spark";
			}
		});
	}
}
