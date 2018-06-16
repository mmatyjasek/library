package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class BookDto {

    private Long id;
    private String title;
    private String author;
    private int issued;
    private Set<Copy> copies = new HashSet<>();

}
