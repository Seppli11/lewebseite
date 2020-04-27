package ninja.seppli.lewebseite.common.article.repository;

import org.springframework.data.repository.CrudRepository;

import ninja.seppli.lewebseite.common.article.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Long>{

}
