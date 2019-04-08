package com.practice.SpringBoot.Versioning;

public class PersonV2 {

	private Name name;
	
	public PersonV2() {
	}
	

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public PersonV2(Name name) {
		super();
		this.name = name;
	}
	
	
}
