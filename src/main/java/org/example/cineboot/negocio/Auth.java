package org.example.cineboot.negocio;

import org.example.cineboot.Usuario;
import org.example.cineboot.dados.DB;
import org.example.cineboot.exceptions.InvalidLoginException;

import java.io.IOException;
import java.util.List;

public class Auth {
    private Usuario usuario;
    private static Auth instance;
    private final DB db = DB.getInstance();

    public static Auth getInstance() {
        if (instance == null) {
            instance = new Auth();
        }
        return instance;
    }

    public void login(String login, String senha)  throws InvalidLoginException, IOException {
        List<Usuario> usuarios = db.carregarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                this.usuario = usuario;
                return;
            }
        }

        throw new InvalidLoginException("Login ou senha incorretos!");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void refresh() {
        List<Usuario> usuarios = db.carregarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(this.usuario.getLogin())) {
                this.usuario = usuario;
            }
        }
    }
}
