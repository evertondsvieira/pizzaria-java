package poo;

public class Pedido {
    private String pedidoId;
    private String cliente;
    private String estado;

    public Pedido(String pedidoId, String cliente, String estado) {
        this.pedidoId = pedidoId;
        this.cliente = cliente;
        this.estado = estado;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEstado() {
        return estado;
    }
}
