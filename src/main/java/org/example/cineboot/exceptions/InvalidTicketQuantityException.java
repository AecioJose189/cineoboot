package org.example.cineboot.exceptions;

public class InvalidTicketQuantityException extends RuntimeException {
  private int quantidadeComprada;
  private int quantidadePermitida;

  public InvalidTicketQuantityException(String message, int quantidadeComprada, int quantidadePermitida) {
    super(message);
    this.quantidadeComprada = quantidadeComprada;
    this.quantidadePermitida = quantidadePermitida;
  }

  public int getQuantidadeComprada() {
    return quantidadeComprada;
  }

  public void setQuantidadeComprada(int quantidadeComprada) {
    this.quantidadeComprada = quantidadeComprada;
  }

  public int getQuantidadePermitida() {
    return quantidadePermitida;
  }

  public void setQuantidadePermitida(int quantidadePermitida) {
    this.quantidadePermitida = quantidadePermitida;
  }
}
