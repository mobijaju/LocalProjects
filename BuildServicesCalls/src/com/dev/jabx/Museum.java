package com.dev.jabx;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Museum")
public class Museum {
	String name;
	Boolean childrenAllowed;
	String city;
	private Exhibition special;
	private Exhibition permanent;
	public String getName() {
		return name;
	}
	@XmlElement( name = "Musuem_Name")
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getChildrenAllowed() {
		return childrenAllowed;
	}
	@XmlAttribute( name ="Children_Allowed")
	public void setChildrenAllowed(Boolean childrenAllowed) {
		this.childrenAllowed = childrenAllowed;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@XmlElement(name = "special")
	public void setSpecial(Exhibition special) {
		this.special = special;
		
	}
	public Exhibition getSpecial(){
		return special;
	}
	@XmlElement(name = "permanent")
	public void setPermanent(Exhibition permanent) {
	this.permanent = permanent;
		
	}
	public Exhibition getPermanent(){
	return permanent;
	}
}
