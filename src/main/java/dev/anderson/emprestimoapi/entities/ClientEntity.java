package dev.anderson.emprestimoapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    private String name;

    private String cpf;

    private String telephone;

    private BigDecimal salary;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LoanEntity> loans;

    public void update(ClientEntity client) {
        this.name = client.name;
        this.cpf = client.cpf;
        this.telephone = client.telephone;
        this.salary = client.salary;
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
