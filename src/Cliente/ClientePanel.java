package Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientePanel extends JPanel {

    // Componentes de Coleta de Informação
    private JTextField txtNome, txtCpf, txtTelefone, txtEmail, txtEndereco;
    private JButton btnSalvar, btnDeletar;
    private JTable tabelaClientes;
    private DefaultTableModel modeloTabela;

    private RepositoryCliente repo = RepositoryCliente.getInstancia();

    public ClientePanel() {
        setLayout(new BorderLayout(10, 10)); // Layout principal do painel

        // 1. Painel Superior: Campos de Texto (JTextField)
        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Cadastro de Cliente"));

        painelFormulario.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        painelFormulario.add(txtCpf);

        painelFormulario.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painelFormulario.add(txtTelefone);

        painelFormulario.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painelFormulario.add(txtEmail);

        painelFormulario.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        painelFormulario.add(txtEndereco);

        // 2. Botões (JButton)
        JPanel painelBotoes = new JPanel();
        btnSalvar = new JButton("Salvar Cliente");
        btnDeletar = new JButton("Remover Selecionado");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnDeletar);

        painelFormulario.add(painelBotoes);

        add(painelFormulario, BorderLayout.NORTH);

        // 3. Tabela para listar os dados (JTable)
        String[] colunas = {"Nome", "CPF", "Telefone", "Email", "Endereço"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaClientes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaClientes);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));

        add(scrollPane, BorderLayout.CENTER);

        // 4. Configurar as Ações dos Botões (Eventos)
        configurarEventos();

        // Preenche a tabela ao iniciar a tela
        atualizarTabela();
    }

    private void configurarEventos() {
        // Ação de Salvar
        btnSalvar.addActionListener(e -> {
            if(txtNome.getText().isEmpty() || txtCpf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome e CPF são obrigatórios!");
                return;
            }

            Cliente novoCliente = new Cliente();
            novoCliente.setNome(txtNome.getText());
            novoCliente.setCpf(txtCpf.getText());
            novoCliente.setNumero_telefone(txtTelefone.getText());
            novoCliente.setEmail(txtEmail.getText());
            novoCliente.setEndereco(txtEndereco.getText());

            repo.salvar(novoCliente);
            atualizarTabela();
            limparCampos();
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
        });

        // Ação de Remover
        btnDeletar.addActionListener(e -> {
            int linhaSelecionada = tabelaClientes.getSelectedRow();
            if (linhaSelecionada >= 0) {
                // A coluna 1 é onde o CPF está armazenado na JTable
                String cpfSelecionado = modeloTabela.getValueAt(linhaSelecionada, 1).toString();
                repo.deletar(cpfSelecionado);
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela para remover.");
            }
        });
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0); // Limpa as linhas atuais
        List<Cliente> clientes = repo.buscarTodos();
        for (Cliente c : clientes) {
            modeloTabela.addRow(new Object[]{
                    c.getNome(),
                    c.getCpf(),
                    c.getNumero_telefone(),
                    c.getEmail(),
                    c.getEndereco()
            });
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
    }
}