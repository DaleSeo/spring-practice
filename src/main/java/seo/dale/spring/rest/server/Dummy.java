package seo.dale.spring.rest.server;

import java.util.Date;

public class Dummy {
	
	private Integer id;
	private String name;
	private Date date;

	public Dummy(Integer id, String name, Date date) {
		this.id = id;
		this.name = name;
		this.date = date;
	}

	public Dummy() {
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
