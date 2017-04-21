package com.springcamel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springcamel.model.JobRequest;

@Component
public class DbProcessor implements Processor {
	private static Logger log=LoggerFactory.getLogger(DbProcessor.class);
	@Override
	public void process(Exchange msg) throws Exception {
		JobRequest request=(JobRequest) msg.getIn().getBody();
		log.info("aa gaye {}",request.toString());
		msg.getIn().setBody(request.toString(), String.class);
	}

}
