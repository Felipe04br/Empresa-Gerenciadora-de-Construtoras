package Funcionario;

import java.util.Scanner;

public class MenuFuncionario {
    private Scanner scanner = new Scanner(System.in);
    private FuncionarioRepository repo = FuncionarioRepository.getInstancia();

    public void exibirMenu() {
        String op = "";
        while (!op.equals("4")) {
            System.out.println("1-Cadastrar 2-Listar 3-Remover 4-Voltar");
            op = scanner.nextLine();
                switch (op) {
                    case "1" -> cadastrar();
                    case "2" -> listar();
                    case "3" -> remover();
                }
        }
    }

    private void cadastrar() {
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("CPF: ");
        String cpf = scanner.nextLine();
        System.out.println("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.println("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.println("Salário: ");
        Double salario = Double.parseDouble(scanner.nextLine());

        String tipo = "";
        while (!(tipo.equals("1")||(tipo.equals("2")))) {
            System.out.println("Tipo: 1-Gestão 2-Operacional");
            tipo = scanner.nextLine();
                switch (tipo) {
                    case "1" -> {
                        Gestao g = new Gestao(nome, cpf, cargo, endereco, salario);
                        System.out.println("Setor: ");
                        g.setSetor(scanner.nextLine());
                        System.out.println("Formação: ");
                        g.setFormacao(scanner.nextLine());
                        repo.salvar(g);
                        System.out.println("Salvo!");
                    }
                    case "2" -> {
                        Operacional o = new Operacional(nome, cpf, cargo, endereco, salario);
                        System.out.println("Local: ");
                        o.setLocal(scanner.nextLine());
                        System.out.println("Supervisor: ");
                        o.setSupervisor(scanner.nextLine());
                        repo.salvar(o);
                        System.out.println("Salvo!");
                    }
                }
        }
    }

    private void listar() {
        for (Funcionario f : repo.buscarTodos()) {
            System.out.println("CPF: " + f.getCpf() + " | " + f.getNome() +
                    " | Cargo: " + f.getCargo() + " | Salário: " + f.getSalario() +
                    " | Endereço: " + f.getEndereco());
            if (f instanceof Gestao g) {
                System.out.println("  Setor: " + g.getSetor() + " | Formação: " + g.getFormacao());
            } else if (f instanceof Operacional o) {
                System.out.println("  Local: " + o.getLocal() + " | Supervisor: " + o.getSupervisor());
            }
        }
    }

    private void remover() {
        System.out.println("Digite o CPF do funcionário a remover: ");
        String cpf = scanner.nextLine();
        repo.deletar(cpf);
    }
}