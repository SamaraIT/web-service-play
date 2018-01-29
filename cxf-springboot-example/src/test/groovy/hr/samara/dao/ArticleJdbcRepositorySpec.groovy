package hr.samara.dao

import groovy.util.logging.Slf4j
import hr.samara.model.Article
import org.h2.jdbc.JdbcSQLException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.annotation.Rollback
import spock.lang.Specification

@JdbcTest
@Rollback(false)
@Slf4j
class ArticleJdbcRepositorySpec extends Specification {

    @Autowired
    private JdbcTemplate jdbcTemplate
    private ArticleRepository articleRepository

    def setup() {
        this.articleRepository = new ArticleJdbcRepository(jdbcTemplate)
    }

    def "should return one article - after data initialization -> data.sql"() {
        when: "after database initialization"
        List<Article> articles = articleRepository.findAll()
        log.info("fetched " + articles)
        then: "assert there is only one record"
        articles.size() == 1
        assertFirstArticle(articles[0])
    }

    def "should save article"() {
        given: "create Article"
        Article article = new Article(name: 'Orange', price: 9.1)
        when:
        Long id = articleRepository.save(article)
        Article savedArticle = articleRepository.findById(id)
        log.info("saved " + savedArticle)
        then:
        assertFirstArticle(savedArticle)
    }

    def "find article by name"() {
        given:
        String name = 'Orange'
        when:
        Article article = articleRepository.findByName(name)
        log.info("findByName " + article)
        then:
        assertSecondArticle(article)

    }

    def "should return two articles"() {
        when: "after adding one extra article"
        List<Article> articles = articleRepository.findAll()
        log.info("fetched " + articles)
        then: "assert there are two records is db"
        articles.size() == 2
        assertFirstArticle(articles[0])
        assertSecondArticle(articles[1])
    }

    def "should update article price"() {
        given:
        Article article = articleRepository.findByName('Orange')
        log.info("article before update " + article)
        BigDecimal price = 108
        when:
        articleRepository.updatePrice(article.id, price)
        Article updatedArticle = articleRepository.findByName(article.name)
        log.info("updatedArticle " + updatedArticle)
        then:
        article != updatedArticle
        updatedArticle.id == 2
        updatedArticle.name == 'Orange'
        updatedArticle.price == price
        updatedArticle.created
    }

    def "should fail when saving article"() {
        when: "saving article with null values will throw exception"
        articleRepository.save(new Article())
        then:
        DataIntegrityViolationException ex = thrown()
        JdbcSQLException rootCause = ex.getRootCause()
        rootCause.message.startsWith("NULL not allowed for column \"NAME\"")
        log.info("expected expection", ex)
    }

    private void assertFirstArticle(Article article) {
        article.id == 1
        article.name == 'Banana'
        article.price == 7.5
        article.created
        article.description == null
    }

    private void assertSecondArticle(Article article) {
        article.id == 2
        article.name == 'Orange'
        article.price == 9.1
        article.created
        article.description == null
    }
}
