package bookstudy.posting.dto;

import bookstudy.posting.domain.Article;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
