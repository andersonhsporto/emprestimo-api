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

    @JsonProperty("name")
    @NotEmpty(message = "O campo 'nome' é obrigatório")
    private String name;

    @JsonProperty("cpf")
    @CPF
    private String cpf;

    @JsonProperty("telephone")
    @NotEmpty(message = "O campo 'telefone' é obrigatório")
    private String telephone;

    @JsonProperty("salary")
    @NotNull(message = "O campo 'salário' é obrigatório")
    private BigDecimal salary;

    @JsonProperty("street")
    @NotEmpty(message = "O campo 'rua' é obrigatório")
    private String street;

    @JsonProperty("number")
    @NotEmpty(message = "O campo 'número' é obrigatório")
    private String number;

    @JsonProperty("zipCode")
    @NotEmpty(message = "O campo 'CEP' é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O campo 'CEP' deve estar no formato '99999-999'")
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
