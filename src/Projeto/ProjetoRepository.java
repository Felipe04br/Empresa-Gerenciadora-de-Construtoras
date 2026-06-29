package Projeto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoRepository {
    private static ProjetoRepository instancia;
    private List<Projeto> lista;
    private static final String ARQUIVO = "projetos.dat";

    @SuppressWarnings("unchecked")
    private ProjetoRepository() {
        this.lista = new ArrayList<>();
        carregarDados();
    }

    public static ProjetoRepository getInstancia() {
        if (instancia == null) instancia = new ProjetoRepository();
        return instancia;
    }

    public void salvar(Projeto novoProjeto) {
        lista.add(novoProjeto);
        salvarDados();
    }

    public List<Projeto> buscarTodos() { return lista; }

    public Projeto buscarPorId(int id) {
        for (Projeto p : lista) { if (p.getId() == id) return p; }
        return null;
    }

    public void deletar(int id) {
        Projeto p = buscarPorId(id);
        if (p != null) { lista.remove(p); salvarDados(); }
    }

    public void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(lista);
        } catch (IOException e) { e.printStackTrace(); }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File f = new File(ARQUIVO);
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                lista = (List<Projeto>) ois.readObject();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
