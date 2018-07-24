package com.kodilla.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class BookController {

    private final BookMapper bookMapper;
    private final BookDbService bookDbService;

    @Autowired
    public BookController(BookMapper bookMapper, BookDbService bookDbService) {
        this.bookMapper = bookMapper;
        this.bookDbService = bookDbService;
    }

    @GetMapping(value = "/getBooks")
    public List<BookDto> getBooks(){
        return bookMapper.mapToBookDtoList(bookDbService.getAllBooks());
    }

    @PostMapping(value = "/createBook", consumes = APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto){
        bookDbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @DeleteMapping(value = "/deleteBook")
    public void deleteBook(@RequestParam Long id){
        bookDbService.deleteBook(id);
    }
}
