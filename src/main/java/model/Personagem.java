package model;

public class Personagem {
    private int id_personagem;
    private String nome;
    private String elemento;
    private String tipo_arma;
    private int estrelas;
    private String talentos;
    private String imagem;

    public int getId_personagem() {
        return id_personagem;
    }

    public void setId_personagem(int id_personagem) {
        this.id_personagem = id_personagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
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

    public String getTalentos() {
        return talentos;
    }

    public void setTalentos(String talentos) {
        this.talentos = talentos;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
