package Projeto;

import java.time.LocalDate;

//Atributos:

public class Projeto {
    private static int contadorId = 0;
    private int id;
    private String nome;
    private TipoProjeto tipo;
    private String endereco;
    private Planta planta;
    private boolean temPiscina;
    private boolean acordoAssinado;
    private String dataInicio;
    private String dataPrevisao;
    private double orcamentoTotal;
    private double valorEntrada;
    private int numeroParcelas;
    private StatusProjeto status;
    private StatusProjeto statusAnterior;
    private boolean temReclamacao;
    private String descricaoReclamacao;

 // Atribuições dos atributos:

    public Projeto(String nome, TipoProjeto tipo, String endereco, Planta planta, boolean temPiscina, double orcamentoTotal, int numeroParcelas) {
        this.id = ++contadorId;
        this.nome = nome;
        this.tipo = tipo;
        this.endereco = endereco;
        this.planta = planta;
        this.temPiscina = temPiscina;
        this.orcamentoTotal = orcamentoTotal;
        this.numeroParcelas = numeroParcelas;
        this.acordoAssinado = false;
        this.status = StatusProjeto.EM_ANDAMENTO_INICIAL;
        this.statusAnterior = null;
        this.temReclamacao = false;
        this.descricaoReclamacao = null;
        this.dataInicio = LocalDate.now().toString();
        this.dataPrevisao = numeroParcelas + "meses";
    }

// Getter e Setter -> Encapsulamento:

    public int getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public TipoProjeto getTipo(){
        return this.tipo;
    }

    public String getEndereco(){
        return this.endereco;
    }
    public void setEndereco(String endereco){
        if (acordoAssinado){
            System.out.println("Acordo assinado. Não é possível alterar.");
        } else {
            this.endereco = endereco;
        }
    }

    public Planta getPlanta(){
        return this.planta;
    }

    public boolean isTemPiscina(){
        return this.temPiscina;
    }
    public void setTemPiscina(boolean temPiscina){
        double multa = orcamentoTotal * 0.25;

        if (acordoAssinado){
            System.out.println("Como o acordo está assinado. Para cancelar há uma multa. Multa: R$ " + multa);
            this.temPiscina = temPiscina;
        } else {
            System.out.println("Como o não foi assinado o contrato, é possivel fazer alterações.");
            this.temPiscina = temPiscina;
        }
    }

    public boolean isAcordoAssinado(){
        return this.acordoAssinado;
    }
    public void setAcordoAssinado(boolean acordoAssinado){
        this.acordoAssinado = acordoAssinado;
    }

    public String getDataInicio(){
        return this.dataInicio;
    }

    public String getDataPrevisao(){
        return this.dataPrevisao;
    }
    private void setDataPrevisao(String dataPrevisao){
        this.dataPrevisao = dataPrevisao;
    }

    public double getOrcamentoTotal(){
        return this.orcamentoTotal;
    }

    public double getValorEntrada(){
        return this.valorEntrada;
    }
    public void setValorEntrada(double valorEntrada){
        double valorMinimo = orcamentoTotal * 0.20;

        if(acordoAssinado){     // true
            System.out.println("Não é possível alterar após o acordo ser assinado.");
        } else if (valorEntrada < valorMinimo) {
            System.out.println("Valor mínimo de entrada é R$ " + valorMinimo);
        } else {
            this.valorEntrada = valorEntrada;
        }
    }

