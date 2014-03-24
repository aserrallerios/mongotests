package com.tengen;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerMain {
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(FreemarkerMain.class, "/");
		try {
			Template template = conf.getTemplate("hello.ftl");
			StringWriter writer = new StringWriter();
			Map<String, Object> map = new HashMap<>();
			map.put("name", "Albert");
			template.process(map, writer);

			System.out.println(writer);
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
