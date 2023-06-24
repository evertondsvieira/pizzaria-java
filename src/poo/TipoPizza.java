package poo;

public class TipoPizza {
    private String nome;
    private double precoPorCentimetroQuadrado;

    public TipoPizza(String nome, double precoPorCentimetroQuadrado) {
        this.nome = nome;
        this.precoPorCentimetroQuadrado = precoPorCentimetroQuadrado;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoPorCentimetroQuadrado() {
        return precoPorCentimetroQuadrado;
    }
}