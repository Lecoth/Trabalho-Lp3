package db;

import model.Usuario;

public class UserSessao {
    private static Usuario usuarioLogado;
    public static void setUsuarioLogado(Usuario u) { usuarioLogado = u; }
    public static Usuario getUsuarioLogado() { return usuarioLogado; }
}
