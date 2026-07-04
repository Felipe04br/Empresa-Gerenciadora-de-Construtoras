package Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FuncionarioPanel extends JPanel {
    private JTextField txtNome, txtCpf, txtCargo, txtEndereco, txtSalario, txtEspecifico1, txtEspecifico2;
    private JLabel lblEsp1, lblEsp2;
    private JComboBox<String> cbTipo;
    private JTable tabela;
    private DefaultTableModel modelo;
    private FuncionarioRepository repo = FuncionarioRepository.getInstancia();

    public FuncionarioPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(9, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Cadastro de Funcionários"));

        form.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[]{"Gestão", "Operacional"});
        form.add(cbTipo);

        form.add(new JLabel("Nome:")); txtNome = new JTextField(); form.add(txtNome);
        form.add(new JLabel("CPF:")); txtCpf = new JTextField(); form.add(txtCpf);
        form.add(new JLabel("Cargo:")); txtCargo = new JTextField(); form.add(txtCargo);
        form.add(new JLabel("Endereço:")); txtEndereco = new JTextField(); form.add(txtEndereco);
        form.add(new JLabel("Salário:")); txtSalario = new JTextField(); form.add(txtSalario);

        lblEsp1 = new JLabel("Setor:"); txtEspecifico1 = new JTextField();
        form.add(lblEsp1); form.add(txtEspecifico1);
        lblEsp2 = new JLabel("Formação:"); txtEspecifico2 = new JTextField();
        form.add(lblEsp2); form.add(txtEspecifico2);

        cbTipo.addActionListener(e -> {
            if (cbTipo.getSelectedIndex() == 0) {
                lblEsp1.setText("Setor:"); lblEsp2.setText("Formação:");
            } else {
                lblEsp1.setText("Local:"); lblEsp2.setText("Supervisor:");
            }
        });

        JButton btnSalvar = new JButton("Salvar");
        JButton btnRemover = new JButton("Remover por CPF");
        form.add(btnSalvar); form.add(btnRemover);
        add(form, BorderLayout.NORTH);

        String[] cols = {"Tipo", "Nome", "CPF", "Cargo", "Salário", "Atributos Específicos"};
        modelo = new DefaultTableModel(cols, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                String cpf = txtCpf.getText();
                String cargo = txtCargo.getText();
                String end = txtEndereco.getText();
                Double sal = Double.parseDouble(txtSalario.getText());

                if (cbTipo.getSelectedIndex() == 0) {
                    Gestao g = new Gestao(nome, cpf, cargo, end, sal);
                    g.setSetor(txtEspecifico1.getText());
                    g.setFormacao(txtEspecifico2.getText());
                    repo.salvar(g);
                } else {
                    Operacional o = new Operacional(nome, cpf, cargo, end, sal);
                    o.setLocal(txtEspecifico1.getText());
                    o.setSupervisor(txtEspecifico2.getText());
                    repo.salvar(o);
                }
                atualizarTabela();
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro nos dados digitados. Verifique o salário.");
            }
        });

        btnRemover.addActionListener(e -> {
            String cpf = JOptionPane.showInputDialog(this, "Digite o CPF do funcionário a remover:");
            if (cpf != null && !cpf.isEmpty()) {
                repo.deletar(cpf);
                atualizarTabela();
            }
        });

        atualizarTabela();
    }

    private void atualizarTabela() {
        modelo.setRowCount(0);
        for (Funcionario f : repo.buscarTodos()) {
            String tipo = (f instanceof Gestao) ? "Gestão" : "Operacional";
            String esp = (f instanceof Gestao g) ? "Setor: " + g.getSetor() + " | Form: " + g.getFormacao()
                    : "Local: " + ((Operacional)f).getLocal() + " | Superv: " + ((Operacional)f).getSupervisor();
            modelo.addRow(new Object[]{tipo, f.getNome(), f.getCpf(), f.getCargo(), f.getSalario(), esp});
        }
    }

    private void limparCampos() {
        txtNome.setText(""); txtCpf.setText(""); txtCargo.setText(""); txtEndereco.setText("");
        txtSalario.setText(""); txtEspecifico1.setText(""); txtEspecifico2.setText("");
    }
}