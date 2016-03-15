package BuildServicesCalls;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import java.io.*;

@XmlRootElement
public class DataTypeConverter { 

	String name;
	String sex;
	int age;
	int id;
	
	public String getName(){
		return name;
		
	}
	
	@XmlElement
	public void setName(String name){
		this.name = name;
	}
	public int getAge(){
		return age;
	}
    @XmlElement
    public void setAge(int age){
    	this.age= age;	   	
    }
    public int getId(){
    	return id;
    }
    @XmlAttribute
    public void setId(int id){
    	this.id = id;
    }


public static void main(String[] args){
	
	DataTypeConverter st = new DataTypeConverter();
	st.setAge(13);
	st.setName("Mike");
	
	try {
		File f = new File("DataTypeConverter.xml");
		JAXB.marshal(st, f);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		Document document = docBuilder.parse("/users/DataTypeConverter.xml");
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.transform(new DOMSource(document), new StreamResult(System.out));
		
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	
}
}