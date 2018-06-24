package com.kodilla.library.copy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kodilla.library.book.Book;
import com.kodilla.library.hire.Hire;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "copies")
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "copy_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "titleId")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.AVAILABLE;

    @JsonIgnore
    @OneToMany(targetEntity = Hire.class,
            mappedBy = "copy",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private List<Hire> hires = new ArrayList<>();

    public void addHire(Hire hire) {
        this.hires.add(hire);
    }

}
