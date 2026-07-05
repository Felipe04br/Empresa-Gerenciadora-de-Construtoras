package Projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProjetoPanel extends JPanel {
    private JTextField txtNome, txtEndereco, txtOrcamento, txtParcelas, txtMetragem, txtComodos, txtPavimentos, txtArquivoPlanta;
    private JTextField txtEsp1, txtEsp2;
    private JCheckBox chkPiscina, chkB1, chkB2, chkB3, chkB4;
    private JLabel lblEsp1, lblEsp2;
    private JComboBox<String> cbTipo;
    private JPanel painelEspecifico;
    private JTable tabela;
    private DefaultTableModel modelo;
    private ProjetoRepository repo = ProjetoRepository.getInstancia();

    public ProjetoPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel formGeral = new JPanel(new GridLayout(11, 2, 3, 3));
        formGeral.setBorder(BorderFactory.createTitledBorder("Informações Gerais do Projeto e Planta"));

        formGeral.add(new JLabel("Tipo de Obra:"));
        cbTipo = new JComboBox<>(new String[]{"Casa", "Prédio", "Reforma"});
        formGeral.add(cbTipo);

        formGeral.add(new JLabel("Nome do Projeto:")); txtNome = new JTextField(); formGeral.add(txtNome);
        formGeral.add(new JLabel("Endereço:")); txtEndereco = new JTextField(); formGeral.add(txtEndereco);
        formGeral.add(new JLabel("Orçamento Total (R$):")); txtOrcamento = new JTextField(); formGeral.add(txtOrcamento);
        formGeral.add(new JLabel("Número Parcelas:")); txtParcelas = new JTextField(); formGeral.add(txtParcelas);
        formGeral.add(new JLabel("Metragem Total (m²):")); txtMetragem = new JTextField(); formGeral.add(txtMetragem);
        formGeral.add(new JLabel("Qtd Cômodos:")); txtComodos = new JTextField(); formGeral.add(txtComodos);
        formGeral.add(new JLabel("Qtd Pavimentos:")); txtPavimentos = new JTextField(); formGeral.add(txtPavimentos);
        formGeral.add(new JLabel("Caminho Desenho Planta:")); txtArquivoPlanta = new JTextField(); formGeral.add(txtArquivoPlanta);

        chkPiscina = new JCheckBox("Terá Piscina?");
        formGeral.add(chkPiscina);
        formGeral.add(new JLabel(""));

        // Painel dinâmico para os campos de subclasses
        painelEspecifico = new JPanel(new GridLayout(4, 2, 3, 3));
        painelEspecifico.setBorder(BorderFactory.createTitledBorder("Especificidades do Tipo"));

        lblEsp1 = new JLabel("Nº Dormitórios:"); txtEsp1 = new JTextField();
        lblEsp2 = new JLabel(""); txtEsp2 = new JTextField(); txtEsp2.setVisible(false);
        chkB1 = new JCheckBox(); chkB2 = new JCheckBox(); chkB3 = new JCheckBox(); chkB4 = new JCheckBox();

        configurarCamposEspecificos();

        cbTipo.addActionListener(e -> configurarCamposEspecificos());

        JPanel containerNorte = new JPanel(new BorderLayout());
        containerNorte.add(formGeral, BorderLayout.CENTER);
        containerNorte.add(painelEspecifico, BorderLayout.SOUTH);
        add(containerNorte, BorderLayout.NORTH);

        // Ações e Listagem
        JPanel painelAcoes = new JPanel();
        JButton btnSalvar = new JButton("Cadastrar Projeto");
        JButton btnRemover = new JButton("Excluir por ID");
        JButton btnStatus = new JButton("Alterar Status");
        painelAcoes.add(btnSalvar); painelAcoes.add(btnRemover); painelAcoes.add(btnStatus);
        containerNorte.add(painelAcoes, BorderLayout.NORTH);

        String[] cols = {"ID", "Tipo", "Nome", "Orçamento", "Status", "Previsão"};
        modelo = new DefaultTableModel(cols, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                String end = txtEndereco.getText();
                double valor = Double.parseDouble(txtOrcamento.getText());
                int parc = Integer.parseInt(txtParcelas.getText());
                boolean pisc = chkPiscina.isSelected();

                Planta planta = new Planta(Double.parseDouble(txtMetragem.getText()), Integer.parseInt(txtComodos.getText()), Integer.parseInt(txtPavimentos.getText()), txtArquivoPlanta.getText());

                int sel = cbTipo.getSelectedIndex();
                if (sel == 0) { // Casa
                    int dorm = Integer.parseInt(txtEsp1.getText());
                    repo.salvar(new ProjetoResidencial(nome, TipoProjeto.CASA, end, planta, pisc, valor, parc, dorm));
                } else if (sel == 1) { // Prédio
                    int andares = Integer.parseInt(txtEsp1.getText());
                    int u = Integer.parseInt(txtEsp2.getText());
                    repo.salvar(new ProjetoPredio(nome, TipoProjeto.PREDIO, end, planta, pisc, valor, parc, u, andares, chkB1.isSelected(), chkB2.isSelected(), chkB3.isSelected(), chkB4.isSelected(), false, false));
                } else if (sel == 2) { // Reforma
                    repo.salvar(new ProjetoReforma(nome, TipoProjeto.REFORMA, end, planta, pisc, valor, parc, txtEsp1.getText(), chkB1.isSelected()));
                }
                atualizarTabela();
                JOptionPane.showMessageDialog(this, "Projeto salvo com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro de validação nos campos numéricos.");
            }
        });

        btnRemover.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Digite o ID do projeto:");
            if (idStr != null) {
                repo.deletar(Integer.parseInt(idStr));
                atualizarTabela();
            }
        });

        btnStatus.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                int id = (int) modelo.getValueAt(linha, 0);
                Projeto p = repo.buscarPorId(id);
                String op = JOptionPane.showInputDialog(this, "1-EM_ANDAMENTO_INICIAL\n2-EM_ANDAMENTO\n3-EM_CONCLUSAO\n4-CONCLUIDO\n5-PAUSADO");
                if (op != null) {
                    p.setStatus(op);
                    repo.salvarDados();
                    atualizarTabela();
                }
            }
        });

        atualizarTabela();
    }

    private void configurarCamposEspecificos() {
        painelEspecifico.removeAll();
        int sel = cbTipo.getSelectedIndex();
        if (sel == 0) { // Casa
            lblEsp1.setText("Nº Dormitórios:"); txtEsp1.setVisible(true);
            painelEspecifico.add(lblEsp1); painelEspecifico.add(txtEsp1);
        } else if (sel == 1) { // Prédio
            lblEsp1.setText("Nº Andares:"); txtEsp1.setVisible(true);
            lblEsp2.setText("Unidades por andar:"); txtEsp2.setVisible(true);
            chkB1.setText("Elevador"); chkB2.setText("Gerador"); chkB3.setText("Área de Lazer"); chkB4.setText("Salão de Festas");
            painelEspecifico.add(lblEsp1); painelEspecifico.add(txtEsp1);
            painelEspecifico.add(lblEsp2); painelEspecifico.add(txtEsp2);
            painelEspecifico.add(chkB1); painelEspecifico.add(chkB2);
            painelEspecifico.add(chkB3); painelEspecifico.add(chkB4);
        } else if (sel == 2) { // Reforma
            lblEsp1.setText("Descrição das Alterações:"); txtEsp1.setVisible(true);
            chkB1.setText("Precisa de Reforço Estrutural?");
            painelEspecifico.add(lblEsp1); painelEspecifico.add(txtEsp1);
            painelEspecifico.add(chkB1);
        }
        painelEspecifico.revalidate();
        painelEspecifico.repaint();
    }

    private void atualizarTabela() {
        modelo.setRowCount(0);
        for (Projeto p : repo.buscarTodos()) {
            modelo.addRow(new Object[]{p.getId(), p.getTipo(), p.getNome(), p.getOrcamentoTotal(), p.getStatus(), p.getDataPrevisao()});
        }
    }
}