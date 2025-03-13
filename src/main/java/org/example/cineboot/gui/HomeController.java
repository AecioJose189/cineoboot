package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.cineboot.negocio.TipoDeUsuario;
import org.example.cineboot.negocio.Usuario;
import org.example.cineboot.dados.DB;
import org.example.cineboot.negocio.Auth;
import org.example.cineboot.negocio.Filme;

import java.io.IOException;
import java.util.List;


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

    @FXML
    private Button cadastrarUsuarioButton;

    private final Auth auth = Auth.getInstance();

    private List<Filme> filmes;

    @FXML
    public void initialize() {
        usuario = auth.getUsuario();

        if (!usuario.getTipo().equals(TipoDeUsuario.ADMINISTRADOR)) {
            cadastrarUsuarioButton.setVisible(false);
        }

        atualizarLabels();
        rodar();
    }

    private Usuario usuario;

    private void atualizarLabels() {
        olaUsuario.setText("Bem-vindo, " + usuario.getNome() + "!");
        voceTemTantasMacas1.setText("Você tem " + usuario.quantidadeIngressos() + " ingressos comprados.");
    }

    public void rodar() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Ocorreu um erro ao carregar a tela de compra de ingresso");

//        filmes = db.getFilmes();
//
//        for (Filme filme : filmes) {
//
//        }
        cadastrarUsuarioButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/cadastro.fxml"));
                Parent root = loader.load();

                Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageAtual.close();

                Stage stage = new Stage();
                stage.setTitle("Cadastrar usuário");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();
            } catch (IOException e) {
                alert.setHeaderText("Ocorreu um erro interno.");
                alert.setContentText("Erro ao carregar a tela de minhas compras.");
                alert.showAndWait();
            }
        });

        minhasComprasButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/minhas-compras.fxml"));
                Parent root = loader.load();

                MinhasComprasController controller = loader.getController();
                controller.setupMinhasCompras();

                Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageAtual.close();

                Stage stage = new Stage();
                stage.setTitle("Minhas compras");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();
            } catch (IOException e) {
                alert.setHeaderText("Ocorreu um erro interno.");
                alert.setContentText("Erro ao carregar a tela de minhas compras.");
                alert.showAndWait();
            }
        });

        filmeAvatarPoster.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/compra.fxml"));
                Parent root = loader.load();

                CompraController controller = loader.getController();
                controller.inicializarTelaComFilme("353a6f84-e979-4cf3-8fef-a934605afe22");

                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();

                Stage stage = new Stage();
                stage.setTitle("Detalhes do Filme");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();

            } catch (IOException e) {
                alert.setContentText("Erro ao carregar dados do filme Avatar.");
                alert.showAndWait();
            }

        });
        filmeHomemAranha.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/compra.fxml"));
                Parent root = loader.load();

                CompraController controller = loader.getController();
                controller.inicializarTelaComFilme("75d3a937-2f96-4e2f-9889-a5ec2e60b9f7");

                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();

                Stage stage = new Stage();
                stage.setTitle("Detalhes do Filme");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();

            } catch (IOException e) {
                alert.setContentText("Erro ao carregar dados do filme Homem Aranha.");
                alert.showAndWait();
            }

        });
        filmeOppenheimerPoster.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/compra.fxml"));
                Parent root = loader.load();

                CompraController controller = loader.getController();
                controller.inicializarTelaComFilme("31579740-785c-4f11-b202-e2854a193672");

                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();

                Stage stage = new Stage();
                stage.setTitle("Detalhes do Filme");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();

            } catch (IOException e) {
                alert.setContentText("Erro ao carregar dados do filme Oppenheimer.");
                alert.showAndWait();
            }

        });
        filmeAindaEstouAqui.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/compra.fxml"));
                Parent root = loader.load();

                CompraController controller = loader.getController();
                controller.inicializarTelaComFilme("7026de69-7acc-4bec-85cd-299679bd770f");

                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.close();

                Stage stage = new Stage();
                stage.setTitle("Detalhes do Filme");
                stage.setScene(new Scene(root, 650, 700));
                stage.show();

            } catch (IOException e) {
                alert.setContentText("Erro ao carregar dados do filme Ainda Estou Aqui.");
                alert.showAndWait();
            }
        });
    }
}
