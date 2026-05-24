package Cliente;

import java.util.ArrayList;
import java.util.List;

public class RepositoryCliente {

    private static RepositoryCliente instancia;
    private List<Cliente> lista;

    private RepositoryCliente() {
        this.lista = new ArrayList<>();
    }

    public static RepositoryCliente getInstancia() {
        if (instancia == null) {
            instancia = new RepositoryCliente();
        }
        return instancia;
    }

    public void salvar(Cliente novoCliente) {
        lista.add(novoCliente);
    }

    public List<Cliente> buscarTodos() {
        return lista;
    }

    public Cliente buscarPorCpf(String cpf) {
        for (Cliente cliente : lista) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public void deletar(String cpf) {
        Cliente cliente = buscarPorCpf(cpf);

        if (cliente != null) {
            lista.remove(cliente);
            System.out.println("Cliente removido!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}