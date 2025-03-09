package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.cineboot.Filme;

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

    private ImageView filmeteste;

    @FXML
    public void initialize() {
        rodar();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    e.printStackTrace();
                }

            });
//            filmeAvatarPoster.setOnMousePressed(event ->{
//                // Carrega a segunda tela
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("segunda-tela.fxml"));
//
//
//
//                // Obtém o controlador da segunda tela

//
//                // Passa o filme para o controlador da segunda tela
//
//
//                // Fecha a primeira tela
//                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                primaryStage.close();
//
//                // Exibe a segunda tela
//                Stage stage = new Stage();
//                stage.setTitle("Detalhes do Filme");
//                stage.setScene(new Scene(root, 800, 600));
//                stage.show();
//            }

        }


    public Tela01() throws IOException {

    }
}
