package com.kodilla.library.book;

import java.util.List;
import java.util.stream.Collectors;

class BooksMapper {

    private BooksMapper() {
        throw new IllegalStateException();
    }

    static Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getIssued(),
                bookDto.getCopies());
    }

    static BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIssued(),
                book.getCopies());
    }

    static List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(t -> new BookDto(t.getId(), t.getTitle(), t.getAuthor(), t.getIssued(), t.getCopies()))
                .collect(Collectors.toList());
    }

}
