package com.kodilla.library.repository;

import com.kodilla.library.domain.Copy;
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

    List<Copy> findAllByBook_Id(Long id);
}
