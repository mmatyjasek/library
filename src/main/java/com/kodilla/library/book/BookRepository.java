package com.kodilla.library.book;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Override
    Book save(Book task);

    @Override
    Optional<Book> findById(Long id);

    @Override
    void deleteById(Long id);
}
