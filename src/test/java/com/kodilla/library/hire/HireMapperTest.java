package com.kodilla.library.hire;

import com.kodilla.library.book.Book;
import com.kodilla.library.copy.Copy;
import com.kodilla.library.copy.Status;
import com.kodilla.library.user.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HireMapperTest {

    @Autowired
    HireMapper hireMapper;

    @Test
    public void shouldMapToHireDto() {
        Book book = new Book(1L, "Title", "Author", 1999, new HashSet<>());
        Copy copy = new Copy(2L, book, Status.AVAILABLE, new ArrayList<>());
        User user = new User(3L, "Marcin", "Matyjasek", Instant.now().minus(Duration.ofDays(10)), new ArrayList<>());
        Hire hire = new Hire(4L, copy, user, Instant.now().minus(Duration.ofDays(5)), Instant.now());
        HireDto hireDto = hireMapper.mapToHireDto(hire);

        Assert.assertEquals(hire.getId(), hireDto.getId());
        Assert.assertEquals(hire.getCopy(), hireDto.getCopy());
        Assert.assertEquals(hire.getUser(), hireDto.getUser());
        Assert.assertEquals(hire.getHireDate(), hireDto.getHireDate());
        Assert.assertEquals(hire.getReturnDate(), hireDto.getReturnDate());

    }

    @Test
    public void shouldMapToHireDtoList() {
        Book book = new Book(1L, "Title", "Author", 1999, new HashSet<>());
        Copy copy = new Copy(2L, book, Status.AVAILABLE, new ArrayList<>());
        User user = new User(3L, "Marcin", "Matyjasek", Instant.now().minus(Duration.ofDays(10)), new ArrayList<>());
        Hire hire = new Hire(4L, copy, user, Instant.now().minus(Duration.ofDays(5)), Instant.now());
        List<Hire> hires = new ArrayList<>();
        hires.add(hire);
        List<HireDto> hireDtoList = hireMapper.mapToHireDtoList(hires);

        Assert.assertEquals(hires.size(), hireDtoList.size());
        Assert.assertEquals(hires.get(0).getId(), hireDtoList.get(0).getId());
        Assert.assertEquals(hires.get(0).getCopy(), hireDtoList.get(0).getCopy());
        Assert.assertEquals(hires.get(0).getUser(), hireDtoList.get(0).getUser());
        Assert.assertEquals(hires.get(0).getHireDate(), hireDtoList.get(0).getHireDate());
        Assert.assertEquals(hires.get(0).getReturnDate(), hireDtoList.get(0).getReturnDate());
    }

}