# Integrantes:
* Fernanda Menon - RM 554673
* Gabriel Machado - RM 556714
* Luiza Macena - RM 556237
* Roger Cardoso - RM 557230

# Sistema de Gestão de Consumo – Challenge Dasa

## Objetivo do Projeto
O **Sistema de Gestão de Consumo** foi desenvolvido como parte do **Challenge Dasa – Sprint 4**, com o objetivo de solucionar o problema de **baixa visibilidade no consumo de insumos** nas unidades de diagnóstico.  

O sistema busca oferecer **controle, rastreabilidade e visibilidade** sobre o consumo de **descartáveis, equipamentos e remédios**, permitindo maior eficiência operacional e otimização de recursos.

---

## Escopo e Descrição da Solução
O projeto implementa uma **API RESTful** em **Java (Spring Boot)**, estruturada segundo o padrão **DDD (Domain-Driven Design)**.  
A solução permite **gerenciar o estoque de insumos hospitalares**, com operações de **cadastro, atualização, listagem e exclusão** de produtos.

Cada tipo de item (descartável, equipamento e remédio) possui sua entidade, controller, service e repositório próprios, respeitando os princípios de separação de responsabilidade e modularidade.

---

## Estrutura do Projeto

DDDJava-ChallengeDasa_Sprint4/

└── estoque/  
├── pom.xml  
├── src/  
│ ├── main/java/com/example/estoque/dasa/  
│ │ ├── controller/  
│ │ │ ├── DescartavelController.java  
│ │ │ ├── EquipamentoController.java  
│ │ │ └── RemedioController.java  
│ │ ├── model/  
│ │ │ ├── CategoriaRisco.java  
│ │ │ ├── Descartavel.java  
│ │ │ ├── Equipamento.java  
│ │ │ ├── Produto.java  
│ │ │ ├── Remedio.java  
│ │ │ └── TipoDescartavel.java  
│ │ ├── repository/  
│ │ │ ├── DescartavelRepository.java  
│ │ │ ├── EquipamentoRepository.java  
│ │ │ └── RemedioRepository.java  
│ │ ├── service/  
│ │ │ ├── DescartavelService.java  
│ │ │ ├── EquipamentoService.java  
│ │ │ └── RemedioService.java  
│ │ └── util/  
│ │ └── BooleanToCharConverter.java  
│ └── resources/  
│ ├── application.properties  
│ ├── static/  
│ └── templates/  
├── script/  
│ ├── Tabelas.sql  
│ └── Inserts.sql  
└── collections/  
├── Challenge_Collection_Descartavel.json  
├── Challenge_Collection_Equipamento.json  
└── Challenge_Collection_Remedio.json  


---

## ⚙️ Tecnologias Utilizadas

| Categoria | Tecnologias |
|------------|--------------|
| Linguagem | Java 17 |
| Framework principal | Spring Boot |
| ORM / Banco de Dados | Spring Data JPA |
| Dependência de produtividade | Lombok |
| Banco de dados | Oracle (ajustável via `application.properties`) |
| Build / Gerenciamento | Maven |
| Testes de API | Postman (coleções incluídas) |

---

## Como Executar o Projeto

### Pré-requisitos
- Java JDK 17+
- Maven 3.9+
- IDE (IntelliJ IDEA / Eclipse / VS Code)
- Lombok instalado e habilitado na IDE

### Passos
#### 1. Clone o repositório ou extraia o ZIP:
   
     git clone https://github.com/seu-usuario/DDDJava-ChallengeDasa_Sprint4.git
   
#### 2. Acesse o diretório do projeto:
   
     cd estoque

#### 3. Compile e execute o projeto:
   
     mvn spring-boot:run
   
#### 4. A API estará disponível em:
   
     http://localhost:8080

---

## Principais Funcionalidades

* Cadastro e gerenciamento de produtos (descartáveis, equipamentos, remédios)

* Controle de risco e tipo de descarte (via CategoriaRisco e TipoDescartavel)

* Conversão automática de booleanos para caracteres (BooleanToCharConverter)

* Camadas separadas (controller, service, repository) conforme DDD

* Scripts SQL para criação e carga inicial do banco

* Coleções Postman prontas para teste da API

---

## Benefícios e Resultados Esperados

* Redução de inconsistências no controle de estoque.

* Maior rastreabilidade de insumos.

* Simplificação dos processos de auditoria e reposição.

* Base sólida para expansão futura (ex: relatórios, dashboards, alertas automáticos).
