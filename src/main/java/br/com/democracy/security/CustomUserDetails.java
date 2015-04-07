package br.com.democracy.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	/** Id do usuário no DB */
	private long id;
	
	/** Senha do usuário */
	private String password;

	/** Nome do usuario */
	private String username;
	
	/** Email do usuario */
	private String email;
	
	/** Tipo de usuário {@link UserTypeEnum} */
	private String userType;

	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private List<GrantedAuthority> authorities;

	public CustomUserDetails(long id, String username, String email, String password,
			boolean accountNonExpired,
			boolean accountNonLocked, boolean credentialsNonExpired,
			boolean enabled, List<GrantedAuthority> authorities, String userType) {

		this.id = id;
		this.password = password;
		this.username = username;
		this.email = email;
		this.userType = userType;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	public long getId() {
		return this.id;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}

	public String getUserType() {
		return this.userType;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setAuthorities(String typeUser) {
		this.authorities = new ArrayList<GrantedAuthority>();
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

}
