package org.cassandraConnect.cassConnect;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
    
    Cluster cluster;
    Session session;
    
    cluster = Cluster.builder().addContactPoint("192.168.23.133").build();
    session = cluster.connect("musicdb");
    ResultSet results = session.execute("Select * From performers_by_style LIMIT 10");
    for (Row row : results) {
    System.out.format("%s %d\n", row.getString("firstname"), row.getInt("age"));
    }
        
    }
}
