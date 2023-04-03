package dev.anderson.emprestimoapi.controller;

import dev.anderson.emprestimoapi.dto.LoanDto;
import dev.anderson.emprestimoapi.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/clientes/{cpf}/emprestimos")
@AllArgsConstructor
public class LoanController {

    private LoanService loanService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String makeLoan(@PathVariable String cpf, @Valid @RequestBody LoanDto loanDto) throws Exception {
        return loanService.makeLoan(cpf, loanDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLoan(@PathVariable String cpf, @PathVariable Long id) throws Exception {
        loanService.deleteLoan(cpf, id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LoanDto getLoan(@PathVariable String cpf, @PathVariable Long id) throws Exception {
        return loanService.getLoan(cpf, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LoanDto> getAllLoans(@PathVariable String cpf) throws Exception {
        return loanService.getAllLoans(cpf);
    }
}
