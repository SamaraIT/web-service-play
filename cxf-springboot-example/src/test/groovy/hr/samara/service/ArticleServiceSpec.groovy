package hr.samara.service

import groovy.util.logging.Slf4j
import hr.samara.model.Article
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

@SpringBootTest(classes = ServiceConfig)
@Slf4j
class ArticleServiceSpec extends Specification {

    @Autowired
    private ArticleService articleService

    def "fetchArticles"() {
        when:
        List<Article> articles = articleService.fetchArticles()
        log.info("fetched " + articles)
        then:
        articles.size() == 1
    }

    def "FindArticleByName with blank parameter should throw exception"() {
        when: "findArticleByName is called with blank name"
        articleService.findArticleByName('')
        then: "ConstraintViolationException is thrown"
        ConstraintViolationException ex = thrown()
        assertNameViolation(ex)
    }

    def "FindArticleByName with null parameter should throw exception"() {
        when: "findArticleByName is called with blank name"
        articleService.findArticleByName(null)
        then: "ConstraintViolationException is thrown"
        ConstraintViolationException ex = thrown()
        assertNameViolation(ex)
    }

    private void assertNameViolation(ConstraintViolationException ex) {
        ex.getConstraintViolations().size() == 1
        ConstraintViolation violation = ex.getConstraintViolations()[0]
        log.info(" ** violation= " + violation)
        violation.getMessage() == 'Name is mandatory'
        violation.getMessageTemplate() == '{name.blank}'
    }

    def "FindArticleByName with uknown name"() {
        when: "article is not present in system"
        articleService.findArticleByName('watermelon')
        then: "EmptyResultDataAccessException is thrown"
        EmptyResultDataAccessException ex = thrown()
        log.info("article is not found, ", ex)
        ex.getMessage() == "Incorrect result size: expected 1, actual 0"
    }

    def "saveArticle"() {
        given:
        Article article = new Article()
        article.name = 'Apple'
        article.price = 1.99
        article.description = 'Very good apples'
        when:
        Long id = articleService.saveArticle(article)
        Article loadedArticle = articleService.findArticleById(id)
        then:
        loadedArticle
        loadedArticle.name == article.name
        loadedArticle.price == article.price
        loadedArticle.description == article.description
        loadedArticle.created
    }

    def "save article without price will throw DataIntegrityViolationException"() {
        given:
        Article article = new Article()
        article.name = 'Apple'
        article.description = 'Very good apples'
        when:
        articleService.saveArticle(article)
        then: "DataIntegrityViolationException is thrown"
        DataIntegrityViolationException ex = thrown()
        log.info("cannot save article without price, ", ex)
    }

    def "Save article twice will throw DataIntegrityViolationException"() {
        given:
        Article article = new Article()
        article.name = 'Apple'
        article.description = 'Very good apples'
        when:
        articleService.saveArticle(article)
        articleService.saveArticle(article)
        then: "DataIntegrityViolationException is thrown"
        DataIntegrityViolationException ex = thrown()
        log.info("cannot save article without price, ", ex)
    }

}
