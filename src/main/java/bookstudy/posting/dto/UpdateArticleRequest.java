package bookstudy.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UpdateArticleRequest {
    private String title;
    private String content;
}
