package poo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaCadastroCliente extends JFrame {
    private JTextField textFieldNome;
    private JTextField textFieldSobrenome;
    private JTextField textFieldTelefone;
    private List<Cliente> listaClientes;

    public TelaCadastroCliente(List<Cliente> clientes) {
        super("Cadastro de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        listaClientes = clientes;

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel labelNome = new JLabel("Nome:");
        textFieldNome = new JTextField(20);

        JLabel labelSobrenome = new JLabel("Sobrenome:");
        textFieldSobrenome = new JTextField(20);

        JLabel labelTelefone = new JLabel("Telefone:");
        textFieldTelefone = new JTextField(20);

        JButton buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (camposPreenchidos()) {
                    cadastrarCliente();
                } else {
                    JOptionPane.showMessageDialog(TelaCadastroCliente.this, "Por favor, preencha todos os campos", "Campos vazios", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(labelNome);
        panel.add(textFieldNome);
        panel.add(labelSobrenome);
        panel.add(textFieldSobrenome);
        panel.add(labelTelefone);
        panel.add(textFieldTelefone);
        panel.add(new JLabel());
        panel.add(buttonCadastrar);

        add(panel);

        setVisible(true);
    }

    private boolean camposPreenchidos() {
        String nome = textFieldNome.getText();
        String sobrenome = textFieldSobrenome.getText();
        String telefone = textFieldTelefone.getText();

        return !nome.isEmpty() && !sobrenome.isEmpty() && !telefone.isEmpty();
    }

    private void cadastrarCliente() {
        String nome = textFieldNome.getText();
        String sobrenome = textFieldSobrenome.getText();
        String telefone = textFieldTelefone.getText();

        Cliente cliente = new Cliente(nome, sobrenome, telefone);
        listaClientes.add(cliente);

        System.out.println("Nome: " + nome);
        System.out.println("Sobrenome: " + sobrenome);
        System.out.println("Telefone: " + telefone);

        new TelaListaClientes(listaClientes);
        dispose(); // Close the current window after opening the new one
    }

    public static void main(String[] args) {
        List<Cliente> listaClientes = new ArrayList<>();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroCliente(listaClientes);
            }
        });
    }
}