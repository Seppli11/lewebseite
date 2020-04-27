package ninja.seppli.lewebseite.common.permission.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ninja.seppli.lewebseite.common.permission.model.User;

/**
 * the user repository
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public interface UserRepository extends CrudRepository<User, Long>{
	/**
	 * Finds the username with the given name
	 * @param name the name
	 * @return the user or an empty optional
	 */
	Optional<User> findUserByUsername(String name);
}
