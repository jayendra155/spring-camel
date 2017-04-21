package com.springcamel.processors;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springcamel.utility.FileValidationException;

public class FileValidationExceptionProcessor implements Processor {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void process(Exchange msg) throws Exception {
		String line = msg.getIn().getBody(String.class);
		Map<String, Object> map = msg.getIn().getHeaders();
		logger.info("Header data is: ");
		Set<String> headers = map.keySet();
		for (String header : headers)
			{
				logger.info(header+":\t"+map.get(header));
			}
		logger.info("Processing line: " + line);
		if (line.split(",").length != 3) {

			FileValidationException e = new FileValidationException();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			msg.getIn().setBody(sw.toString() + "\n");
			throw e;
		}

	}
}
