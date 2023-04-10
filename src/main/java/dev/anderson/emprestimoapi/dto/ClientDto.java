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
    @CPF(message = "Cpf inválido")
    private String cpf;

    @JsonProperty("telefone")
    @Pattern(regexp = "\\d{10,11}", message = "O campo 'telefone' deve conter apenas números")
    private String telephone;

    @JsonProperty("salario")
    @DecimalMin(value = "0.0", inclusive = false, message = "O campo 'salario' deve ser maior que 0")
    private BigDecimal salary;

    @JsonProperty("rua")
    private String street;

    @JsonProperty("numero")
    @Digits(integer = 10, fraction = 0, message = "O campo 'numero' deve ser um número inteiro")
    private String number;

    @JsonProperty("CEP")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O campo 'CEP' deve estar no formato '99999-999'")
    private String zipCode;
}
