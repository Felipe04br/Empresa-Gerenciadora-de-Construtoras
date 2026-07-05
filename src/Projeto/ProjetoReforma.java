package Projeto;
import java.io.Serializable;

public class ProjetoReforma extends Projeto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descricaoAlteracoes;
    private boolean precisaReforco;

    public ProjetoReforma(String nome, TipoProjeto tipo, String endereco, Planta planta, boolean temPiscina, double orcamentoTotal, int numeroParcelas, String descricaoAlteracoes, boolean precisaReforco){
        super(nome, tipo, endereco, planta, temPiscina, orcamentoTotal, numeroParcelas);
        this.descricaoAlteracoes = descricaoAlteracoes;
        this.precisaReforco = precisaReforco;
    }

    public String getDescricaoAlteracoes() {
        return this.descricaoAlteracoes;
    }
    public void setDescricaoAlteracoes(String descricaoAlteracoes) {
        if(isAcordoAssinado()){
            System.out.println("Não é possível alterar a descrição após o acordo ser assinado");
        }else {
            this.descricaoAlteracoes = descricaoAlteracoes;
        }
    }

    public boolean isPrecisaReforco() {
        return this.precisaReforco;
    }
    public void setPrecisaReforco(boolean precisaReforco) {
        if (isAcordoAssinado()){
            System.out.println("Não é possível alterar a necessidade de reforço após o acordo ser assinado.");
        }else {
            this.precisaReforco = precisaReforco;
        }
    }
}

