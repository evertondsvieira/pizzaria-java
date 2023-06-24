package poo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaListaClientes extends JFrame {
    private JTable tableClientes;
    private JTextField textFieldFiltro;

    private List<Cliente> listaClientes;
    private List<Pizza> listaPizzas;

    public TelaListaClientes(List<Cliente> clientes) {
        super("Lista de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        listaClientes = clientes;
        listaPizzas = new ArrayList<>();

        JPanel panel = new JPanel(new BorderLayout());

        // Painel superior para o filtro
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelFiltro = new JLabel("Filtro (Sobrenome/Telefone):");
        textFieldFiltro = new JTextField(20);
        JButton buttonFiltrar = new JButton("Filtrar");
        buttonFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filtrarClientes();
            }
        });
        panelFiltro.add(labelFiltro);
        panelFiltro.add(textFieldFiltro);
        panelFiltro.add(buttonFiltrar);

        panel.add(panelFiltro, BorderLayout.NORTH);

        // Tabela de clientes
        tableClientes = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableClientes);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botões para voltar à tela anterior, excluir cliente, atualizar dados do cliente e adicionar pizza
        JButton buttonVoltar = new JButton("Voltar");
        buttonVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                voltarTelaAnterior();
            }
        });

        JButton buttonExcluir = new JButton("Excluir");
        buttonExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirCliente();
            }
        });

        JButton buttonAtualizar = new JButton("Atualizar");
        buttonAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarCliente();
            }
        });

        JButton buttonAdicionarPizza = new JButton("Adicionar Pizza");
        buttonAdicionarPizza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaPedidoPizza();
            }
        });

        // Painel para os botões de navegação e ações
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.add(buttonVoltar);
        panelBotoes.add(buttonExcluir);
        panelBotoes.add(buttonAtualizar);
        panelBotoes.add(buttonAdicionarPizza);
        panel.add(panelBotoes, BorderLayout.SOUTH);

        add(panel);

        preencherTabela();

        setVisible(true);
    }

    private void preencherTabela() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Sobrenome");
        model.addColumn("Telefone");
        model.addColumn("Status"); // Novo campo para o status do pedido

        for (Cliente cliente : listaClientes) {
            Object[] rowData = new Object[4];
            rowData[0] = cliente.getNome();
            rowData[1] = cliente.getSobrenome();
            rowData[2] = cliente.getTelefone();
            rowData[3] = getStatusPedido(cliente); // Define o status do pedido para cada cliente
            model.addRow(rowData);
        }

        tableClientes.setModel(model);
    }

    private String getStatusPedido(Cliente cliente) {
        // Lógica para determinar o status do pedido do cliente
        // Aqui, é usado um valor fixo para fins de exemplo
        return "Aberto";
    }

    private void filtrarClientes() {
        String filtro = textFieldFiltro.getText().toLowerCase();

        List<Cliente> clientesFiltrados = new ArrayList<>();

        for (Cliente cliente : listaClientes) {
            if (cliente.getSobrenome().toLowerCase().contains(filtro)
                    || cliente.getTelefone().contains(filtro)) {
                clientesFiltrados.add(cliente);
            }
        }

        // Update the table with filtered clients (if necessary)
        DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
        model.setRowCount(0);
        for (Cliente cliente : clientesFiltrados) {
            Object[] rowData = new Object[4];
            rowData[0] = cliente.getNome();
            rowData[1] = cliente.getSobrenome();
            rowData[2] = cliente.getTelefone();
            rowData[3] = getStatusPedido(cliente);
            model.addRow(rowData);
        }
    }

    private void voltarTelaAnterior() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaCadastroCliente telaCadastro = new TelaCadastroCliente(listaClientes);
                telaCadastro.setVisible(true);
                dispose();
            }
        });
    }

    private void excluirCliente() {
        int selectedRow = tableClientes.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
            String sobrenome = (String) model.getValueAt(selectedRow, 1);
            String telefone = (String) model.getValueAt(selectedRow, 2);
            Cliente clienteExcluir = null;

            for (Cliente cliente : listaClientes) {
                if (cliente.getSobrenome().equals(sobrenome) && cliente.getTelefone().equals(telefone)) {
                    clienteExcluir = cliente;
                    break;
                }
            }

            if (clienteExcluir != null) {
                listaClientes.remove(clienteExcluir);
                model.removeRow(selectedRow);
            }
        }
    }

    private void atualizarCliente() {
        int selectedRow = tableClientes.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
            String sobrenome = (String) model.getValueAt(selectedRow, 1);
            String telefone = (String) model.getValueAt(selectedRow, 2);
            Cliente clienteAtualizar = null;

            for (Cliente cliente : listaClientes) {
                if (cliente.getSobrenome().equals(sobrenome) && cliente.getTelefone().equals(telefone)) {
                    clienteAtualizar = cliente;
                    break;
                }
            }

            if (clienteAtualizar != null) {
                String novoNome = JOptionPane.showInputDialog("Digite o novo nome:", clienteAtualizar.getNome());
                String novoSobrenome = JOptionPane.showInputDialog("Digite o novo sobrenome:", clienteAtualizar.getSobrenome());
                String novoTelefone = JOptionPane.showInputDialog("Digite o novo telefone:", clienteAtualizar.getTelefone());
                clienteAtualizar.setNome(novoNome);
                clienteAtualizar.setSobrenome(novoSobrenome);
                clienteAtualizar.setTelefone(novoTelefone);
                model.setValueAt(novoNome, selectedRow, 0);
                model.setValueAt(novoSobrenome, selectedRow, 1);
                model.setValueAt(novoTelefone, selectedRow, 2);
                model.setValueAt(getStatusPedido(clienteAtualizar), selectedRow, 3); // Atualiza o status do pedido na tabela
            }
        }
    }

    private void abrirTelaPedidoPizza() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaPedidoPizza telaPedidoPizza = new TelaPedidoPizza(listaPizzas);
                telaPedidoPizza.setVisible(true);
                dispose();
            }
        });
    }

    public void adicionarPizza(Pizza pizza) {
        listaPizzas.add(pizza);
    }

    public static void main(String[] args) {
        List<Cliente> listaClientes = new ArrayList<>();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaListaClientes(listaClientes);
            }
        });
    }
}
