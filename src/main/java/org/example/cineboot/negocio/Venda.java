package org.example.cineboot.negocio;
import java.util.ArrayList;
import java.util.List;


public class Venda {
    private List<Ingresso> ingressos = new ArrayList<>();

    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public void mostrarIngressos() {
        for (Ingresso ingresso : ingressos) {
            ingresso.exibirDetalhes();
        }
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }
}
