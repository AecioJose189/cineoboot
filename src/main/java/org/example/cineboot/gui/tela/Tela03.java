package org.example.cineboot.gui.tela;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.cineboot.negocio.Filme;
import org.example.cineboot.negocio.Sessao;

import java.io.IOException;

public class Tela03 {
    private int quantidadeTotal;

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

    @FXML
    private void initialize() {
        buttonFinal();
    }

    public void setDetalhes(Filme filme, Sessao sessao, int quantidadeMeia, int quantidadeInteira, int quantidadeVip) {
        this.filme = filme;
        this.sessao = sessao;
        this.quantidadeTotal = quantidadeMeia + quantidadeInteira + quantidadeVip;
        exibirDetalhes();
    }

    public void buttonFinal() {
        concluir.setOnAction(
                event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/tela01.fxml"));
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
        }
    }
}
