package Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {
    private static FuncionarioRepository instancia;
    private List<Funcionario> lista;

    private FuncionarioRepository() {
        this.lista = new ArrayList<>();
    }

    public static FuncionarioRepository getInstancia() {
        if (instancia == null) {
            instancia = new FuncionarioRepository();
        }
        return instancia;
    }

    public void salvar(Funcionario novoFuncionario) {
        lista.add(novoFuncionario);
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
            System.out.println("Removido!");
        } else {
            System.out.println("Funcionário não encontrado para remoção.");
        }
    }
}
