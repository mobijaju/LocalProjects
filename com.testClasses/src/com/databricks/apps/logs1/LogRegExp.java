package com.databricks.apps.logs1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogRegExp implements LogExample {

	  public static void main(String argv[]) {

//	  String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
	  String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\"" ;
			  //\"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";

	    
	    System.out.println("Using RE Pattern:");
	    System.out.println(logEntryPattern);

	    System.out.println("Input line is:");
	    System.out.println(logEntryLine);

	    Pattern p = Pattern.compile(logEntryPattern);
	    Matcher matcher = p.matcher(logEntryLine);
	    System.out.println(matcher.groupCount());
	    System.out.println(matcher.matches());
	    System.out.println(NUM_FIELDS);
	    
	    if (!matcher.matches() || 
	      NUM_FIELDS != matcher.groupCount()) {
	      System.err.println("Bad log entry (or problem with RE?):");
	      System.err.println(logEntryLine);
	      return;
	    }
	    System.out.println("IP Address: " + matcher.group(1));
	    System.out.println("Da: " + matcher.group(2));
	    System.out.println("Dat: " + matcher.group(3));
	    System.out.println("Date&Time: " + matcher.group(4));
	    System.out.println("Request: " + matcher.group(5));
         System.out.println("Response: " + matcher.group(6));
	    System.out.println("Bytes Sent: " + matcher.group(7));
	   if (!matcher.group(8).equals("-"))
	      System.out.println("Referer: " + matcher.group(8));
	    System.out.println("Browser: " + matcher.group(9));
	  }
	}

