package dev.anderson.emprestimoapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class ClientDto {

    @JsonProperty("nome")
    @NotEmpty(message = "O campo 'nome' é obrigatório")
    private String name;

    @JsonProperty("cpf")
    @CPF
    private String cpf;

    @JsonProperty("telefone")
    @NotEmpty(message = "O campo 'telefone' é obrigatório")
    private String telephone;

    @JsonProperty("salario")
    @NotNull(message = "O campo 'salario' é obrigatório")
    private BigDecimal salary;

    @JsonProperty("rua")
    @NotEmpty(message = "O campo 'rua' é obrigatório")
    private String street;

    @JsonProperty("numero")
    @NotEmpty(message = "O campo 'numero' é obrigatório")
    private String number;

    @JsonProperty("cep")
    @NotEmpty(message = "O campo 'cep' é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O campo 'cep' deve estar no formato '99999-999'")
    private String zipCode;

    @Override
    public String toString() {
        return "ClientDto { " +
                "name = '" + name + '\'' +
                ", cpf = '" + cpf + '\'' +
                ", telephone = '" + telephone + '\'' +
                ", salary = " + salary +
                ", street = '" + street + '\'' +
                ", number = '" + number + '\'' +
                ", zipCode = '" + zipCode + '\'' +
                " } ";
    }
}
