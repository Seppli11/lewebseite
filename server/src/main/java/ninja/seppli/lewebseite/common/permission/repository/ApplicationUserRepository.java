package ninja.seppli.lewebseite.common.permission.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ninja.seppli.lewebseite.common.permission.model.ApplicationUser;

/**
 * the user repository
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long>{
	/**
	 * Finds the username with the given name
	 * @param name the name
	 * @return the user or an empty optional
	 */
	Optional<ApplicationUser> findUserByUsername(String name);
}
