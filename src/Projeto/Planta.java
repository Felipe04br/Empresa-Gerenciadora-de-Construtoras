package Projeto;

public class Planta {
    private double metragemTotal;
    private int numeroComodos;
    private int numeroPavimentos;
    private String arquivoDesenho;

    public double getMetragemTotal() {
        return metragemTotal;
    }

    public void setMetragemTotal(double metragemTotal, boolean acordoAssinado) {
        if (acordoAssinado) {
            System.out.println("Não é possível alterar a metragem após o contrato ter sido assinado.");
        } else {
            this.metragemTotal = metragemTotal;
        }
    }

    public int getNumeroComodos() {
        return numeroComodos;
    }

    public void setNumeroComodos(int numeroComodos,  boolean acordoAssinado) {
        if (acordoAssinado) {
            System.out.println("Não é possível alterar o número de cômodos após o contrato ter sido assinado.");
        } else {
            this.numeroComodos = numeroComodos;
        }
    }

    public int getNumeroPavimentos() {
        return numeroPavimentos;
    }

    public void setNumeroPavimentos(int numeroPavimentos,  boolean acordoAssinado) {
        if (acordoAssinado) {
            System.out.println("Não é possível alterar o número de pavimentos após o contrato ter sido assinado.");
        } else {
            this.numeroPavimentos = numeroPavimentos;
        }
    }

    public String getArquivoDesenho() {
        return arquivoDesenho;
    }

    public void setArquivoDesenho(String arquivoDesenho, boolean acordoAssinado) {
        if (acordoAssinado) {
            System.out.println("Não é possível alterar o local após o contrato ter sido assinado.");
        } else {
            this.arquivoDesenho = arquivoDesenho;
        }
    }

    public Planta(double metragemTotal, int numeroComodos, int numeroPavimentos, String arquivoDesenho) {
        this.metragemTotal = metragemTotal;
        this.numeroComodos = numeroComodos;
        this.numeroPavimentos = numeroPavimentos;
        this.arquivoDesenho = arquivoDesenho;

    }
}
