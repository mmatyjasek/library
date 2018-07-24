package com.kodilla.library.hire;

import com.kodilla.library.copy.Copy;
import com.kodilla.library.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hire_history")
public class Hire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hire_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private Instant hireDate = Instant.now();

    private Instant returnDate;


}
