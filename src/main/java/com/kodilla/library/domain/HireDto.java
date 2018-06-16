package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class HireDto {

    private Long id;
    private Copy copy;
    private User user;
    private Instant hireDate;
    private Instant returnDate;

}
