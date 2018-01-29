package hr.samara.dao;

import hr.samara.model.Article;

import java.math.BigDecimal;
import java.util.List;

public interface ArticleRepository {
    List<Article> findAll();
    Article findById(Long id);
    Article findByName(String name);
    void updatePrice(Long id, BigDecimal price);
    Long save(Article article);
}
