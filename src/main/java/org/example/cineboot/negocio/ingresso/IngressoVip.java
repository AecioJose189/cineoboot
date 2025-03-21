package org.example.cineboot.negocio.ingresso;

import org.example.cineboot.negocio.Filme;
import org.example.cineboot.negocio.Sessao;

public class IngressoVip extends Ingresso {
    private double adicional;

    public IngressoVip(double preco, Filme filme, Sessao sessao) {
        super(preco, filme, sessao);
        this.adicional = adicional - (preco * 0.5);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Ingresso VIP - Preço: R$" + (preco + adicional));
    }
}
