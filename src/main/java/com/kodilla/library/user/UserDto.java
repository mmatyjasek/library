package com.kodilla.library.user;

import com.kodilla.library.hire.Hire;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private Instant accountCreated = Instant.now();
    private List<Hire> hires = new ArrayList<>();


}
