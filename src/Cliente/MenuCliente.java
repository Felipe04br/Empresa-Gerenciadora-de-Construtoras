package Cliente;

import java.util.Scanner;

public class MenuCliente {


        private Scanner scanner = new Scanner(System.in);
        private RepositoryCliente repo = RepositoryCliente.getInstancia();

        public void exibirMenu() {

            String op = "";

            while (!op.equals("4")) {

                System.out.println("1-Cadastrar 2-Listar 3-Remover 4-Sair");
                op = scanner.nextLine();

                switch (op) {

                    case "1" -> cadastrar();

                    case "2" -> listar();

                    case "3" -> remover();

                    case "4" -> System.out.println("Encerrando...");
                }
            }
        }

        private void cadastrar() {

            Cliente cliente = new Cliente();

            System.out.println("Nome:");
            cliente.setNome(scanner.nextLine());

            System.out.println("CPF:");
            cliente.setCpf(scanner.nextLine());

            System.out.println("CNPJ:");
            cliente.setCnpj(scanner.nextLine());

            System.out.println("Telefone:");
            cliente.setNumero_telefone(scanner.nextLine());

            System.out.println("Email:");
            cliente.setEmail(scanner.nextLine());

            System.out.println("Data de nascimento:");
            cliente.setData_nascimento(scanner.nextLine());

            System.out.println("Estado civil:");
            cliente.setEstado_civil(scanner.nextLine());

            System.out.println("Endereço:");
            cliente.setEndereco(scanner.nextLine());

            repo.salvar(cliente);

            System.out.println("Cliente salvo!");
        }

        private void listar() {

            for (Cliente c : repo.buscarTodos()) {
                System.out.println("Nome: " + c.getNome() + " | CPF: " + c.getCpf() +
                        " | Telefone: " + c.getNumero_telefone() + " | emal: " + c.getEmail() + "| Data de nascimento : "
                        + c.getData_nascimento() + "| Estado civil : " + c.getEstado_civil() + " | Endereço: " + c.getEndereco());


            }
        }

            private void remover(){

                System.out.println("Digite o CPF:");
                String cpf = scanner.nextLine();
                repo.deletar(cpf);
            }

}
