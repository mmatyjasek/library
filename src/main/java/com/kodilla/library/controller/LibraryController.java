package com.kodilla.library.controller;

import com.kodilla.library.domain.*;
import com.kodilla.library.exception.NotAvailableException;
import com.kodilla.library.exception.NotFoundEntityException;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.mapper.CopyMapper;
import com.kodilla.library.mapper.HireMapper;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.BookDbService;
import com.kodilla.library.service.CopyDbService;
import com.kodilla.library.service.HireDbService;
import com.kodilla.library.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    @Autowired
    BookDbService bookDbService;

    @Autowired
    CopyDbService copyDbService;

    @Autowired
    HireDbService hireDbService;

    @Autowired
    UserDbService userDbService;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    CopyMapper copyMapper;

    @Autowired
    HireMapper hireMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping(value = "getBooks")
    public List<BookDto> getBooks(){
        return bookMapper.mapToBookDtoList(bookDbService.getAllBooks());
    }

    @GetMapping(value = "getCopies")
    public List<CopyDto> getCopiesOf(@RequestParam Long bookId){
        return copyMapper.mapToCopyDtoList(copyDbService.getCopiesOf(bookId));
    }

    @GetMapping(value = "getNoOfCopiesOf")
    public Long getNoOfCopiesOf(@RequestParam Long bookId){
        return  copyDbService.getCopiesOf(bookId).stream().filter(copyDto -> copyDto.getStatus() == Status.AVAILABLE).count();
    }

    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers(){
        return userMapper.mapToUserDtoList(userDbService.getAllUsers());
    }

    @GetMapping(value = "getHires")
    public List<HireDto> getHires(){
        return hireMapper.mapToHireDtoList(hireDbService.getAllHires());
    }

    @PostMapping(value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
        userDbService.saveUser(userMapper.mapToUser(userDto));
    }

    @PostMapping(value = "createBook", consumes = APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto){
        bookDbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @PostMapping(value = "createCopy")
    public void createCopy(@RequestParam Long bookId) throws NotFoundEntityException{
        Book book = bookDbService.getBook(bookId).orElseThrow(() -> new NotFoundEntityException("Could not found book with ID: " + bookId));
        Copy copy = new Copy();
        copy.setBook(book);
        copyDbService.saveCopy(copy);
    }

    @PutMapping(value = "reportLostDamage")
    public CopyDto reportLostDamage(@RequestParam Long id) throws NotFoundEntityException {
        changeStatus(id, Status.LOST_DAMAGED);
        return changeStatus(id, Status.LOST_DAMAGED);
    }

    @DeleteMapping(value = "deleteCopy")
    public void deleteCopy(@RequestParam Long id){
        copyDbService.deleteCopy(id);
    }

    @DeleteMapping(value = "deleteBook")
    public void deleteBook(@RequestParam Long id){
        bookDbService.deleteBook(id);
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long id){
        userDbService.deleteUser(id);
    }

    @PostMapping(value = "hire")
    public void hire(@RequestParam Long bookId, @RequestParam Long userId) throws NotFoundEntityException{
        if(getNoOfCopiesOf(bookId)>0) {
            Book book = bookDbService.getBook(bookId).orElseThrow(() -> new NotFoundEntityException("Could not found book with ID: " + bookId));
            Copy copy = book.getCopies().stream().filter(b -> b.getStatus() == Status.AVAILABLE).findFirst().get();
            User user = userDbService.getUser(userId).orElseThrow(() -> new NotFoundEntityException("Could not found user with ID: " + userId));
            changeStatus(copy.getId(), Status.RENTED);
            Hire hire = new Hire();
            hire.setCopy(copy);
            hire.setUser(user);
            hireDbService.saveHire(hire);
        } else {
            throw new NotAvailableException("No available copies of this book");
        }
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestParam Long hireId) throws NotFoundEntityException{
        Hire hire = hireDbService.getHire(hireId).orElseThrow(() -> new NotFoundEntityException("Could not found hire with ID: " + hireId));
        hire.setReturnDate(Instant.now());
        changeStatus(hire.getCopy().getId(), Status.AVAILABLE);
    }

    public CopyDto changeStatus(Long id, Status status) throws NotFoundEntityException {
        Copy foundCopy = copyDbService.getCopy(id).orElseThrow(() -> new NotFoundEntityException("Could not found copy with ID: " + id));
        foundCopy.setStatus(status);
        return copyMapper.mapToCopyDto(copyDbService.saveCopy(foundCopy));
    }

}
