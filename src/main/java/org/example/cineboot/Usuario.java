package org.example.cineboot;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String login;
    private String senha;
    private int quantidadeIngressos;
    private int id;

    public Usuario() {}

    public Usuario(String login, String senha, int quantidadeIngressos, int id) {
        this.login = login;
        this.senha = senha;
        this.quantidadeIngressos = quantidadeIngressos;
        this.id = id;
    }

    // Getters e setters
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public int quantidadeIngressos() { return quantidadeIngressos; }
    public void quantidadeIngressos(int quantidadeMacas) { this.quantidadeIngressos = quantidadeMacas; }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}