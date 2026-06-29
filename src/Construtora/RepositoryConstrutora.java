package Construtora;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

    public class RepositoryConstrutora {
        private static final String ARQUIVO = "construtora.dat";
        private static RepositoryConstrutora instancia;
        private List<Construtora> lista;

        private RepositoryConstrutora() {
            this.lista = new ArrayList<>();
            carregarDados();
        }

        public static RepositoryConstrutora getInstancia() {
            if (instancia == null) {
                instancia = new RepositoryConstrutora();
            }
            return instancia;
        }

        public void salvar(Construtora novaConstrutora) {
            lista.add(novaConstrutora);
        }

        public List<Construtora> buscarTodos() {
            return lista;
        }

        public Construtora buscarPorCnpj(String cnpj) {
            for (Construtora c : lista) {
                if (c.getCnpj().equals(cnpj)) {
                    return c;
                }
            }
            return null;
        }

        public void deletar(String cnpj) {
            Construtora construtora = buscarPorCnpj(cnpj);

            if (construtora != null) {
                lista.remove(construtora);
                System.out.println("Construtora.Construtora removida!");
            } else {
                System.out.println("Construtora.Construtora não encontrada.");
            }
        }
        @SuppressWarnings("unchecked")
        private void carregarDados() {
            File f = new File(ARQUIVO);
            if (f.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                    lista = (List<Construtora>) ois.readObject();
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        private void salvarDados() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
                oos.writeObject(lista);
            } catch (IOException e) { e.printStackTrace(); }
        }
    }


