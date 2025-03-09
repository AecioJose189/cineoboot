package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.cineboot.Filme;
import org.example.cineboot.dados.DB;
import org.example.cineboot.negocio.IngressoMeiaEntrada;
import org.example.cineboot.negocio.IngressoVip;
import org.example.cineboot.negocio.Sessao;
import org.example.cineboot.negocio.Venda;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Tela03 {
    @FXML
    private Button concluir;

    private Filme filme;

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

    public void buttonFinal(){
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

                    } catch (
                            IOException e) {
                        e.printStackTrace();
                    }
                });

    }
    public void exibirDetalhes(int id) {


        DB db = DB.getInstance();
        filme = db.getFilme(id);

        //horario do filme falta ainda
        //quantidade de ingresso falta ainda

    }



    public void setConcluir(Button concluir) {
        this.concluir = concluir;
    }

}
