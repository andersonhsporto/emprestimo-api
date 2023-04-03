package dev.anderson.emprestimoapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class ClientDto {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("CPF")
    @CPF(message = "O campo 'CPF' deve estar no formato '999.999.999-99'")
    private String cpf;

    @JsonProperty("telefone")
    private String telephone;

    @JsonProperty("salario")
    @DecimalMin(value = "0.0", inclusive = false, message = "O campo 'salario' deve ser maior que 0")
    private BigDecimal salary;

    @JsonProperty("rua")
    private String street;

    @JsonProperty("numero")
    @Digits(integer = 5, fraction = 0, message = "O campo 'numero' deve ser um número inteiro")
    private String number;

    @JsonProperty("CEP")
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
