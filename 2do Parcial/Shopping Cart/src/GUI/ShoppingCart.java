package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShoppingCart {

    private JPanel main;
    private JTextField productNameTF;
    private JComboBox unitCB;
    private JCheckBox taxesCHB;
    private JTextField quantityTF;
    private JTextField priceTF;
    private JPanel panelButtons;
    private JButton payButton;
    private JButton exitButton;
    private JTable table;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JPanel tableJPanel;
    private JTextField noItemsTF;

    private JPanel cards;
    private CardLayout cardLayout;
    private JPanel paymentPanel;
    private JPanel receiptPanel;
    private JFrame ticketFrame;
    private JTextArea paymentTA;
    private JTextArea greetingsTA;

    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        paymentTA = new JTextArea(table.getRowCount(), table.getColumnCount());
        paymentTA.setEditable(false);
        panel.add(new JScrollPane(paymentTA), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createReceiptPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        greetingsTA = new JTextArea(10, 30);
        greetingsTA.setText("Payment successful!\nThank you for your purchase.");
        greetingsTA.setEditable(false);
        panel.add(new JScrollPane(greetingsTA), BorderLayout.CENTER);

        return panel;
    }

    public ShoppingCart() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(table.getRowCount() < 1){
                        throw new Exception("No items");
                    }
                    paymentTA.setText("");
                    double total = 0;

                    int rowCount = table.getRowCount();
                    int columnCount = table.getColumnCount();

                    StringBuilder tableText = new StringBuilder();

                    for (int row = 0; row < rowCount; row++) {
                        for (int column = 0; column < columnCount; column++) {
                            Object cellValue = table.getValueAt(row, column);

                            if (cellValue != null) {
                                tableText.append(cellValue.toString()).append(" "); // Separate cells with tabs
                                if (column == columnCount - 1){
                                    total += ((Number) cellValue).doubleValue();
                                }

                            }
                        }
                         tableText.append("\n"); // Newline for the next row
                    }

                    String result = tableText.toString();
                    result += (" Total:  " + total + "\n");

                    if(taxesCHB.isSelected()){
                        double iva = 0.16;
                        double greatTotal = total + (total * iva);
                        result += (" Iva:  " + iva * 100 + "%\n");
                        result += (" Total:  " + greatTotal + "\n");
                    }

                    paymentTA.setText(result);
                    ticketFrame.setVisible(true);

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(main, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }

            }
        });

    }

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
        model.addColumn("Name");
        model.addColumn("Quantity");
        model.addColumn("Unit");
        model.addColumn("No items");
        model.addColumn("Price");
        model.addColumn("Total");
        // Create the JTable with the model
        table = new JTable(model);
        // Create a JScrollPane and add the table to it
        scrollPane = new JScrollPane(table);
        // Add the scrollPane to the content pane
        tableJPanel = new JPanel();
        tableJPanel.add(scrollPane, BorderLayout.CENTER);

        ticketFrame = new JFrame("Ticket");
        ticketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cards = new JPanel();
        cardLayout = new CardLayout();
        cards.setLayout(cardLayout);

        paymentPanel = createPaymentPanel();
        receiptPanel = createReceiptPanel();

        cards.add(paymentPanel, "Payment");
        cards.add(receiptPanel, "Receipt");

        JButton payButtonReceipt = new JButton("Pay");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(payButtonReceipt);

        ticketFrame.getContentPane().add(cards, BorderLayout.CENTER);
        ticketFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        ticketFrame.pack();
        ticketFrame.setLocationRelativeTo(mainPanel); // Center the frame
        ticketFrame.setVisible(false);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(productNameTF.getText().equals("") || unitCB.getSelectedIndex() == 0 || quantityTF.getText().equals("") || priceTF.getText().equals("") ){
                        throw new Exception();
                    }else{
                        try {

                            String name = productNameTF.getText();
                            String unity = unitCB.getSelectedItem().toString();
                            double quantity = Double.parseDouble(quantityTF.getText());
                            double price = Double.parseDouble(priceTF.getText());
                            int items = Integer.parseInt(noItemsTF.getText());
                            if (items < 1 ){
                                throw new Exception();
                            }
                            double total = quantity * price;

                            // Create an array representing the data for the new row
                            Object[] newRowData = {name, quantity, unity, items, price, total}; // Replace with your actual values
                            // Add the new row to the model
                            model.addRow(newRowData);
                            // Refresh the table to display the new row
                            model.fireTableDataChanged();
                            cleanInputs();
                        }catch (Exception casting){
                            JOptionPane.showMessageDialog(main, "Number format error", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(main, "Data is missing", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { // Check if a row is selected
                    model.removeRow(selectedRow); // Remove the selected row from the model
                }
            }
        });



        payButtonReceipt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Receipt");

                // Create a timer to close the frames after 1 second (1000 milliseconds)
                Timer timer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Close the frames
                        cardLayout.previous(cards);
                        ticketFrame.setVisible(false);
                    }
                });

                timer.setRepeats(false); // Set the timer to run only once
                timer.start(); // Start the timer

                cleanInputs();
                taxesCHB.setSelected(false);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
            }
        });


    }

    private void cleanInputs(){
        productNameTF.setText("");
        quantityTF.setText("");
        unitCB.setSelectedIndex(0);
        noItemsTF.setText("");
        priceTF.setText("");
    }

}
