package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.cineboot.negocio.Compra;
import org.example.cineboot.negocio.Filme;
import org.example.cineboot.negocio.Sessao;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;

public class ItemHistoricoController {

    @FXML
    private ImageView imagemFilme;

    @FXML
    private Label nomeDoFilmeLabel;

    @FXML
    private Text valorTotalDaCompraText;

    @FXML
    private Text horarioDoFilmeText;

    @FXML
    private Text dataDoFilmeText;

    @FXML
    private Text quantidadeMeiaText;

    @FXML
    private Text quantidadeInteiraText;

    @FXML
    private Text quantidadeVipText;

    private final Locale localBrasil = new Locale("pt", "BR");
    private Filme filme;
    private Sessao sessao;


    public void setupComponent(Filme filme, Sessao sessao, Compra compra) {
        this.filme = filme;
        this.sessao = sessao;
        nomeDoFilmeLabel.setText(filme.getTitulo());
        valorTotalDaCompraText.setText(NumberFormat.getCurrencyInstance(localBrasil).format(compra.getQuantidadeInteira() * 24 + compra.getQuantidadeVip() * 12 + compra.getQuantidadeMeia() * 12));
        horarioDoFilmeText.setText(sessao.getHorario());
        dataDoFilmeText.setText(sessao.getData());
        quantidadeMeiaText.setText(compra.getQuantidadeMeia() + " x Meia");
        quantidadeInteiraText.setText(compra.getQuantidadeInteira() + " x Inteira");
        quantidadeVipText.setText(compra.getQuantidadeVip() + " x VIP");
        exibirImagem();
    }

    private void exibirImagem() {
        URL imageUrl = getClass().getResource(filme.getImagem());
        if (imageUrl != null) {
            imagemFilme.setImage(new Image(imageUrl.toString()));
        } else {
            System.out.println("Imagem não encontrada: " + filme.getImagem());
        }
    }

}
