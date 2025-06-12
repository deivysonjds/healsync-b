# healsync-b
projeto da cadeira de projeto integrador e poo do 3° semestre do curso de sistemas para internet pela Unicap

# 🩺 HealSync - Backend

Repositório oficial do **backend da plataforma HealSync**, um sistema de gerenciamento de **atendimentos, pacientes e funcionários** para instituições de saúde. Este serviço é construído com **Java 17**, **Spring Boot**, e segue princípios RESTful, pronto para integração com o frontend [HealSync Frontend]((https://github.com/deivysonjds/healsync-f) (React + Next.js).

🔗 Repositório do frontend: [https://github.com/deivysonjds/healsync-f](https://github.com/deivysonjds/healsync-f)

---

## ⚙️ Tecnologias Utilizadas

- ✅ Java 21
- ✅ Spring Boot 3.x
- ✅ Spring Web (REST)
- ✅ Spring Data JPA
- ✅ Spring Security (JWT)
- ✅ Banco de Dados: PostgreSQL (ou H2 em dev)
- ✅ Lombok
- ✅ Docker (opcional)
- ✅ Swagger 

---

## 📁 Estrutura do Projeto

📦 src  
┣ 📂main  
┃ ┣ 📂java/com/healsync  
┃ ┃ ┣ 📂controllers → Endpoints REST (pacientes, atendimentos, funcionários, etc)  
┃ ┃ ┣ 📂services → Regras de negócio  
┃ ┃ ┣ 📂repositories → Interfaces JPA  
┃ ┃ ┣ 📂models → Entidades JPA  
┃ ┃ ┣ 📂DTO → Data Transfer Objects  
┃ ┃ ┣ 📂securiy → Configurações de segurança, CORS, JWT, etc.  
┃ ┃ ┗ 📂exceptions → Tratamento de exceções globais  
┃ ┗ 📂resources  
┃ ┣ 📄 application.yml → Configurações de ambiente  
┗ 📂test → Testes automatizados  

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos

- Java 21
- Gradle
- PostgreSQL ( h2 localmente )

### 1. Clonar o repositório

```bash
git clone https://github.com/deivysonjds/healsync-b.git
cd healsync

./gradlew bootrun

```
---

## 🔐 Autenticação

A API utiliza autenticação baseada em **JWT (JSON Web Tokens)**. As rotas protegidas exigem o envio do token no header:


### Fluxo de Autenticação:

1. Usuário envia `POST /login` com email e senha.
2. API retorna o JWT válido.
3. Usuário acessa as rotas protegidas com o token no header.

---

## 📦 Endpoints Principais

A documentação interativa pode ser acessada via:

> 🔍 `http://localhost:8080/swagger-ui/index.html` - localmente  
> 🔍 [swagger](https://healsync-b-production.up.railway.app/swagger-ui/index.html#/) - em produção

---
