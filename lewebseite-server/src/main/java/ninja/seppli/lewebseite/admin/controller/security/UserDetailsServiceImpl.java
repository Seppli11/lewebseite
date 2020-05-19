package ninja.seppli.lewebseite.admin.controller.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ninja.seppli.lewebseite.common.permission.model.ApplicationUser;
import ninja.seppli.lewebseite.common.permission.service.ApplicationUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ApplicationUserService service;

	/**
	 * Constructor
	 *
	 * @param service the app user service
	 */
	@Autowired
	public UserDetailsServiceImpl(ApplicationUserService service) {
		this.service = service;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser appUser = service.getByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("The user \"" + username + "\" wasn't found"));
		return new User(appUser.getUsername(), appUser.getPassword(), Arrays.asList());
	}

}
