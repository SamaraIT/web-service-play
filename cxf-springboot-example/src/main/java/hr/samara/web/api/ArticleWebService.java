package hr.samara.web.api;

import hr.samara.model.Article;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ArticleWebService {

    @WebMethod(operationName = "getArticles")
    ArticlesResponse getArticles();

    Article findArticleByName(@WebParam(name = "name") String name);
}
