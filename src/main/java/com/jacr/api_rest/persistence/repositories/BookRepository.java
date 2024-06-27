package com.jacr.api_rest.persistence.repositories;

import com.jacr.api_rest.persistence.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
