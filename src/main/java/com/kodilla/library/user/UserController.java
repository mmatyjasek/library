package com.kodilla.library.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserDbService userDbService, UserMapper userMapper) {
        this.userDbService = userDbService;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers(){
        return userMapper.mapToUserDtoList(userDbService.getAllUsers());
    }

    @PostMapping(value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
        userDbService.saveUser(userMapper.mapToUser(userDto));
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long id){
        userDbService.deleteUser(id);
    }
}
