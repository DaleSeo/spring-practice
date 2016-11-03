package seo.dale.spring.core.security.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Dale Seo
 */
public class Role implements GrantedAuthority {

	private String name;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
