package dev.anderson.emprestimoapi.repositories;

import dev.anderson.emprestimoapi.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    ClientEntity findByCpf(String cpf);
}
