# 🏗️ Sistema de Gestão para Construtora

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![POO](https://img.shields.io/badge/POO-Conceitos_Aplicados-blue?style=for-the-badge)

Um sistema de gerenciamento desenvolvido em **Java** para simular as operações e o fluxo de trabalho de uma empresa do ramo da construção civil. O projeto opera de forma interativa via interface de linha de comando (CLI) através da classe `Main`. Para o armazenamento dos dados, a aplicação utiliza repositórios baseados em listas (`ArrayList`) rodando em memória.

Este repositório é uma demonstração prática da aplicação de conceitos sólidos de **Programação Orientada a Objetos (POO)** e de arquitetura de software, ideal para composição de portfólio na área de desenvolvimento.

## 🚀 Funcionalidades

O sistema está estruturado em quatro módulos integrados cada um elaborado por um membro da equipe:

### 1. 🏢 Gestão de Projetos (`MenuProjeto`)
* Suporta a criação e o controle de três categorias de obras: Projetos Residenciais (Casas), Prédios comerciais/residenciais e Projetos de Reforma.
* **Controle Estrito de Contratos:** O sistema garante a integridade do escopo ao impedir a alteração de dados estruturais da planta (como metragem total, número de pavimentos e cômodos) caso o acordo já conste como assinado (`acordoAssinado == true`).
* **Lógica Financeira Embutida:** A aplicação calcula automaticamente o valor das parcelas utilizando regras de Juros Compostos (com taxa configurada a 1%) e bloqueia negociações que não atinjam o valor mínimo de 20% de entrada do orçamento total.
* **Simulação de Antecipação:** Permite recalcular a previsão de entrega da obra com base no volume de parcelas já pagas, aplicando reduções lógicas de prazo no cronograma.
* **Rastreabilidade de Status:** Utiliza estruturas `Enum` (`StatusProjeto`) para transitar o projeto entre os estados de: `EM_ANDAMENTO_INICIAL`, `EM_ANDAMENTO`, `EM_CONCLUSAO`, `CONCLUIDO` e `PAUSADO`.

### 2. 👷 Gestão de Funcionários (`MenuFuncionario`) - EU
* Cadastro de colaboradores separados logicamente por nível de atuação.
* **Módulo de Gestão:** Registra o setor de atuação e o nível de formação do profissional.
* **Módulo Operacional:** Realiza o controle do local de alocação da equipe e vincula o respectivo supervisor responsável.

### 3. 🤝 Gestão de Clientes (`MenuCliente`)
* Registro rápido de informações cadastrais do cliente, armazenando dados como Nome, CPF/CNPJ, Contatos, Estado Civil e Endereço para fácil consulta.

### 4. 🏗️ Gestão de Construtoras (`MenuConstrutora`)
* Cadastro focado em empresas parceiras, retendo dados essenciais de validação técnica, como o número do CREA e a identificação do Responsável Técnico.

## 🧠 Arquitetura e Padrões de Projeto (Design Patterns)

O código fonte foi arquitetado aplicando as melhores práticas para garantir escalabilidade e manutenção:

* **Herança e Polimorfismo:** As subclasses `ProjetoPredio`, `ProjetoResidencial` e `ProjetoReforma` herdam características fundamentais da classe genérica `Projeto`, adicionando apenas suas particularidades (ex: necessidade de reforço estrutural em reformas). A mesma lógica é aplicada nos cargos sob a superclasse genérica `Funcionario`.
* **Padrão Singleton:** Os repositórios de dados (`FuncionarioRepository`, `RepositoryCliente`, `RepositoryConstrutora`) foram construídos utilizando o padrão Singleton. Isso garante que a aplicação instancie uma única fonte de verdade para os dados em memória durante todo o ciclo de execução do programa.
* **Encapsulamento Inteligente:** O sistema blinda a manipulação direta das variáveis e adiciona regras de negócio diretamente nos métodos de acesso (Getters/Setters). Por exemplo, tentar remover uma piscina do projeto após a assinatura do contrato gera um alerta automático de multa equivalente a 25% do orçamento.

## 📂 Estrutura do Projeto

```text
src/
├── Cliente/           # Entidades, Menus e Repositórios de Clientes
├── Construtora/       # Entidades, Menus e Repositórios de Construtoras
├── Funcionario/       # Classes com Herança (Gestão, Operacional) e Repositórios
├── Main/              # Ponto de entrada do sistema (Main.java)
└── Projeto/           # Lógica complexa de Projetos, Herança, Planta e Enums