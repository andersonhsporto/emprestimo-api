package dev.anderson.emprestimoapi.repositories;

import dev.anderson.emprestimoapi.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
