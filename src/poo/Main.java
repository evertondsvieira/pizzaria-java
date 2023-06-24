package poo;

import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                List<Cliente> listaClientes = new ArrayList<>();
                TelaCadastroCliente telaCadastro = new TelaCadastroCliente(listaClientes);
                telaCadastro.setVisible(true);
            }
        });
    }
}
