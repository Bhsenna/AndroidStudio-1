package br.ifsc.edu.br.taco;

public class Alimento {
    String nome_alimento;
    String categoria;
    String forma_preparo;

    public Alimento(String nome_alimento, String categoria, String forma_preparo) {
        this.nome_alimento = nome_alimento;
        this.categoria = categoria;
        this.forma_preparo = forma_preparo;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "nome_alimento='" + nome_alimento + '\'' +
                ", categoria='" + categoria + '\'' +
                ", forma_preparo='" + forma_preparo + '\'' +
                '}';
    }
}
