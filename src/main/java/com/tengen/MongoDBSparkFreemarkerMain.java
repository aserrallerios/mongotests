package com.tengen;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MongoDBSparkFreemarkerMain {
	public static void main(String[] args) throws UnknownHostException {

		Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(FreemarkerMain.class, "/");

		MongoClient client = new MongoClient(new ServerAddress("localhost",
				27017));
		DB database = client.getDB("test");
		DBCollection collection = database.getCollection("hello");

		Spark.get(new Route("/") {

			@Override
			public Object handle(Request request, Response response) {
				StringWriter writer = new StringWriter();
				try {
					Template template = conf.getTemplate("hello.ftl");
					DBObject document = collection.findOne();
					template.process(document, writer);
				} catch (IOException | TemplateException e) {
					halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		});
	}
}
