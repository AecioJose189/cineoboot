package org.example.cineboot.negocio;

public class Usuario {
    private String id;
    private String username;
    private String senha;
    private int quantidadeIngressos;
    private Boolean isVip;
    private String nome;
    private TipoDeUsuario tipo;

    public Usuario(String id, String username, String senha, int quantidadeIngressos, Boolean isVip, String nome, TipoDeUsuario tipo) {
        this.id = id;
        this.username = username;
        this.senha = senha;
        this.quantidadeIngressos = quantidadeIngressos;
        this.isVip = isVip;
        this.nome = nome;
        this.tipo = tipo;
    }

    // Getters e setters
    public String getUsername() { return username; }
    public void setUsername(String login) { this.username = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public int quantidadeIngressos() { return quantidadeIngressos; }
    public void quantidadeIngressos(int quantidadeMacas) { this.quantidadeIngressos = quantidadeMacas; }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public TipoDeUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeUsuario tipo) {
        this.tipo = tipo;
    }

    public Boolean getVip() {
        return isVip;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}