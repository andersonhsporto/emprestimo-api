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

    @JsonProperty("cpf")
    @CPF(message = "CPF inválido")
    private String cpf;

    @JsonProperty("telefone")
    @Pattern(regexp = "^\\d+$", message = "O campo 'telefone' deve conter apenas números")
    @Pattern(regexp = "^.{10,11}$", message = "O campo 'telefone' deve conter entre 10 e 11 números")
    private String telephone;

    @JsonProperty("rendimentoMensal")
    @DecimalMin(value = "0.0", inclusive = false, message = "O campo 'rendimentoMensal' deve ser maior que 0")
    private BigDecimal salary;

    @JsonProperty("rua")
    private String street;

    @JsonProperty("numero")
    @Digits(integer = 10, fraction = 0, message = "O campo 'numero' deve ser um número inteiro")
    private String number;

    @JsonProperty("cep")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O campo 'CEP' deve estar no formato '99999-999'")
    private String zipCode;
}
