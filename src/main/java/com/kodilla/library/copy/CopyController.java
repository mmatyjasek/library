package com.kodilla.library.copy;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("copies")
@AllArgsConstructor
public class CopyController {

    private final CopyDbService copyDbService;

    @GetMapping(value = "getNoOfCopiesOf")
    public Long getNoOfCopiesOf(@RequestParam Long bookId) {
        return copyDbService.getNoOfCopiesOf(bookId);
    }

    @GetMapping
    public List<CopyDto> getCopiesOf(@RequestParam Long bookId) {
        return CopyMapper.mapToCopyDtoList(copyDbService.getCopiesOf(bookId));
    }

    @PostMapping
    public CopyDto createCopy(@RequestParam Long bookId) {
        return CopyMapper.mapToCopyDto(copyDbService.saveCopy(bookId));
    }

    @DeleteMapping
    public void deleteCopy(@RequestParam Long id) {
        copyDbService.deleteCopy(id);
    }


}
