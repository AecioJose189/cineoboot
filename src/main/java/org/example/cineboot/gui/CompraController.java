package org.example.cineboot.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.cineboot.negocio.Filme;
import org.example.cineboot.dados.DB;
import org.example.cineboot.negocio.*;
import org.example.cineboot.negocio.ingresso.IngressoMeiaEntrada;
import org.example.cineboot.negocio.ingresso.IngressoNormal;
import org.example.cineboot.negocio.ingresso.IngressoVip;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class CompraController {
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

    @FXML
    private ImageView classIndicativaFoto;

    private DB db;
    private Auth auth;
    private Filme filme;
    private Sessao sessao;

    private Spinner<Integer> meiaSpinner;
    private Label precoMeiaLabel;
    private Spinner<Integer> inteiraSpinner;
    private Label precoInteiraLabel;
    private Spinner<Integer> vipSpinner;
    private Label precoVipLabel;
    private Set<String> horarios;
    private Label precoTotalLabel;

    private float precoTotal;

    private final Locale localBrasil = new Locale("pt","BR");

    private static final String PATH_claOPPENHEIMER = "/org/example/cineboot/image/classIndicativa/18 anos.png";
    private static final String PATH_claHOMEM_ARANHA = "/org/example/cineboot/image/classIndicativa/livre.png";;
    private static final String PATH_claAVATAR = "/org/example/cineboot/image/classIndicativa/12 anos.png";
    private static final String PATH_claAINDA_ESTOU_AQUI = "/org/example/cineboot/image/classIndicativa/14 anos.png";




    @FXML
    private void initialize() {
        confirmarEscolhaPg2.setDisable(true);
        botaoVoltar();
        db = DB.getInstance();
        auth = Auth.getInstance();
        horarios = new HashSet<>();
        setupPrecoLabels();
        setupSpinners();
        setupButton();
    }

    private void onEscolherData(String data) {
        horarios.clear();
        for (int i = 0; i < filme.getSessoes().size(); i++) {
            Sessao sessaoAtual = filme.getSessoes().get(i);
            if (data.equals(sessaoAtual.getData()) && sessaoAtual.getIngressosTotais() - sessaoAtual.getIngressosComprados() > 0) {
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
                int ingressosDisponiveis = sessaoAtual.getIngressosTotais() - sessaoAtual.getIngressosComprados();

                if (ingressosDisponiveis > 0) {
                    quantidadeDisponivelLabel.setText(
                            "Quantidade de cadeiras disponíveis: " + ingressosDisponiveis + "/" + sessaoAtual.getIngressosTotais()
                    );
                    sessao = sessaoAtual;
                    confirmarEscolhaPg2.setDisable(false);
                } else {
                    selecioneHorarioCbox.getSelectionModel().clearSelection();
                    Platform.runLater(() -> quantidadeDisponivelLabel.setText("Sessão esgotada!"));
                }
            }
        }
    }


    private void setupButton() {
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
                        venda.adicionarIngresso(new IngressoNormal(20, filme, sessao));
                    }
                    for (int i = 0; i < vipSpinner.getValue(); i++) {
                        venda.adicionarIngresso(new IngressoVip(20, filme, sessao));
                    }

                    venda.setUsuario(auth.getUsuario());

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cineboot/resumo.fxml"));
                        Parent root = loader.load();

                        ResumoController tela03Controller = loader.getController();

                        tela03Controller.setDetalhes(filme, sessao, quantidadeMeia, quantidadeInteira, quantidadeVip, precoTotal);

                        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stageAtual.close();

                        Stage stage = new Stage();
                        stage.setTitle("Resumo do pedido");
                        stage.setScene(new Scene(root, 650, 700));
                        stage.show();

                    } catch (IOException e) {
                        System.out.println("Erro ao carregar tela 03: " + e.getMessage());
                    }

                    try {
                        db.processarVenda(venda);
                    } catch (Exception e) {
                        System.out.println("Erro ao processar venda no banco de dados: " + e.getMessage());
                    }
                }

        );
    }

    private void setupPrecoLabels(){
        precoMeiaLabel = new Label("");
        precoMeiaLabel.setLayoutX(310);
        precoMeiaLabel.setLayoutY(514);

        precoInteiraLabel = new Label("");
        precoInteiraLabel.setLayoutX(310);
        precoInteiraLabel.setLayoutY(548);

        precoVipLabel = new Label("");
        precoVipLabel.setLayoutX(310);
        precoVipLabel.setLayoutY(588);

        precoTotalLabel = new Label("");
        precoTotalLabel.setLayoutX(310);
        precoTotalLabel.setLayoutY(628);

        root.getChildren().add(precoMeiaLabel);
        root.getChildren().add(precoInteiraLabel);
        root.getChildren().add(precoVipLabel);
        root.getChildren().add(precoTotalLabel);
    }

    private void updatePrecoTotal(){
        precoTotal = meiaSpinner.getValue() * 12 + inteiraSpinner.getValue() * 24 + vipSpinner.getValue() * 12;
        precoTotalLabel.setText(NumberFormat.getCurrencyInstance(localBrasil).format(precoTotal));
    }

    private void setupSpinners() {
        meiaSpinner = new Spinner<>(0, 10, 0);
        meiaSpinner.setLayoutX(240);
        meiaSpinner.setLayoutY(514);
        meiaSpinner.setPrefHeight(25);
        meiaSpinner.setPrefWidth(62);
        meiaSpinner.valueProperty().addListener(
            (observable, oldValue, newValue) -> {
                updatePrecoTotal();
                if (newValue >0) {
                    precoMeiaLabel.setText(NumberFormat.getCurrencyInstance(localBrasil).format(newValue * 12));
                } else {
                    precoMeiaLabel.setText("");
                }
            }
        );

        inteiraSpinner = new Spinner<>(0, 10, 0);
        inteiraSpinner.setLayoutX(240);
        inteiraSpinner.setLayoutY(548);
        inteiraSpinner.setPrefHeight(25);
        inteiraSpinner.setPrefWidth(62);
        inteiraSpinner.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    updatePrecoTotal();
                    if (newValue >0) {
                        precoInteiraLabel.setText(NumberFormat.getCurrencyInstance(localBrasil).format(newValue * 24));
                    } else {
                        precoInteiraLabel.setText("");
                    }
                }
        );

        vipSpinner = new Spinner<>(0, 10, 0);
        vipSpinner.setLayoutX(240);
        vipSpinner.setLayoutY(588);
        vipSpinner.setPrefHeight(25);
        vipSpinner.setPrefWidth(62);
        vipSpinner.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    updatePrecoTotal();
                    if (newValue >0) {
                        precoVipLabel.setText(NumberFormat.getCurrencyInstance(localBrasil).format(newValue * 12));
                    } else {
                        precoVipLabel.setText("");
                    }
                }
        );

        root.getChildren().add(meiaSpinner);
        root.getChildren().add(inteiraSpinner);
        root.getChildren().add(vipSpinner);
    }


    public void exibirDetalhes(int id) {
        DB db = DB.getInstance();
        filme = db.getFilme(id);

        nomeDoFilmepg2.setText(filme.getTitulo());
        sinopseLabel.setText(filme.getDescricao());

        Set<String> datas = new HashSet<>();

        duracaoLabel.setText("Duração: " + filme.getDuracao());

        String imagePathClass;

        switch (id) {
            case 1:
                imagePathClass = PATH_claAVATAR;
                break;
            case 2:
                imagePathClass = PATH_claHOMEM_ARANHA;
                break;
            case 3:
                imagePathClass = PATH_claOPPENHEIMER;
                break;
            case 4:
                imagePathClass = PATH_claAINDA_ESTOU_AQUI;
                break;
            default:
                System.out.println("Nenhuma imagem encontrada para o ID: " + id);
                return;
        }

        URL imageUrl = getClass().getResource(filme.getImagem());
        URL imageUrlClass = getClass().getResource(imagePathClass);

        if (imageUrl !=null && imageUrlClass != null ) {
            imagemPag2.setImage(new Image(imageUrl.toString()));
            classIndicativaFoto.setImage(new Image(imageUrlClass.toString()));
        } else {
            System.out.println("Imagem não encontrada: " + filme.getImagem());
        }


        for (int i = 0; i < filme.getSessoes().size(); i++) {
            Sessao sessaoAtual = filme.getSessoes().get(i);
            if (sessaoAtual.getIngressosTotais() - sessaoAtual.getIngressosComprados() <= 0) {
                continue;
            }

            datas.add(filme.getSessoes().get(i).getData());
        }

        selecioneDataCbox.getItems().addAll(datas);
        selecioneDataCbox.valueProperty().addListener((observable, oldValue, newValue) -> onEscolherData(newValue));
        selecioneHorarioCbox.valueProperty().addListener((observable, oldValue, newValue) -> onEscolherHorario(newValue));

    }

    public void botaoVoltar() {
        voltarpg2.setOnMouseClicked(event -> {
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
                System.out.println("Erro ao carregar tela 01: " + e.getMessage());
            }

        });
    }


    public void inicializarTelaComFilme(int id) {
        exibirDetalhes(id);
    }

}
