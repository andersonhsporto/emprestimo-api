package dev.anderson.emprestimoapi.client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    ClientEntity findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
