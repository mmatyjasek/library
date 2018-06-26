package com.kodilla.library.hire;

import com.kodilla.library.exception.NotFoundEntityException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * todo: fix paths
 */
@RestController
@RequestMapping("/v1/library")
@AllArgsConstructor
public class HireController {

    private final HireDbService hireDbService;

    @GetMapping(value = "getHires")
    public List<HireDto> getHires() {
        return HireMapper.mapToHireDtoList(hireDbService.getAllHires());
    }

    @PostMapping(value = "hire")
    public HireDto hire(@RequestParam Long bookId, @RequestParam Long userId) {
        return HireMapper.mapToHireDto(hireDbService.hire(bookId, userId));
    }

    @PutMapping(value = "reportLostDamage")
    public void reportLostDamage(@RequestParam Long hireId) {
        hireDbService.reportLostDamage(hireId);
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestParam Long hireId) throws NotFoundEntityException {
        hireDbService.returnBook(hireId);
    }
}
