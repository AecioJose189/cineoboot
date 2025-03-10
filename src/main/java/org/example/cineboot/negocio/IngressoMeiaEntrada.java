package org.example.cineboot.negocio;

public class IngressoMeiaEntrada extends Ingresso implements Desconto {
    public IngressoMeiaEntrada(double preco, Filme filme, Sessao sessao) {
        super(preco, filme, sessao);
    }

    @Override
    public double calcularDesconto() {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço do  ingresso não pode ser negativo.");
        }
        return preco * 0.5;
    }

    @Override
    public void exibirDetalhes() {
        try {
            double desconto = calcularDesconto();
            System.out.println("Ingresso Meia Entrada - Preço: R$" + desconto);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na exibição dos detalhes do ingresso: " + e.getMessage());
        }
    }
}