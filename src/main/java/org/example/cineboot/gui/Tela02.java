package org.example.cineboot.gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.cineboot.Filme;
import org.example.cineboot.dados.DB;

import java.io.IOException;

public class Tela02 {
    @FXML
    private Label nomeDoFilmepg2;

    @FXML
    private ImageView imagemPag2;

    @FXML
    private Button voltarpg2;

    @FXML
    private Label sinopseLabel;

    @FXML
    private Label duracaoLabel;

    @FXML
    private Label classificacaoLabel;


    @FXML
    private AnchorPane root;

    private Spinner<Integer> meiaSpinner;

    public void exibirDetalhes(int id) {
        meiaSpinner = new Spinner<>(0,10,0);
        meiaSpinner.setLayoutX(240);
        meiaSpinner.setLayoutY(514);
        meiaSpinner.setEditable(true);

        root.getChildren().add(meiaSpinner);

        DB db = DB.getInstance();
        Filme filme = db.getFilme(id);

        nomeDoFilmepg2.setText(filme.getTitulo());
        sinopseLabel.setText(filme.getDescricao());

        duracaoLabel.setText("Duração: "+filme.getDuracao());
        classificacaoLabel.setText("Classificação: "+filme.getClassificacao());

        System.out.println("OIIIIIIIIIII");
        meiaSpinner.increment(5);
        // imagemPag2.setImage(new Image(filme.getImagem()));
    }
    public void voltarTela() {
        voltarpg2.setOnMouseClicked(event -> {
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
                e.printStackTrace();
            }

        });
    }


    public void inicializarTelaComFilme(int id) {
        exibirDetalhes(id);
    }
}
