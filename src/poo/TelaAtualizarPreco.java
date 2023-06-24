package poo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TelaAtualizarPreco extends JFrame {
    private JTextField textFieldSimples;
    private JTextField textFieldEspecial;
    private JTextField textFieldPremium;
    private JButton buttonAtualizar;
    private Map<String, Double> precos;

    public TelaAtualizarPreco(Map<String, Double> precosIniciais) {
        super("Atualizar Preço");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        precos = new HashMap<>(precosIniciais);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel labelSimples = new JLabel("Preço Simples:");
        textFieldSimples = new JTextField(Double.toString(precos.get("Simples")));

        JLabel labelEspecial = new JLabel("Preço Especial:");
        textFieldEspecial = new JTextField(Double.toString(precos.get("Especial")));

        JLabel labelPremium = new JLabel("Preço Premium:");
        textFieldPremium = new JTextField(Double.toString(precos.get("Premium")));

        panel.add(labelSimples);
        panel.add(textFieldSimples);
        panel.add(labelEspecial);
        panel.add(textFieldEspecial);
        panel.add(labelPremium);
        panel.add(textFieldPremium);

        buttonAtualizar = new JButton("Atualizar");
        buttonAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarPrecos();
            }
        });
        panel.add(buttonAtualizar);

        add(panel);

        setVisible(true);
    }

    private void atualizarPrecos() {
        double precoSimples = Double.parseDouble(textFieldSimples.getText());
        double precoEspecial = Double.parseDouble(textFieldEspecial.getText());
        double precoPremium = Double.parseDouble(textFieldPremium.getText());

        precos.put("Simples", precoSimples);
        precos.put("Especial", precoEspecial);
        precos.put("Premium", precoPremium);

        JOptionPane.showMessageDialog(this, "Preços atualizados com sucesso!");
    }

    public static void main(String[] args) {
        Map<String, Double> precosIniciais = new HashMap<>();
        precosIniciais.put("Simples", 10.0);
        precosIniciais.put("Especial", 15.0);
        precosIniciais.put("Premium", 20.0);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaAtualizarPreco(precosIniciais);
            }
        });
    }
}
