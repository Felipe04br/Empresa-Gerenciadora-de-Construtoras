package Main;

import Cliente.ClientePanel;
import Construtora.ConstrutoraPanel;
import Funcionario.FuncionarioPanel;
import Projeto.ProjetoPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Inicializa a GUI na thread de eventos correta do Swing
        SwingUtilities.invokeLater(() -> {
            JFrame janelaPrincipal = new JFrame("Corporate System - Construtora & Engenharia");
            janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janelaPrincipal.setSize(900, 700);
            janelaPrincipal.setLocationRelativeTo(null); // Centraliza na tela

            // Gerenciador Unificado de Abas
            JTabbedPane abas = new JTabbedPane();

            // Injeção de cada painel customizado dentro de sua aba correspondente
            abas.addTab("Clientes", new ClientePanel());
            abas.addTab("Construtoras", new ConstrutoraPanel());
            abas.addTab("Funcionários", new FuncionarioPanel());
            abas.addTab("Projetos de Obra", new ProjetoPanel());

            janelaPrincipal.add(abas);
            janelaPrincipal.setVisible(true);
        });
    }
}