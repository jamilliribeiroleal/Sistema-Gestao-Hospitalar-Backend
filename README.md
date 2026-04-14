# VidaPlus Backend

Backend da plataforma **VidaPlus**, um sistema de gestão hospitalar e de serviços de saúde desenvolvido com **Spring Boot**.  
O projeto foi criado para centralizar e otimizar processos assistenciais e administrativos, integrando funcionalidades como cadastro de pacientes e profissionais, consultas, prontuários, receitas, teleconsultas, internações, auditoria e autenticação.

---

## Sobre o projeto

A instituição **VidaPlus** atua na administração de hospitais, o que exige uma solução capaz de integrar diferentes serviços e perfis de usuários em um único ambiente.

Este projeto tem como objetivo oferecer uma **API REST segura, organizada e escalável**, permitindo o gerenciamento das principais operações da instituição, com foco em:

- cadastro e gerenciamento de pacientes
- cadastro e gerenciamento de profissionais de saúde
- controle de consultas presenciais e teleconsultas
- gerenciamento de prontuários e receitas
- controle de leitos, unidades e internações
- autenticação e autorização com diferentes níveis de acesso
- auditoria de ações realizadas no sistema

---

## Tecnologias utilizadas

### Backend
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security
- Bean Validation
- Flyway
- MySQL
- JWT

### Documentação e utilitários
- Springdoc OpenAPI / Swagger
- ModelMapper
- Maven

### Ferramentas de desenvolvimento
- Eclipse / VS Code
- Git e GitHub
- Postman

---

## Arquitetura do projeto

O projeto foi estruturado em camadas para facilitar a manutenção, organização e escalabilidade:

```text
src/main/java/br/com/vidaplus/vidaplusbackend
├── config
├── controllers
├── dto
├── entities
├── enums
├── exceptions
├── mappers
├── repositories
├── security
├── services
└── util
```
## Arquitetura do projeto

O backend foi organizado em camadas para facilitar manutenção, escalabilidade e clareza do código:

- **config**: configurações gerais da aplicação
- **controllers**: endpoints da API
- **dto**: objetos de entrada e saída
- **entities**: entidades do domínio
- **enums**: enums que representam estados e tipos do sistema
- **exceptions**: tratamento global de erros e exceções customizadas
- **mappers**: conversão entre entidades e DTOs
- **repositories**: acesso aos dados com Spring Data JPA
- **security**: autenticação e autorização com JWT
- **services**: regras de negócio
- **util**: classes utilitárias
  

## Principais funcionalidades

### Gestão de usuários e acesso
- autenticação com JWT
- controle de perfis e permissões
- identificação do usuário autenticado
- separação de acesso por papéis do sistema

### Gestão de pacientes
- cadastro de pacientes
- atualização de dados
- visualização de informações resumidas e detalhadas

### Gestão de profissionais
- cadastro de profissionais de saúde
- atualização de dados
- visualização de perfis e agenda

### Consultas e teleconsultas
- criação e gerenciamento de consultas
- registro de status de consulta
- suporte a teleconsultas

### Prontuários e receitas
- registro de prontuários clínicos
- emissão e consulta de receitas
- suporte a receita digital

### Estrutura hospitalar
- cadastro e gerenciamento de unidades
- gerenciamento de leitos
- controle de internações

### Administração e auditoria
- endpoints administrativos
- relatórios resumidos
- auditoria de ações do sistema

## Segurança

A aplicação utiliza **Spring Security** com **JWT** para proteger os endpoints.

### Recursos de segurança implementados
- autenticação por token
- controle de acesso baseado em perfil
- filtro de autenticação JWT
- tratamento de acesso negado
- tratamento de requisições não autorizadas

## Banco de dados

O banco de dados utilizado é o **MySQL**, com controle de versionamento do schema por meio do **Flyway**.

### Migrations
- `V1__estrutura_inicial_vidaplus.sql`
- `V2__seeds_iniciais_vidaplus.sql`

Essas migrations são responsáveis por:
- criar a estrutura inicial do banco
- inserir dados iniciais para testes e desenvolvimento

## Como executar o projeto

### Pré-requisitos

Antes de iniciar, você precisa ter instalado:

- Java 17
- Maven
- MySQL
- Git

### Autora

## Jamilli Ribeiro Leal
Analista de Dados em formação e desenvolvedora back-end com interesse em projetos de tecnologia, dados e soluções aplicadas a problemas reais.
