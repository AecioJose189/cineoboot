package org.example.cineboot.dados;

import org.example.cineboot.Filme;
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

    public void processarVenda(){
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONArray json = new JSONArray(content);
            for (int j = 0; j < json.length(); j++) {
                System.out.println(json.getJSONObject(j));
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

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }
}
