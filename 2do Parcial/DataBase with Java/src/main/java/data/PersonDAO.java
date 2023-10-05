package data;

import domain.Person;
import util.ConnectionMine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private Connection connection;
    private static final String SQL_SELECT = "SELECT * FROM person";
    private static final String SQL_INSERT = "INSERT INTO person (id, name, born) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE person SET name = ?, born = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM person WHERE id = ?";

    public PersonDAO() {

    }

    public PersonDAO(Connection connection){
        this.connection = connection;
    }

    public List<Person> select() throws SQLException {
        Connection connection2 = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Person> persons = new ArrayList<>();

        try {
            connection2 = (this.connection != null) ? this.connection : ConnectionMine.getConnection();
            preparedStatement = connection2.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                short id = resultSet.getShort("id");
                String name = resultSet.getString("name");
                Date born = resultSet.getDate("born");
                persons.add(new Person(id, name, born));
            }
        }finally {
            try {
                ConnectionMine.close(resultSet);
                ConnectionMine.close(preparedStatement);
                if(this.connection == null){
                    ConnectionMine.close(connection2);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return persons;
    }

    public int insert(Person person) throws SQLException {
        Connection connection2 = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection2 = (this.connection != null) ? this.connection : ConnectionMine.getConnection();
            preparedStatement = connection2.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());
            java.sql.Date sqlDate = new java.sql.Date(person.getBorn().getTime());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.executeUpdate();
        }finally {
            try {
                ConnectionMine.close(preparedStatement);
                if(this.connection == null){
                    ConnectionMine.close(connection2);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return rows;
    }

    public int update(Person person) throws SQLException {
        Connection connection2 = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection2 = (this.connection != null) ? this.connection : ConnectionMine.getConnection();
            preparedStatement = connection2.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, person.getName());
            java.sql.Date sqlDate = new java.sql.Date(person.getBorn().getTime());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setInt(3, person.getId());
            rows = preparedStatement.executeUpdate();
        }finally {
            try {
                ConnectionMine.close(preparedStatement);
                if(this.connection == null){
                    ConnectionMine.close(connection2);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return rows;
    }

    public int delete(Person person) throws SQLException {
        Connection connection2 = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection2 = (this.connection != null) ? this.connection : ConnectionMine.getConnection();
            preparedStatement = connection2.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, person.getId());
            rows = preparedStatement.executeUpdate();
        }finally {
            try {
                ConnectionMine.close(preparedStatement);
                if(this.connection == null){
                    ConnectionMine.close(connection2);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return rows;
    }

}

