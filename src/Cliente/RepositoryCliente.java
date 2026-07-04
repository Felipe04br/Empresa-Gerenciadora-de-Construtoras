package Cliente;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class RepositoryCliente {

    private static RepositoryCliente instancia;
    private List<Cliente> lista;
    private static final String ARQUIVO = "clientes.dat";

    @SuppressWarnings("unchecked")
    private RepositoryCliente() {
        this.lista = new ArrayList<>();
        carregarDados();
    }

    public static RepositoryCliente getInstancia() {
        if (instancia == null) {
            instancia = new RepositoryCliente();
        }
        return instancia;
    }

    public void salvar(Cliente novoCliente) {
        lista.add(novoCliente);
        salvarDados();
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
            salvarDados();
            System.out.println("Cliente removido!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
                lista = (List<Cliente>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar os dados: " + e.getMessage());
            }
        }
    }
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
}