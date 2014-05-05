package org.gujavasc.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {
	
	private Integer id;
	private String name;
	private String description;
	private Integer year;
	private String genero;
	
	public Book(Integer id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public Book(Integer id, String name, String description, Integer year, String genero){
		this.id = id;
		this.name = name;
		this.description = description;
		this.year = year;
		this.genero = genero;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}
