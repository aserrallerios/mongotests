package com.tengen;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkFreemarkerMain {
	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(FreemarkerMain.class, "/");

		Spark.get(new Route("/") {

			@Override
			public Object handle(Request request, Response response) {
				StringWriter writer = new StringWriter();
				try {
					Template template = conf.getTemplate("hello.ftl");
					Map<String, Object> map = new HashMap<>();
					map.put("name", "Albert");
					template.process(map, writer);
				} catch (IOException | TemplateException e) {
					halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		});
	}
}
