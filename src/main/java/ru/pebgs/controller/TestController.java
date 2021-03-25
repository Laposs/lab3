package ru.pebgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pebgs.model.Student;

import javax.persistence.EntityManagerFactory;

@RestController
public class TestController {
    @Autowired
    private EntityManagerFactory entityManager;

    @GetMapping(path = "/")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(entityManager.createEntityManager().createNamedQuery("findAll", Student.class).getResultList());
    }
}
