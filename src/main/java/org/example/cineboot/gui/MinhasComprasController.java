package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.cineboot.dados.DB;
import org.example.cineboot.negocio.Compra;
import org.example.cineboot.negocio.Filme;
import org.example.cineboot.negocio.Sessao;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;

public class MinhasComprasController {
    @FXML
    private ScrollPane scrollPane;

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

    @FXML
    private Button botaoVoltar;

    private final Locale localBrasil = new Locale("pt", "BR");
    private Compra compra;
    private DB db;
    private Filme filme;
    private Sessao sessao;

    @FXML
    private void initialize() {
        db = DB.getInstance();
    }

    private void rodarMinhasCompras() {
        filme = db.getFilme(compra.getFilmeId());
        sessao = db.getSessao(compra.getSessaoId());

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


    public void setupMinhasCompras(Compra compra) {
        this.compra = compra;
        botaoVoltar.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/home.fxml"));
                Parent root = loader.load();

                Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageAtual.close();

                Stage stage = new Stage();
                stage.setTitle("Tela Inicial");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();

            } catch (IOException e) {
                System.out.println("Erro ao carregar a tela inicial: " + e.getMessage());
            }
        });
        rodarMinhasCompras();
    }
}
