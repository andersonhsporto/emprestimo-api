package dev.anderson.emprestimoapi.controller;

import dev.anderson.emprestimoapi.service.PeopleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class PeopleController {

    private PeopleService peopleService;

    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
