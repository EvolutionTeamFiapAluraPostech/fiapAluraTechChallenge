# Pós-Tech-FIAP/ALURA-Fase01-Spring-01

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

# Descrição do projeto
Repositório do projeto do desafio Spring I da pós tech da FIAP/ALURA. Desenvolvimento de uma API de cálculo de quadro de pessoal, utilizando o framework Spring em Java.

## Requisitos:
1. A API deve ser desenvolvida em Java, utilizando o framework Spring.
2. A API deve permitir o cadastro de usuários, incluindo informações como nome, email e senha.

## Critérios de avaliação:
1. Funcionalidade completa e correta da API, incluindo todas as operações solicitadas.
2. Utilização adequada do framework Spring, seguindo as melhores práticas de desenvolvimento.
3. Estruturação do código, incluindo organização de pacotes e classes.
4. Clareza e legibilidade do código.
5. Utilização de boas práticas de segurança, como authenticação e autorização adequadas.
6. Tratamento adequado de erros e exceções.

## Dicas:
1. Utilize as anotações do Spring Framework, como '@RestController' para definir os endpoints da API e '@Service' para implementar a lógica do negócio.
2. Utilize o Spring Security para a autenticação e autorização dos usuários.
3. Utilize uma biblioteca de persistência de dados, como Spring Data JPA, para acessar o banco de dados.
4. Faça testes unitários para garantir a corretude e robustez de sua API.

# Tecnologias utilizadas
1. Java 17
2. Spring Boot 3.1.2
3. Spring Web MVC
4. Spring Data JPA
5. Lombok
6. Postgres 15.1
7. Flyway
8. JUnit

# Setup do Projeto

Para realizar o setup do projeto é necessário possuir o Java 17, docker 24 e docker-compose 1.29 instalado em sua máquina.
Faca o download do projeto (https://github.com/EvolutionTeamFiapAluraPostech/fiapAluraTechChallenge) e atualize suas dependências com o gradle.
Antes de iniciar o projeto é necessário criar o banco de dados. O banco de dados está programado para ser criado em um container. 
Para criar o container, execute o docker-compose.
Acesse a pasta raiz do projeto, no mesmo local onde encontra-se o arquivo docker-compose.yml. Para executá-lo, execute o comando docker-compose up -d (para rodar detached e não prender o terminal).
Para iniciar o projeto, basta executar o Spring Boot Run no IntelliJ.
Após a inicialização do projeto, será necessário se autenticar, pois o Spring Security está habilitado. Para tanto, utilize o Postman (ou outra aplicação de sua preferência), crie um endpoint para realizar a autenticação, com a seguinte url **localhost:8080/authenticate**. No body, inclua um json contendo o atributo “email” com o valor “thomas.anderson@itcompany.com” e outro atributo “password” com o valor “@Bcd1234”. Realize a requisição para este endpoint para se obter o token JWT que deverá ser utilizado para consumir os demais endpoints do projeto.
Segue abaixo instruções do endpoint para se autenticar na aplicação.

POST /authenticate HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 76

{
"email": "[thomas.anderson@itcompany.com](mailto:thomas.anderson@itcompany.com)",
"password": "@Bcd1234"
}