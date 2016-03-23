package com.dev.jabx;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MUSEUMS")
public class Museums {
	List<Museum> museums;

	@XmlElement(name = "MUSEUM")
	public void setMuseum(List<Museum> museums) {
		this.museums = museums;
	}
    public List<Museum> getMuseum(){
    	return museums;
    }
	public void add(Museum museum) {
		if (this.museums == null) {
			this.museums = new ArrayList<Museum>();
		}
		this.museums.add(museum);

	}
}