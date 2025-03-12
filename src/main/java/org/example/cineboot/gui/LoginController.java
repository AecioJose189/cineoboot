package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.cineboot.exceptions.InvalidLoginException;
import org.example.cineboot.negocio.Auth;

import java.io.IOException;

public class LoginController {
    @FXML private TextField loginField;
    @FXML private PasswordField senhaField;
    @FXML private Button loginButton;

    private Auth auth = Auth.getInstance();

    @FXML
    public void initialize() {
        loginButton.setOnAction(event -> {
            try {
                tryLogin();
            } catch (IOException e){
                e.printStackTrace();
            }
        });
    }

    private void tryLogin() throws IOException {
        String login = loginField.getText();
        String senha = senhaField.getText();

        try {
            auth.login(login, senha);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/home.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            loginField.getScene().getWindow().hide();
        } catch (InvalidLoginException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro ao fazer login");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }


    }
}