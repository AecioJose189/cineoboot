package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.cineboot.dados.DB;
import org.example.cineboot.negocio.Auth;
import org.example.cineboot.negocio.Compra;
import org.example.cineboot.negocio.Filme;
import org.example.cineboot.negocio.Sessao;

import java.io.IOException;
import java.util.ArrayList;

public class MinhasComprasController {
    @FXML
    private VBox itemsContainerVbox;

    @FXML
    private Button botaoVoltar;

    private ArrayList<Compra> compras;
    private DB db;
    private Auth auth;

    @FXML
    private void initialize() {
        db = DB.getInstance();
        auth = Auth.getInstance();

        String usuarioId = auth.getUsuario().getId();
        compras = db.getCompras(usuarioId);
    }

    private void rodarMinhasCompras() {
        for (Compra compra : compras) {
            try {
                Filme filme = db.getFilme(compra.getFilmeId());
                Sessao sessao = db.getSessao(compra.getSessaoId());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/item-historico.fxml"));
                itemsContainerVbox.getChildren().add(loader.load());

                ItemHistoricoController controller = loader.getController();
                controller.setupComponent(filme, sessao, compra);

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Ocorreu um erro interno.");
                alert.setContentText("Erro ao carregar os dados das compras.");
            }
        }
    }

    public void setupMinhasCompras() {
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Ocorreu um erro interno.");
                alert.setContentText("Erro ao carregar a tela inicial.");
                alert.showAndWait();
            }
        });
        rodarMinhasCompras();
    }
}
