# contract-manager-api

**PT 🇧🇷**  
Projeto de estudo e teste para fixar/testar conceitos sobre de arquitetura em nível Júnior em Java.  
O objetivo é simular um gerenciamento de contrato através de uma API Restful, abordando DDD, uso de UUID e boas práticas de desenvolvimento.

**EN 🌎**  
Study and test project to consolidate concepts about architecture as Junior Level in Java.  
The goal is to simulate handler contracts through an API Restful, using concepts of DDD and good practices as well.

---

## 🚀 Tecnologias utilizadas

- **Java 25**
- **Spring Boot 3.5.6**
- **Maven**
- **H2**
- **Teste E2E**
- **Hibernate**
- **Lombok**
- - **Security**

---

## 🎯 Objetivos do projeto

- Criar uma API Restful(Adotando níveis de Richardson)
- Aplicar boas práticas de DDD 
- Manipulação de UUID custom
- Documentação via Swagger.  
- Adotar práticas de segurança pra permitir uso de Spring Security.

---

## 📂 Estrutura inicial do projeto

```
contract-manager-api/
├── src/
│ ├── main/
│ │ ├── java/br/com/gora/contract-manager-api/
│ │ │ ├── contract/
│ │ │ │ ├── controller
│ │ │ │ │ ├── openapi
│ │ │ │ │ │ ├── ContractControllerOpenApi.java
│ │ │ │ │ ├── ContractController.java
│ │ │ │ ├── domain/
│ │ │ │ │ ├── enums
│ │ │ │ │ │ ├── ContractStatus.java
│ │ │ │ │ ├── Contract.java
│ │ │ │ │ ├── ContractBuilder.java
│ │ │ │ │ └── ContractId.java
│ │ │ │ ├── dto/
│ │ │ │ │ ├── CreateContractDTO.java
│ │ │ │ ├── exception/
│ │ │ │ │ ├── CMAContractNameInvalidSizeException.java
│ │ │ │ ├── repository/
│ │ │ │ │ ├── ContractRepository.java
│ │ │ │ └── service/
│ │ │ │ │ ├── ContractService.java
│ │ │ ├── infra/
│ │ │ │ │ ├── ContractRepository.java
│ │ └── resources/
│ │ └── application.yml
│ └── test/
│ └── ...
└── pom.xml
```

## 🧩 Autor

**Bruno Silva**  
Projeto pessoal de estudos.  

---

## 📜 Licença

Este projeto é apenas para fins educacionais e não possui fins comerciais.

---

