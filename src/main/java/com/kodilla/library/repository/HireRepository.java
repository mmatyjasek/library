package com.kodilla.library.repository;

import com.kodilla.library.domain.Hire;
import org.springframework.data.repository.CrudRepository;

public interface HireRepository extends CrudRepository<Hire, Long> {
}
