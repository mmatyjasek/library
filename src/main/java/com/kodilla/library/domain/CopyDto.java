package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CopyDto {

    private Long id;
    private Book book;
    private Status status = Status.AVAILABLE;
    private List<Hire> hires = new ArrayList<>();

}
