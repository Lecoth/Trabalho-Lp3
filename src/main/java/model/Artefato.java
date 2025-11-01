package model;

public class Artefato {
    private int id_artefato;
    private String nome_set;
    private int estrelas;
    private String efeito_2pecas;
    private String efeito_4pecas;
    private String imagem;

    public int getId_artefato() {
        return id_artefato;
    }

    public void setId_artefato(int id_artefato) {
        this.id_artefato = id_artefato;
    }

    public String getNome_set() {
        return nome_set;
    }

    public void setNome_set(String nome_set) {
        this.nome_set = nome_set;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public String getEfeito_2pecas() {
        return efeito_2pecas;
    }

    public void setEfeito_2pecas(String efeito_2pecas) {
        this.efeito_2pecas = efeito_2pecas;
    }

    public String getEfeito_4pecas() {
        return efeito_4pecas;
    }

    public void setEfeito_4pecas(String efeito_4pecas) {
        this.efeito_4pecas = efeito_4pecas;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
