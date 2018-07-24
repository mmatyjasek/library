package com.kodilla.library.copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class CopyController {

    private final CopyDbService copyDbService;
    private final  CopyMapper copyMapper;

    @Autowired
    public CopyController(CopyDbService copyDbService, CopyMapper copyMapper) {
        this.copyDbService = copyDbService;
        this.copyMapper = copyMapper;
    }

    @GetMapping(value = "getCopies")
    public List<CopyDto> getCopiesOf(@RequestParam Long bookId){
        return copyMapper.mapToCopyDtoList(copyDbService.getCopiesOf(bookId));
    }

    @GetMapping(value = "getNoOfCopiesOf")
    public Long getNoOfCopiesOf(@RequestParam Long bookId){
        return  copyDbService.getNoOfCopiesOf(bookId);
    }

    @PostMapping(value = "createCopy")
    public CopyDto createCopy(@RequestParam Long bookId) {
        return copyMapper.mapToCopyDto(copyDbService.saveCopy(bookId));
    }

    @DeleteMapping(value = "deleteCopy")
    public void deleteCopy(@RequestParam Long id){
        copyDbService.deleteCopy(id);
    }


}
