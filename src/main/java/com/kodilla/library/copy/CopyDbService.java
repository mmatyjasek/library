package com.kodilla.library.copy;

import com.kodilla.library.book.Book;
import com.kodilla.library.book.BooksDbService;
import com.kodilla.library.exception.NotFoundEntityException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CopyDbService {

    private final CopyRepository copyRepository;
    private final BooksDbService booksDbService;

    public void changeStatus(final Long id, final Status status) throws NotFoundEntityException {
        Copy foundCopy = getCopy(id).orElseThrow(() -> new NotFoundEntityException("Could not found copy with ID: " + id));
        foundCopy.setStatus(status);
        copyRepository.save(foundCopy);
    }

    public Long getNoOfCopiesOf(final Long id) {
        return copyRepository.findAllByBook_Id(id)
                .orElseThrow(() -> new NotFoundEntityException("Could not found book with ID: " + id))
                .stream()
                .filter(copyDto -> copyDto.getStatus().equals(Status.AVAILABLE))
                .count();
    }

    List<Copy> getCopiesOf(final Long id) {
        return copyRepository.findAllByBook_Id(id)
                .orElseThrow(() -> new NotFoundEntityException("Could not found book with ID: " + id));
    }


    Copy saveCopy(final Long bookId) throws NotFoundEntityException {
        Book book = booksDbService.getBook(bookId);
        Copy copy = new Copy();
        copy.setBook(book);
        return copyRepository.save(copy);
    }


    void deleteCopy(final Long id) {
        copyRepository.deleteById(id);
    }

    private Optional<Copy> getCopy(final Long id) {
        return copyRepository.findById(id);
    }

}
