# Projeto de API REST com Java, Spring, Maven e Hibernate

Este projeto é uma aplicação backend que utiliza **Java** com o framework **Spring**, gerenciamento de dependências com **Maven**, persistência de dados com **Hibernate**, e documentação de API com **Swagger**.

## Autores

- Bruno de Medeiros - [brunomedeirosluz](https://github.com/brunomedeirosluz)
- Daniel Berbert [Daniel-Silva-Berbert](https://github.com/Daniel-Silva-Berbert)
- Matheus CasaGrande [M-Casagrande](https://github.com/M-Casagrande)

## Documentação da API

A API é documentada usando **Swagger**. Você pode acessar a documentação através do seguinte link:

- [Documentação Swagger](http://localhost:8080/swagger-ui/index.html#/)

## Estrutura do Banco de Dados

O projeto conta com quatro tabelas principais no banco de dados:

### 1. Tabela `Usuario`

A tabela `Usuario` contém as seguintes colunas e relacionamentos:

- **id**: Chave primária, gerada automaticamente.
- **nome**: Campo obrigatório, deve conter apenas letras e espaços.
- **cpf**: Campo obrigatório e único, segue o formato de CPF.
- **nascimento**: Campo obrigatório, do tipo `LocalDate`.
- **userName**: Campo obrigatório e único.
- **email**: Campo obrigatório, segue o formato de e-mail.
- **password**: Campo obrigatório, mínimo de 6 caracteres.
- **permissoes**: Relacionamento `ManyToMany` com a tabela `Permissao`.
- **cargo**: Relacionamento `ManyToOne` com a tabela `Cargo`.
- **projetos**: Relacionamento `ManyToMany` com a tabela `Projeto`.

### 2. Tabela `Projeto`

A tabela `Projeto` contém as seguintes colunas e relacionamentos:

- **id**: Chave primária, gerada automaticamente.
- **nome**: Campo obrigatório, deve conter apenas letras e espaços.
- **usuarios**: Relacionamento `ManyToMany` com a tabela `Usuario`.

### 3. Tabela `Permissao`

A tabela `Permissao` contém as seguintes colunas e relacionamentos:

- **id**: Chave primária, gerada automaticamente.
- **nome**: Campo obrigatório, deve conter apenas letras e espaços.
- **usuarios**: Relacionamento `ManyToMany` com a tabela `Usuario`.

### 4. Tabela `Cargo`

A tabela `Cargo` contém as seguintes colunas e relacionamentos:

- **id**: Chave primária, gerada automaticamente.
- **nome**: Campo obrigatório, deve conter apenas letras e espaços.
- **remuneracao**: Campo obrigatório, do tipo `Float`.
- **usuarios**: Relacionamento `OneToMany` com a tabela `Usuario`.

## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Java 21**: Linguagem de programação.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Maven**: Ferramenta de automação de build e gerenciamento de dependências.
- **Hibernate**: Framework ORM (Object Relational Mapping) para persistência de dados.
- **Swagger**: Ferramenta para documentação da API.

## Executando o Projeto

Para executar este projeto localmente, siga os passos abaixo:

1. Clone o repositório:

   ```bash
   git clone git@github.com:Daniel-Silva-Berbert/Estrutura_base_spring_boot.git
   
2. Abra o projeto em sua IDE preferida.

3. Configure o banco de dados e as credenciais de conexão no arquivo `application.properties`.

4. Execute o projeto como uma aplicação Spring Boot, utilizando o seguinte comando:

   ```bash
   mvn spring-boot:run
