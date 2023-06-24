package poo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

public class TelaListaPedidos extends JFrame {
    private JTable tabela;
    private DefaultTableModel model;
    private JButton buttonAtualizarStatus;

    public TelaListaPedidos() {
        super("Lista de Pedidos");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();
        tabela = new JTable(model);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        model.addColumn("Forma");
        model.addColumn("Dimensões");
        model.addColumn("Sabor 1");
        model.addColumn("Sabor 2");
        model.addColumn("Valor");
        model.addColumn("Status");

        JScrollPane scrollPane = new JScrollPane(tabela);

        buttonAtualizarStatus = new JButton("Atualizar Status");
        buttonAtualizarStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirModalAtualizarStatus();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonAtualizarStatus, BorderLayout.SOUTH);

        add(panel);
    }

    public void atualizarTabela(List<Pizza> listaPizzas) {
        model.setRowCount(0);
        
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        for (Pizza pizza : listaPizzas) {
            Object[] row = new Object[6];
            row[0] = pizza.getForma();
            row[1] = pizza.getDimensoes();
            row[2] = pizza.getSabores()[0];
            row[3] = pizza.getSabores()[1];
            row[4] = decimalFormat.format(pizza.getPrecoTotal());
            row[5] = pizza.getStatus();

            model.addRow(row);
        }
    }

    private void abrirModalAtualizarStatus() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            String[] opcoesStatus = {"Aberto", "A Caminho", "Entregue"};
            String novoStatus = (String) JOptionPane.showInputDialog(this, "Selecione o novo status:", "Atualizar Status",
                    JOptionPane.PLAIN_MESSAGE, null, opcoesStatus, opcoesStatus[0]);

            if (novoStatus != null) {
                DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                model.setValueAt(novoStatus, selectedRow, 5);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um pedido da tabela.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
