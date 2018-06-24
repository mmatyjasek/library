package com.kodilla.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDbService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(final Long id) {return bookRepository.findById(id);}

    public Book saveBook(final Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(final Long id) {
        bookRepository.deleteById(id);
    }
}
