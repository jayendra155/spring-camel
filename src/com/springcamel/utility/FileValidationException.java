package com.springcamel.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger;

	public FileValidationException() {
		this("File Validation Exception");
	}

	public FileValidationException(String msg) {
		super(msg);
		logger = LoggerFactory.getLogger(getClass());
		logger.info(this.getMessage());
	}

	@Override
	public String getMessage() {
		return "The input file has line(s) not in proper format";
	}

	@Override
	public void printStackTrace() {
	}
}
