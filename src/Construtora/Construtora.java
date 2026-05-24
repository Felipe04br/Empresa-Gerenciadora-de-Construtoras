package Construtora;

public class Construtora {

        private String razaoSocial;
        private String cnpj;
        private String telefone;
        private String email;
        private String endereco;
        private String responsavelTecnico;
        private String crea; // Registro no Conselho Regional de Engenharia
        private String dataFundacao;
        private boolean ativa;

        public String getRazaoSocial() {
            return razaoSocial;
        }

        public void setRazaoSocial(String razaoSocial) {
            this.razaoSocial = razaoSocial;
        }

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getResponsavelTecnico() {
            return responsavelTecnico;
        }

        public void setResponsavelTecnico(String responsavelTecnico) {
            this.responsavelTecnico = responsavelTecnico;
        }

        public String getCrea() {
            return crea;
        }

        public void setCrea(String crea) {
            this.crea = crea;
        }

        public String getDataFundacao() {
            return dataFundacao;
        }

        public void setDataFundacao(String dataFundacao) {
            this.dataFundacao = dataFundacao;
        }

        public boolean isAtiva() {
            return ativa;
        }

        public void setAtiva(boolean ativa) {
            this.ativa = ativa;
        }
    }


