package com.kodilla.library.hire;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HireMapper {

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
