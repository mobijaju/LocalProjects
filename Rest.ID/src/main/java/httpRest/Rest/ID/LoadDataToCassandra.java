package httpRest.Rest.ID;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoadDataToCassandra {

	private static String pop;
	private static String Date;
	private static String StateNumber;
	private static String GeoRegion;
	private static int State;
	private static String Region;

	// create a logger to log headers
	public HttpEntity APIcall() throws ClientProtocolException, IOException {

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
			return entity;

		}

	}

	public JsonNode getInitDataFromAPI() throws ParseException, IOException {
		LoadDataToCassandra getdata = new LoadDataToCassandra();
		String data2 = EntityUtils.toString(getdata.APIcall());
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unused")
		JsonNode node1 = mapper.readValue(data2, JsonNode.class);
		return node1;
	}

	public static String getDate() throws ParseException, IOException {
		LoadDataToCassandra getData = new LoadDataToCassandra();
		JsonNode node1 = getData.getInitDataFromAPI();
		 Date = null;

		if (node1.isArray()) {

			for (int index = 0; index < node1.size(); index++) {
				int index2 = 2;
				Date = node1.path(index).path(index2).asText();
				System.out.println(Date);
			}
		}
		return Date;

	}

	public static String getStateNumber() throws ParseException, IOException {
		LoadDataToCassandra getData = new LoadDataToCassandra();
		JsonNode node1 = getData.getInitDataFromAPI();
		StateNumber = null;

		if (node1.isArray()) {

			for (int index = 0; index < node1.size(); index++) {
				int index2 = 3;
				StateNumber = node1.path(index).path(index2).asText();
				 System.out.println(StateNumber);
				// return(DateData);
			}
		}
		return StateNumber;
	}

	public static String getpop() throws ParseException, IOException {
		LoadDataToCassandra getData = new LoadDataToCassandra();
		JsonNode node1 = getData.getInitDataFromAPI();
		pop = null;

		if (node1.isArray()) {

			for (int index = 0; index < node1.size(); index++) {
				int index2 = 0;
				pop = node1.path(index).path(index2).asText();
				System.out.println(pop);
				// return(DateData);
			}
		}
		return pop;

	}

	public static String getGeoRegion() throws ParseException, IOException {
		LoadDataToCassandra getData = new LoadDataToCassandra();
		JsonNode node1 = getData.getInitDataFromAPI();
		GeoRegion = null;

		if (node1.isArray()) {

			for (int index = 0; index < node1.size(); index++) {
				int index2 = 0;
				GeoRegion = node1.path(index).path(index2).asText();
			    System.out.println(GeoRegion);
				// return(DateData);
			}
		}
		return GeoRegion;
	}

	public static int getState() {
		return State;
	}

	public static String getRegion() {
		return Region;
	}

	public static void main(String[] args) {

		LoadDataToCassandra data = new LoadDataToCassandra();
		try {
			String returnData = data.getDate();
			System.out.println(returnData);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
