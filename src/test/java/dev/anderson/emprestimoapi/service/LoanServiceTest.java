package dev.anderson.emprestimoapi.service;

import dev.anderson.emprestimoapi.dto.ClientDto;
import dev.anderson.emprestimoapi.dto.LoanDto;
import dev.anderson.emprestimoapi.entities.ClientEntity;
import dev.anderson.emprestimoapi.entities.LoanEntity;
import dev.anderson.emprestimoapi.exceptions.ClientNotFoundException;
import dev.anderson.emprestimoapi.exceptions.LoanNotFoundException;
import dev.anderson.emprestimoapi.exceptions.MaxLoanException;
import dev.anderson.emprestimoapi.mapper.ClientMapper;
import dev.anderson.emprestimoapi.mapper.LoanMapper;
import dev.anderson.emprestimoapi.repositories.ClientRepository;
import dev.anderson.emprestimoapi.repositories.LoanRepository;
import dev.anderson.emprestimoapi.types.Membership;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.main.banner-mode=off")
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class LoanServiceTest {

    private final ClientRepository clientRepository;

    private final LoanRepository loanRepository;

    private final ClientMapper clientMapper;

    private final LoanMapper loanMapper;

    private final LoanService loanService;

    @Autowired
    public LoanServiceTest(ClientRepository clientRepository,
                           LoanRepository loanRepository,
                           ClientMapper clientMapper,
                           LoanMapper loanMapper,
                           LoanService loanService) {
        this.clientRepository = clientRepository;
        this.loanRepository = loanRepository;
        this.clientMapper = clientMapper;
        this.loanMapper = loanMapper;
        this.loanService = loanService;
    }

    @Test
    @DisplayName("Create a loan of a client that does not exist")
    void makeLoanOfClientThatDoesNotExist() {
        String cpf = "12345678901";
        LoanDto loanDto = new LoanDto();
        loanDto.setCpfClient(cpf);
        loanDto.setStartValue(BigDecimal.valueOf(1000.00).setScale(2));
        loanDto.setStartDate(LocalDate.now());
        loanDto.setEndDate(LocalDate.now().plusMonths(10));
        loanDto.setMembership(Membership.GOLD);

        assertThrows(ClientNotFoundException.class, () -> loanService.makeLoan(cpf, loanDto));
    }

    @Test
    @DisplayName("Create a loan of a client that does exist")
    void makeLoanOfClientThatDoesExist() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setCpf("12345678901");
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        ClientEntity clientEntity = clientMapper.toModel(clientDto);
        clientRepository.save(clientEntity);

        String cpf = "12345678901";

        LoanDto loanDto = new LoanDto();
        loanDto.setCpfClient(cpf);
        loanDto.setStartValue(BigDecimal.valueOf(1000.00).setScale(2));
        loanDto.setStartDate(LocalDate.now());
        loanDto.setEndDate(LocalDate.now().plusMonths(10));
        loanDto.setMembership(Membership.GOLD);

        LoanDto loanDtoAfter = loanService.makeLoan(cpf, loanDto);

        assertEquals(loanDtoAfter.getCpfClient(), cpf);
        assert (loanDtoAfter.getStartValue().equals(BigDecimal.valueOf(1000.00).setScale(2)));
        assert (loanDtoAfter.getStartDate().equals(LocalDate.now()));
        assert (loanDtoAfter.getEndDate().equals(LocalDate.now().plusMonths(10)));
        assert (loanDtoAfter.getMembership().equals(Membership.GOLD));
    }

    @Test
    @DisplayName("Create a loan of a client that does exist but is not eligible for loan")
    void makeLoanOfClientNotEligibleForLoan() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setCpf("12345678901");
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        ClientEntity clientEntity = clientMapper.toModel(clientDto);
        clientRepository.save(clientEntity);

        String cpf = "12345678901";

        LoanDto loanDto = new LoanDto();
        loanDto.setCpfClient(cpf);
        loanDto.setStartValue(BigDecimal.valueOf(5000000000.00).setScale(2));
        loanDto.setStartDate(LocalDate.now());
        loanDto.setEndDate(LocalDate.now().plusMonths(10));
        loanDto.setMembership(Membership.GOLD);

        assertThrows(MaxLoanException.class, () -> loanService.makeLoan(cpf, loanDto));
    }

    @Test
    @DisplayName("Create a loan of a client that does exist and is eligible for loan")
    void makeLoanOfClientEligibleForLoan() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setCpf("12345678901");
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        ClientEntity clientEntity = clientMapper.toModel(clientDto);
        clientRepository.save(clientEntity);

        String cpf = "12345678901";

        LoanDto loanDto = new LoanDto();
        loanDto.setCpfClient(cpf);
        loanDto.setStartValue(BigDecimal.valueOf(100).setScale(2));
        loanDto.setStartDate(LocalDate.now());
        loanDto.setEndDate(LocalDate.now().plusMonths(10));
        loanDto.setMembership(Membership.GOLD);

        LoanDto loanDtoAfter = loanService.makeLoan(cpf, loanDto);
        BigDecimal endValueExpected = BigDecimal.valueOf(120.000).setScale(3);

        assertEquals(loanDtoAfter.getEndValue(), endValueExpected);
        assert (loanDtoAfter.getStartDate().equals(LocalDate.now()));
        assert (loanDtoAfter.getEndDate().equals(LocalDate.now().plusMonths(10)));
        assert (loanDtoAfter.getMembership().equals(Membership.GOLD));
    }

    @Test
    @DisplayName("Delete a loan of a client that does not exist")
    void deleteLoanOfClientThatDoesNotExist() {
        String cpf = "12345678901";
        Long id = 42L;

        assertThrows(ClientNotFoundException.class, () -> loanService.deleteLoan(cpf, id));
    }

    @Test
    @DisplayName("Delete a loan of a client that does exist but does not have the loan")
    void deleteLoanThatDoesNotExistOfClientThatExist() {
        ClientDto clientDto = new ClientDto();
        clientDto.setCpf("12345678901");
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        ClientEntity clientEntity = clientMapper.toModel(clientDto);
        clientRepository.save(clientEntity);

        String cpf = clientDto.getCpf();
        Long id = 42L;

        assertThrows(LoanNotFoundException.class, () -> loanService.deleteLoan(cpf, id));
    }

    @Test
    @DisplayName("Delete a loan of a client that does exist and has the loan")
    void deleteLoanThatExistOfClientThatExist() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setCpf("12345678901");
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        ClientEntity clientEntity = clientMapper.toModel(clientDto);
        clientRepository.save(clientEntity);

        String cpf = "12345678901";

        LoanDto loanDto = new LoanDto();
        loanDto.setCpfClient(cpf);
        loanDto.setStartValue(BigDecimal.valueOf(5000000000.00).setScale(2));
        loanDto.setStartDate(LocalDate.now());
        loanDto.setEndDate(LocalDate.now().plusMonths(10));
        loanDto.setMembership(Membership.GOLD);

        LoanEntity loanEntity = loanMapper.toModel(loanDto);
        loanRepository.save(loanEntity);

        Long id = loanEntity.getId();
        loanService.deleteLoan(cpf, id);

        assertFalse(loanRepository.existsById(id));
    }

    @Test
    @DisplayName("Get a loan of a client that does not exist")
    void getLoanOfClientThatDoesNotExist() {
        String cpf = "12345678901";
        Long id = 42L;

        assertThrows(ClientNotFoundException.class, () -> loanService.getLoan(cpf, id));
    }

    @Test
    @DisplayName("Get a loan of a client that does exist but does not have the loan")
    void getLoanThatDoesNotExistOfClientThatExist() {
        ClientDto clientDto = new ClientDto();
        clientDto.setCpf("12345678901");
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        ClientEntity clientEntity = clientMapper.toModel(clientDto);
        clientRepository.save(clientEntity);

        String cpf = clientDto.getCpf();
        Long id = 42L;

        assertThrows(LoanNotFoundException.class, () -> loanService.getLoan(cpf, id));
    }

    @Test
    @Transactional
    @DisplayName("Get a loan of a client that does exist and has the loan")
    void getLoanThatExistOfClientThatExist() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setCpf("12345678901");
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        ClientEntity clientEntity = clientMapper.toModel(clientDto);
        clientEntity.setLoans(new ArrayList<>());

        LoanDto loanDto = new LoanDto();
        loanDto.setCpfClient(clientDto.getCpf());
        loanDto.setStartValue(BigDecimal.valueOf(100.00).setScale(2));
        loanDto.setStartDate(LocalDate.now());
        loanDto.setEndDate(LocalDate.now().plusMonths(10));
        loanDto.setMembership(Membership.GOLD);

        LoanEntity loanEntity = loanMapper.toModel(loanDto);
        loanEntity.setClient(clientEntity);
        loanEntity.updateEndValue();
        clientEntity.addLoan(loanEntity);
        clientRepository.save(clientEntity);

        LoanDto loanDtoAfter = loanService.getLoan(clientDto.getCpf(), loanEntity.getId());

        assertEquals(loanDtoAfter.getEndValue(), loanEntity.getEndValue());
        assertEquals(loanDtoAfter.getStartDate(), loanEntity.getStartDate());
        assertEquals(loanDtoAfter.getEndDate(), loanEntity.getEndDate());
        assertEquals(loanDtoAfter.getMembership(), loanEntity.getMembership());
    }

    @Test
    @DisplayName("Get all loans of a client that does not exist")
    void getAllLoansOfClientThatDoesNotExist() {
        String cpf = "12345678901";

        assertThrows(ClientNotFoundException.class, () -> loanService.getAllLoans(cpf));
    }

    @Test
    @DisplayName("Get all loans of a client that does exist but does not have any loan")
    void getAllLoansOfClientThatDoesExist() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setCpf("12345678901");
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        ClientEntity clientEntity = clientMapper.toModel(clientDto);
        clientEntity.setLoans(new ArrayList<>());

        LoanDto loanDto = new LoanDto();
        loanDto.setCpfClient(clientDto.getCpf());
        loanDto.setStartValue(BigDecimal.valueOf(100.00).setScale(2));
        loanDto.setStartDate(LocalDate.now());
        loanDto.setEndDate(LocalDate.now().plusMonths(10));
        loanDto.setMembership(Membership.GOLD);

        LoanEntity loanEntity = loanMapper.toModel(loanDto);
        loanEntity.setClient(clientEntity);
        loanEntity.updateEndValue();
        clientEntity.addLoan(loanEntity);
        clientRepository.save(clientEntity);

        List<LoanDto> loanDtos = loanService.getAllLoans(clientDto.getCpf());

        assertEquals(1, loanDtos.size());
    }

    @Test
    @DisplayName("Get all loans of a client that does exist but does not have any loan")
    void getAllEmptyLoanListOfClientThatDoesExist() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setCpf("12345678901");
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        ClientEntity clientEntity = clientMapper.toModel(clientDto);
        clientEntity.setLoans(new ArrayList<>());
        clientRepository.save(clientEntity);

        List<LoanDto> loanDtos = loanService.getAllLoans(clientDto.getCpf());

        assertEquals(0, loanDtos.size());
    }

}