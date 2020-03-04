package com.spring.firstapi.backend.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.spring.firstapi.backend.entities.Article;
import com.spring.firstapi.backend.exceptions.ArticleExistsException;
import com.spring.firstapi.backend.exceptions.ArticleNotFoundException;
import com.spring.firstapi.backend.exceptions.TitleNotFoundException;
// import com.spring.firstapi.backend.exceptions.TitleNotFoundException;
import com.spring.firstapi.backend.services.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Validated
@RequestMapping(value = "/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // get all articles
    @GetMapping("/hello")
    public String helloWorld() {
        return "<h2>Hello World!</h2>";
    }

    // get all articles
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    // get article by id
    @GetMapping("/{id}")
    public Optional<Article> getArticleById(@PathVariable("id") @Min(1) Long id) {
        try {
            return articleService.getArticleById(id);
        } catch (ArticleNotFoundException e) {
            // e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // add new article
    @PostMapping("/")
    public ResponseEntity<Void> addArticle(@Valid @RequestBody Article article, UriComponentsBuilder builder) {
        try {
            articleService.addArticle(article);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/articles/{id}").buildAndExpand(article.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (ArticleExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // update an article
    @PostMapping("/{id}")
    public Article updateArticleById(@PathVariable("id") Long id, @RequestBody Article article) {
        try {
            return articleService.updateArticleById(id, article);
        } catch (ArticleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // delete an article
    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable("id") Long id) throws ArticleNotFoundException {
        articleService.deleteArticleById(id);
    }

    // get article by title
    @GetMapping("/bytitle/{title}")
    public Article getArticleByTitle(@PathVariable("title") String title) throws TitleNotFoundException {
        Article article = articleService.getArticleByTitle(title);
        if (article == null) {
            throw new TitleNotFoundException("Title: '" + title + "' doesn't exist.");
        } else {
            return article;
        }
    }

}