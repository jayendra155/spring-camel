package com.springcamel.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DOBToAgeProcessor {
	public String process(String line) throws ParseException {
		Logger logger = LoggerFactory.getLogger(getClass());
		String[] tokens = line.split(",");
		if (!"DOB".equalsIgnoreCase(tokens[1])) {
			String[] newDate = tokens[1].split("-");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			logger.info("Received date : " + dateFormat.toString());
			Date date = dateFormat.parse(newDate[0] + "/" + newDate[1] + "/"
					+ newDate[2]);
			logger.info("New Date is : " + date.toString());
			line = date.toString();
		}
		return line;
	}
}
