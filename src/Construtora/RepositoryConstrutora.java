package Construtora;

import java.util.ArrayList;
import java.util.List;

    public class RepositoryConstrutora {

        private static RepositoryConstrutora instancia;
        private List<Construtora> lista;

        private RepositoryConstrutora() {
            this.lista = new ArrayList<>();
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
    }


