package com.dev.jabx;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class RunXML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Museum simpleMuseum = new Museum();
		simpleMuseum.setName( "Simple Museum" );
		simpleMuseum.setCity( "Oviedo, Spain" );
		simpleMuseum.setName( "Another Simple Museum" );
		simpleMuseum.setCity( "Gijon, Spain" );
		try {

			JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance( Museums.class );
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
			jaxbMarshaller.marshal( simpleMuseum, new File( "simple.xml" ) );
			jaxbMarshaller.marshal( simpleMuseum, System.out );
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
