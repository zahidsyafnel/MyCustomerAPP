package id.co.bankmandiri.mycustomerapp.dao;

import id.co.bankmandiri.mycustomerapp.domain.Customer;
import id.co.bankmandiri.mycustomerapp.domain.CustomerException;
import service.CustomerService;
import util.dbutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.dbutil.connection;

public class CustomerDao implements CustomerService {
    String query = "SELECT * FROM customer";
    private final String queryDisplayCustomer = "SELECT * FROM customer";
    private final String queryFindCustomerId =
            "SELECT * FROM customer WHERE customerid = ?";
    private final String queryeditCustomer =
            "UPDATE customer " +
                    "SET firstname = ?, lastname = ?, dateofbirth = ? " +
                    "WHERE customerid = ?";
    private final String queryDeleteCustomer =
            "DELETE FROM customer" +
                    "Where customerId = ?";
    private final String queryInsertCustomer = "INSERT INTO customer(firstname,lastname, dateofbirth) VALUES (?, ?, ?)";

    private Connection connection;
    public CustomerDao(Connection connection) {
        this.connection = dbutil.getConnection();
    }

    @Override
    public List<Customer> displayAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("customerid") + " ");
                System.out.println(resultSet.getString("firstname") + " ");
                System.out.println(resultSet.getString("lastname") + " ");
                System.out.println(resultSet.getDate("dateofbirth").toLocalDate());
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCustomer(Customer customer) throws CustomerException {
        try (
                PreparedStatement ps = connection.prepareStatement(queryInsertCustomer);
        ) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setDate(3, Date.valueOf(customer.getDateOfBirth()));
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new CustomerException("Add customer fail");
        }
    }

    @Override
    public void editCustomer(Customer customer) throws CustomerException {
        try (
                PreparedStatement ps = connection.prepareStatement(queryeditCustomer);
        ) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setDate(3, Date.valueOf(customer.getDateOfBirth()));
            ps.setInt(4,customer.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new CustomerException("Edit customer fail");
        }
    }


    @Override
    public Customer findCustomerById(int id) throws CustomerException {
        Customer customer = null;
        try (
                PreparedStatement ps = connection.prepareStatement(queryFindCustomerId);
        ) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("customerid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getDate("dateofbirth").toLocalDate()
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new CustomerException("Find customer fail");
        }
        return customer;
    }

    @Override
    public void deleteCustomer(int id) throws CustomerException {
        try (
                PreparedStatement ps = connection.prepareStatement(queryDeleteCustomer);
        ) {
            ps.executeUpdate();
            ps.setInt(1, id);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new CustomerException("Delete customer fail");
        }
    }
}
