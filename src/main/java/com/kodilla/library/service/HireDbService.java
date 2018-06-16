package com.kodilla.library.service;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.domain.Hire;
import com.kodilla.library.repository.CopyRepository;
import com.kodilla.library.repository.HireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HireDbService {

    @Autowired
    HireRepository hireRepository;

    public List<Hire> getAllHires(){
        return hireRepository.findAll();
    }

    public Optional<Hire> getHire(final Long id) {return hireRepository.findById(id);}

    public Hire saveHire(final Hire hire){
        return hireRepository.save(hire);
    }

    public void deleteHire(final Long id) {
        hireRepository.deleteById(id);
    }
}

