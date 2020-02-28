package com.spring.firstapi.backend.services;

import java.util.List;
import java.util.Optional;

import com.spring.firstapi.backend.entities.Article;
import com.spring.firstapi.backend.exceptions.ArticleExistsException;
import com.spring.firstapi.backend.exceptions.ArticleNotFoundException;
// article repo
import com.spring.firstapi.backend.repositories.ArticleRepository;
//imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    // get all articles
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    // create an article
    public Article addArticle(Article article) throws ArticleExistsException {
        // check if article exist using article title
        Article existingArticle = articleRepository.findByTitle(article.getTitle());

        if (existingArticle != null) {
            throw new ArticleExistsException("Article already exists!");
        } else {
            return articleRepository.save(article);
        }

    }

    // get article by id
    // throw article not found exception
    public Optional<Article> getArticleById(Long id) throws ArticleNotFoundException {
        Optional<Article> article = articleRepository.findById(id);
        if (!article.isPresent()) {
            throw new ArticleNotFoundException("Article was not found");
        } else {
            return article;
        }

    }

    // update article by id;
    public Article updateArticleById(Long id, Article article) throws ArticleNotFoundException {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isPresent()) {
            throw new ArticleNotFoundException("Article was not found, plz provide the correct id");
        } else {
            article.setId(id);
            return articleRepository.save(article);
        }
    }

    // delete article by id;
    public void deleteArticleById(Long id) throws ArticleNotFoundException {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Article was not found, plz provide the correct id");
        } else {
            articleRepository.deleteById(id);
        }

    }

    // get article by title
    public Article getArticleByTitle(String title) {
        Article article = articleRepository.findByTitle(title);
        return article;
    }

}