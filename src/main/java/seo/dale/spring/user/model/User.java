package seo.dale.spring.user.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import seo.dale.spring.common.model.BaseEntity;
import seo.dale.spring.core.security.model.Role;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.List;

/**
 * @author Dale Seo
 */
@Entity
public class User extends BaseEntity implements UserDetails {

	private String username;
	private String password;

	private String firstName;
	private String lastName;
	private String email;

	@Transient
	private List<Role> authorities;

	public User() {
	}

	public User(String username, String password, List<Role> authorities) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public void update(User updated) {
		username = StringUtils.defaultString(updated.username, username);
		password = StringUtils.defaultString(updated.password, password);

		firstName = StringUtils.defaultString(updated.firstName, firstName);
		lastName = StringUtils.defaultString(updated.lastName, lastName);
		email = StringUtils.defaultString(updated.email, email);
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
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static class Builder {

		private User built;

		public Builder(String username, String password) {
			built = new User();
			built.username = username;
			built.password = password;
		}

		public User build() {
			return built;
		}

		public Builder firstName(String firstName) {
			built.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			built.lastName = lastName;
			return this;
		}

		public Builder email(String email) {
			built.email = email;
			return this;
		}

	}

}
