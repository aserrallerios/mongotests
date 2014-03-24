package com.tengen;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FruitPicker {
	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(FruitPicker.class, "/");

		Spark.get(new Route("/") {

			@Override
			public Object handle(Request request, Response response) {
				StringWriter writer = new StringWriter();
				try {
					Template template = conf.getTemplate("fruitpicker.ftl");
					Map<String, Object> map = new HashMap<>();
					map.put("name", "Albert");
					map.put("fruits",
							Arrays.asList("apple", "banana", "orange"));
					template.process(map, writer);
				} catch (IOException | TemplateException e) {
					halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		});

		Spark.post(new Route("/fruit_picker") {

			@Override
			public Object handle(Request request, Response response) {
				String param = request.queryParams("fruit");
				if (param == null) {
					return "Why didn't you pick a fruit?";
				} else {
					return "Your favourite fruit is " + param;
				}
			}
		});
	}
}
