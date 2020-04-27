package ninja.seppli.lewebseite.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

/**
 * Represents an abstract service with the basic functionality
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 * @param <T> The object type
 * @param <ID> the type of the id
 * @param <REPO> the type of the CrudRepository
 */
public abstract class AbstractService <T, ID, REPO extends CrudRepository<T, ID>> {
	/**
	 * the repo
	 */
	private REPO repo;

	/**
	 * Constructor
	 * @param repo the curd repository
	 */
	public AbstractService(REPO repo) {
		this.repo = repo;
	}

	/**
	 * Returns all instances of the object of the service.
	 * The returned list is new and changes won't be reflected in the db
	 * @return the list
	 */
	public List<T> getAll() {
		List<T> list = new ArrayList<>();
		repo.findAll().forEach(list::add);
		return list;
	}



	/**
	 * Returns the object with the given id or an empty optional if
	 * the object couldn't be found
	 * @param id the id to search for
	 * @return the optional
	 */
	public Optional<T> get(ID id) {
		return repo.findById(id);
	}

	/**
	 * Saves or updates the given object in the db
	 * @param obj the object to update
	 * @return The updated object. This should be used from now in case somehting changed
	 * 	in the object, like the primary key
	 */
	public T save(T obj) {
		return repo.save(obj);
	}

	/**
	 * Deletes the given object
	 * @param obj the object to remove
	 */
	public void delete(T obj) {
		repo.delete(obj);
	}

	/**
	 * Returns the internal crud repository
	 * @return the repo
	 */
	protected REPO getRepo() {
		return repo;
	}
}
