package dev.anderson.emprestimoapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

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
