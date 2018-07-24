package com.kodilla.library.book;

import com.kodilla.library.exception.NotFoundEntityException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BooksDbService {

    private final BookRepository bookRepository;

    public Book getBook(final Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Could not found book with ID: " + id));
    }

    List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    void deleteBook(final Long id) {
        bookRepository.deleteById(id);
    }
}
