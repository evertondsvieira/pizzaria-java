package poo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TelaPedidoPizza extends JFrame {
    private JComboBox<String> comboForma;
    private JTextField txtDimensoes;
    private JTextField txtSabor1;
    private JTextField txtSabor2;
    private JComboBox<String> comboTipo;
    private JButton btnAdicionarPizza;
    private List<Pizza> listaPizzas;
    private TelaListaPedidos telaListaPedidos;

    public TelaPedidoPizza(List<Pizza> pizzas) {
        super("Pedido de Pizza");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);

        listaPizzas = pizzas;
        telaListaPedidos = null;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel lblForma = new JLabel("Forma:");
        comboForma = new JComboBox<>(new String[]{"Circular", "Quadrado", "Triângulo"});

        JLabel lblDimensoes = new JLabel("Dimensões:");
        txtDimensoes = new JTextField();

        JLabel lblSabor1 = new JLabel("Sabor 1:");
        txtSabor1 = new JTextField();

        JLabel lblSabor2 = new JLabel("Sabor 2:");
        txtSabor2 = new JTextField();

        JLabel lblTipo = new JLabel("Tipo:");
        comboTipo = new JComboBox<>(new String[]{"Simples", "Especial", "Premium"});

        btnAdicionarPizza = new JButton("Adicionar Pizza");

        panel.add(lblForma);
        panel.add(comboForma);
        panel.add(lblDimensoes);
        panel.add(txtDimensoes);
        panel.add(lblSabor1);
        panel.add(txtSabor1);
        panel.add(lblSabor2);
        panel.add(txtSabor2);
        panel.add(lblTipo);
        panel.add(comboTipo);
        panel.add(btnAdicionarPizza);

        btnAdicionarPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String forma = comboForma.getSelectedItem().toString();
                String dimensoesStr = txtDimensoes.getText();
                String sabor1 = txtSabor1.getText();
                String sabor2 = txtSabor2.getText();
                String tipo = comboTipo.getSelectedItem().toString();

                if (dimensoesStr.isEmpty() || sabor1.isEmpty() || sabor2.isEmpty()) {
                    JOptionPane.showMessageDialog(TelaPedidoPizza.this, "Preencha todos os campos!");
                    return;
                }

                double dimensoes;
                try {
                    dimensoes = Double.parseDouble(dimensoesStr);
                    if (forma.equals("Quadrado") && (dimensoes < 10 || dimensoes > 40)) {
                        JOptionPane.showMessageDialog(TelaPedidoPizza.this, "O lado do quadrado deve ter no mínimo 10 e no máximo 40 cm!");
                        return;
                    } else if (forma.equals("Triângulo") && (dimensoes < 20 || dimensoes > 60)) {
                        JOptionPane.showMessageDialog(TelaPedidoPizza.this, "O lado do triângulo deve ter no mínimo 20 e no máximo 60 cm!");
                        return;
                    } else if (forma.equals("Circular") && (dimensoes < 7 || dimensoes > 23)) {
                        JOptionPane.showMessageDialog(TelaPedidoPizza.this, "O raio do círculo deve ter no mínimo 7 e no máximo 23 cm!");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TelaPedidoPizza.this, "Digite um número válido para as dimensões!");
                    return;
                }

                double precoPorCentimetro;
                if (tipo.equals("Simples")) {
                    precoPorCentimetro = 0.05;
                } else if (tipo.equals("Especial")) {
                    precoPorCentimetro = 0.07;
                } else { // Premium
                    precoPorCentimetro = 0.1;
                }

                double area = calcularArea(forma, dimensoes);
                double precoTotal = calcularPrecoTotal(area, precoPorCentimetro);

                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                String precoTotalFormatado = decimalFormat.format(precoTotal);

                String[] sabores = {sabor1, sabor2};
                Pizza pizza = new Pizza(forma, dimensoes, sabores, tipo, precoTotalFormatado, precoTotal);
                listaPizzas.add(pizza);

                JOptionPane.showMessageDialog(TelaPedidoPizza.this, "Pizza adicionada ao pedido! Preço total: R$" + precoTotalFormatado);

                txtDimensoes.setText("");
                txtSabor1.setText("");
                txtSabor2.setText("");

                if (telaListaPedidos == null) {
                    telaListaPedidos = new TelaListaPedidos();
                }

                telaListaPedidos.atualizarTabela(listaPizzas);
                telaListaPedidos.setVisible(true);
            }
        });

        add(panel);
        setVisible(true);
    }

    private double calcularArea(String forma, double dimensoes) {
        if (forma.equals("Quadrado")) {
            return Math.pow(dimensoes, 2);
        } else if (forma.equals("Triângulo")) {
            return (Math.sqrt(3) / 4) * Math.pow(dimensoes, 2);
        } else { // Circular
            return Math.PI * Math.pow(dimensoes, 2);
        }
    }

    private double calcularPrecoTotal(double area, double precoPorCentimetro) {
        return area * precoPorCentimetro;
    }

    public static void main(String[] args) {
        List<Pizza> listaPizzas = new ArrayList<>();
        TelaPedidoPizza telaPedidoPizza = new TelaPedidoPizza(listaPizzas);
    }
}