package dev.anderson.emprestimoapi.service;

import dev.anderson.emprestimoapi.dto.ClientDto;
import dev.anderson.emprestimoapi.entities.ClientEntity;
import dev.anderson.emprestimoapi.exceptions.ClientDuplicatedException;
import dev.anderson.emprestimoapi.exceptions.ClientNotFoundException;
import dev.anderson.emprestimoapi.mapper.ClientMapper;
import dev.anderson.emprestimoapi.repositories.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.main.banner-mode=off")
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ClientServiceTest {

    private final ClientService clientService;

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceTest(ClientService clientService,
                             ClientRepository clientRepository,
                             ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Test
    @DisplayName("Test if client that does not exist is created successfully")
    void makeClientThatDoesNotExist() throws ClientDuplicatedException {

        ClientDto clientDto = new ClientDto();
        clientDto.setCpf("12345678901");
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        clientService.makeClient(clientDto);

        ClientEntity clientEntityAfter = clientRepository.findByCpf(clientDto.getCpf());
        ClientDto clientDtoAfter = clientMapper.toDto(clientEntityAfter);

        assertEquals(clientDto, clientDtoAfter);
    }

    @Test
    @DisplayName("Test if client that already exists throws ClientDuplicatedException")
    void makeClientThatAlreadyExists() {
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

        assertThrows(ClientDuplicatedException.class, () -> clientService.makeClient(clientDto));
    }

    @Test
    @DisplayName("Test if getAllClients returns an empty list when there are no clients")
    void getAllClientsThatDoesNotExist() {
        List<ClientDto> clientDtoList = clientService.getAllClients();

        assertTrue(clientDtoList.isEmpty());
    }

    @Test
    @DisplayName("Test if getAllClients returns a list with one client when there is one client")
    void getAllClientWhenThereAreClients() {
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

        List<ClientDto> clientDtoList = clientService.getAllClients();

        assertFalse(clientDtoList.isEmpty());
    }

    @Test
    @DisplayName("Test if getClientByCpf throws ClientNotFoundException when client does not exist")
    void getClientByCpfThatDoesNotExist() {
        String cpf = "12345678901";
        assertThrows(ClientNotFoundException.class, () -> clientService.getClientByCpf(cpf));
    }

    @Test
    @DisplayName("Test if getClientByCpf returns a client when client exists")
    void getClientByCpfThatExists() throws ClientNotFoundException {
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

        ClientDto clientDtoAfter = clientService.getClientByCpf(clientDto.getCpf());

        assertEquals(clientDto, clientDtoAfter);
    }

    @Test
    @DisplayName("Test if deleteClientByCpf throws ClientNotFoundException when client does not exist")
    void deleteClientByCpfThatDoesNotExist() {
        String cpf = "12345678901";
        assertThrows(ClientNotFoundException.class, () -> clientService.deleteClientByCpf(cpf));
    }

    @Test
    @DisplayName("Test if deleteClientByCpf deletes a client when client exists")
    void deleteClientByCpfThatExists() throws ClientNotFoundException {
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

        clientService.deleteClientByCpf(clientDto.getCpf());

        assertThrows(ClientNotFoundException.class, () -> clientService.getClientByCpf(clientDto.getCpf()));
    }

    @Test
    @DisplayName("Test if updateClientByCpf throws ClientNotFoundException when client does not exist")
    void updateClientThatDoesNotExist() {
        ClientDto clientDto = new ClientDto();
        clientDto.setCpf("12345678901");
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        String cpf = "12345678901";

        assertThrows(ClientNotFoundException.class, () -> clientService.updateClientByCpf(cpf, clientDto));
    }

    @Test
    @DisplayName("Test if updateClientByCpf updates a client when client exists")
    void updateClientThatExists() throws ClientNotFoundException, ClientDuplicatedException {
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

        clientDto.setName("Frodo Baggins");

        clientService.updateClientByCpf(clientDto.getCpf(), clientDto);

        ClientEntity clientEntityAfterUpdate = clientRepository.findByCpf(clientDto.getCpf());
        ClientDto clientDtoAfterUpdate = clientMapper.toDto(clientEntityAfterUpdate);

        assertEquals(clientDto, clientDtoAfterUpdate);
    }

    @Test
    @DisplayName("Test if updateClientByCpf return a ClientDuplicatedException when client already exists")
    void updateClientThatAlreadyExists() {
        ClientDto clientDtoOld = new ClientDto();
        clientDtoOld.setCpf("05193681506");
        clientDtoOld.setName("Bilbo Baggins");
        clientDtoOld.setStreet("Hobbiton");
        clientDtoOld.setNumber("1");
        clientDtoOld.setTelephone("1122223333");
        clientDtoOld.setZipCode("12345-678");
        clientDtoOld.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        ClientEntity clientEntityOld = clientMapper.toModel(clientDtoOld);
        clientRepository.save(clientEntityOld);

        String cpf = "12345678901";
        ClientDto clientDto = new ClientDto();
        clientDto.setCpf(cpf);
        clientDto.setName("Bilbo Baggins");
        clientDto.setStreet("Hobbiton");
        clientDto.setNumber("1");
        clientDto.setTelephone("1122223333");
        clientDto.setZipCode("12345-678");
        clientDto.setSalary(BigDecimal.valueOf(1000.00).setScale(2));

        ClientEntity clientEntity = clientMapper.toModel(clientDto);
        clientRepository.save(clientEntity);

        clientDto.setName("Frodo Baggins");
        clientDto.setCpf("05193681506");

        assertThrows(ClientDuplicatedException.class, () -> clientService.updateClientByCpf(cpf, clientDto));
    }
}