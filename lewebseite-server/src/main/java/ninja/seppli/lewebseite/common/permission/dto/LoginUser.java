package ninja.seppli.lewebseite.common.permission.dto;

import ninja.seppli.lewebseite.common.permission.model.ApplicationUser;
import ninja.seppli.lewebseite.common.service.LazyModelMapper;

/**
 * A version of {@link ApplicationUser} without password so it can be sent safly to the front end
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class LoginUser {
	public String username;
	public String password;

	private static LazyModelMapper mapper = new LazyModelMapper();
	/**
	 * Converts from a user to a SaveUser (without password)
	 * @param user the user to convert
	 * @return the saveuser
	 */
	public static LoginUser fromUser(ApplicationUser user) {
		return mapper.getMapper().map(user, LoginUser.class);
	}
}
