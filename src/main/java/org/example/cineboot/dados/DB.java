package org.example.cineboot.dados;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.cineboot.negocio.Filme;
import org.example.cineboot.negocio.Sessao;
import org.example.cineboot.negocio.Venda;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DB {
    private static DB instance;
    private final File file = new File("src/main/resources/org/example/cineboot/data/db.json");

    private DB() {
    }

    public void processarVenda(Venda venda) throws Exception {
        int ingressosComprados = venda.getIngressos().size();
        Sessao sessao = venda.getIngressos().getFirst().getSessao();
        Filme filme = venda.getIngressos().getFirst().getFilme();

        try {
            String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
            JSONArray json = new JSONArray(content);

            boolean encontrou = false;
            for (int j = 0; j < json.length(); j++) {
                JSONObject filmeAtual = json.getJSONObject(j);
                if (filmeAtual.getString("nome").equals(filme.getTitulo())) {
                    JSONArray sessoes = filmeAtual.getJSONArray("sessoes");
                    for (int i = 0; i < sessoes.length(); i++) {
                        JSONObject sessaoAtual = sessoes.getJSONObject(i);
                        if (sessaoAtual.getInt("id") == sessao.getId()) {
                            int ingressosAtuais = sessaoAtual.getInt("ingressosComprados");
                            if (ingressosAtuais + ingressosComprados > sessaoAtual.getInt("ingressosTotais")) {
                                throw new Exception("Número de ingressos está acima do limite.");
                            }
                            sessaoAtual.put("ingressosComprados", ingressosAtuais + ingressosComprados);
                            encontrou = true;
                            break;
                        }
                    }
                    if (encontrou) break;
                }
            }

            if (encontrou) {
                Files.write(
                        Paths.get(file.getPath()),
                        json.toString(4).getBytes()
                );
            }

        } catch (Exception e) {
            System.out.println("Erro ao processar venda: " + e.getMessage());
        }
    }



    public Filme getFilme(int id) {
        Filme filme1 = null;
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONArray json = new JSONArray(content);
            for (int j = 0; j < json.length(); j++) {
                JSONObject jsonObject = json.getJSONObject(j);
                if (jsonObject.getInt("id") == id) {
                    System.out.println(jsonObject);
                    JSONArray sessoes = jsonObject.getJSONArray("sessoes");
                    ArrayList<Sessao> sessoesArrayList = new ArrayList<Sessao>();

                    for (int k = 0; k < sessoes.length(); k++) {
                        JSONObject sessao = sessoes.getJSONObject(k);
                        sessoesArrayList.add(
                                new Sessao(
                                        sessao.getLong("id"),
                                        sessao.getString("data"),
                                        sessao.getString("horario"),
                                        sessao.getInt("ingressosTotais"),
                                        sessao.getInt("ingressosComprados")
                                )
                        );
                    }

                    return new Filme(
                            jsonObject.getString("nome"),
                            jsonObject.getString("sinopse"),
                            jsonObject.getString("imagem"),
                            jsonObject.getString("duracao"),
                            jsonObject.getString("classificacao"),
                            sessoesArrayList
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter filme: " + e.getMessage());
        }

        return filme1;
    }


    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }
}
