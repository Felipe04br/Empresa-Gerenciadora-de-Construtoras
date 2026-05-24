package Construtora;

import java.util.Scanner;
import java.util.List;
public class MenuConstrutora {
        private Scanner scanner = new Scanner(System.in);
        private RepositoryConstrutora repo = RepositoryConstrutora.getInstancia();

        public void exibirMenu() {

            String op = "";

            while (!op.equals("4")) {

                System.out.println("===== MENU CONSTRUTORA =====");
                System.out.println("1 - Cadastrar");
                System.out.println("2 - Listar");
                System.out.println("3 - Remover");
                System.out.println("4 - Sair");
                System.out.print("Opção: ");
                op = scanner.nextLine();

                switch (op) {
                    case "1" -> cadastrar();
                    case "2" -> listar();
                    case "3" -> remover();
                    case "4" -> System.out.println("Encerrando...");
                    default  -> System.out.println("Opção inválida!");
                }
            }
        }

        private void cadastrar() {

            Construtora construtora = new Construtora();

            System.out.println("Razão Social:");
            construtora.setRazaoSocial(scanner.nextLine());

            System.out.println("CNPJ:");
            construtora.setCnpj(scanner.nextLine());

            System.out.println("Telefone:");
            construtora.setTelefone(scanner.nextLine());

            System.out.println("Email:");
            construtora.setEmail(scanner.nextLine());

            System.out.println("Endereço:");
            construtora.setEndereco(scanner.nextLine());

            System.out.println("Responsável Técnico:");
            construtora.setResponsavelTecnico(scanner.nextLine());

            System.out.println("CREA:");
            construtora.setCrea(scanner.nextLine());

            System.out.println("Data de Fundação:");
            construtora.setDataFundacao(scanner.nextLine());

            construtora.setAtiva(true); // nova construtora começa ativa por padrão

            repo.salvar(construtora);

            System.out.println("Construtora salva com sucesso!");
        }

        private void listar() {

            List<Construtora> construtoras = repo.buscarTodos();

            if (construtoras.isEmpty()) {
                System.out.println("Nenhuma construtora cadastrada.");
                return;
            }

            for (Construtora c : construtoras) {
                System.out.println("----------------------------------");
                System.out.println("Razão Social : " + c.getRazaoSocial());
                System.out.println("CNPJ         : " + c.getCnpj());
                System.out.println("Telefone     : " + c.getTelefone());
                System.out.println("Email        : " + c.getEmail());
                System.out.println("Endereço     : " + c.getEndereco());
                System.out.println("Resp. Técnico: " + c.getResponsavelTecnico());
                System.out.println("CREA         : " + c.getCrea());
                System.out.println("Fundação     : " + c.getDataFundacao());
                System.out.println("Ativa        : " + (c.isAtiva() ? "Sim" : "Não"));
            }
            System.out.println("----------------------------------");
        }

        private void remover() {

            System.out.println("Digite o CNPJ da construtora:");
            String cnpj = scanner.nextLine();
            repo.deletar(cnpj);
        }


}
