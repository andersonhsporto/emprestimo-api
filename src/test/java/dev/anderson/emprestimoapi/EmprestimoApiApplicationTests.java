package dev.anderson.emprestimoapi;

import dev.anderson.emprestimoapi.controller.ClientController;
import dev.anderson.emprestimoapi.controller.LoanController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class EmprestimoApiApplicationTests {

    private final ClientController clientController;

    private final LoanController loanController;

    @Autowired
    public EmprestimoApiApplicationTests(ClientController clientController, LoanController loanController) {
        this.clientController = clientController;
        this.loanController = loanController;
    }

    @Test
    @DisplayName("Test if context loads successfully and controllers are not null")
    void contextLoads() {
        assertThat(clientController).isNotNull();
        assertThat(loanController).isNotNull();
    }

}
