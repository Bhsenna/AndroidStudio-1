package br.ifsc.edu.br.taco;

public class Alimento {
    int codigo;
    String nome_alimento;
    String categoria;

    public Alimento(int codigo, String nome_alimento, String categoria) {
        this.codigo = codigo;
        this.nome_alimento = nome_alimento;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "codigo=" + codigo +
                ", nome_alimento='" + nome_alimento + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
