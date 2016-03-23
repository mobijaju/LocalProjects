package httpRest.Rest.ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A simple Java REST GET example using the Apache HTTP library. This executes a
 * call against the Yahoo Weather API service, which is actually an RSS service
 * (<a href="http://developer.yahoo.com/weather/" title=
 * "http://developer.yahoo.com/weather/">http://developer.yahoo.com/weather/</a>
 * ).
 * 
 * Try this Twitter API URL for another example (it returns JSON results):
 * <a href="http://search.twitter.com/search.json?q=%40apple" title=
 * "http://search.twitter.com/search.json?q=%40apple">http://search.twitter.com/
 * search.json?q=%40apple</a> (see this url for more twitter info:
 * <a href="https://dev.twitter.com/docs/using-search" title=
 * "https://dev.twitter.com/docs/using-search">https://dev.twitter.com/docs/
 * using-search</a>)
 * 
 * Apache HttpClient: <a href="http://hc.apache.org/httpclient-3.x/" title=
 * "http://hc.apache.org/httpclient-3.x/">http://hc.apache.org/httpclient-3.x/
 * </a>
 *
 */
public class httpGet {

	public static void main(String[] args) {

		Logger logger = Logger.getLogger("myloger");
		logger.log(Level.ALL, "getclass", httpGet.class);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpHost check = new HttpHost("api.census.gov");
			// specify the get request
			HttpGet getRequest = new HttpGet(
					"/data/2015/pep/population?get=POP,GEONAME&for=STATE:*&DATE=8&key=c1df2c8aa812a4f930d8bb6daf6f1b4ed1b59222");
			System.out.println("executing request to " + check);
			HttpResponse httpResponse = httpclient.execute(check, getRequest);
			HttpEntity entity = httpResponse.getEntity();
			System.out.println("----------------------------------------");
			System.out.println(httpResponse.getStatusLine());
			Header[] headers = httpResponse.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i]);
			}
			System.out.println("----------------------------------------");

			if (entity != null) {
		//	System.out.println(EntityUtils.toString(entity));
                
			}
			if (entity != null) {
				String data2 = EntityUtils.toString(entity);
				ObjectMapper mapper = new ObjectMapper();
				System.out.println(data2);
				try {
					
					
					JsonNode node1= mapper.readValue(data2, JsonNode.class);
					if (node1.isArray()){
						
						String State;
						String region;
						String GeographicalRegion;
						String Country;
						
						for (int index = 0; index<node1.size(); index ++){
							int index2 = 1;
							int count = 0;
							
							String GeoRegion  = node1.path(index).path(index2).textValue();
							
							//this is two will do thesame thing
				        	List<String> GeoRegionList = Arrays.asList(GeoRegion.split(","));
				        	String GeoRegionLists[] = GeoRegion.split(",");
							//---------------------------------------------------------------
				        	State = GeoRegionLists[0];
							System.out.println(State);
							count = count + 1;
							if (count < 0 || count >= GeoRegionLists.length) {
								
							}
								
							else {
								region = GeoRegionList.get(count);
								System.out.println(region);		
								count=count + 1;
							}
							
    						if (GeoRegionList.size()>count) {
    							GeographicalRegion = GeoRegionLists[count];
    						System.out.println(GeoRegionList.get(count));
							System.out.println(GeographicalRegion);
							count=count + 1;
						}
    						
    						if (GeoRegionList.size()>count) {
    							Country =GeoRegionLists[count];
    							System.out.println(Country);
    							count=count + 1;
    						}
    						

						//	System.out.println(GeoRegionList.get(3));
//						}
//						for (int index = 0; index<node1.size(); index ++){
//							int index2 = 0;
//							String pop  = node1.path(index).path(index2).textValue();
//							System.out.print(pop);
//						}
//						for (int index = 0; index<node1.size(); index ++){
//							int index2 = 2;
//							String date  = node1.path(index).path(index2).textValue();
//							System.out.println(date);
//						}
//						for (int index = 0; index<node1.size(); index ++){
//							int index2 = 3;
//							String state  = node1.path(index).path(index2).textValue();
//							System.out.print(state);
						}
					}
					String x  = node1.path(0).path(2).textValue();
					System.out.println(x);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
}