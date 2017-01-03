package httpRest.Rest.ID;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class httpGet {
	private static final Logger logger = LogManager.getLogger("httpGet.class");
	private static Cluster cluster;
	private static Session session;
	private static String State;
	private static String region;
	private static String GeographicalRegion;
	private static String Country;
	private static String node;
	private static String pop;
	private static String CountryII;
	private static int index3 = 0;

	public static void main(String[] args) {
		// this is useless for now
	
		  logger.info("Start ------------------------ HttpGet" + httpGet.Country);
		// -----------------------------------------------------------------------
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

			node = "192.168.30.134";
			cluster = Cluster.builder().addContactPoint(node).build();
			session = cluster.connect("population_data");

			PreparedStatement statement = session
					.prepare("INSERT INTO pop_by_state " + "(state, " + "region, " + "country, " + "geolocaltion, "
							+ "pop, " + "pop_date, " + "statenumber " + ") VALUES (?, ?, ?, ?, ?, ?, ?);");

			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i]);
			}
			System.out.println("----------------------------------------");

			if (entity != null) {
				// System.out.println(EntityUtils.toString(entity));

			}
			if (entity != null) {
				String data2 = EntityUtils.toString(entity);
				ObjectMapper mapper = new ObjectMapper();
				System.out.println(data2);
				try {
				//	ArrayList<String> popdata = new ArrayList<String>();
					JsonNode node1 = mapper.readValue(data2, JsonNode.class);
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
							System.out.println(State);
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
								System.out.println(GeoRegionList.get(count));
								System.out.println(GeographicalRegion);
								count = count + 1;
							}

							if (GeoRegionList.size() > count) {
								region = GeoRegionLists[count];
								System.out.println(region);
								count = count + 1;
							}

							if (GeoRegionList.size() > count) {
								CountryII = GeoRegionLists[count];
								System.out.println(CountryII);
								count = count + 1;
							}

							index2 = index2 - 1;
							pop = node1.path(index).path(index2).asText();
							System.out.println(pop);

							index2 = 3;
							String statenumber = node1.path(index).path(index2).textValue();
							System.out.println(statenumber);

							if (region != null) {
								java.util.Date date = new java.util.Date();
								Timestamp timedata = new Timestamp(date.getTime());
								BoundStatement boundStatement = new BoundStatement(statement);
								if (pop != "POP") {
									session.execute(boundStatement.bind(State, region, CountryII, GeographicalRegion,
											pop, timedata, statenumber));
								}
							}
						}

					}
					  logger.debug("End ------------------------ HttpGet");
					cluster.close();
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