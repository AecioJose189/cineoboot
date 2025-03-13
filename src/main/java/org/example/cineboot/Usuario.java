package org.example.cineboot;

import org.example.cineboot.negocio.Compra;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String login;
    private String senha;
    private int quantidadeIngressos;
//    private ArrayList<Compra> compras;

    public Usuario(int id, String login, String senha, int quantidadeIngressos) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.quantidadeIngressos = quantidadeIngressos;
//        this.compras = compras;
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