package dev.anderson.emprestimoapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/clientes/{cpf}/emprestimos")
@RequiredArgsConstructor
public class LoanController {

    @GetMapping
    public String hello(@PathVariable String cpf) {
        return "Hello World" + cpf;
    }
}
