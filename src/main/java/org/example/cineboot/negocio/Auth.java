package org.example.cineboot.negocio;

import org.example.cineboot.dados.DB;
import org.example.cineboot.exceptions.InvalidCredentialsException;

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

    public void login(String login, String senha) throws InvalidCredentialsException, IOException {
        List<Usuario> usuarios = db.carregarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(login) && usuario.getSenha().equals(senha)) {
                this.usuario = usuario;
                return;
            }
        }

        throw new InvalidCredentialsException("Login ou senha incorretos!");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void refresh() {
        try {
            List<Usuario> usuarios = db.carregarUsuarios();
            for (Usuario usuario : usuarios) {
                if (usuario.getUsername().equals(this.usuario.getUsername())) {
                    this.usuario = usuario;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
