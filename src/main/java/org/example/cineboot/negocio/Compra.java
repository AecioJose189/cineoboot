package org.example.cineboot.negocio;

public class Compra {
    private Integer filmeId;
    private Integer sessaoId;
    private Integer quantidadeInteira;
    private Integer quantidadeVip;
    private Integer quantidadeMeia;
    private Long id;

    public Compra(Integer filmeId, Integer sessaoId, Integer quantidadeInteira, Integer quantidadeVip, Integer quantidadeMeia, Long id) {
        this.filmeId = filmeId;
        this.sessaoId = sessaoId;
        this.quantidadeInteira = quantidadeInteira;
        this.quantidadeVip = quantidadeVip;
        this.quantidadeMeia = quantidadeMeia;
        this.id = id;
    }

    public Integer getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(Integer filmeId) {
        this.filmeId = filmeId;
    }

    public Integer getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Integer sessaoId) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
