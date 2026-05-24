package Main;

import Funcionario.MenuFuncionario;
import Projeto.MenuProjeto;
import Construtora.MenuConstrutora;
import Cliente.MenuCliente;
import java.util.Scanner;
import static java.lang.IO.println;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuFuncionario funcionario = new MenuFuncionario();
        MenuProjeto projeto = new MenuProjeto();
        MenuConstrutora construtora = new MenuConstrutora();
        MenuCliente cliente = new MenuCliente();
        int op = 0;
        while (op != 5) {
            System.out.println("\n=== Empresa ===");
            System.out.println("1-Funcionario 2-Cliente 3-Projeto 4-Construtora 5-Sair");
            op = scanner.nextInt();
            switch (op) {
                case 1 -> funcionario.exibirMenu();
                case 2 -> cliente.exibirMenu();
                case 3 -> projeto.exibirMenu();
                case 4 -> construtora.exibirMenu();
            }

        }
    }
}