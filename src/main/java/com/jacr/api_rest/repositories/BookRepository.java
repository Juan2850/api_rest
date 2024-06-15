package com.jacr.api_rest.repositories;

import com.jacr.api_rest.models.Book;
import com.jacr.api_rest.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
