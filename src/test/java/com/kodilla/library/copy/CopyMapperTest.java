package com.kodilla.library.copy;

import com.kodilla.library.book.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CopyMapperTest {

    @Autowired
    CopyMapper copyMapper;

    @Test
    public void shouldMapToCopyDto() {
        Book book = new Book(1L, "Title", "Author", 1999, new HashSet<>());
        Copy copy = new Copy(2L, book, Status.AVAILABLE, new ArrayList<>());
        CopyDto copyDto = copyMapper.mapToCopyDto(copy);

        Assert.assertEquals(copy.getId(), copyDto.getId());
        Assert.assertEquals(copy.getBook(), copyDto.getBook());
        Assert.assertEquals(copy.getStatus(), copyDto.getStatus());
        Assert.assertEquals(copy.getHires(), copyDto.getHires());

    }

    @Test
    public void shouldMapToCopyDtoList() {
        Book book = new Book(1L, "Title", "Author", 1999, new HashSet<>());
        Copy copy = new Copy(2L, book, Status.AVAILABLE, new ArrayList<>());
        List<Copy> copyList = new ArrayList<>();
        copyList.add(copy);
        List<CopyDto> copyDtoList = copyMapper.mapToCopyDtoList(copyList);

        Assert.assertEquals(copyList.size(), copyDtoList.size());
        Assert.assertEquals(copyList.get(0).getId(), copyDtoList.get(0).getId());
        Assert.assertEquals(copyList.get(0).getBook(), copyDtoList.get(0).getBook());
        Assert.assertEquals(copyList.get(0).getStatus(), copyDtoList.get(0).getStatus());
        Assert.assertEquals(copyList.get(0).getHires(), copyDtoList.get(0).getHires());
    }

}