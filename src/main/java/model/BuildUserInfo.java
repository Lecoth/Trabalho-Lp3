package model;


public class BuildUserInfo {
    private int id_build_user;
    private int id_usuario;
    private Personagem personagem;
    private Arma arma;
    private Artefato artefato;
    private String nome_usuario;
    private String nome_build;
    private boolean privada;
    private String sands_main;
    private String goblet_main;
    private String circlet_main;

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

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public Artefato getArtefato() {
        return artefato;
    }

    public void setArtefato(Artefato artefato) {
        this.artefato = artefato;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
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
}
