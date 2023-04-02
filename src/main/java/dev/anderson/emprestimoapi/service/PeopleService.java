package dev.anderson.emprestimoapi.service;

import dev.anderson.emprestimoapi.repositories.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PeopleService {

    private PeopleRepository peopleRepository;


}
