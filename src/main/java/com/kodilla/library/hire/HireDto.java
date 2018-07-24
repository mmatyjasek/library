package com.kodilla.library.hire;

import com.kodilla.library.copy.Copy;
import com.kodilla.library.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
class HireDto {

    private Long id;
    private Copy copy;
    private User user;
    private Instant hireDate;
    private Instant returnDate;

}
