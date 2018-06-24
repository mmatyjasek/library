package com.kodilla.library.copy;

import com.kodilla.library.book.Book;
import com.kodilla.library.book.BookDbService;
import com.kodilla.library.exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CopyDbService {

    private final CopyRepository copyRepository;
    private final BookDbService bookDbService;

    @Autowired
    public CopyDbService(CopyRepository copyRepository, BookDbService bookDbService) {
        this.copyRepository = copyRepository;
        this.bookDbService = bookDbService;
    }

    public List<Copy> getCopiesOf(final Long id) {return copyRepository.findAllByBook_Id(id).orElseThrow(() -> new NotFoundEntityException("Could not found book with ID: " + id));}

    public Long getNoOfCopiesOf(final Long id) {
        return copyRepository.findAllByBook_Id(id).orElseThrow(() -> new NotFoundEntityException("Could not found book with ID: " + id))
                .stream().filter(copyDto -> copyDto.getStatus() == Status.AVAILABLE).count();
    }

    public Optional<Copy> getCopy(final Long id) {return copyRepository.findById(id);}

    public Copy saveCopy(final Long bookId) throws NotFoundEntityException{
        Book book = bookDbService.getBook(bookId).orElseThrow(() -> new NotFoundEntityException("Could not found book with ID: " + bookId));
        Copy copy = new Copy();
        copy.setBook(book);
        return copyRepository.save(copy);
    }

    public void changeStatus(final Long id, final Status status) throws NotFoundEntityException {
        Copy foundCopy = getCopy(id).orElseThrow(() -> new NotFoundEntityException("Could not found copy with ID: " + id));
        foundCopy.setStatus(status);
        copyRepository.save(foundCopy);
    }

    public void deleteCopy(final Long id) {
        copyRepository.deleteById(id);
    }
}
