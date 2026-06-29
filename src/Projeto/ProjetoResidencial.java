package Projeto;
import java.io.Serializable;

public class ProjetoResidencial extends Projeto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numeroDormitorio;

    public ProjetoResidencial(String nome, TipoProjeto tipo, String endereco, Planta planta, boolean temPiscina, double orcamentoTotal, int numeroParcelas, int numeroDormitorio){
        super(nome, tipo, endereco, planta, temPiscina, orcamentoTotal, numeroParcelas);
        this.numeroDormitorio = numeroDormitorio;
    }

    public int getNumeroDormitorio(){
        return this.numeroDormitorio;
    }

    public void setNumeroDormitorio(int numeroDormitorio){
        if (isAcordoAssinado()){
            System.out.println("Não é possível alterar após o acordo ser assinado.");
        } else {
            this.numeroDormitorio = numeroDormitorio;
        }
    }
}