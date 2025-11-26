package model;

public class Favorito {
    private int idBuild;
    private String tipo;
    private String nomeDisplay;

    public Favorito(int idBuild, String tipo, String nomeDisplay) {
        this.idBuild = idBuild;
        this.tipo = tipo;
        this.nomeDisplay = nomeDisplay;
    }

    public int getIdBuild() { return idBuild; }
    public String getTipo() { return tipo; }
    public String getNome() { return nomeDisplay; }

    @Override
    public String toString() {
        return "[" + tipo + "] " + nomeDisplay;
    }
}
