package poo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TelaCadastroSabores extends JFrame {
    private JComboBox<String> comboBoxTipoPizza;
    private JTextField textFieldSabor;
    private JButton buttonCadastrar;
    private Map<String, String> sabores;

    public TelaCadastroSabores() {
        super("Cadastro de Sabores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        sabores = new HashMap<>();

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel labelTipoPizza = new JLabel("Tipo de Pizza:");
        comboBoxTipoPizza = new JComboBox<>();
        comboBoxTipoPizza.addItem("Simples");
        comboBoxTipoPizza.addItem("Especial");
        comboBoxTipoPizza.addItem("Premium");

        JLabel labelSabor = new JLabel("Sabor:");
        textFieldSabor = new JTextField();

        panel.add(labelTipoPizza);
        panel.add(comboBoxTipoPizza);
        panel.add(labelSabor);
        panel.add(textFieldSabor);

        buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarSabor();
            }
        });
        panel.add(buttonCadastrar);

        add(panel);

        setVisible(true);
    }

    private void cadastrarSabor() {
        String tipoPizza = (String) comboBoxTipoPizza.getSelectedItem();
        String sabor = textFieldSabor.getText();

        sabores.put(sabor, tipoPizza);

        // Lógica para cadastrar o sabor no sistema (salvar em banco de dados, por exemplo)

        JOptionPane.showMessageDialog(this, "Sabor cadastrado com sucesso!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroSabores();
            }
        });
    }
}
