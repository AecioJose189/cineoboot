package org.example.cineboot.negocio;

public class IngressoMeiaEntrada extends Ingresso implements Desconto {
    public IngressoMeiaEntrada(double preco) {
        super(preco);
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
