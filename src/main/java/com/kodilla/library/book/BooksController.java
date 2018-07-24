package com.kodilla.library.book;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("books")
@AllArgsConstructor
@Slf4j
public class BooksController {

    private final BooksDbService booksDbService;

    @GetMapping
    public List<BookDto> getBooks() {
        log.info("Get books called");

        return BooksMapper.mapToBookDtoList(booksDbService.getAllBooks());
    }

    @PostMapping
    public void createBook(@RequestBody BookDto bookDto) {
        booksDbService.saveBook(BooksMapper.mapToBook(bookDto));
    }

    @DeleteMapping
    public void deleteBook(@RequestParam Long id) {
        booksDbService.deleteBook(id);
    }
}
