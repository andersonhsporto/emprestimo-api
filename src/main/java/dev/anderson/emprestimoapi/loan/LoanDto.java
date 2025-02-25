package dev.anderson.emprestimoapi.loan;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.anderson.emprestimoapi.common.types.Membership;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanDto {

    private Long id;

    private String cpf;

    @JsonProperty("valorInicial")
    @NotNull(message = "O campo 'ValorInicial' é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O campo 'ValorInicial' deve ser maior que 0")
    private BigDecimal startValue;

    @JsonProperty("dataInicio")
    @NotNull(message = "O campo 'dataInicio' é obrigatório")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonProperty("dataFinal")
    @NotNull(message = "O campo 'dataFinal' é obrigatório")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @JsonProperty("relacionamento")
    @NotNull(message = "O campo 'relacionamento' é obrigatório")
    private Membership membership;

    @JsonProperty("ValorFinal")
    private BigDecimal endValue;
}
