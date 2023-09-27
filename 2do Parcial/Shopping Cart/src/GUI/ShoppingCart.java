package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class ShoppingCart {

    private JPanel main;
    private JTextField productNameTF;
    private JComboBox measurementCB;
    private JCheckBox taxesCB;
    private JTextField quantityTF;
    private JTextField priceTF;
    private JPanel panelButtons;
    private JButton payButton;
    private JButton exitButton;
    private JTable table;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JPanel tableJPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ShoppingCart");
        frame.setContentPane(new ShoppingCart().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {

        panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");


        panelButtons.add(addButton);
        panelButtons.add(deleteButton);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Column 1");
        model.addColumn("Column 2");
        model.addColumn("Column 3");
        model.addColumn("Column 4");

        // Create the JTable with the model
        table = new JTable(model);

        // Create a JScrollPane and add the table to it
        scrollPane = new JScrollPane(table);

        // Add the scrollPane to the content pane
        tableJPanel = new JPanel();
        tableJPanel.add(scrollPane, BorderLayout.CENTER);




    }
}
