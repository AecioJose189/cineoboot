package org.example.cineboot.negocio;

import org.example.cineboot.Filme;

public class IngressoMeiaEntrada extends Ingresso implements Desconto {
    public IngressoMeiaEntrada(double preco, Filme filme, Sessao sessao) {
        super(preco, filme, sessao);
    }

    @Override
    public double calcularDesconto() {
        return preco * 0.5;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Ingresso Meia Entrada - Preço: R$" + calcularDesconto());
    }
}
