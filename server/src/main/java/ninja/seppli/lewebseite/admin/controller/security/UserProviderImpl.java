package ninja.seppli.lewebseite.admin.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ninja.seppli.lewebseite.common.permission.model.ApplicationUser;
import ninja.seppli.lewebseite.common.permission.service.ApplicationUserService;

/**
 * the implementation of the {@link UserProvider} interface
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Component
public class UserProviderImpl implements UserProvider {

	/**
	 * the app user service
	 */
	private ApplicationUserService service;

	/**
	 * Constructor
	 * @param service
	 */
	@Autowired
	public UserProviderImpl(ApplicationUserService service) {
		this.service = service;
	}

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public ApplicationUser getUser() {
		Authentication auth = getAuthentication();
		if(auth == null) {
			return null;
		}
		String username = auth.getPrincipal().toString();
		return service.getByName(username).orElse(null);
	}
}