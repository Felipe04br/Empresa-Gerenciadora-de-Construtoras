package Construtora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConstrutoraPanel extends JPanel {
    private JTextField txtRazao, txtCnpj, txtTel, txtEmail, txtEnd, txtResp, txtCrea, txtFundacao;
    private JTable tabela;
    private DefaultTableModel modelo;
    private RepositoryConstrutora repo = RepositoryConstrutora.getInstancia();

    public ConstrutoraPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(9, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Cadastro de Construtoras"));

        form.add(new JLabel("Razão Social:")); txtRazao = new JTextField(); form.add(txtRazao);
        form.add(new JLabel("CNPJ:")); txtCnpj = new JTextField(); form.add(txtCnpj);
        form.add(new JLabel("Telefone:")); txtTel = new JTextField(); form.add(txtTel);
        form.add(new JLabel("Email:")); txtEmail = new JTextField(); form.add(txtEmail);
        form.add(new JLabel("Endereço:")); txtEnd = new JTextField(); form.add(txtEnd);
        form.add(new JLabel("Resp. Técnico:")); txtResp = new JTextField(); form.add(txtResp);
        form.add(new JLabel("CREA:")); txtCrea = new JTextField(); form.add(txtCrea);
        form.add(new JLabel("Data Fundação:")); txtFundacao = new JTextField(); form.add(txtFundacao);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnRemover = new JButton("Remover por CNPJ");
        form.add(btnSalvar); form.add(btnRemover);
        add(form, BorderLayout.NORTH);

        String[] cols = {"Razão Social", "CNPJ", "Telefone", "Email", "CREA", "Ativa"};
        modelo = new DefaultTableModel(cols, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> {
            Construtora c = new Construtora();
            c.setRazaoSocial(txtRazao.getText()); c.setCnpj(txtCnpj.getText()); c.setTelefone(txtTel.getText());
            c.setEmail(txtEmail.getText()); c.setEndereco(txtEnd.getText()); c.setResponsavelTecnico(txtResp.getText());
            c.setCrea(txtCrea.getText()); c.setDataFundacao(txtFundacao.getText()); c.setAtiva(true);
            repo.salvar(c);
            atualizarTabela();
            limparCampos();
        });

        btnRemover.addActionListener(e -> {
            String cnpj = JOptionPane.showInputDialog(this, "Digite o CNPJ da construtora a remover:");
            if (cnpj != null && !cnpj.isEmpty()) {
                repo.deletar(cnpj);
                atualizarTabela();
            }
        });

        atualizarTabela();
    }

    private void atualizarTabela() {
        modelo.setRowCount(0);
        for (Construtora c : repo.buscarTodos()) {
            modelo.addRow(new Object[]{c.getRazaoSocial(), c.getCnpj(), c.getTelefone(), c.getEmail(), c.getCrea(), c.isAtiva() ? "Sim" : "Não"});
        }
    }

    private void limparCampos() {
        txtRazao.setText(""); txtCnpj.setText(""); txtTel.setText(""); txtEmail.setText("");
        txtEnd.setText(""); txtResp.setText(""); txtCrea.setText(""); txtFundacao.setText("");
    }
}