package com.kodilla.library.repository;

import com.kodilla.library.domain.*;
import com.kodilla.library.mapper.CopyMapper;
import com.kodilla.library.service.CopyDbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;

import java.util.HashSet;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HireRepositoryTestSuite {

    @Autowired
    HireRepository hireRepository;

    @Autowired
    CopyRepository copyRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CopyMapper copyMapper;

    @Autowired
    CopyDbService copyDbService;

    @Test
    public void testSaveHires() {

        Book book1 = new Book();
        book1.setTitle("Title 1");
        book1.setAuthor("Author 1");
        book1.setIssued(1984);
        book1.setCopies(new HashSet<>());

        Copy copy1 = new Copy();
        copy1.setBook(book1);


        book1.getCopies().add(copy1);

        User user1 = new User();
        user1.setName("John");
        user1.setSurname("Doe");
        user1.setAccountCreated(Instant.now().minus(Duration.ofDays(30)));

        Hire hire1 = new Hire();
        hire1.setCopy(copy1);
        hire1.setUser(user1);
        hire1.setHireDate(Instant.now().minus(Duration.ofDays(10)));
        hire1.setReturnDate(Instant.now());

        copy1.getHires().add(hire1);
        user1.getHires().add(hire1);

        bookRepository.save(book1);
        long book1Id = book1.getId();
        hireRepository.save(hire1);
        long hire1Id = hire1.getId();
        long copy1Id = copy1.getId();
        long user1Id = user1.getId();


        Assert.assertNotEquals(0, book1Id);
        Assert.assertNotEquals(0, hire1Id);
        Assert.assertNotEquals(0, copy1Id);
        Assert.assertNotEquals(0, user1Id);


       try {
            bookRepository.deleteById(book1Id);
            hireRepository.deleteById(hire1Id);

        } catch (Exception e) {

        }
    }

}
