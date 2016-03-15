package com.databricks.apps.logs;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jetty.io.RuntimeIOException;

import scala.Console;

public class ApacheAccessLog implements Serializable {

	private static final Logger logger = Logger.getLogger("Access");
	private String ipAddress;
	private long ByteSize;
	private String Request;
	private String dateTimeString;
	private Integer Response;

	
	private ApacheAccessLog(String ipAddress, String dateTime, String Request){
			// String Response,String ByteSize) {;
		this.ipAddress = ipAddress;
		this.Request = Request;
	//	this.Response = Integer.parseInt(Response);
		this.dateTimeString = dateTime;
	//	this.ByteSize = Long.parseLong(ByteSize);
	  }
	



	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Long getByteSize() {
		return ByteSize;
	}

	public void setByteSize(long byteSize) {
		ByteSize = byteSize;
	}

	public String getRequest() {
		return Request;
	}

	public void setRequest(String request) {
		Request = request;
	}

	public String getDateTimeString() {
		return dateTimeString;
	}

	public void setDateTimeString(String dateTimeString) {
		this.dateTimeString = dateTimeString;
	}

//	public int getResponse() {
//		return Response;
//	}
//
//	public void setResponse(int response) {
//		Response = response;
//	}



	// Example Apache log line:
	// 127.0.0.1 - - [21/Jul/2014:9:55:27 -0800] "GET /home.html HTTP/1.1" 200
	// 2048
	// try to understand this pattern library
//	private static final String LOG_ENTRY_PATTERN ="^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+)";
	private static final String LOG_ENTRY_PATTERN ="^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\"" ;
	
	private static final Pattern PATTERN = Pattern.compile(LOG_ENTRY_PATTERN);

	public static ApacheAccessLog parseFromLogLine(String logline) {
		Matcher m = PATTERN.matcher(logline);
		if (!m.find()) {
			logger.log(Level.ALL, "Cannot parse logline" + logline);
			throw new RuntimeException("Error parsing logline");
		}
		
		

		return new ApacheAccessLog(m.group(1), m.group(4), m.group(5)); //, m.group(6), m.group(7));
	}

	@Override
	
	public String toString(){
		return String.format("%s [%s] \"%s\" %s %s", ipAddress, dateTimeString,
				Request, Response, ByteSize);
	}
	
}
