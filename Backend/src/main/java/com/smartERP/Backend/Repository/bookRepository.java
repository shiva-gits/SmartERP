package com.smartERP.Backend.Repository;

import com.smartERP.Backend.Entities.book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface bookRepository extends JpaRepository<book, Long> {
    Optional<book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);
}