package br.com.democracy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.persistence.User;
import br.com.democracy.persistence.enums.UserTypeEnum;
import br.com.democracy.security.CustomUserDetails;
import br.com.democracy.service.UserService;

@SuppressWarnings("deprecation")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		try {
			User user = userService.getUserByEmail(username);

			return new CustomUserDetails(user.getId(), user.getName(),
					user.getEmail(), user.getPassword(), true, true, true,
					true, getGrantedAuthorities(getRoles(user.getType())), user
							.getType().toString());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> getRoles(Integer userType) {
		List<String> roles = new ArrayList<String>();

		if (userType.equals(UserTypeEnum.ADMIN.id())) {
			roles.add("ROLE_ADMIN");
		} else if (userType.equals(UserTypeEnum.NORMAL.id())) {
			roles.add("ROLE_USER");
		}

		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(
			List<String> roles) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new GrantedAuthorityImpl(role));
		}

		return authorities;
	}

}
