package com.kodilla.library.copy;

import java.util.List;
import java.util.stream.Collectors;

public class CopyMapper {

    private CopyMapper() {
        throw new IllegalStateException();
    }

    static CopyDto mapToCopyDto(final Copy copy) {
        return new CopyDto(
                copy.getId(),
                copy.getBook(),
                copy.getStatus(),
                copy.getHires());
    }

    static List<CopyDto> mapToCopyDtoList(final List<Copy> copyList) {
        return copyList.stream()
                .map(t -> new CopyDto(t.getId(), t.getBook(), t.getStatus(), t.getHires()))
                .collect(Collectors.toList());
    }

}
