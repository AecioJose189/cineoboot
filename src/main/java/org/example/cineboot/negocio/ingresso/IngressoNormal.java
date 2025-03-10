package org.example.cineboot.negocio.ingresso;

import org.example.cineboot.negocio.Filme;
import org.example.cineboot.negocio.Sessao;

public class IngressoNormal extends Ingresso {
    public IngressoNormal(double preco, Filme filme, Sessao sessao) {
        super(preco, filme, sessao);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Ingresso Normal - Preço: R$" + preco);
    }
}
