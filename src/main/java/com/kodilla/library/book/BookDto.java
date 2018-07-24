package com.kodilla.library.book;

import com.kodilla.library.copy.Copy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
class BookDto {

    private Long id;
    private String title;
    private String author;
    private int issued;
    private Set<Copy> copies = new HashSet<>();

}
