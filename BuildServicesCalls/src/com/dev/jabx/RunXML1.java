package com.dev.jabx;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class RunXML1 {

	public static void main(String[] args) {
		Museum simpleMuseum = new Museum();
		simpleMuseum.setName( "Simple Museum" );
		simpleMuseum.setCity( "Oviedo, Spain" );
		Museum anotherSimpleMuseum = new Museum();
		anotherSimpleMuseum.setName( "Another Simple Museum" );
		anotherSimpleMuseum.setCity( "Gijon, Spain" );
		Museums listOfMuseums = new Museums();
		listOfMuseums.add( simpleMuseum );
		listOfMuseums.add( anotherSimpleMuseum );
	
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance( Museums.class );
			Marshaller jaxbMarshaller;
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
			jaxbMarshaller.marshal( listOfMuseums, new File( "simple.xml" ) );
			jaxbMarshaller.marshal( listOfMuseums, System.out );
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

}
