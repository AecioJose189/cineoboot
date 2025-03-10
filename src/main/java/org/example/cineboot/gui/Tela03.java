package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.cineboot.Filme;
import org.example.cineboot.negocio.Sessao;
import java.io.IOException;
import java.net.URL;

public class Tela03 {
    private int quantidadeMeia;
    private int quantidadeInteira;
    private int quantidadeVip;
    private int quantidadeTotal;

    @FXML
    private Button concluir;

    private Filme filme;

    private Sessao sessao;

    @FXML
    private ImageView imagemPag3;

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

    private static final String PATH_AINDA_ESTOU_AQUI = "/org/example/cineboot/image/aindaestouaqui.jpg";
    private static final String PATH_AVATAR = "/org/example/cineboot/image/avatar.jpg";
    private static final String PATH_HOMEM_ARANHA = "/org/example/cineboot/image/homemaranha.jpg";
    private static final String PATH_OPPENHEIMER = "/org/example/cineboot/image/oppenheimer.jpg";

    public void setDetalhes(Filme filme, Sessao sessao, int quantidadeMeia, int quantidadeInteira, int quantidadeVip) {
        this.filme = filme;
        this.sessao = sessao;
        this.quantidadeMeia = quantidadeMeia;
        this.quantidadeInteira = quantidadeInteira;
        this.quantidadeVip = quantidadeVip;

        this.quantidadeTotal = quantidadeMeia + quantidadeInteira + quantidadeVip;

        exibirDetalhes();
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

    public void setFilme(Filme filme) {
        this.filme = filme;
        exibirDetalhes();
    }

    private void exibirDetalhes() {
        if (filme != null) {
            nomeDoFilmePg3.setText("Titulo do filme: "+filme.getTitulo());
            ingressosQuantidadePg3.setText("Quantidade total de ingressos: " +String.valueOf(this.quantidadeTotal));
            horarioLabelPg3.setText("Horario da sessão: "+sessao.getHorario());
            dataLabelPg3.setText("Data da sessão: "+sessao.getData());
        }

        String imagePath = filme.getImagem();
        URL imageUrl = getClass().getResource(imagePath);

        if (imageUrl != null) {
            imagemPag3.setImage(new Image(imageUrl.toString()));
        } else {
            System.out.println("Imagem não encontrada: " + imagePath);
        }


    }


    public void setConcluir(Button concluir) {
        this.concluir = concluir;
    }
}
