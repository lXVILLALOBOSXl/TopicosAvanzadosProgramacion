package guiinterface;


import data.PersonDAO;
import domain.Person;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import util.ConnectionMine;
import util.DateLabelFormatter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class GUI extends JFrame {

    private JButton btnAddPerson;
    private JButton btnModifyPerson;
    private JButton btnDeletePerson;
    private JTable tblPerson;
    private JTextField jtfID;
    private JTextField jtfName;
    private JDatePickerImpl jdpBorn;
    private Connection connection;
    private static final JLabel lblID = new JLabel("Id * : "), lblName = new JLabel("Name * : "), lblBorn = new JLabel("Born date * :");

    private DefaultTableModel dtmPerson = new DefaultTableModel();
    private Short selectedPerson;

    public GUI(){
        super();
        inicializa();
    }

    protected void inicializa(){


        //Definir elementos del layout
        btnAddPerson = new JButton("ADD");
        btnModifyPerson = new JButton("MODIFY");
        btnDeletePerson = new JButton("DELETE");
        tblPerson = new JTable(){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        jtfID = new JTextField();
        jtfName = new JTextField();
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        jdpBorn = new JDatePickerImpl(datePanel,new DateLabelFormatter());
        //Establecer propiedades de la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600,400));
        this.setTitle("DataBase");
        this.setLocationRelativeTo(null);


        //Crea las tablas
        setTables();

        //Acomodo de los elementos del layout
        GroupLayout groupLayout;
        groupLayout = new GroupLayout(this.getContentPane());

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        //Se agregan los evenetos a los componentes del programa
        btnAddPerson.addActionListener((ActionEvent) -> {
            try {
                addPerson();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnModifyPerson.addActionListener((ActionEvent) -> {
            try {
                updatePerson();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnDeletePerson.addActionListener((ActionEvent) -> {
            try {
                deletePerson();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        tblPerson.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tblPerson.getSelectedRow() > -1) {
                    // Hay una fila seleccionada en la tabla
                    // Almacenamos el identificador del elemento al que se hizo click
                    selectedPerson = (short) Integer.parseInt(tblPerson.getValueAt(tblPerson.getSelectedRow(), 0).toString());
                    // Ponemos la informacion del elemnto seleccionado en las textbox correpondientes
                    jtfID.setText(tblPerson.getValueAt(tblPerson.getSelectedRow(), 0).toString());
                    jtfName.setText(tblPerson.getValueAt(tblPerson.getSelectedRow(), 1).toString());
                    String pattern = "yyyy-MM-dd"; // Define the pattern that matches your date string
                    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                    Date selectedDate = null;
                    try {
                        selectedDate = dateFormat.parse((String) tblPerson.getValueAt(tblPerson.getSelectedRow(), 2));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    // Set the JDatePicker's value to the selectedDate
                    ((UtilDateModel) jdpBorn.getModel()).setValue(selectedDate);
                }
            }
        });



        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup()
                        .addGroup(
                                groupLayout.createParallelGroup()
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(lblID)
                                                        .addComponent(jtfID,10,80,233)
                                                        .addComponent(lblName)
                                                        .addComponent(jtfName,10,80,233)
                                                        .addComponent(lblBorn)
                                                        .addComponent(jdpBorn,10,80,233)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(tblPerson)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(btnAddPerson,10,80,233)
                                                        .addComponent(btnModifyPerson,10,80,233)
                                                        .addComponent(btnDeletePerson,10,80,233)
                                        )
                        )
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(lblID)
                                                        .addComponent(jtfID,25,25,25)
                                                        .addComponent(lblName)
                                                        .addComponent(jtfName,25,25,25)
                                                        .addComponent(lblBorn)
                                                        .addComponent(jdpBorn,25,25,25)
                                        )
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(tblPerson)
                                        )
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(btnAddPerson)
                                                        .addComponent(btnModifyPerson)
                                                        .addComponent(btnDeletePerson)
                                        )
                        )
        );


        setLayout(groupLayout);
        this.pack();

    }

    /***
     * Configura, carga y pide la informacion para las tablas
     */
    private void setTables() {
        dtmPerson.addColumn("id");
        dtmPerson.addColumn("Name");
        dtmPerson.addColumn("Born Date");

        try {
            setPersons();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tblPerson.setModel(dtmPerson);


    }

    /***
     * Elimina la venta seleccionada de la bd
     * @throws SQLException si hubo algun error con la bd
     */
    private void deletePerson() throws SQLException {
        if(selectedPerson == null){
            return;
        }
        connection = ConnectionMine.getConnection();
        if (connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }
        PersonDAO personDAO = new PersonDAO(connection);
        Person person = new Person(selectedPerson);
        personDAO.delete(person);
        connection.commit();
        dtmPerson.setRowCount(0);
        setPersons();
    }

    /***
     * Actualiza un registro en la bd con la informacion ingresada
     * @throws SQLException si hubo algun error con la bd
     */
    private void updatePerson() throws SQLException {
        if(jtfID.getText().isEmpty() || jtfName.getText().isEmpty() ) {
            return;
        }
        connection = ConnectionMine.getConnection();
        if (connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }
        PersonDAO personDAO = new PersonDAO(connection);
        Person person = new Person();
        person.setId(Short.parseShort(jtfID.getText()));
        person.setName(jtfName.getText());
        person.setBorn(((UtilDateModel) jdpBorn.getModel()).getValue());

        personDAO.update(person);
        connection.commit();
        dtmPerson.setRowCount(0);
        setPersons();
    }

    /***
     * Registra un auto en la bd con la informacion ingresada
     * @throws SQLException si hubo algun error con la bd
     */
    private void addPerson() throws SQLException {
        if(jtfID.getText().isEmpty() || jtfName.getText().isEmpty() ) {
            return;
        }
        connection = ConnectionMine.getConnection();
        if (connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }


        PersonDAO personDAO = new PersonDAO(connection);
        Person person = new Person();
        person.setId(Short.parseShort(jtfID.getText()));
        person.setName(jtfName.getText());
        person.setBorn(((UtilDateModel) jdpBorn.getModel()).getValue());


        personDAO.insert(person);
        connection.commit();
        dtmPerson.setRowCount(0);
        setPersons();
    }

    /***
     * Trae los registros de la BD y los muestra en tabla
     * @throws SQLException si hubo algun error con la bd
     */
    private void setPersons() throws SQLException {
        connection = ConnectionMine.getConnection();
        if(connection.getAutoCommit()){
            connection.setAutoCommit(false);
        }
        PersonDAO personDAO = new PersonDAO(connection);
        java.util.List<Person> persons = personDAO.select();

        String [] personData = new String[3];

        for (Person person: persons) {
            personData[0] = String.valueOf(person.getId());
            personData[1] = person.getName();
            personData[2] = String.valueOf(person.getBorn());
            dtmPerson.addRow(personData);
        }

    }

}
