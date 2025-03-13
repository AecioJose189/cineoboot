package org.example.cineboot.dados;

import org.example.cineboot.exceptions.InvalidFieldException;
import org.example.cineboot.negocio.Usuario;
import org.example.cineboot.exceptions.InvalidTicketQuantityException;
import org.example.cineboot.negocio.*;
import org.example.cineboot.negocio.ingresso.Ingresso;
import org.example.cineboot.negocio.ingresso.IngressoMeiaEntrada;
import org.example.cineboot.negocio.ingresso.IngressoNormal;
import org.example.cineboot.negocio.ingresso.IngressoVip;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DB {
    private static DB instance;
    private final File moviesDbFile = new File("src/main/resources/org/example/cineboot/data/db.json");
    private final File usersDbFile = new File("src/main/resources/org/example/cineboot/data/users.json");

    private DB() {}

    public void processarVenda(Venda venda) throws InvalidTicketQuantityException, IOException {
        int quantidadeMeia = 0;
        int quantidadeInteira = 0;
        int quantidadeVip = 0;

        for (Ingresso ingresso : venda.getIngressos()) {
            if (ingresso instanceof IngressoMeiaEntrada) {
                quantidadeMeia++;
            } else if (ingresso instanceof IngressoNormal) {
                quantidadeInteira++;
            } else if (ingresso instanceof IngressoVip) {
                quantidadeVip++;
            }
        }

        int ingressosComprados = quantidadeMeia + quantidadeInteira + quantidadeVip;

        Sessao sessao = venda.getIngressos().getFirst().getSessao();
        Filme filme = venda.getIngressos().getFirst().getFilme();
        Usuario usuario = venda.getUsuario();

        String content = new String(Files.readAllBytes(Paths.get(moviesDbFile.getPath())));
        JSONArray json = new JSONArray(content);

        boolean encontrou = false;

        for (int j = 0; j < json.length(); j++) {
            JSONObject filmeAtual = json.getJSONObject(j);
            if (filmeAtual.getString("nome").equals(filme.getTitulo())) {
                JSONArray sessoes = filmeAtual.getJSONArray("sessoes");
                for (int i = 0; i < sessoes.length(); i++) {
                    JSONObject sessaoAtual = sessoes.getJSONObject(i);
                    if (sessaoAtual.getString("id").equals(sessao.getId())) {
                        int ingressosAtuais = sessaoAtual.getInt("ingressosComprados");
                        if (ingressosAtuais + ingressosComprados > sessaoAtual.getInt("ingressosTotais")) {
                            throw new InvalidTicketQuantityException("Número de ingressos está acima do limite.", ingressosComprados, sessaoAtual.getInt("ingressosTotais") - ingressosAtuais);
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

        content = new String(Files.readAllBytes(Paths.get(usersDbFile.getPath())));
        json = new JSONArray(content);

        for (int i = 0; i < json.length(); i++) {
            JSONObject user = json.getJSONObject(i);
            if (usuario.getId().equals(user.getString("id"))) {
                int qtdIngressos = user.getInt("quantidadeIngressos");
                user.put("quantidadeIngressos", ingressosComprados + qtdIngressos);
                JSONArray compras = user.getJSONArray("compras");
                JSONObject novaCompra = new JSONObject();
                novaCompra.put("id", UUID.randomUUID().toString());
                novaCompra.put("filmeId", filme.getId());
                novaCompra.put("sessaoId", sessao.getId());
                novaCompra.put("quantidadeMeia", quantidadeMeia);
                novaCompra.put("quantidadeInteira", quantidadeInteira);
                novaCompra.put("quantidadeVip", quantidadeVip);
                System.out.println(novaCompra);
                compras.put(novaCompra);
                Files.write(
                        Paths.get(usersDbFile.getPath()),
                        json.toString(4).getBytes()
                );
            }
        }
    }

    public Filme getFilme(String id) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(moviesDbFile.toURI())));
            JSONArray json = new JSONArray(content);
            for (int j = 0; j < json.length(); j++) {
                JSONObject jsonObject = json.getJSONObject(j);
                if (jsonObject.getString("id").equals( id)) {
                    JSONArray sessoes = jsonObject.getJSONArray("sessoes");
                    ArrayList<Sessao> sessoesArrayList = new ArrayList<>();

                    for (int k = 0; k < sessoes.length(); k++) {
                        JSONObject sessao = sessoes.getJSONObject(k);
                        sessoesArrayList.add(
                                new Sessao(
                                        sessao.getString("id"),
                                        sessao.getString("data"),
                                        sessao.getString("horario"),
                                        sessao.getInt("ingressosTotais"),
                                        sessao.getInt("ingressosComprados")
                                )
                        );
                    }

                    return new Filme(
                            jsonObject.getString("id"),
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

    public List<Filme> getFilmes() {
        List<Filme> filmes = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(moviesDbFile.toURI())));
            JSONArray json = new JSONArray(content);
            for (int j = 0; j < json.length(); j++) {
                JSONObject jsonObject = json.getJSONObject(j);
                JSONArray sessoes = jsonObject.getJSONArray("sessoes");
                ArrayList<Sessao> sessoesArrayList = new ArrayList<>();

                for (int k = 0; k < sessoes.length(); k++) {
                    JSONObject sessao = sessoes.getJSONObject(k);
                    sessoesArrayList.add(new Sessao(sessao.getString("id"),
                            sessao.getString("data"),
                            sessao.getString("horario"),
                            sessao.getInt("ingressosTotais"),
                            sessao.getInt("ingressosComprados")));
                }

                filmes.add(new Filme(jsonObject.getString("id"),
                        jsonObject.getString("nome"),
                        jsonObject.getString("sinopse"),
                        jsonObject.getString("imagem"),
                        jsonObject.getString("duracao"),
                        jsonObject.getString("classificacao"),
                        sessoesArrayList));
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter filme: " + e.getMessage());
        }
        return filmes;
    }

    public Sessao getSessao(String id) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(moviesDbFile.toURI())));
            JSONArray json = new JSONArray(content);
            for (int j = 0; j < json.length(); j++) {
                JSONObject jsonObject = json.getJSONObject(j);
                JSONArray sessoes = jsonObject.getJSONArray("sessoes");
                for (int k = 0; k < sessoes.length(); k++) {
                    JSONObject sessao = sessoes.getJSONObject(k);
                    if (sessao.getString("id").equals(id)) {
                        return new Sessao(sessao.getString("id"),
                                sessao.getString("data"),
                                sessao.getString("horario"),
                                sessao.getInt("ingressosTotais"),
                                sessao.getInt("ingressosComprados"));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter sessão: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Compra> getCompras(String userId) {
        ArrayList<Compra> comprasArraylist = new ArrayList<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get(usersDbFile.toURI())));
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject usuario = jsonArray.getJSONObject(i);
                if (usuario.getString("id").equals(userId)) {
                    JSONArray compras = usuario.getJSONArray("compras");
                    for (int j = 0; j < compras.length(); j++) {
                        JSONObject compraAtual = compras.getJSONObject(j);
                        comprasArraylist.add(new Compra(compraAtual.getString("filmeId"),
                                compraAtual.getString("sessaoId"),
                                compraAtual.getInt("quantidadeInteira"),
                                compraAtual.getInt("quantidadeVip"),
                                compraAtual.getInt("quantidadeMeia"),
                                compraAtual.getString("id")));
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comprasArraylist;
    }

    public List<Usuario> carregarUsuarios() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        String content = new String(Files.readAllBytes(Paths.get(usersDbFile.toURI())));
        JSONArray jsonArray = new JSONArray(content);
        for (int j = 0; j < jsonArray.length(); j++) {
            JSONObject jsonObject = jsonArray.getJSONObject(j);
            Usuario usuario = new Usuario(jsonObject.getString("id"),
                    jsonObject.getString("username"),
                    jsonObject.getString("senha"),
                    jsonObject.getInt("quantidadeIngressos"),
                    jsonObject.getBoolean("isVip"),
                    jsonObject.getString("nome"),
                    TipoDeUsuario.valueOf(jsonObject.getString("tipo")));
            usuarios.add(usuario);
        }

        return usuarios;
    }

    public void criarUsuario(String nome, String username, String senha, TipoDeUsuario tipoDeUsuario, boolean isVip) throws IOException, Exception , InvalidFieldException {
        if (nome.isBlank()) {
            throw new InvalidFieldException("Preencha o nome.", "Nome");
        }
        if (username.isBlank()) {
            throw new InvalidFieldException("Preencha o nome de usuário.", "Nome de usuário");
        }
        if (senha.isBlank()) {
            throw new InvalidFieldException("Preencha a senha.", "Senha");
        }
        if (tipoDeUsuario == null) {
            throw new InvalidFieldException("Preencha o tipo de usuário.", "Tipo de usuário");
        }

        String content = new String(Files.readAllBytes(Paths.get(usersDbFile.toURI())));
        JSONArray usuarios = new JSONArray(content);
        for (int j = 0; j < usuarios.length(); j++) {
            JSONObject usuario = usuarios.getJSONObject(j);
            if (usuario.getString("username").equals(username)) {
                throw new Exception("Usuário já cadastrado!");
            }
        }

        JSONObject usuario = new JSONObject();
        usuario.put("id", UUID.randomUUID().toString());
        usuario.put("nome", nome);
        usuario.put("username", username);
        usuario.put("senha", senha);
        usuario.put("tipo", tipoDeUsuario.toString());
        usuario.put("isVip", isVip);
        usuario.put("compras", new JSONArray());
        usuario.put("quantidadeIngressos",0);
        usuarios.put(usuario);

        Files.write(
                Paths.get(usersDbFile.getPath()),
                usuarios.toString(4).getBytes()
        );
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }
}
