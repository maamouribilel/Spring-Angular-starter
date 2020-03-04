package com.spring.firstapi.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.spring.firstapi.backend.entities.Article;
import com.spring.firstapi.backend.entities.Comment;
import com.spring.firstapi.backend.exceptions.ArticleNotFoundException;
import com.spring.firstapi.backend.repositories.ArticleRepository;
import com.spring.firstapi.backend.repositories.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/articles")
public class CommentController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    // get comments by article id
    @GetMapping(value = "/{article_id}/comments")
    public List<Comment> getCommentsByArticleId(@PathVariable Long article_id) throws ArticleNotFoundException {
        Optional<Article> articleOptional = articleRepository.findById(article_id);
        if (!articleOptional.isPresent()) {
            throw new ArticleNotFoundException("Article does not exists");
        } else {
            return articleOptional.get().getComments();
        }
    }

    // create a comment
    @PostMapping(value = "/{article_id}/comments")
    public Comment createComment(@PathVariable Long article_id, @RequestBody Comment comment)
            throws ArticleNotFoundException {
        Optional<Article> articleOptional = articleRepository.findById(article_id);
        if (!articleOptional.isPresent()) {
            throw new ArticleNotFoundException("Article does not exists");
        } else {
            Article article = articleOptional.get();
            comment.setArticle(article);
            return commentRepository.save(comment);
        }

    }

    // get comment by id
    @GetMapping(value = "/comments/{comment_id}")
    public Optional<Comment> getCommentById(@PathVariable Long comment_id) throws ArticleNotFoundException {
        Optional<Comment> commentOptional = commentRepository.findById(comment_id);
        if (!commentOptional.isPresent()) {
            throw new ArticleNotFoundException("Comment does not exists");
        } else {
            return commentOptional;
        }
    }

}