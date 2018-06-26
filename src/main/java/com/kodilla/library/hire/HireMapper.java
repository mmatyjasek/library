package com.kodilla.library.hire;

import java.util.List;
import java.util.stream.Collectors;

public class HireMapper {

    static HireDto mapToHireDto(final Hire hire) {
        return new HireDto(
                hire.getId(),
                hire.getCopy(),
                hire.getUser(),
                hire.getHireDate(),
                hire.getReturnDate());
    }

    static List<HireDto> mapToHireDtoList(final List<Hire> hireList) {
        return hireList.stream()
                .map(t -> new HireDto(t.getId(), t.getCopy(), t.getUser(), t.getHireDate(), t.getReturnDate()))
                .collect(Collectors.toList());
    }

}
