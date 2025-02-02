package br.ifsc.edu.br.taco;

public class Preparo {
    int cod;
    String forma;

    public Preparo(int cod, String forma) {
        this.cod = cod;
        this.forma = forma;
    }

    @Override
    public String toString() {
        return "Preparo{" +
                "cod=" + cod +
                ", forma='" + forma + '\'' +
                '}';
    }
}
