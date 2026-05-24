package Projeto;

public class ProjetoPredio extends Projeto {
    private int numeroUnidades;
    private int numeroAndares;
    private boolean temElevador;
    private boolean temGerador;
    private boolean temAreaLazer;
    private boolean temSalaoFestas;
    private boolean temCondominio;
    private boolean temGasCanalizado;

    public ProjetoPredio(String nome, TipoProjeto tipo, String endereco, Planta planta, boolean temPiscina, double orcamentoTotal, int numeroParcelas,  int numeroUnidades, int numeroAndares, boolean temElevador, boolean temGerador, boolean temAreaLazer, boolean temSalaoFestas, boolean temCondominio, boolean temGasCanalizado) {
        super(nome, tipo, endereco, planta, temPiscina, orcamentoTotal, numeroParcelas);
        this.numeroUnidades = numeroUnidades;
        this.numeroAndares = numeroAndares;
        this.temElevador = temElevador;
        this.temGerador = temGerador;
        this.temAreaLazer = temAreaLazer;
        this.temSalaoFestas = temSalaoFestas;
        this.temCondominio = temCondominio;
        this.temGasCanalizado = temGasCanalizado;
    }

    public int getNumeroUnidades() {
        return this.numeroUnidades;
    }
    public void setNumeroUnidades(int numeroUnidades) {
        if (isAcordoAssinado()) {
            System.out.println("Não é possível alterar o número de unidades após o acordo ser assinado.");
        } else {
        this.numeroUnidades = numeroUnidades;
        }
    }

    public int getNumeroAndares() {
        return this.numeroAndares;
    }
    public void setNumeroAndares(int numeroAndares) {
        if (isAcordoAssinado()) {
            System.out.println("Não é possível alterar o número de andares após o acordo ser assinado.");
        } else {
            this.numeroAndares = numeroAndares;
        }
    }

    public boolean isTemElevador() {
        return this.temElevador;
    }
    public void setTemElevador(boolean temElevador) {
        if (isAcordoAssinado()) {
            System.out.println("Não é possível alterar a presença de elevador após o acordo ser assinado.");
        } else {
            this.temElevador = temElevador;
        }
    }

    public boolean isTemGerador() {
        return this.temGerador;
    }
    public void setTemGerador(boolean temGerador) {
        if (isAcordoAssinado()) {
            System.out.println("Não é possível alterar a presença de gerador após o acordo ser assinado.");
        } else {
            this.temGerador = temGerador;
        }
    }

    public boolean isTemAreaLazer() {
        return this.temAreaLazer;
    }
    public void setTemAreaLazer(boolean temAreaLazer) {
        if (isAcordoAssinado()) {
            System.out.println("Não é possível alterar a presença da área de lazer após o acordo ser assinado.");
        } else {
            this.temAreaLazer = temAreaLazer;
        }
    }

    public boolean isTemSalaoFestas() {
        return this.temSalaoFestas;
    }
    public void setTemSalaoFestas(boolean temSalaoFestas) {
        if (isAcordoAssinado()) {
            System.out.println("Não é possível alterar a presença do salão de festa após o acordo ser assinado.");
        } else {
            this.temSalaoFestas = temSalaoFestas;
        }
    }

    public boolean isTemCondominio() {
        return this.temCondominio;
    }
    public void setTemCondominio(boolean temCondominio) {
        if (isAcordoAssinado()) {
            System.out.println("Não é possível alterar a presença do condôminio após o acordo ser assinado.");
        } else {
            this.temCondominio = temCondominio;
        }
    }

    public boolean isTemGasCanalizado() {
        return this.temGasCanalizado;
    }
    public void setTemGasCanalizado(boolean temGasCanalizado) {
        if (isAcordoAssinado()) {
            System.out.println("Não é possível alterar a presença do gás canalizado após o acordo ser assinado.");
        } else {
            this.temGasCanalizado = temGasCanalizado;
        }
    }
}

