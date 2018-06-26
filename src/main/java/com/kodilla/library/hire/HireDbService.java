package com.kodilla.library.hire;

import com.kodilla.library.book.Book;
import com.kodilla.library.book.BooksDbService;
import com.kodilla.library.copy.Copy;
import com.kodilla.library.copy.CopyDbService;
import com.kodilla.library.copy.Status;
import com.kodilla.library.exception.NotAvailableException;
import com.kodilla.library.exception.NotFoundEntityException;
import com.kodilla.library.user.User;
import com.kodilla.library.user.UserDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class HireDbService {

    private final int ZERO_COPIES = 0;

    private final HireRepository hireRepository;
    private final CopyDbService copyDbService;
    private final BooksDbService booksDbService;
    private final UserDbService userDbService;

    public Hire hire(final Long bookId, final Long userId) {

        if (copyDbService.getNoOfCopiesOf(bookId) == ZERO_COPIES) {
            throw new NotAvailableException("No available copies of this book");
        }

        Book book = booksDbService.getBook(bookId);
        Copy copy = book.getCopies().stream()
                .filter(b -> b.getStatus() == Status.AVAILABLE)
                .findFirst()
                .get();
        User user = userDbService.getUser(userId);

        copyDbService.changeStatus(copy.getId(), Status.RENTED);

        Hire hire = new Hire();
        hire.setCopy(copy);
        hire.setUser(user);

        return hireRepository.save(hire);
    }

    List<Hire> getAllHires() {
        return hireRepository.findAll();
    }

    void reportLostDamage(final Long hireId) {
        Hire hire = getHire(hireId);

        Long copyId = hire.getCopy().getId();

        copyDbService.changeStatus(copyId, Status.LOST_DAMAGED);
    }

    void returnBook(final Long hireId) {
        Hire hire = getHire(hireId);
        hire.setReturnDate(Instant.now());
        copyDbService.changeStatus(hire.getCopy().getId(), Status.AVAILABLE);
    }

    private Hire getHire(final Long id) {
        return hireRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Could not found hire with " +
                "ID: " + id));
    }

}

