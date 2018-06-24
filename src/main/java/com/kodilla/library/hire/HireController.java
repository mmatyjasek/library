package com.kodilla.library.hire;

import com.kodilla.library.exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class HireController {

    private final HireDbService hireDbService;
    private final HireMapper hireMapper;

    @Autowired
    public HireController(HireDbService hireDbService, HireMapper hireMapper) {
        this.hireDbService = hireDbService;
        this.hireMapper = hireMapper;
    }

    @GetMapping(value = "getHires")
    public List<HireDto> getHires(){
        return hireMapper.mapToHireDtoList(hireDbService.getAllHires());
    }

    @PostMapping(value = "hire")
    public HireDto hire(@RequestParam Long bookId, @RequestParam Long userId){
        return hireMapper.mapToHireDto(hireDbService.hire(bookId, userId));
    }

    @PutMapping(value = "reportLostDamage")
    public void reportLostDamage(@RequestParam Long hireId) {
        hireDbService.reportLostDamage(hireId);
    }
    @PutMapping(value = "returnBook")
    public void returnBook(@RequestParam Long hireId) throws NotFoundEntityException{
        hireDbService.returnBook(hireId);
    }
}
