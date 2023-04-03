package dev.anderson.emprestimoapi.controller;

import dev.anderson.emprestimoapi.dto.LoanDto;
import dev.anderson.emprestimoapi.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/clientes/{cpf}/emprestimos")
@AllArgsConstructor
public class LoanController {

    private LoanService loanService;
    @PostMapping
    public String makeLoan(@PathVariable String cpf, @Valid @RequestBody LoanDto loanDto) throws Exception {
        return loanService.makeLoan(cpf, loanDto);
    }
}
