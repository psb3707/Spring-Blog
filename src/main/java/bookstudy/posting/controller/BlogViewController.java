package bookstudy.posting.controller;

import bookstudy.posting.dto.ArticleListViewResponse;
import bookstudy.posting.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(article -> new ArticleListViewResponse(article))
                .toList();
        model.addAttribute("articles", articles);
        return "articleList";
    }
}
