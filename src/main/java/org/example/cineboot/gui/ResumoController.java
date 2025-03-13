package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.cineboot.negocio.Auth;
import org.example.cineboot.negocio.Filme;
import org.example.cineboot.negocio.Sessao;

import java.io.IOException;

public class ResumoController {
    private int quantidadeTotal;
    private float precoTotal;

    @FXML
    private Button concluir;

    private Filme filme;

    private Sessao sessao;

    @FXML
    private Label nomeDoFilmePg3;

    @FXML
    private Label dataLabelPg3;

    @FXML
    private Label horarioLabelPg3;

    @FXML
    private Label ingressosQuantidadePg3;

    private Auth auth;

    @FXML
    private Label precoTotalLabel;


    @FXML
    private void initialize() {
        auth = Auth.getInstance();
        buttonFinal();
    }

    public void setDetalhes(Filme filme, Sessao sessao, int quantidadeMeia, int quantidadeInteira, int quantidadeVip, float precoTotal) {
        this.filme = filme;
        this.sessao = sessao;
        this.quantidadeTotal = quantidadeMeia + quantidadeInteira + quantidadeVip;
        this.precoTotal = precoTotal;
        exibirDetalhes();
    }

    public void buttonFinal() {
        concluir.setOnAction(
                event -> {
                    auth.refresh();
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

    }

    public void setFilme(Filme filme) {
        this.filme = filme;
        exibirDetalhes();
    }

    private void exibirDetalhes() {
        if (filme != null) {
            nomeDoFilmePg3.setText("Titulo do filme: " + filme.getTitulo());
            ingressosQuantidadePg3.setText("Quantidade total de ingressos: " + this.quantidadeTotal);
            horarioLabelPg3.setText("Horario da sessão: " + sessao.getHorario());
            dataLabelPg3.setText("Data da sessão: " + sessao.getData());
            precoTotalLabel.setText("Preço total: "+precoTotal);
        }
    }
}
