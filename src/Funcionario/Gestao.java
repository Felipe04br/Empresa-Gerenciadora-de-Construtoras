package Funcionario;
import java.io.Serializable;

public class Gestao extends Funcionario  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String setor;
    private String formacao;

    public Gestao(String nome, String cpf, String cargo,  String endereco, Double salario) {
        super(nome, cpf, cargo,  endereco, salario);
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
}
