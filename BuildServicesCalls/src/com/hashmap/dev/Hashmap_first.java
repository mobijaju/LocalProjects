package com.hashmap.dev;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Hashmap_first {
	
	
	 public static void main(String args[]) {
		   
	      // Create a hash map
	      HashMap<String, Double> map = new HashMap<String, Double>();
	      // Put elements to the map
	      map.put("Zara", new Double(3434.34));
	      map.put("Mahnaz", new Double(123.22));
	      map.put("Ayan", new Double(1378.00));
	      map.put("Daisy", new Double(99.22));
	      map.put("Qadir", new Double(-19.08));
	      
	      for(String a: map.keySet())
	      {
	    	 if(map.containsKey("Zara1")) {
	    		 System.out.println(map.get("Zara")); 
	    	 }
	    	 else {
				System.out.println(map.toString());
				
	    	 }	 
	      }
	      
	      Set st = map.entrySet();
	      Iterator m = st.iterator();
	      while(m.hasNext())
	      {
	    	  Map.Entry getmap = (Map.Entry)m.next();
	    	  System.out.print(getmap.getKey() + ":");
	    	  System.out.println(getmap.getValue());
	      }
	      
	/*      
	    // Get a set of the entries
	      Set set = map.entrySet();
	      // Get an iterator
	      Iterator i = set.iterator();
	      // Display elements
	      while(i.hasNext()) {
	    	  Map.Entry me = (Map.Entry)i.next();
	         System.out.print(me.getKey() + ": ");
	         System.out.println(me.getValue());
	      }
	      System.out.println();
	      // Deposit 1000 into Zara's account
	      double balance = ((Double)map.get("Zara")).doubleValue();
	      map.put("Zara", new Double(balance + 1000));
	      System.out.println("Zara's new balance: " +
	      map.get("Zara"));*/
	   }
	}

