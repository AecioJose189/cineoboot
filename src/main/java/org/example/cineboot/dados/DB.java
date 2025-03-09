package org.example.cineboot.dados;

import org.example.cineboot.Filme;
import org.example.cineboot.negocio.Sessao;
import org.example.cineboot.negocio.Venda;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DB {
    private static DB instance;
    private final File file = new File("src/main/resources/org/example/cineboot/data/db.json");

    private DB() {

    }

    public void processarVenda(Venda venda) {
        int ingressosComprados = venda.getIngressos().size();
        Sessao sessao = venda.getIngressos().getFirst().getSessao();
        Filme filme = venda.getIngressos().getFirst().getFilme();

        try {
            // 1. Ler o arquivo
            String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
            JSONArray json = new JSONArray(content);

            // 2. Modificar os dados
            boolean encontrou = false;
            for (int j = 0; j < json.length(); j++) {
                JSONObject filmeAtual = json.getJSONObject(j);
                if (filmeAtual.getString("nome").equals(filme.getTitulo())) {
                    JSONArray sessoes = filmeAtual.getJSONArray("sessoes");
                    for (int i = 0; i < sessoes.length(); i++) {
                        JSONObject sessaoAtual = sessoes.getJSONObject(i);
                        if (sessaoAtual.getInt("id") == sessao.getId()) {
                            int ingressosAtuais = sessaoAtual.getInt("ingressosComprados");
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
            e.printStackTrace();
        }
    }

    public Filme getFilme(int id) {
        Filme filme = null;
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONArray json = new JSONArray(content);
            for (int j = 0; j < json.length(); j++) {
                JSONObject jsonObject = json.getJSONObject(j);
                if (jsonObject.getInt("id") == id) {
                    System.out.println(jsonObject);
                    return new Filme(jsonObject.getString("nome"),
                            jsonObject.getString("sinopse"),
                            jsonObject.getString("imagem"),
                            jsonObject.getString("duracao"),
                            jsonObject.getString("classificacao")
                            );

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return filme;
    }

    public Sessao getSessao(int id) {
        Sessao sessao = null;
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONArray json = new JSONArray(content);

            for (int j = 0; j < json.length(); j++) {
                JSONObject jsonObject = json.getJSONObject(j);
                if (jsonObject.getInt("id") == id) {
                    System.out.println(jsonObject);
                    return new Sessao(
                            jsonObject.getLong("id"),
                            jsonObject.getString("data"),
                            jsonObject.getString("horario"),
                            jsonObject.getInt("ingressosTotais"),
                            jsonObject.getInt("ingressosComprados")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sessao;
    }


    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }
}
