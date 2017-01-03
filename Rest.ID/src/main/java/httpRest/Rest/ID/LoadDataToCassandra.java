package httpRest.Rest.ID;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoadDataToCassandra {

	private static Logger logger = LogManager.getLogger("LoadDataToCassandra.class");
	private static Cluster cluster;
	private static Session session;
	private static String State;
	private static String region;
	private static String GeographicalRegion;
	private static String Country;
	private static String node;
	private static String pop;
	private static String CountryII;
	
	public HttpEntity APIcall() throws ClientProtocolException, IOException {
		logger.info("Start ------------------------ HttpGet");
		DefaultHttpClient httpclient = new DefaultHttpClient();
		{

			HttpHost check = new HttpHost("api.census.gov");
			// specify the get request
			HttpGet getRequest = new HttpGet(
					"/data/2015/pep/population?get=POP,GEONAME&for=STATE:*&DATE=8&key=c1df2c8aa812a4f930d8bb6daf6f1b4ed1b59222");
			// log this System.out.println("executing request to " + check);
			HttpResponse httpResponse;
			httpResponse = httpclient.execute(check, getRequest);
			HttpEntity entity = httpResponse.getEntity();
			Header[] headers = httpResponse.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				logger.info(headers[i]+ "" + httpResponse.getStatusLine() +"/" +httpResponse.getAllHeaders());
			}			
			return entity;
		}
		
	}

	public JsonNode getInitDataFromAPI() throws ParseException, IOException {
		LoadDataToCassandra getdata = new LoadDataToCassandra();
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String fullReturnedData = EntityUtils.toString(getdata.APIcall());
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode node1 = mapper.readValue(fullReturnedData, JsonNode.class);
		httpclient.getConnectionManager().shutdown();
		return node1;
	}
	
//doing this was way too slow////////
//--------------------------------------------
	
	/*	public PreparedStatement setCassandConnectionandLoad(){
		node = "192.168.30.134";
		cluster = Cluster.builder().addContactPoint(node).build();
		session = cluster.connect("population_data");

		PreparedStatement statement = session
				.prepare("INSERT INTO pop_by_state " + "(state, " + "region, " + "country, " + "geolocaltion, "
						+ "pop, " + "pop_date, " + "statenumber " + ") VALUES (?, ?, ?, ?, ?, ?, ?);");
		return statement;
		
	}*/
	public static String getData() throws ParseException, IOException {
		LoadDataToCassandra getData = new LoadDataToCassandra();
		JsonNode node1 = getData.getInitDataFromAPI();
		node = "192.168.30.134";
		cluster = Cluster.builder().addContactPoint(node).build();
		session = cluster.connect("population_data");

		PreparedStatement statement = session
				.prepare("INSERT INTO pop_by_state " + "(state, " + "region, " + "country, " + "geolocaltion, "
						+ "pop, " + "pop_date, " + "statenumber " + ") VALUES (?, ?, ?, ?, ?, ?, ?);");
        PreparedStatement statement1 = session.prepare("SELECT state,region FROM pop_by_state");

		try {
			if (node1.isArray()) {

				for (int index = 0; index < node1.size(); index++) {
					int index2 = 1;
					int count = 0;

					String GeoRegion = node1.path(index).path(index2).textValue();

					// this is two will do thesame thing
					List<String> GeoRegionList = Arrays.asList(GeoRegion.split(","));
					String GeoRegionLists[] = GeoRegion.split(",");
					// ---------------------------------------------------------------
					State = GeoRegionLists[0];
					count = count + 1;
					if (count < 0 || count >= GeoRegionLists.length) {

					}

					else {
						Country = GeoRegionList.get(count);

						System.out.println(Country);
						count = count + 1;
					}

					if (GeoRegionList.size() > count) {
						GeographicalRegion = GeoRegionLists[count];
						count = count + 1;
					}

					if (GeoRegionList.size() > count) {
						region = GeoRegionLists[count];
						count = count + 1;
					}

					if (GeoRegionList.size() > count) {
						CountryII = GeoRegionLists[count];
						count = count + 1;
					}

					index2 = index2 - 1;
					pop = node1.path(index).path(index2).asText();

					index2 = 3;
					String statenumber = node1.path(index).path(index2).textValue();
                    //---------------------------------------------------------------------------------------------
					
					
					
					
					//=---------------------------------------------------------
					if (region != null) {
						java.util.Date date = new java.util.Date();
						Timestamp timedata = new Timestamp(date.getTime());
						BoundStatement boundStatement = new BoundStatement(statement);
						if (pop != "POP") {
							session.execute(boundStatement.bind(State, region, CountryII, GeographicalRegion,
									pop, timedata, statenumber));
						logger.debug(State +" " + region+" "+ CountryII+" "+GeographicalRegion+" "+
									pop+" "+ timedata+" "+ statenumber);
						
						
						}
					}
				}

			}
			  logger.info("End ------------------------ HttpGet");
			cluster.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	//	logger.info(getData.setCassandConnectionandLoad().getOutgoingPayload());
		return Country;
	}



	public static void main(String[] args) {

		LoadDataToCassandra data = new LoadDataToCassandra();
		try {
			String returnData = data.getData();
			System.out.println(returnData);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
