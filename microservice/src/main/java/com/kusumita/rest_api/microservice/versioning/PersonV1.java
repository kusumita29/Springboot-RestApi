package com.kusumita.rest_api.microservice.versioning;

public class PersonV1 {
	private String name;
	
	public PersonV1(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "PersonV1 [name=" + name + "]";
	}
	
	
}
