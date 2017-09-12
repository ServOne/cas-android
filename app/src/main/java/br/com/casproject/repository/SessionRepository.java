package br.com.casproject.repository;


import br.com.casproject.modelo.Usuario;

public final class SessionRepository {

    private static String nomeusuario;
    private static String senhausuario;
    private static Usuario usuario;

    public SessionRepository(String nomeusuario, String senhausuario) {
        this.nomeusuario = nomeusuario;
        this.senhausuario = senhausuario;
    }

    public static String getNomeusuario() {
        return nomeusuario;
    }

    public static void setNomeusuario(String nomeusuario) {
        SessionRepository.nomeusuario = nomeusuario;
    }

    public static String getSenhausuario() {
        return senhausuario;
    }

    public static void setSenhausuario(String senhausuario) {
        SessionRepository.senhausuario = senhausuario;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        SessionRepository.usuario = usuario;
    }
}
