package ru.pebgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.pebgs.model.Mark;
import ru.pebgs.serivce.MainService;

import java.util.Optional;

@RestController
public class MarkController {
    @Autowired
    private MainService mainService;

    @GetMapping(path = "/mark/{id}")
    public ResponseEntity<?> getMarkById(@PathVariable("id") Long id) {
        Optional<Mark> markOptional = mainService.getMarkById(id);
        if (markOptional.isPresent()) {
            return ResponseEntity.ok(markOptional.get());
        }
        return new ResponseEntity<>("{message: \"Not found\"}", HttpStatus.NOT_FOUND);
    }
}
