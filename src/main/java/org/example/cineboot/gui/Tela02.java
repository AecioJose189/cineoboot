package org.example.cineboot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.cineboot.Filme;
import org.example.cineboot.dados.DB;
import org.example.cineboot.negocio.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
    private ComboBox<String> selecioneDataCbox;

    @FXML
    private Label duracaoLabel;

    @FXML
    private Label classificacaoLabel;

    @FXML
    private AnchorPane root;

    @FXML
    private ComboBox<String> selecioneHorarioCbox;

    @FXML
    private Label quantidadeDisponivelLabel;

    @FXML
    private Button confirmarEscolhaPg2;

    private DB db;
    private Tela03 tela03;
    private Filme filme;
    private Sessao sessao;
    private Spinner<Integer> meiaSpinner;
    private Spinner<Integer> inteiraSpinner;
    private Spinner<Integer> vipSpinner;
    private Set<String> horarios;

    @FXML
    private void initialize() {
        botaoVoltar();
        db = DB.getInstance();
        horarios = new HashSet<>();
        setupButton();
    }

    private void onEscolherData(String data) {
        horarios.clear();
        for (int i = 0; i < filme.getSessoes().size(); i++) {
            Sessao sessaoAtual = filme.getSessoes().get(i);
            if (data.equals(sessaoAtual.getData())) {
                horarios.add(sessaoAtual.getHorario());
            }
        }

        selecioneHorarioCbox.getItems().setAll(horarios);
    }

    private void onEscolherHorario(String horarioEscolhido) {
        for (int i = 0; i < filme.getSessoes().size(); i++) {
            Sessao sessaoAtual = filme.getSessoes().get(i);
            String dataEscolhida = selecioneDataCbox.getValue();
            if (dataEscolhida.equals(sessaoAtual.getData()) && horarioEscolhido.equals(sessaoAtual.getHorario())) {
                quantidadeDisponivelLabel.setText("Quantidade de cadeiras disponíveis " + (sessaoAtual.getIngressosTotais() - sessaoAtual.getIngressosComprados()) + "/" + sessaoAtual.getIngressosTotais());
                sessao = sessaoAtual;
            }
        }
    }

    private void setupButton(){
        confirmarEscolhaPg2.setOnAction(
                event -> {
                    Venda venda = new Venda();

                    int quantidadeMeia = meiaSpinner.getValue();
                    int quantidadeInteira = inteiraSpinner.getValue();
                    int quantidadeVip = vipSpinner.getValue();

                    for (int i = 0; i < meiaSpinner.getValue(); i++) {
                        venda.adicionarIngresso(new IngressoMeiaEntrada(20, filme, sessao));
                    }
                    for (int i = 0; i < inteiraSpinner.getValue(); i++) {
                        venda.adicionarIngresso(new IngressoVip(20, filme, sessao));
                    }
                    for (int i = 0; i < vipSpinner.getValue(); i++) {
                        venda.adicionarIngresso(new IngressoVip(20, filme, sessao));
                    }
                    try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/tela03.fxml"));
                        Parent root = loader.load();

                        Tela03 tela03Controller = loader.getController();

                        tela03Controller.setDetalhes(filme, sessao, quantidadeMeia, quantidadeInteira, quantidadeVip);

                        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stageAtual.close();

                        Stage stage = new Stage();
                        stage.setTitle("Resumo do pedido");
                        stage.setScene(new Scene(root, 650, 700));
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    db.processarVenda(venda);
                }

        );
    }

    private void setupSpinners() {
        meiaSpinner = new Spinner<>(0, 10, 0);
        meiaSpinner.setLayoutX(240);
        meiaSpinner.setLayoutY(514);
        meiaSpinner.setPrefHeight(25);
        meiaSpinner.setPrefWidth(62);

        inteiraSpinner = new Spinner<>(0, 10, 0);
        inteiraSpinner.setLayoutX(240);
        inteiraSpinner.setLayoutY(548);
        inteiraSpinner.setPrefHeight(25);
        inteiraSpinner.setPrefWidth(62);

        vipSpinner = new Spinner<>(0, 10, 0);
        vipSpinner.setLayoutX(240);
        vipSpinner.setLayoutY(588);
        vipSpinner.setPrefHeight(25);
        vipSpinner.setPrefWidth(62);

        root.getChildren().add(meiaSpinner);
        root.getChildren().add(inteiraSpinner);
        root.getChildren().add(vipSpinner);
    }


    public void exibirDetalhes(int id) {
        setupSpinners();

        DB db = DB.getInstance();
        filme = db.getFilme(id);

        nomeDoFilmepg2.setText(filme.getTitulo());
        sinopseLabel.setText(filme.getDescricao());

        Set<String> datas = new HashSet<>();

        duracaoLabel.setText("Duração: " + filme.getDuracao());
        classificacaoLabel.setText("Classificação: " + filme.getClassificacao());

        for (int i = 0; i < filme.getSessoes().size(); i++) {
            Sessao sessaoAtual = filme.getSessoes().get(i);
            if (sessaoAtual.getIngressosTotais() - sessaoAtual.getIngressosComprados() == 0) {
                continue;
            }

            datas.add(filme.getSessoes().get(i).getData());
        }

        selecioneDataCbox.getItems().addAll(datas);
        selecioneDataCbox.valueProperty().addListener((observable, oldValue, newValue) -> {
            onEscolherData(newValue);
        });
        selecioneHorarioCbox.valueProperty().addListener((observable, oldValue, newValue) -> {
            onEscolherHorario(newValue);
        });

        // imagemPag2.setImage(new Image(filme.getImagem()));
    }

    public void botaoVoltar() {
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
