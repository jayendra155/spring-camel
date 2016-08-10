package com.springcamel.routebuilders;

import org.apache.camel.builder.RouteBuilder;

import com.springcamel.processors.FileValidationExceptionProcessor;
import com.springcamel.utility.FileValidationException;

public class FileToFileRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(FileValidationException.class)
				.maximumRedeliveries(0)
				.handled(true)
				.to("file://C:/NotBackedUp/junk/main/log?fileName=exceptions.log&fileExist=append&noop=true");

		from("file://C:/NotBackedUp/junk/main/in?noop=true")
				.split(body().tokenize("\n")).streaming()
				.process(new FileValidationExceptionProcessor())
				.to("file://C:/NotBackedUp/junk/main/out?fileExist=append");
	}
}