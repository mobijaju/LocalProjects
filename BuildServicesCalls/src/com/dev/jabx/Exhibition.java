package com.dev.jabx;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "Exhibition")
public class Exhibition {

	String name;
	LocalDate from;
	LocalDate to;
	private List<Exhibition> artistsSpecial;
	
	public String getName() {
		return name;
	}
	@XmlElement(name ="Name")
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getFrom() {
		return from;
	}
	@XmlJavaTypeAdapter( LocalDateAdapter.class )
	@XmlElement(name ="FROM")
	public void setFrom(LocalDate from) {
		this.from = from;
	}
	
	public LocalDate getTo() {
		return to;
	}
	@XmlJavaTypeAdapter( LocalDateAdapter.class )
	@XmlElement(name ="TO")
	public void setTo(LocalDate to) {
		this.to = to;
	}
	@XmlElement(name ="artistsSpecial")
	public void setArtists(List<Exhibition> artistsSpecial) {
		this.artistsSpecial = artistsSpecial;
	}
	
	public List<Exhibition> getArtists(){
		return artistsSpecial;
		
	}
}
