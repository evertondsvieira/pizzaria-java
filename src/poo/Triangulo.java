package poo;

public class Triangulo extends Forma {
    private double lado;

    public Triangulo(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return (Math.sqrt(3) / 4) * lado * lado;
    }
}