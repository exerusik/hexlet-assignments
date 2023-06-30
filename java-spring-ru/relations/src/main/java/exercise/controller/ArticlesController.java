package exercise.controller;

import exercise.dto.ArticleDto;
import exercise.model.Article;
import exercise.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleRepository articleRepository;

    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        articleRepository.deleteById(id);
    }

    // BEGIN
    @PostMapping(path = "")
    public void createArticle(@RequestBody ArticleDto article) {
        Article article1 = new Article();
        article1.setName(article.getName());
        article1.setBody(article.getBody());
        article1.setCategory(article.getCategory());
        articleRepository.save(article1);
    }

    @PatchMapping("/{id}")
    public void updateArticle(@PathVariable long id, @RequestBody ArticleDto article) {
        Article article1 = articleRepository.findById(id);
        article1.setName(article.getName());
        article1.setBody(article.getBody());
        article1.setCategory(article.getCategory());
        articleRepository.save(article1);

    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable long id) {
        return articleRepository.findById(id);
    }
    // END
}
