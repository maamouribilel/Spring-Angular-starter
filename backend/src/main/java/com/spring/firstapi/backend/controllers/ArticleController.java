package com.spring.firstapi.backend.controllers;

import java.util.List;
import java.util.Optional;

import com.spring.firstapi.backend.entities.Article;
import com.spring.firstapi.backend.exceptions.ArticleExistsException;
import com.spring.firstapi.backend.exceptions.ArticleNotFoundException;
import com.spring.firstapi.backend.services.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // get all articles
    @GetMapping("/")
    public String helloWorld() {
        return "<h2>Hello World!</h2>";
    }

    // get all articles
    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    // get article by id
    @GetMapping("/articles/{id}")
    public Optional<Article> getArticleById(@PathVariable("id") Long id) {
        try {
            return articleService.getArticleById(id);
        } catch (ArticleNotFoundException e) {
            // e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // add new article
    @PostMapping("/articles")
    public ResponseEntity<Void> addArticle(@RequestBody Article article, UriComponentsBuilder builder) {
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
    @PostMapping("/articles/{id}")
    public Article updateArticleById(@PathVariable("id") Long id, @RequestBody Article article) {
        try {
            return articleService.updateArticleById(id, article);
        } catch (ArticleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // delete an article
    @DeleteMapping("/articles/{id}")
    public void deleteArticleById(@PathVariable("id") Long id) throws ArticleNotFoundException {
        articleService.deleteArticleById(id);
    }

    // get article by id
    @GetMapping("/articles/bytitle/{title}")
    public Article getArticleById(@PathVariable("title") String title) {
        return articleService.getArticleByTitle(title);
    }

}