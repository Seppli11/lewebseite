package ninja.seppli.lewebseite.common.permission.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ninja.seppli.lewebseite.common.exception.HttpNestedRuntimeException;
import ninja.seppli.lewebseite.common.permission.model.ApplicationUser;
import ninja.seppli.lewebseite.common.permission.repository.ApplicationUserRepository;
import ninja.seppli.lewebseite.common.service.AbstractService;

/**
 * The service for the user repo
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Service
public class ApplicationUserService extends AbstractService<ApplicationUser, Long, ApplicationUserRepository>{

	/**
	 * Constructor
	 * @param repo the user repo
	 */
	@Autowired
	public ApplicationUserService(ApplicationUserRepository repo) {
		super(repo);
	}

	/**
	 * Finds the user with the given username
	 * @param username the username to search for
	 * @returns the optional with the user or empty
	 */
	public Optional<ApplicationUser> getByName(String username) {
		return getRepo().findUserByUsername(username);
	}

	/**
	 * Saves the application user. A {@link RuntimeException} with a {@link UserAlreadyExistsException} will be thrown
	 * if a new user is created with the same username which already exists
	 */
	@Override
	public ApplicationUser save(ApplicationUser obj) {
		if(obj.getId() <= 0) {
			ApplicationUser other = getByName(obj.getUsername()).orElse(null);
			if(other != null && other.getId() != obj.getId()) {
				throw new HttpNestedRuntimeException(new UserAlreadyExistsException("The user with the name \"" + obj.getUsername() + "\" already exists"));
			}
		}

		return super.save(obj);
	}

}
