# 🏗️ Sistema de Gestão para Construtora

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![POO](https://img.shields.io/badge/POO-Conceitos_Aplicados-blue?style=for-the-badge)

Um sistema de gerenciamento desenvolvido em **Java** para simular as operações e o fluxo de trabalho de uma empresa do ramo da construção civil. O projeto conta com uma interface gráfica desktop construída com **Java Swing**, organizada em abas (`JTabbedPane`) a partir da classe `Main`, onde cada módulo do sistema é representado por um painel próprio (`ClientePanel`, `ConstrutoraPanel`, `FuncionarioPanel` e `ProjetoPanel`). Para o armazenamento dos dados, a aplicação utiliza repositórios baseados em listas (`ArrayList`) mantidas em memória durante a execução, com persistência automática em disco através de serialização de objetos (`ObjectOutputStream`/`ObjectInputStream`), garantindo que os dados cadastrados não se percam entre uma execução e outra.

Este repositório é uma demonstração prática de um trabalho equipe da aplicação de conceitos sólidos de **Programação Orientada a Objetos (POO)** e de arquitetura de software, ideal para composição de portfólio na área de desenvolvimento.

## 🚀 Funcionalidades

O sistema está estruturado em quatro módulos integrados e representado por uma aba própria na janela principal e elaborado por um membro da equipe:

### 1. 🏢 Gestão de Projetos (`ProjetoPanel`)
* Suporta a criação e o controle de três categorias de obras: Projetos Residenciais (Casas), Prédios comerciais/residenciais e Projetos de Reforma, com campos específicos exibidos dinamicamente no formulário conforme o tipo selecionado.
* **Lógica Financeira Embutida:** A aplicação calcula automaticamente o valor das parcelas utilizando regras de Juros Compostos (com taxa configurada a 1%) e bloqueia negociações que não atinjam o valor mínimo de 20% de entrada do orçamento total.
* **Rastreabilidade de Status:** Utiliza estruturas `Enum` (`StatusProjeto`) para transitar o projeto entre os estados de: `EM_ANDAMENTO_INICIAL`, `EM_ANDAMENTO`, `EM_CONCLUSAO`, `CONCLUIDO` e `PAUSADO`, atualizável diretamente pela tabela de listagem.

### 2. 👷 Gestão de Funcionários (`FuncionarioPanel`)
* Cadastro de colaboradores separados logicamente por nível de atuação.
* **Módulo de Gestão:** Registra o setor de atuação e o nível de formação do profissional.
* **Módulo Operacional:** Realiza o controle do local de alocação da equipe e vincula o respectivo supervisor responsável.

### 3. 🤝 Gestão de Clientes (`ClientePanel`)
* Registro rápido de informações cadastrais do cliente, armazenando dados como Nome, CPF/CNPJ, Contatos, Estado Civil e Endereço para fácil consulta, com tabela de listagem e remoção por seleção de linha.

### 4. 🏗️ Gestão de Construtoras (`ConstrutoraPanel`)
* Cadastro focado em empresas parceiras, retendo dados essenciais de validação técnica, como o número do CREA e a identificação do Responsável Técnico.

## 🧠 Arquitetura e Padrões de Projeto (Design Patterns)

O código fonte foi arquitetado aplicando as melhores práticas para garantir escalabilidade e manutenção:

* **Interface Gráfica com Java Swing:** A janela principal (`Main.java`) utiliza um `JTabbedPane` para organizar os quatro módulos do sistema, cada um implementado como um `JPanel` independente (`ClientePanel`, `ConstrutoraPanel`, `FuncionarioPanel`, `ProjetoPanel`), contendo seus próprios formulários (`JTextField`, `JComboBox`, `JCheckBox`) e tabelas de listagem (`JTable`/`DefaultTableModel`).
* **Herança e Polimorfismo:** As subclasses `ProjetoPredio`, `ProjetoResidencial` e `ProjetoReforma` herdam características fundamentais da classe genérica `Projeto`, adicionando apenas suas particularidades (ex: necessidade de reforço estrutural em reformas). A mesma lógica é aplicada nos cargos sob a superclasse genérica `Funcionario`.
* **Padrão Singleton:** Os repositórios de dados (`ProjetoRepository`, `FuncionarioRepository`, `RepositoryCliente`, `RepositoryConstrutora`) foram construídos utilizando o padrão Singleton. Isso garante que a aplicação instancie uma única fonte de verdade para os dados em memória durante todo o ciclo de execução do programa.
* **Persistência via Serialização:** Cada repositório mantém sua lista de objetos sincronizada com um arquivo `.dat` próprio (`clientes.dat`, `projetos.dat`, etc.) usando `ObjectOutputStream`/`ObjectInputStream`. Para que essa gravação funcione, **todas** as classes de entidade (e qualquer classe usada como atributo delas, como `Planta`) precisam implementar `Serializable` — caso contrário, o Java falha ao gravar o objeto e os dados não sobrevivem ao fechar a aplicação.
* **Encapsulamento Inteligente:** O sistema blinda a manipulação direta das variáveis e adiciona regras de negócio diretamente nos métodos de acesso (Getters/Setters). Por exemplo, tentar remover uma piscina do projeto após a assinatura do contrato gera um alerta automático de multa equivalente a 25% do orçamento.

## 📂 Estrutura do Projeto

```text
src/
├── Cliente/           # Entidade, Painel Swing (ClientePanel) e Repositório de Clientes
├── Construtora/       # Entidade, Painel Swing (ConstrutoraPanel) e Repositório de Construtoras
├── Funcionario/       # Classes com Herança (Gestão, Operacional), Painel Swing e Repositório
├── Main/              # Ponto de entrada do sistema (Main.java) com a JFrame e o JTabbedPane
└── Projeto/           # Lógica complexa de Projetos, Herança, Planta, Enums e Painel Swing (ProjetoPanel)
```