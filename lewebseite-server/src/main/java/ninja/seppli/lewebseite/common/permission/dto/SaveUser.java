package ninja.seppli.lewebseite.common.permission.dto;

import ninja.seppli.lewebseite.common.permission.model.ApplicationUser;
import ninja.seppli.lewebseite.common.service.LazyModelMapper;

/**
 * A version of {@link ApplicationUser} without password so it can be sent safly to the front end
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class SaveUser {
	long id;
	String username;
	String email;


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	private static LazyModelMapper mapper = new LazyModelMapper();
	/**
	 * Converts from a user to a SaveUser (without password)
	 * @param user the user to convert
	 * @return the saveuser
	 */
	public static SaveUser fromUser(ApplicationUser user) {
		return mapper.getMapper().map(user, SaveUser.class);
	}
}
