package hr.samara.web;

import hr.samara.model.Article;
import hr.samara.service.ArticleServiceImpl;
import hr.samara.web.api.ArticleWebService;
import hr.samara.web.api.ArticlesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ArticleWebServiceImpl implements ArticleWebService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ArticleServiceImpl articleService;

    public ArticleWebServiceImpl(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @Override
    public ArticlesResponse getArticles() {
        ArticlesResponse articleResponse = new ArticlesResponse(this.articleService.fetchArticles());
        logger.info("response: " + articleResponse);
        return articleResponse;
    }

    @Override
    public Article findArticleByName(String name) {
        logger.info("request: " + name);
        Article article = articleService.findArticleByName(name);
        return article;
    }
}
