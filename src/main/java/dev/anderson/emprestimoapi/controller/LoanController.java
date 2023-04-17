package dev.anderson.emprestimoapi.controller;

import dev.anderson.emprestimoapi.dto.LoanDto;
import dev.anderson.emprestimoapi.exceptions.ClientNotFoundException;
import dev.anderson.emprestimoapi.exceptions.LoanNotFoundException;
import dev.anderson.emprestimoapi.exceptions.MaxLoanException;
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
    public LoanDto makeLoan(@PathVariable String cpf, @Valid @RequestBody LoanDto loanDto) throws ClientNotFoundException, MaxLoanException {
        return loanService.makeLoan(cpf, loanDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLoan(@PathVariable String cpf, @PathVariable Long id) throws ClientNotFoundException, LoanNotFoundException {
        loanService.deleteLoan(cpf, id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LoanDto getLoan(@PathVariable String cpf, @PathVariable Long id) throws ClientNotFoundException, LoanNotFoundException {
        return loanService.getLoan(cpf, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LoanDto> getAllLoans(@PathVariable String cpf) throws ClientNotFoundException {
        return loanService.getAllLoans(cpf);
    }
}
