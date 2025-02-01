package br.ifsc.edu.br.taco;

public class Alimento {
    String nome_alimento;
    String categoria;

    public Alimento(String nome_alimento, String categoria) {
        this.nome_alimento = nome_alimento;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "nome_alimento='" + nome_alimento + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
