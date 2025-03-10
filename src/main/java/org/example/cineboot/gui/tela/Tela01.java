package org.example.cineboot.gui.tela;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Tela01 {
    @FXML
    private ImageView filmeAvatarPoster;

    @FXML
    private ImageView filmeHomemAranha;

    @FXML
    private ImageView filmeOppenheimerPoster;

    @FXML
    private ImageView filmeAindaEstouAqui;


    @FXML
    public void initialize() {
        rodar();
    }

    public Tela01(){

    }

    // Evento de clique na imagem
    public void rodar() {
        filmeAvatarPoster.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/tela02.fxml"));
                Parent root = loader.load();

                Tela02 controller = loader.getController();
                controller.inicializarTelaComFilme(1);

                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();

                Stage stage = new Stage();
                stage.setTitle("Detalhes do Filme");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();

            } catch (IOException e) {
                System.out.println("Erro ao carregar a tela de detalhes do filme Avatar: " + e.getMessage());
            }

        });
        filmeHomemAranha.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/tela02.fxml"));
                Parent root = loader.load();

                Tela02 controller = loader.getController();
                controller.inicializarTelaComFilme(2);

                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();

                Stage stage = new Stage();
                stage.setTitle("Detalhes do Filme");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();

            } catch (IOException e) {
                System.out.println("Erro ao carregar a tela de detalhes do filme Homem aranha: " + e.getMessage());
            }

        });
        filmeOppenheimerPoster.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/tela02.fxml"));
                Parent root = loader.load();

                Tela02 controller = loader.getController();
                controller.inicializarTelaComFilme(3);

                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();

                Stage stage = new Stage();
                stage.setTitle("Detalhes do Filme");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();

            } catch (IOException e) {
                System.out.println("Erro ao carregar a tela de detalhes do filme Oppenheimer: " + e.getMessage());
            }

        });
        filmeAindaEstouAqui.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/tela02.fxml"));
                Parent root = loader.load();

                Tela02 controller = loader.getController();
                controller.inicializarTelaComFilme(4);

                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();

                Stage stage = new Stage();
                stage.setTitle("Detalhes do Filme");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();

            } catch (IOException e) {
                System.out.println("Erro ao carregar a tela de detalhes do filme Ainda Estou Aqui: " + e.getMessage());
            }

        });

    }
}
