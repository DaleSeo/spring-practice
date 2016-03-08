package seo.dale.spring.core.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author Dale Seo
 */
public class User implements UserDetails {

	private String username;
	private String password;
	private List<Role> authorities;

	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialNonExpred = true;
	private boolean enabled = true;

	public User() {
	}

	public User(String username, String password, List<Role> authorities) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialNonExpred;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
