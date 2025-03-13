package org.example.cineboot.negocio;

public class Compra {
    private String filmeId;
    private String sessaoId;
    private Integer quantidadeInteira;
    private Integer quantidadeVip;
    private Integer quantidadeMeia;
    private String id;

    public Compra(String filmeId, String sessaoId, Integer quantidadeInteira, Integer quantidadeVip, Integer quantidadeMeia, String id) {
        this.filmeId = filmeId;
        this.sessaoId = sessaoId;
        this.quantidadeInteira = quantidadeInteira;
        this.quantidadeVip = quantidadeVip;
        this.quantidadeMeia = quantidadeMeia;
        this.id = id;
    }

    public String getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(String filmeId) {
        this.filmeId = filmeId;
    }

    public String getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(String sessaoId) {
        this.sessaoId = sessaoId;
    }

    public Integer getQuantidadeInteira() {
        return quantidadeInteira;
    }

    public void setQuantidadeInteira(Integer quantidadeInteira) {
        this.quantidadeInteira = quantidadeInteira;
    }

    public Integer getQuantidadeVip() {
        return quantidadeVip;
    }

    public void setQuantidadeVip(Integer quantidadeVip) {
        this.quantidadeVip = quantidadeVip;
    }

    public Integer getQuantidadeMeia() {
        return quantidadeMeia;
    }

    public void setQuantidadeMeia(Integer quantidadeMeia) {
        this.quantidadeMeia = quantidadeMeia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
