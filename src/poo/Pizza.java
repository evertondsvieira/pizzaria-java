package poo;

public class Pizza {
    private String forma;
    private double dimensoes;
    private String[] sabores;
    private String tipo;
    private String status;
    private double precoTotal;

    public Pizza(String forma, double dimensoes, String[] sabores, String status, String tipo, double precoTotal) {
        this.forma = forma;
        this.dimensoes = dimensoes;
        this.sabores = sabores;
        this.tipo = tipo;
        this.precoTotal = precoTotal;
        this.status = "Aberto";
    }

	public String getForma() {
        return forma;
    }

    public double getDimensoes() {
        return dimensoes;
    }

    public String[] getSabores() {
        return sabores;
    }

    public String getTipo() {
        return tipo;
    }
    
    public String getStatus() {
    	return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }
}