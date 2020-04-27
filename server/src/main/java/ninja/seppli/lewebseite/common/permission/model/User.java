package ninja.seppli.lewebseite.common.permission.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a user in the db
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Entity
@Table
public class User {
	/**
	 * the primary id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	/**
	 * the username
	 */
	private String username;
	/**
	 * the hashed password
	 */
	private String password;
	
	/**
	 * Constructor for hibernate
	 */
	protected User() {
	}

	/**
	 * Creates a user without an id
	 * @param username the username
	 * @param password the password
	 */
	public User(String username, String password) {
		this(-1, username, password);
	}

	/**
	 * Constructor
	 * @param id the db id
	 * @param username the username
	 * @param password the password
	 */
	public User(long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


}
