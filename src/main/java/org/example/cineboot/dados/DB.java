package org.example.cineboot.dados;

import org.example.cineboot.Usuario;
import org.example.cineboot.negocio.Filme;
import org.example.cineboot.negocio.Sessao;
import org.example.cineboot.negocio.Venda;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static DB instance;
    private final File moviesDbFile = new File("src/main/resources/org/example/cineboot/data/db.json");
    private final File usersDbFile = new File("src/main/resources/org/example/cineboot/data/users.json");

    public void processarVenda(Venda venda) throws Exception {
        int ingressosComprados = venda.getIngressos().size();
        Sessao sessao = venda.getIngressos().getFirst().getSessao();
        Filme filme = venda.getIngressos().getFirst().getFilme();
        Usuario usuario = venda.getUsuario();

        try {
            String content = new String(Files.readAllBytes(Paths.get(usersDbFile.getPath())));
            JSONArray json = new JSONArray(content);

            for (int i = 0; i < json.length(); i++) {
                JSONObject user = json.getJSONObject(i);
                if (usuario.getId() == user.getInt("id")) {
                    int qtdIngressos = user.getInt("quantidadeIngressos");
                    user.put("quantidadeIngressos", ingressosComprados + qtdIngressos);

                    Files.write(
                            Paths.get(usersDbFile.getPath()),
                            json.toString(4).getBytes()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(moviesDbFile.getPath())));
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
                        Paths.get(moviesDbFile.getPath()),
                        json.toString(4).getBytes()
                );
            }

        } catch (Exception e) {
            System.out.println("Erro ao processar venda: " + e.getMessage());
        }
    }

    public Filme getFilme(int id) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(moviesDbFile.toURI())));
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
        return null;
    }

    public List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get(usersDbFile.toURI())));
            JSONArray jsonArray = new JSONArray(content);
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                Usuario usuario = new Usuario(
                        jsonObject.getString("login"),
                        jsonObject.getString("senha"),
                        jsonObject.getInt("quantidadeIngressos"),
                        jsonObject.getInt("id")
                );
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }
}
