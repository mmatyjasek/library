package com.kodilla.library.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void shouldMapToUser() {

        UserDto userDto = new UserDto(3L, "Marcin", "Matyjasek", Instant.now().minus(Duration.ofDays(10)), new ArrayList<>());
        User user = userMapper.mapToUser(userDto);

        Assert.assertEquals(userDto.getId(), user.getId());
        Assert.assertEquals(userDto.getName(), user.getName());
        Assert.assertEquals(userDto.getSurname(), user.getSurname());
        Assert.assertEquals(userDto.getAccountCreated(), user.getAccountCreated());
        Assert.assertEquals(userDto.getHires(), user.getHires());
    }

    @Test
    public void shouldMapToUserDto() {
        User user = new User(3L, "Marcin", "Matyjasek", Instant.now().minus(Duration.ofDays(10)), new ArrayList<>());
        UserDto userDto = userMapper.mapToUserDto(user);

        Assert.assertEquals(user.getId(), userDto.getId());
        Assert.assertEquals(user.getName(), userDto.getName());
        Assert.assertEquals(user.getSurname(), userDto.getSurname());
        Assert.assertEquals(user.getAccountCreated(), userDto.getAccountCreated());
        Assert.assertEquals(user.getHires(), userDto.getHires());
    }

    @Test
    public void shouldMapToUserDtoList() {
        User user = new User(3L, "Marcin", "Matyjasek", Instant.now().minus(Duration.ofDays(10)), new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(user);
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(users);

        Assert.assertEquals(users.size(), userDtoList.size());
        Assert.assertEquals(users.get(0).getId(), userDtoList.get(0).getId());
        Assert.assertEquals(users.get(0).getName(), userDtoList.get(0).getName());
        Assert.assertEquals(users.get(0).getSurname(), userDtoList.get(0).getSurname());
        Assert.assertEquals(users.get(0).getAccountCreated(), userDtoList.get(0).getAccountCreated());
        Assert.assertEquals(users.get(0).getHires(), userDtoList.get(0).getHires());
    }

}