package hr.samara.web.api;

import hr.samara.model.Article;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@XmlType(name = "ArticlesResponse")
public class ArticlesResponse implements Serializable {

    private List<Article> articles;
    private String status = "OK";

    public ArticlesResponse(List<Article> articles) {
        this.articles = articles;
    }
}
