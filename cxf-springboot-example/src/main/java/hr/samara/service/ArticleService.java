package hr.samara.service;

import hr.samara.model.Article;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Validated
public interface ArticleService {
    List<Article> fetchArticles();
    Article findArticleById(@NotNull(message = "{id.mandatory}")Long id);
    Article findArticleByName(@NotBlank(message = "{name.blank}") String name);
    void updateArticlePrice(@NotNull(message = "{id.mandatory}") Long id, @NotNull @Min(value = 0, message = "{price.positive}") BigDecimal price);
    Long saveArticle(@NotNull Article article);
}
