package com.kodilla.library.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kodilla.library.hire.Hire;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "created")
    private Instant accountCreated = Instant.now();

    @JsonIgnore
    @OneToMany(targetEntity = Hire.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Hire> hires = new ArrayList<>();

    public void addHire(Hire hire) {
        this.hires.add(hire);
    }

}
