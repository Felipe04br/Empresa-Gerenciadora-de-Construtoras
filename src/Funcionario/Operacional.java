package Funcionario;
import java.io.Serializable;

public class Operacional extends Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String local;
    private String supervisor;

    public Operacional(String nome, String cpf, String cargo, String endereco, Double salario) {
        super(nome, cpf, cargo, endereco, salario);
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}
