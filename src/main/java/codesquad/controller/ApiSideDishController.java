package codesquad.controller;

import codesquad.service.SideDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sidedishes")
public class ApiSideDishController {
    @Autowired
    private SideDishService sideDishService;

    @GetMapping("/{word}")
    public ResponseEntity<Iterable<String>> listByWord(@PathVariable String word) {
        return ResponseEntity.status(HttpStatus.OK).body(sideDishService.getListByWord(word));
    }
}
