package org.springframework.web.client;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Company {
	
	private String name;
	private String catchPhrase;
	private String bs;

	public Company() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatchPhrase() {
		return catchPhrase;
	}

	public void setCatchPhrase(String catchPhrase) {
		this.catchPhrase = catchPhrase;
	}

	public String getBs() {
		return bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}
	
	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
