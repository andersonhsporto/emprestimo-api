package dev.anderson.emprestimoapi.common.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo 'rua' é obrigatório")
    private String street;

    @NotNull(message = "O campo 'numero' é obrigatório")
    private String number;

    @NotNull(message = "O campo 'CEP' é obrigatório")
    private String zipCode;

}
