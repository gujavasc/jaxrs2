package org.gujavasc.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {
	
	private Integer id;
	private String name;
	private String description;
	
	public Book(Integer id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public Book(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
