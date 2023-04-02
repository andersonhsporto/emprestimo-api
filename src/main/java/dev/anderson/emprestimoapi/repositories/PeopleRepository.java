package dev.anderson.emprestimoapi.repositories;

import dev.anderson.emprestimoapi.entities.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<PeopleEntity, Long> {
}
