package dev.anderson.emprestimoapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo 'nome' é obrigatório")
    private String name;

    @CPF(message = "O campo 'CPF' deve estar no formato '999.999.999-99'")
    private String cpf;

    @NotEmpty(message = "O campo 'telefone' é obrigatório")
    private String telephone;

    @NotNull(message = "O campo 'salario' é obrigatório")
    private BigDecimal salary;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LoanEntity> loans;

    public void update(ClientEntity client) {
        if (client.name != null)
            this.name = client.name;
        if (client.cpf != null)
            this.cpf = client.cpf;
        if (client.telephone != null)
            this.telephone = client.telephone;
        if (client.salary != null)
            this.salary = client.salary;
        if (client.address != null)
            this.address = client.address;
    }

    public Integer getNumberOfLoans() {
        return loans.size();
    }

    public void addLoan(LoanEntity loan) {
        loans.add(loan);
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telephone='" + telephone + '\'' +
                ", salary=" + salary +
                ", address=" + address +
                '}';
    }
}
