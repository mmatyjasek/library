package com.kodilla.library.hire;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HireRepository extends CrudRepository<Hire, Long> {

    @Override
    List<Hire> findAll();

    @Override
    Hire save(Hire hire);

    @Override
    Optional<Hire> findById(Long id);

    @Override
    void deleteById(Long id);
}
