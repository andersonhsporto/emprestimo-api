package dev.anderson.emprestimoapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.anderson.emprestimoapi.types.Membership;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanDto {

    private Long id;

    @JsonProperty("CPFCliente")
    @CPF
    private String CPFClient;

    @JsonProperty("ValorInicial")
    @NotNull(message = "O campo 'ValorInicial' é obrigatório")
    private BigDecimal startValue;

    @JsonProperty("dataInicio")
    private LocalDateTime startDateTime;

    @JsonProperty("dataFinal")
    private LocalDateTime endDateTime;

    @JsonProperty("relacionamento")
    private Membership membership;

    private BigDecimal endValue;

    @Override
    public String toString() {
        return "LoanDto{" +
                "CPFClient='" + CPFClient + '\'' +
                ", startValue=" + startValue +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", membership=" + membership +
                ", endValue=" + endValue +
                '}';
    }
}
