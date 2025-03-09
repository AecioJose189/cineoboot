package org.example.cineboot.negocio;

public class IngressoNormal extends Ingresso{
    public IngressoNormal(double preco) {
        super(preco);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Ingresso Normal - Preço: R$" + preco);
    }
}
