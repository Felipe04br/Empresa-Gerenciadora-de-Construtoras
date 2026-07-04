package Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class FuncionarioRepository {
    private static FuncionarioRepository instancia;
    private List<Funcionario> lista;
    private static final String ARQUIVO = "funcionarios.dat";

    private FuncionarioRepository() {
        this.lista = new ArrayList<>();
        carregarDados();
    }

    public static FuncionarioRepository getInstancia() {
        if (instancia == null) {
            instancia = new FuncionarioRepository();
        }
        return instancia;
    }

    public void salvar(Funcionario novoFuncionario) {
        lista.add(novoFuncionario);
        salvarDados();
    }

    public List<Funcionario> buscarTodos() {
        return lista;
    }

    public Funcionario buscarPorCpf(String cpf) {
        for (Funcionario funcionario : lista) {
            if (funcionario.getCpf().equals(cpf)) return funcionario;
        }
        return null;
    }

    public void deletar(String cpf) {
        Funcionario funcionario = buscarPorCpf(cpf);
        if (funcionario != null) {
            lista.remove(funcionario);
            salvarDados();
            System.out.println("Removido!");
        } else {
            System.out.println("Funcionário não encontrado para remoção.");
        }
    }
    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File f = new File(ARQUIVO);
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                lista = (List<Funcionario>) ois.readObject();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(lista);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
