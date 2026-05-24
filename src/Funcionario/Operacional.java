package Funcionario;

public class Operacional extends Funcionario{
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
