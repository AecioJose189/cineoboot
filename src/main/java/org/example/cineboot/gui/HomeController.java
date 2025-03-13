package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.cineboot.Usuario;
import org.example.cineboot.negocio.Auth;
import org.example.cineboot.negocio.Compra;
import org.example.cineboot.negocio.Filme;

import java.io.IOException;

public class HomeController {
    @FXML
    private ImageView filmeAvatarPoster;

    @FXML
    private Button minhasComprasButton;

    @FXML
    private Label olaUsuario;

    @FXML
    private Label voceTemTantasMacas1;

    @FXML
    private ImageView filmeHomemAranha;

    @FXML
    private ImageView filmeOppenheimerPoster;

    @FXML
    private ImageView filmeAindaEstouAqui;

    private final Auth auth = Auth.getInstance();

    @FXML
    public void initialize() {
        usuario = auth.getUsuario();
        atualizarLabels();
        rodar();
    }

    private Usuario usuario;

    private void atualizarLabels() {
        olaUsuario.setText("Bem-vindo, " + usuario.getLogin() + "!");
        voceTemTantasMacas1.setText("Você tem " + usuario.quantidadeIngressos() + " ingressos comprados.");
    }

    // Evento de clique na imagem
    public void rodar() {

        minhasComprasButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/minhas-compras.fxml"));
                Parent root = loader.load();

                MinhasComprasController controller = loader.getController();
                controller.setupMinhasCompras(new Compra(
                        1, 2, 7, 3, 1, 1L
                ));

                Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageAtual.close();

                Stage stage = new Stage();
                stage.setTitle("Minhas compras");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();
            } catch (IOException e) {
                System.out.println("Erro ao carregar a tela de minhas compras: " + e.getMessage());
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        });

        filmeAvatarPoster.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/compra.fxml"));
                Parent root = loader.load();

                CompraController controller = loader.getController();
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/compra.fxml"));
                Parent root = loader.load();

                CompraController controller = loader.getController();
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/compra.fxml"));
                Parent root = loader.load();

                CompraController controller = loader.getController();
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/compra.fxml"));
                Parent root = loader.load();

                CompraController controller = loader.getController();
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
