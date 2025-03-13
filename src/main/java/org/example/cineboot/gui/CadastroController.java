package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.cineboot.dados.DB;
import org.example.cineboot.exceptions.InvalidFieldException;
import org.example.cineboot.negocio.TipoDeUsuario;

import java.io.IOException;

public class CadastroController {
    @FXML
    private Label nomeLabel;

    @FXML
    private TextField nomeInput;

    @FXML
    private Label nomeDeUsuarioLabel;

    @FXML
    private TextField nomeDeUsuarioInput;

    @FXML
    private Label senhaLabel;

    @FXML
    private PasswordField senhaInput;

    @FXML
    private Label tipoUsuarioLabel;

    @FXML
    private ChoiceBox<TipoDeUsuario> tipoUsuarioChoiceBox;

    @FXML
    private CheckBox usuarioVipCheckbox;

    @FXML
    private Button botaoCancelar;

    @FXML
    private Button botaoCadastrar;

    private DB db;

    @FXML
    private void initialize() {
        nomeLabel.setLabelFor(nomeInput);
        nomeDeUsuarioLabel.setLabelFor(nomeDeUsuarioInput);
        senhaLabel.setLabelFor(senhaInput);
        tipoUsuarioLabel.setLabelFor(tipoUsuarioChoiceBox);
        tipoUsuarioChoiceBox.getItems().addAll(TipoDeUsuario.values());
        db = DB.getInstance();
        setupButtons();
    }

    private void setupButtons() {
        botaoCancelar.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmacao");
            alert.setHeaderText(null);
            alert.setContentText("Você tem certeza que deseja cancelar?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
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
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setTitle("Erro");
                        alert.setHeaderText("Ocorreu um erro interno.");
                        alert.setContentText("Erro ao carregar a tela inicial.");
                        alert.showAndWait();
                    }
                }
            });
        });

        botaoCadastrar.setOnAction(event -> {
            boolean cadastroSucesso = false;

            try {
                db.criarUsuario(
                        nomeInput.getText(),
                        nomeDeUsuarioInput.getText(),
                        senhaInput.getText(),
                        tipoUsuarioChoiceBox.getValue(),
                        usuarioVipCheckbox.isSelected()
                );
                cadastroSucesso = true;
            } catch (InvalidFieldException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Campo " + e.getField() + " inválido!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cadastroSucesso) {
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
            }
        });
    }
}
