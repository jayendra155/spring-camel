package com.springcamel.routebuilders;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.springcamel.processors.DbProcessor;

@Component
public class DbRoute extends RouteBuilder {

	@Value("${jpa.endpoint}")
	private String jpaEndpoint;
	
	
	@Override
	public void configure() throws Exception {
		from("jpa:com.springcamel.model.JobRequest?"+
			"consumer.namedQuery=new-request&consumer.delay={{process.delay:5s}}&consumeDelete=true")
		.bean(DbProcessor.class, "process")
		.to("file://C:/NotBackedUp/junk/main/fromDb?fileName=abc.txt&fileExist=append");
	}

}
