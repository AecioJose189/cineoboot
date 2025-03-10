package org.example.cineboot.negocio;

public class Sessao {
    private Long id;
    private String data;
    private String horario;
    private Integer ingressosTotais;
    private Integer ingressosComprados;

    public Sessao(Long id, String data, String horario, Integer ingressosTotais, Integer ingressosComprados) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.ingressosTotais = ingressosTotais;
        this.ingressosComprados = ingressosComprados;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getIngressosTotais() {
        return ingressosTotais;
    }

    public void setIngressosTotais(Integer ingressosTotais) {
        this.ingressosTotais = ingressosTotais;
    }

    public Integer getIngressosComprados() {
        return ingressosComprados;
    }

    public void setIngressosComprados(Integer ingressosComprados) {
        this.ingressosComprados = ingressosComprados;
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", horario='" + horario + '\'' +
                ", ingressosTotais=" + ingressosTotais +
                ", ingressosComprados=" + ingressosComprados +
                '}';
    }
}
