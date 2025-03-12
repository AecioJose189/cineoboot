package org.example.cineboot.negocio;

import org.example.cineboot.Usuario;
import org.example.cineboot.negocio.ingresso.Ingresso;

import java.util.ArrayList;
import java.util.List;


public class Venda {
    private List<Ingresso> ingressos = new ArrayList<>();
    private Usuario usuario;

    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public void mostrarIngressos() {
        for (Ingresso ingresso : ingressos) {
            ingresso.exibirDetalhes();
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }
}
