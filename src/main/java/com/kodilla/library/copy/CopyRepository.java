package com.kodilla.library.copy;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CopyRepository extends CrudRepository<Copy, Long> {


    @Override
    Copy save(Copy copy);

    @Override
    Optional<Copy> findById(Long id);

    @Override
    void deleteById(Long id);

    Optional<List<Copy>> findAllByBook_Id(Long id);
}
