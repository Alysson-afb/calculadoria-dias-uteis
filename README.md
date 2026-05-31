# Calculadora de Dias Úteis

> **Projeto Prático: Desenvolvimento para a Disciplina de Linguagem de Programação III (LP3)**

> **Curso: Análise e Desenvolvimento de Sistemas (ADS) — IFSul Campus Venâncio Aires**

---

## Sobre o Projeto

Este sistema foi desenvolvido com o objetivo de auxiliar no cálculo de dias úteis entre duas datas, considerando finais de semana e feriados cadastrados pelo usuário.

A aplicação consiste em um sistema desktop desenvolvido em Java com JavaFX, permitindo o gerenciamento de feriados e o cálculo preciso de períodos úteis para planejamento de atividades, controle de prazos e organização de processos administrativos.

O software é dividido em duas funcionalidades principais:

1. Cadastro de Feriados: Permite registrar e excluir feriados personalizados armazenados em banco de dados.
2. Cálculo de Dias Úteis: Realiza o cálculo da quantidade de dias úteis entre uma data inicial e uma data final, desconsiderando sábados, domingos e os feriados cadastrados.

---

## Tecnologias Utilizadas

* Linguagem de Programação: Java
* Interface Gráfica: JavaFX
* Gerenciamento de Dependências: Maven
* Banco de Dados: MySQL (Script incluso)
* Arquitetura: Programação Orientada a Objetos (POO)

---

## Funcionalidades Principais

### 1. Cadastro de Feriados

* Inclusão de feriados personalizados através de data e descrição.
* Armazenamento permanente em banco de dados.
* Atualização automática da tabela de feriados cadastrados.

### 2. Gerenciamento de Feriados

* Visualização de todos os feriados cadastrados.
* Exclusão de registros com confirmação prévia.
* Atualização dinâmica da listagem após operações de inserção ou remoção.

### 3. Cálculo de Dias Úteis

O sistema realiza o processamento de datas considerando regras de calendário corporativo:

* Período:

  * Seleção de data inicial e data final.
  * Validação do intervalo informado.

* Regras de Cálculo:

  * Exclusão automática de sábados e domingos.
  * Exclusão automática dos feriados cadastrados no sistema.

* Resultado:

  * Exibição da quantidade total de dias úteis do período informado.
  * Atualização instantânea do resultado na interface.

### 4. Integração com Banco de Dados

* Persistência dos feriados utilizando MySQL.
* Operações de inserção, consulta e exclusão realizadas através da camada de serviços da aplicação.
* Recuperação automática dos dados ao iniciar o sistema.
* Criar um banco de dados MySQL.
* Executar o script SQL localizado na pasta: database/schema.sql
* Configurar os parâmetros de conexão com o banco de dados no projeto.

---

## Pré-Requisitos para Execução do Projeto

* Java JDK 11 ou superior
* Apache Maven
* MySQL Server
* JavaFX
* Apache NetBeans (opcional)
* (Desenvolvimento e testes realizados em plataforma Mac Silicon)

---

### Clonar o Repositório

git clone https://github.com/Alysson-afb/calculadora-dias-uteis-javafx.git
