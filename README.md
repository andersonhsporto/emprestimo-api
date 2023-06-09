# Emprestimo Api

## Descrição

Api REST para gerenciamento de clientes e empréstimos.

## Tecnologias

* [JAVA 8](https://www.java.com/pt-BR/) - Linguagem de programação (JDK 1.8).
* [Spring](https://spring.io/projects/spring-boot) - Framework MVC.
* [Apache Maven 3.8.6](https://maven.apache.org/) - Gerenciador de dependências.
* [IntelliJ](https://www.jetbrains.com/idea/) - IDE para desenvolvimento.
* [Docker](https://www.docker.com/) - Serviço de virtualização.
* [H2 Database](https://www.h2database.com/html/main.html) - Banco de dados relacional escrito em Java que funciona em memória.

## Qualidade de código

O projeto foi desenvolvido utilizando o SonarQube para verificar a qualidade de código. O resultado do SonarQube pode ser visto abaixo:

    
<img src="https://sonarcloud.io/api/project_badges/measure?project=andersonhsporto_emprestimo-api&metric=alert_status" 
        alt="sonarcloud" 
        align="left"
        style="display: block; margin: 0 auto" />

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=andersonhsporto_emprestimo-api&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=andersonhsporto_emprestimo-api)

<img src="https://user-images.githubusercontent.com/47704550/232556805-8822145b-e605-4dc8-a691-6cbebbf0f82d.png" 
        alt="Picture" 
        width="580 "
        height="298" 
        style="display: block; margin: 0 auto" />

## Instalação

Para executar o projeto você pode utilizar a IDE de sua preferência, executar através do Maven ou através do Docker.

### IDE

Para executar o projeto através da IDE, basta importar o projeto como um projeto Maven e executar a classe EmprestimoApiApplication.

### Maven

Para executar o projeto através do Maven, basta executar o comando abaixo na raiz do projeto:

```bash
mvn spring-boot:run
```

### Docker

Para executar o projeto através do Docker, basta executar o comando abaixo na raiz do projeto:

```bash
docker-compose up
```

### Testes de Unidade

Para executar os testes de unidade, basta executar o comando abaixo na raiz do projeto:

```bash
mvn test
```

este comando irá executar os testes de unidade e exibirá o resultado no console.

## Requisitos do sistema

- Possuir o JDK 1.8 instalado
- Possuir o Maven instalado
- Possuir o Docker instalado ( Opcional )
- Uma IDE ou editor de sua preferência ( Opcional )

## Dependências


&emsp;As dependências são declaradas no
arquivo [pom.xml](https://github.com/andersonhsporto/emprestimo-api/blob/master/pom.xml).

|         Dependência          |                             Descrição                             | Versão  |
|:----------------------------:|:-----------------------------------------------------------------:|:-------:|
|          H2database          | Banco de dados relacional escrito em Java que funciona em memória | 2.1.214 |
| Spring-boot-starter-data-jpa | Responsável por conectar a aplicação Spring com o banco de dados  |  2.7.9  |
|   Spring-boot-starter-web    |               Responsável pela camada MVC do Spring               |  2.7.9  |
|        Project-lombok        |          Biblioteca para reduzir a verbosidade do código          | 1.18.20 |
|     Mapstruct-processor      |     Responsável por gerar os mappers de conversão de objetos      |  1.4.2  |
|      Spring Validation       |       Responsável por validar os objetos de entrada da API        |  2.7.9  |
|           Junit 5            |           Biblioteca para criação de testes de unidade            |  5.8.1  |
| Jacoco-maven-plugin          | Responsável por gerar o relatório de cobertura de testes do Sonar |  0.8.7  |

## Endpoints

### Clientes

| Método |         Endpoint         |               Descrição                |
|:------:|:------------------------:|:--------------------------------------:|
|  GET   |    "/api/v1/clientes"    | Retorna todos os clientes cadastrados  |
|  GET   | "/api/v1/clientes/{cpf}" | Retorna o cliente com o cpf informado  |
|  POST  |    "/api/v1/clientes"    |        Cadastra um novo cliente        |
|  PUT   | "/api/v1/clientes/{cpf}" | Atualiza o cliente com o cpf informado |
| DELETE | "/api/v1/clientes/{cpf}" |  Deleta o cliente com o cpf informado  |

*Ao atualizar um cliente (PUT), os campos que não forem informados serão mantidos.*

### Empréstimos

| Método |                 Endpoint                  |                              Descrição                               |
|:------:|:-----------------------------------------:|:--------------------------------------------------------------------:|
|  GET   |    "/api/v1/clientes/{cpf}/emprestimos    |     Retorna todos os empréstimos do cliente com o cpf informado      |
|  GET   | "/api/v1/clientes/{cpf}/emprestimos/{id}" | Retorna o empréstimo do cliente com o cpf informado e o id informado |
|  POST  |   "/api/v1/clientes/{cpf}/emprestimos"    |    Cadastra um novo empréstimo para o cliente com o cpf informado    |
| DELETE | "/api/v1/clientes/{cpf}/emprestimos/{id}" | Deleta o empréstimo do cliente com o cpf informado e o id informado  |

## Exemplos de requisições


### Postman

Para facilitar o teste dos endpoints, foi criado uma collection no Postman com todos os endpoints e exemplos de requisições.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://www.postman.com/lively-resonance-591127/workspace/emprestimo-api/collection/19722349-38ae9e8a-c659-4caa-9be0-3d923a63320a?action=share&creator=19722349)


## Json de exemplo

Para facilitar o entendimento do projeto, segue abaixo um json de exemplo para cada recurso.

### Cliente

```json
{
    "nome": "Frodo Bolseiro",
    "cpf": "81599250004",
    "telefone": "11999999999",
    "rendimentoMensal": 100000,
    "rua": "1 Bagshot Row, Condado",
    "numero": 1123,
    "cep": "04111-111"
}
```


* O campo CPF é utilizado como identificador do cliente, portanto não pode ser repetido e deve ser um CPF válido.
* O campo telefone deve ser um número de telefone válido celular ou fixo ( 10 ou 11 dígitos) sem máscara.
* O campo rendimentoMensal deve ser um número positivo.
* O campo CEP deve ser um CEP válido no formato 00000-000.


### Empréstimo

```json
{
    "valorInicial": 1.00,
    "dataInicio": "2017-01-13",
    "dataFinal": "2017-01-13",
    "relacionamento": "GOLD"
}
```

* O campo valorInicial deve ser um número positivo.
* O campo dataInicio deve ser uma data válida no formato yyyy-MM-dd.
* O campo dataFinal deve ser uma data válida no formato yyyy-MM-dd.
* O campo relacionamento descreve o relacionamento do cliente com a empresa este é utilizado para calcular o valor final do empréstimo. 
Os valores possíveis são: OURO, PRATA e BRONZE ou suas respectivas traduções em inglês (GOLD, SILVER e BRONZE).

### Clientes

#### GET /api/v1/clientes

```bash
curl --location --request GET 'http://localhost:8080/api/v1/clientes'
```

#### GET /api/v1/clientes/{cpf}

```bash
curl --location --request GET 'http://localhost:8080/api/v1/clientes/81599250004'
```

#### POST /api/v1/clientes

```bash
curl --location --request POST 'http://localhost:8080/api/v1/clientes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome": "Frodo Bolseiro",
    "cpf": "81599250004",
    "telefone": "11999999999",
    "rendimentoMensal": 100000,
    "rua": "1 Bagshot Row, Condado",
    "numero": 1123,
    "cep": "04111-111"
}'
```

#### PUT /api/v1/clientes/{cpf}

```bash
curl --location --request PUT 'http://localhost:8080/api/v1/clientes/81599250004' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome": "Frodo Bolseiro",
    "cpf": "81599250004",
    "telefone": "11999999999",
    "rendimentoMensal": 1000000,
    "rua": "Valfenda",
    "numero": 1123,
    "cep": "04111-111"
}'
```

#### DELETE /api/v1/clientes/{cpf}

```bash
curl --location --request DELETE 'http://localhost:8080/api/v1/clientes/81599250004'
```

### Empréstimos

#### GET /api/v1/clientes/{cpf}/emprestimos

```bash

curl --location --request GET 'http://localhost:8080/api/v1/clientes/81599250004/emprestimos'
```

#### GET /api/v1/clientes/{cpf}/emprestimos/{id}

```bash

curl --location --request GET 'http://localhost:8080/api/v1/clientes/81599250004/emprestimos/1'
```

#### POST /api/v1/clientes/{cpf}/emprestimos

```bash
curl --location --request POST 'http://localhost:8080/api/v1/clientes/81599250004/emprestimos' \
--header 'Content-Type: application/json' \
--data-raw '{
    "valorInicial": 1.00,
    "dataInicio": "2017-01-13",
    "dataFinal": "2017-01-13",
    "relacionamento": "GOLD"
}'
```

#### DELETE /api/v1/clientes/{cpf}/emprestimos/{id}

```bash
curl --location --request DELETE 'http://localhost:8080/api/v1/clientes/81599250004/emprestimos/1'
```

### Banco de dados

O banco de dados utilizado foi o H2, que é um banco de dados em memória. Para acessar o console do banco de dados, basta acessar a url http://localhost:8080/h2-console e inserir as informações abaixo:

|    Campo     |         Valor          |
|:------------:|:----------------------:|
| Driver Class |     org.h2.Driver      |
|   JDBC URL   | jdbc:h2:mem:emprestimo |   
|  User Name   |           sa           |
|   Password   |      {em branco}       |

#### Relacionamento entre as entidades

O relacionamento entre as entidades Cliente e Empréstimo é de um para muitos, ou seja, um cliente pode ter vários empréstimos, mas um empréstimo só pode ter um cliente.
Além disso, o relacionamento entre as entidades Cliente e Endereço é de um para um, ou seja, um cliente só pode ter um endereço.

![Untitled](https://user-images.githubusercontent.com/47704550/230756413-11ceaabd-5297-4563-b8ff-8a719bf5a70a.png)


## Informações de contato

Caso tenha alguma dúvida, sugestão ou crítica, entre em contato comigo pelo [email](mailto:anderson.higo2@gmail.com)
ou pelo [LinkedIn](https://www.linkedin.com/in/andersonhsporto/).
