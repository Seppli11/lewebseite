package ninja.seppli.lewebseite.common.permission.service;

import org.springframework.beans.factory.annotation.Autowired;

import ninja.seppli.lewebseite.common.permission.model.User;
import ninja.seppli.lewebseite.common.permission.repository.UserRepository;
import ninja.seppli.lewebseite.common.service.AbstractService;

/**
 * The service for the user repo
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class UserService extends AbstractService<User, Long, UserRepository>{

	/**
	 * Constructor
	 * @param repo the user repo
	 */
	@Autowired
	public UserService(UserRepository repo) {
		super(repo);
	}

}
