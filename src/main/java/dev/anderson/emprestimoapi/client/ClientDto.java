package dev.anderson.emprestimoapi.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class ClientDto {

    @JsonProperty("nome")
    @NotNull(message = "'nome' nao pode ser nulo")
    @NotEmpty(message = "'nome' nao pode ser vazio")
    private String name;

    @JsonProperty("cpf")
    @CPF(message = "CPF inválido")
    @NotNull(message = "'cpf' nao pode ser nulo")
    @NotEmpty(message = "'cpf' nao pode ser vazio")
    private String cpf;

    @JsonProperty("telefone")
    @Pattern(regexp = "^\\d+$", message = "O campo 'telefone' deve conter apenas números")
    @Pattern(regexp = "^.{10,11}$", message = "O campo 'telefone' deve conter entre 10 e 11 números")
    private String telephone;

    @JsonProperty("rendimentoMensal")
    @NotNull(message = "'rendimentoMensal' nao pode ser nulo")
    @Min(value = 1, message = "O campo 'rendimentoMensal' deve ser maior que 0")
    @DecimalMin(value = "0.0", inclusive = false, message = "O campo 'rendimentoMensal' deve ser maior que 0")
    private BigDecimal salary;

    @JsonProperty("rua")
    private String street;

    @JsonProperty("numero")
    @Digits(integer = 10, fraction = 0, message = "O campo 'numero' deve ser um número inteiro")
    @Pattern(regexp = "^[1-9]\\d*$", message = "O campo 'numero' não pode ser 0 ou iniciar com 0")
    private String number;

    @JsonProperty("cep")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O campo 'CEP' deve estar no formato '99999-999'")
    private String zipCode;
}
