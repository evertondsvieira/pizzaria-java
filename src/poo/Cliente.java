package poo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String sobrenome;
    private String telefone;
    private List<Pizza> pedido;

    public Cliente(String nome, String sobrenome, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.pedido = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }
    
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Pizza> getPedido() {
        return pedido;
    }

    public void adicionarPizza(Pizza pizza) {
        pedido.add(pizza);
    }
}
