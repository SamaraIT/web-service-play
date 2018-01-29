package hr.samara.service;

import hr.samara.dao.ArticleRepository;
import hr.samara.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> fetchArticles() {
        List<Article> articles = this.articleRepository.findAll();
        logger.info("fetched " + articles.size() + " articles");
        logger.debug("fetched " + articles);
        return articles;
    }

    @Override
    @Transactional(readOnly = true)
    public Article findArticleById(Long id) {
        Article article = this.articleRepository.findById(id);
        logger.info("fetched " + article);
        return article;
    }

    @Override
    @Transactional(readOnly = true)
    public Article findArticleByName(String name) {
        Article article = this.articleRepository.findByName(name);
        logger.info("fetched " + article);
        return article;
    }

    @Override
    public void updateArticlePrice(Long id, BigDecimal price) {
        logger.info("update article.id=" + id + " with price=" + price);
        this.articleRepository.updatePrice(id, price);
    }

    @Override
    public Long saveArticle(Article article) {
        logger.info("save " + article);
        return this.articleRepository.save(article);
    }
}
