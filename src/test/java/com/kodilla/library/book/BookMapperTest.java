package com.kodilla.library.book;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMapperTest {

    @Autowired
    BookMapper bookMapper;

    @Test
    public void shouldMapToBook() {

        BookDto bookDto = new BookDto(1L, "Title", "Author", 1999, new HashSet<>());
        Book book = bookMapper.mapToBook(bookDto);

        Assert.assertEquals(bookDto.getId(), book.getId());
        Assert.assertEquals(bookDto.getTitle(), book.getTitle());
        Assert.assertEquals(bookDto.getAuthor(), book.getAuthor());
        Assert.assertEquals(bookDto.getIssued(), book.getIssued());
        Assert.assertEquals(bookDto.getCopies(), book.getCopies());
    }

    @Test
    public void shouldMapToBookDto() {
        Book book = new Book(1L, "Title", "Author", 1999, new HashSet<>());
        BookDto bookDto = bookMapper.mapToBookDto(book);

        Assert.assertEquals(book.getId(), bookDto.getId());
        Assert.assertEquals(book.getTitle(), bookDto.getTitle());
        Assert.assertEquals(book.getAuthor(), bookDto.getAuthor());
        Assert.assertEquals(book.getIssued(), bookDto.getIssued());
        Assert.assertEquals(book.getCopies(), bookDto.getCopies());
    }

    @Test
    public void shouldMapToBookDtoList() {
        Book book = new Book(1L, "Title", "Author", 1999, new HashSet<>());
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        List<BookDto> bookDtoList = bookMapper.mapToBookDtoList(bookList);

        Assert.assertEquals(bookList.size(), bookDtoList.size());
        Assert.assertEquals(bookList.get(0).getId(), bookDtoList.get(0).getId());
        Assert.assertEquals(bookList.get(0).getTitle(), bookDtoList.get(0).getTitle());
        Assert.assertEquals(bookList.get(0).getAuthor(), bookDtoList.get(0).getAuthor());
        Assert.assertEquals(bookList.get(0).getIssued(), bookDtoList.get(0).getIssued());
        Assert.assertEquals(bookList.get(0).getCopies(), bookDtoList.get(0).getCopies());
    }
}