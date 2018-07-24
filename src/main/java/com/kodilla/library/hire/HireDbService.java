package com.kodilla.library.hire;

import com.kodilla.library.book.Book;
import com.kodilla.library.book.BookDbService;
import com.kodilla.library.copy.Copy;
import com.kodilla.library.copy.CopyDbService;
import com.kodilla.library.copy.Status;
import com.kodilla.library.exception.NotAvailableException;
import com.kodilla.library.exception.NotFoundEntityException;
import com.kodilla.library.user.User;
import com.kodilla.library.user.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class HireDbService {

    private final HireRepository hireRepository;
    private final CopyDbService copyDbService;
    private final BookDbService bookDbService;
    private final UserDbService userDbService;


    @Autowired
    public HireDbService(HireRepository hireRepository, CopyDbService copyDbService, BookDbService bookDbService, UserDbService userDbService) {
        this.hireRepository = hireRepository;
        this.copyDbService = copyDbService;
        this.bookDbService = bookDbService;
        this.userDbService = userDbService;
    }

    public List<Hire> getAllHires(){
        return hireRepository.findAll();
    }

    private Optional<Hire> getHire(final Long id) {return hireRepository.findById(id);}

    public Hire hire(final Long bookId, final Long userId) throws NotFoundEntityException {
        if(copyDbService.getNoOfCopiesOf(bookId)>0) {
            Book book = bookDbService.getBook(bookId).orElseThrow(() -> new NotFoundEntityException("Could not found book with ID: " + bookId));
            Copy copy = book.getCopies().stream().filter(b -> b.getStatus() == Status.AVAILABLE).findFirst().get();
            User user = userDbService.getUser(userId).orElseThrow(() -> new NotFoundEntityException("Could not found user with ID: " + userId));
            copyDbService.changeStatus(copy.getId(), Status.RENTED);
            Hire hire = new Hire();
            hire.setCopy(copy);
            hire.setUser(user);
            return hireRepository.save(hire);
        } else {
            throw new NotAvailableException("No available copies of this book");
        }
    }

    public void reportLostDamage (final Long hireId) {
        Hire hire = getHire(hireId).orElseThrow(() -> new NotFoundEntityException("Could not found hire with ID: " + hireId));
        Long copyId = hire.getCopy().getId();
        copyDbService.changeStatus(copyId, Status.LOST_DAMAGED);
    }

    public void returnBook(final Long hireId) {
        Hire hire = getHire(hireId).orElseThrow(() -> new NotFoundEntityException("Could not found hire with ID: " + hireId));
        hire.setReturnDate(Instant.now());
        copyDbService.changeStatus(hire.getCopy().getId(), Status.AVAILABLE);
    }
}

