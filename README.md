# Checkpoint 1 - Java Advanced  
## Sistema de gerenciamento de funcionários com JPA, Hibernate, Oracle e Reflection

## Integrantes
- Eduardo Martins - RM562259
- Vitor Madrigrano - RM564191

## Link do repositório
- https://github.com/Tidlle/CP1_Java.git

---

# Descrição do projeto

Este projeto foi desenvolvido com o objetivo de representar um sistema de gerenciamento de funcionários utilizando Java com persistência em banco de dados Oracle, através de JPA e Hibernate.

A aplicação foi construída com base nos conceitos de orientação a objetos, herança, sobrescrita de métodos, mapeamento objeto-relacional, uso de annotations, camada DAO, integração com Oracle Database XE e uso de Reflection para geração automática de comandos SQL.

O sistema realiza operações completas de CRUD, permitindo:

- cadastrar funcionários
- buscar funcionários por ID
- atualizar dados de funcionários
- remover funcionários do banco
- gerar automaticamente os comandos SQL de cada operação via Reflection
- demonstrar o funcionamento da persistência com Hibernate

---

# Tecnologias utilizadas

## Linguagem
- Java

## Persistência
- JPA 2.2
- Hibernate 5.4.12.Final

## Banco de dados
- Oracle Database Express Edition 21c

## Driver JDBC
- ojdbc8 21.1.0.0

## IDE
- IntelliJ IDEA Community Edition

## Gerenciador de dependências
- Maven

---

# Conceitos aplicados

Durante o desenvolvimento, foram aplicados os seguintes conceitos:

## Orientação a objetos
O projeto foi estruturado com base em classes, encapsulamento, herança e sobrescrita de métodos.

## Herança
A classe `FuncionarioSenior` herda da classe `Funcionario`.

## Sobrescrita
A subclasse `FuncionarioSenior` sobrescreve métodos da superclasse para adaptar regras específicas de salário e exibição de informações.

## JPA
A persistência dos dados foi feita por meio das annotations JPA, mapeando a entidade Java para uma tabela Oracle.

## Hibernate
O Hibernate foi utilizado como implementação da especificação JPA.

## Reflection
Foi criada uma classe responsável por ler a estrutura da entidade em tempo de execução e gerar automaticamente comandos SQL.

## Camada DAO
A lógica de acesso ao banco foi separada em uma camada DAO, facilitando organização, manutenção e reaproveitamento do código.

---

# Estrutura do projeto

A estrutura do projeto foi organizada em pacotes para separar responsabilidades:

```text
src/main/java
└── br/com/fiap
    ├── annotation
    │   └── Descricao.java
    ├── dao
    │   ├── FuncionarioDao.java
    │   └── FuncionarioDaoImpl.java
    ├── entity
    │   ├── Funcionario.java
    │   ├── FuncionarioSenior.java
    │   └── FuncionarioEstagiario.java
    ├── exception
    │   ├── CommitException.java
    │   └── IdNaoEncontradoException.java
    ├── reflection
    │   └── SqlGenerator.java
    └── view
        └── TesteCrud.java

src/main/resources
└── META-INF
    └── persistence.xml