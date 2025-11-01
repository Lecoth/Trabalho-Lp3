package model;

public class Arma {
    private int id_arma;
    private String nome;
    private String tipo_arma;
    private int estrelas;
    private int base_atk;
    private String sub_status;
    private String efeito;
    private String imagem;

    public int getId_arma() {
        return id_arma;
    }

    public void setId_arma(int id_arma) {
        this.id_arma = id_arma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo_arma() {
        return tipo_arma;
    }

    public void setTipo_arma(String tipo_arma) {
        this.tipo_arma = tipo_arma;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public int getBase_atk() {
        return base_atk;
    }

    public void setBase_atk(int base_atk) {
        this.base_atk = base_atk;
    }

    public String getSub_status() {
        return sub_status;
    }

    public void setSub_status(String sub_status) {
        this.sub_status = sub_status;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
