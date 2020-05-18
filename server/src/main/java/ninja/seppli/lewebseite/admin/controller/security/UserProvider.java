package ninja.seppli.lewebseite.admin.controller.security;

import org.springframework.security.core.Authentication;

import ninja.seppli.lewebseite.common.permission.model.ApplicationUser;

/**
 * An interface to get the authentication and user object
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public interface UserProvider {
	/**
	 * Returns the authentication of the current user
	 * @return the auth obj
	 */
	Authentication getAuthentication();

	/**
	 * Returns the user or null if non is logged in
	 * @return the user or null
	 */
	ApplicationUser getUser();
}
