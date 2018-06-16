package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookDto;
import com.kodilla.library.domain.Hire;
import com.kodilla.library.domain.HireDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HireMapper {
    public Hire mapToHire(final HireDto hireDto) {
        return new Hire(
                hireDto.getId(),
                hireDto.getCopy(),
                hireDto.getUser(),
                hireDto.getHireDate(),
                hireDto.getReturnDate());

    }

    public HireDto mapToHireDto(final Hire hire) {
        return new HireDto(
                hire.getId(),
                hire.getCopy(),
                hire.getUser(),
                hire.getHireDate(),
                hire.getReturnDate());
    }

    public List<HireDto> mapToHireDtoList(final List<Hire> hireList) {
        return hireList.stream()
                .map(t -> new HireDto(t.getId(), t.getCopy(), t.getUser(), t.getHireDate(), t.getReturnDate()))
                .collect(Collectors.toList());
    }

}
