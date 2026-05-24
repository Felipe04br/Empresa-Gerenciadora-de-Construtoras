package Projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuProjeto {
    // Atributos de classe (visíveis por todos os métodos)
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Projeto> projetos = new ArrayList<>();

    // O código do menu isolado em um método próprio
    public void exibirMenu() {
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("=== MENU DO PROJETO ===");
            System.out.println("1. - Criar/Cadastrar Projeto");
            System.out.println("2. - Consultar Projeto");
            System.out.println("3. - Editar Projetos");
            System.out.println("4 - Excluir Projeto");
            System.out.println("5 - Sair");
            System.out.println("Escolha uma opção: ");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarProjeto();
                    break;

                case 2:
                    consultarProjeto();
                    break;

                case 3:
                    editarProjeto();
                    break;

                case 4:
                    excluirProjeto();
                    break;

                case 5:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    // Sub-método para organizar o cadastro
    private void cadastrarProjeto() {
        System.out.println("Digite o tipo do projeto: ");
        System.out.println("1 - Casa");
        System.out.println("2 - Prédio");
        System.out.println("3 - Reforma");
        int tipo = scan.nextInt();
        scan.nextLine();

        System.out.println("Digite o nome do projeto: ");
        String nome = scan.nextLine();

        System.out.println("Digite o endereço projeto: ");
        String endereco = scan.nextLine();

        System.out.println("Digite o orçamento total do projeto: ");
        double valor = scan.nextDouble();
        scan.nextLine();

        System.out.println("Número de parcelas: ");
        int parcelas = scan.nextInt();
        scan.nextLine();

        System.out.println("Terá piscina? (true/false): ");
        boolean piscina = scan.nextBoolean();
        scan.nextLine();

        System.out.println("Digite a metragem total da planta do projeto: ");
        double metragem = scan.nextDouble();
        scan.nextLine();

        System.out.println("Digite o número de cômodos: ");
        int comodos = scan.nextInt();
        scan.nextLine();

        System.out.println("Digite o número de pavimentos: ");
        int pavimentos = scan.nextInt();
        scan.nextLine();

        System.out.println("Digite o caminho do arquivo da planta: ");
        String arquivo = scan.nextLine();

        Planta planta = new Planta(metragem, comodos, pavimentos, arquivo);

        if (tipo == 1) {
            System.out.println("Digite o número de dormitórios: ");
            int dormitario = scan.nextInt();
            scan.nextLine();

            ProjetoResidencial projetoResidencial = new ProjetoResidencial(nome, TipoProjeto.CASA, endereco, planta, piscina, valor, parcelas, dormitario);
            projetos.add(projetoResidencial);
            System.out.println("Projeto Residencial criado com sucesso!");

        } else if (tipo == 2) {
            System.out.println("Digite o número de andares: ");
            int andares = scan.nextInt();
            scan.nextLine();

            System.out.println("Digite o número de quartos por andar: ");
            int quartosAndar = scan.nextInt();
            scan.nextLine();

            System.out.println("Tem elevador? (true/false): ");
            boolean elevador = scan.nextBoolean();
            scan.nextLine();

            System.out.println("Tem gerador? (true/false): ");
            boolean gerador = scan.nextBoolean();
            scan.nextLine();

            System.out.println("Tem área de lazer? (true/false): ");
            boolean areaLazer = scan.nextBoolean();
            scan.nextLine();

            System.out.println("Tem salão de festa? (true/false): ");
            boolean salaFesta = scan.nextBoolean();
            scan.nextLine();

            System.out.println("Tem condomínio? (true/false): ");
            boolean condominio = scan.nextBoolean();
            scan.nextLine();

            System.out.println("Tem gás canalizado? (true/false): ");
            boolean gasCanalizado = scan.nextBoolean();
            scan.nextLine();

            ProjetoPredio projetoPredio = new ProjetoPredio(nome, TipoProjeto.PREDIO, endereco, planta, piscina, valor, parcelas, andares, quartosAndar, elevador, gerador, areaLazer, salaFesta, condominio, gasCanalizado);
            projetos.add(projetoPredio);
            System.out.println("Projeto Predio criado com sucesso!");

        } else if (tipo == 3) {
            System.out.println("Descreva as alterações necessárias: ");
            String descricao = scan.nextLine();

            System.out.println("Precisa de reforçar a estrutura? (true/false): ");
            boolean precisaReforar = scan.nextBoolean();
            scan.nextLine();

            ProjetoReforma projetoReforma = new ProjetoReforma(nome, TipoProjeto.REFORMA, endereco, planta, piscina, valor, parcelas, descricao, precisaReforar);
            projetos.add(projetoReforma);
            System.out.println("Projeto Reforma criado com sucesso!");
        }
    }

    // Sub-método para organizar a consulta
    private void consultarProjeto() {
        System.out.println("Digite o ID do projeto: ");
        int idProjeto = scan.nextInt();
        scan.nextLine();

        boolean encontrado = false;

        for (Projeto p : projetos) {
            if (p.getId() == idProjeto) {
                System.out.println("=== PROJETO ENCONTRADO ===");
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getNome());
                System.out.println("Endereço: " + p.getEndereco());
                System.out.println("Status: " + p.getStatus());
                System.out.println("Orçamento: R$ " + p.getOrcamentoTotal());
                System.out.println("Parcelas: " + p.getNumeroParcelas());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum projeto encontrado!");
        }
    }

    // Sub-método para organizar a edição
    private void editarProjeto() {
        System.out.println("Informe o ID do projeto: ");
        int idBuscado = scan.nextInt();
        scan.nextLine();

        boolean buscado = false;

        for (Projeto p : projetos) {
            if (p.getId() == idBuscado) {
                buscado = true;
                System.out.println("=== EDITAR PROJETO ===");
                System.out.println("1 - Editar nome");
                System.out.println("2 - Editar endereço");
                System.out.println("3 - Atualizar status");
                System.out.println("4 - Registrar reclamação");
                System.out.println("5 - Voltar");
                int opcaoEditar = scan.nextInt();
                scan.nextLine();

                switch (opcaoEditar) {
                    case 1:
                        System.out.println("Novo nome: ");
                        String novoNome = scan.nextLine();
                        p.setNome(novoNome);
                        System.out.println("Nome atualizado!");
                        break;
                    case 2:
                        System.out.println("Novo endereço: ");
                        String novoEndereco = scan.nextLine();
                        p.setEndereco(novoEndereco);
                        System.out.println("Endereço atualizado!");
                        break;
                    case 3:
                        System.out.println("1-EM_ANDAMENTO 2-EM_CONCLUSAO 3-CONCLUIDO 4-PAUSADO");
                        int opcaoStatus = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Status atualizado!");
                        break;
                    case 4:
                        System.out.println("Digite a reclamação: ");
                        String reclamacao = scan.nextLine();
                        System.out.println("Reclamação registrada!");
                        break;
                    case 5:
                        break;
                }
                break;
            }
        }
        if (!buscado) {
            System.out.println("Projeto não encontrado para edição.");
        }
    }

    // Sub-método para organizar a exclusão
    private void excluirProjeto() {
        System.out.println("Digite o ID do projeto para excluir: ");
        int idExcluir = scan.nextInt();
        scan.nextLine();
        boolean removido = projetos.removeIf(p -> p.getId() == idExcluir);
        if (removido) {
            System.out.println("Projeto excluído com sucesso!");
        } else {
            System.out.println("Projeto não encontrado.");
        }
    }
}
