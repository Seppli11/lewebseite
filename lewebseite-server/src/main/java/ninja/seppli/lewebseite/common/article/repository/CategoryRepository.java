package ninja.seppli.lewebseite.common.article.repository;

import org.springframework.data.repository.CrudRepository;

import ninja.seppli.lewebseite.common.article.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
