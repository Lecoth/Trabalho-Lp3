package model;

import java.time.LocalDateTime;

public class BuildUsuario {
    private int id_build_user; // Usando user pq diminui o tamanho do nome
    private int id_usuario;
    private int id_personagem;
    private int id_arma_usada;
    private int id_art_set;
    private String nome_build;
    private boolean privada;
    private String sands_main;
    private String goblet_main;
    private String circlet_main;
    private String descricao; // Não lembro pq coloquei isso, seria um resumo do pq fez essa build?
    private java.time.LocalDateTime data_criacao;

    public int getId_build_user() {
        return id_build_user;
    }

    public void setId_build_user(int id_build_user) {
        this.id_build_user = id_build_user;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_personagem() {
        return id_personagem;
    }

    public void setId_personagem(int id_personagem) {
        this.id_personagem = id_personagem;
    }

    public int getId_arma_usada() {
        return id_arma_usada;
    }

    public void setId_arma_usada(int id_arma_usada) {
        this.id_arma_usada = id_arma_usada;
    }

    public int getId_art_set() {
        return id_art_set;
    }

    public void setId_art_set(int id_art_set) {
        this.id_art_set = id_art_set;
    }

    public String getNome_build() {
        return nome_build;
    }

    public void setNome_build(String nome_build) {
        this.nome_build = nome_build;
    }

    public boolean isPrivada() {
        return privada;
    }

    public void setPrivada(boolean privada) {
        this.privada = privada;
    }

    public String getSands_main() {
        return sands_main;
    }

    public void setSands_main(String sands_main) {
        this.sands_main = sands_main;
    }

    public String getGoblet_main() {
        return goblet_main;
    }

    public void setGoblet_main(String goblet_main) {
        this.goblet_main = goblet_main;
    }

    public String getCirclet_main() {
        return circlet_main;
    }

    public void setCirclet_main(String circlet_main) {
        this.circlet_main = circlet_main;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }
}