    public int getNumeroParcelas(){
        return this.numeroParcelas;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public StatusProjeto getStatusAnterior(){
        return this.statusAnterior;
    }

    public boolean isTemReclamacao(){
        return this.temReclamacao;
    }

    public String getDescricaoReclamacao(){
        return this.descricaoReclamacao;
    }
    public void setDescricaoReclamacao(String descricaoReclamacao){
        if (!temReclamacao){     // false
            System.out.println("Não tem registro de reclamação.");
        } else {
            this.descricaoReclamacao = descricaoReclamacao;
        }
    }

    public boolean isTemReclamacaoAnterior(){
        return this.temReclamacao;
    }
    public void setTemReclamacaoAnterior(boolean temReclamacao){
        if (this.status != StatusProjeto.CONCLUIDO){
            System.out.println("Só é possível registrar reclamação após a obra ter sido concluída.");
        } else {
            this.temReclamacao = temReclamacao;
        }
    }


    public void setStatus(StatusProjeto status) {
        switch (this.status) {
            case EM_ANDAMENTO_INICIAL:
                if (status == StatusProjeto. EM_ANDAMENTO || status == StatusProjeto.PAUSADO) {
                    this.statusAnterior = this.status;
                    this.status = status;
                } else  {
                    System.out.println("Status inválido! A obra precisa seguir o seu curso. O próximo status deve ser EM_ANDAMENTO.");
                }
                break;

            case EM_ANDAMENTO:
                if ( status == StatusProjeto.EM_CONCLUSAO || status == StatusProjeto.PAUSADO) {
                    this.statusAnterior = this.status;
                    this.status = status;
                } else {
                    System.out.println("Status inválido! A obra precisa seguir o seu curso. O próximo status deve ser EM_CONCLUSAO.");
                }
                break;

            case EM_CONCLUSAO:
                if (status == StatusProjeto.CONCLUIDO || status == StatusProjeto.PAUSADO) {
                    this.statusAnterior = this.status;
                    this.status = status;
                } else {
                    System.out.println("Status inválido! A obra precisa seguir o seu curso. O próximo status deve ser CONCLUIDO.");
                }
                break;

                case CONCLUIDO:
                    if (temReclamacao){
                        this.statusAnterior = this.status;
                        this.status = status;
                    } else {
                        System.out.println("Obra concluída! Registre uma reclamação para reabrir.");
                    }
                    break;

                case PAUSADO:
                    this.status = this.statusAnterior;
                    this.statusAnterior  = null;
                    break;
        }
    }

 // Métodos de cálculo:

    public double calcularValorPorM2() {
        return orcamentoTotal / planta.getMetragemTotal();
    }

    public double calcularEntradaSugerida(double porcentagem){
       if (porcentagem < 20){
           System.out.println("Precisa ser no mínimo 20%.");
           return 0;
       } else {
           return orcamentoTotal * (porcentagem /100);
       }
    }
//Calcular o valor financiado:

    public double calcularParcelas(){
        double valorFinanciado = orcamentoTotal - valorEntrada;

//Limite de parcelas:

        if (orcamentoTotal <= 200000)
            if (numeroParcelas > 120) {
                numeroParcelas = 120;
                System.out.println("Número de parcelas informado é superior ao limite. Parcelas ajustado para 120 parcelas");
            } else {
             if (numeroParcelas > 240) {
                 numeroParcelas = 240;
                 System.out.println("Número de parcelas informado é superior ao limite. Parcelas ajustado para 240 parcelas");
             }
            }

//Calculando o Juros Compostos:
        double Jc = valorFinanciado * Math.pow(1.01 , numeroParcelas);

//Retornar o valor da parcela mensal:

        return Jc / numeroParcelas;
    }

    public void anteciparPrazo(int parcelasPagas){
        double percentualPago  = (double) parcelasPagas / numeroParcelas * 100;
        int prazoRestante = numeroParcelas - parcelasPagas;
        int novoPrazo;

        if (this.status == StatusProjeto.CONCLUIDO || this.status == StatusProjeto.PAUSADO) {
            System.out.println("Não é possível antecipar a obra neste status.");
            return;
        } else {
            if (percentualPago <= 33) {
                novoPrazo = prazoRestante - (int) (prazoRestante * 0.7);
        } else if (percentualPago <= 66) {
                novoPrazo = prazoRestante - (int) (prazoRestante * 0.55);
            } else {
                novoPrazo = prazoRestante - (int) (prazoRestante * 0.3);
            }
        }

        setDataPrevisao(novoPrazo + "meses");
        this.status =  StatusProjeto.EM_CONCLUSAO;
    }
}