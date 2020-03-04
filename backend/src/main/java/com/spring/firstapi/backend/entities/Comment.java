package com.spring.firstapi.backend.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity

public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @NotEmpty(message = "Comment is mondatory field. Please provide a comment")
    @Size(min = 2, message = "Comment should be at least 2 characters.")
    private String content;

    @ManyToOne( fetch = FetchType.LAZY)
    @JsonIgnore
    private Article article;

    public Comment() {
        super();
    }

    public Comment(Long id,
            @NotEmpty(message = "Comment is mondatory field. Please provide a comment") @Size(min = 2, message = "Comment should be at least 2 characters.") String content,
            Article article) {
        this.id = id;
        this.content = content;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Comment [article=" + article + ", content=" + content + ", id=" + id + "]";
    }

    

}