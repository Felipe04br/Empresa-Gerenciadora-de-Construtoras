package Funcionario;

public class Funcionario {
    protected String nome;
    protected String cpf;
    protected String cargo;
    protected Double salario;
    protected String endereco;

    public Funcionario(String nome, String cpf, String cargo, String endereco, Double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
        this.endereco = endereco;
    }


    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void limparEndereco(){
        this.endereco=null;
    }
}
