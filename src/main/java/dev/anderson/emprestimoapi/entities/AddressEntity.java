package dev.anderson.emprestimoapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo 'rua' é obrigatório")
    private String street;

    @NotEmpty(message = "O campo 'número' é obrigatório")
    private String number;

    @NotEmpty(message = "O campo 'CEP' é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}")
    private String zipCode;

}
