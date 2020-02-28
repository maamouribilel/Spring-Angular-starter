// package
package com.spring.firstapi.backend.repositories;

// entity
import com.spring.firstapi.backend.entities.Article;
// imports
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Article findByTitle(String title);
    

}