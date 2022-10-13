package dao;


import id.co.bankmandiri.mycustomerapp.dao.CustomerDao;
import id.co.bankmandiri.mycustomerapp.domain.Customer;
import id.co.bankmandiri.mycustomerapp.domain.CustomerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.dbutil;

import java.sql.Connection;
import java.time.LocalDate;

class CustomerDaoTest {
    private static CustomerDao dao;

    @BeforeEach
    void setUp() {
        Connection connection = dbutil.getConnection();
        dao = new CustomerDao(connection);
    }

    @Test
    void displayAllCustomer() {
    }

    @Test
    void addCustomer() {
        Customer customer = new Customer("John", "Doe",
                LocalDate.of(1990, 9,9));
        try {
            dao.addCustomer(customer);
//            System.out.println(dao.addCustomer(customer));
            Customer result = dao.findCustomerById(4);
            System.out.println(result);
            Assertions.assertEquals("John", result.getFirstName());
        } catch (CustomerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void editCustomer() {
        try {
            Customer customer = dao.findCustomerById(3);
            customer.setFistName("Tom");
            customer.setLastName("Hanks");
            customer.setDateOfBirth(LocalDate.now());
            dao.editCustomer(customer);
            System.out.println(customer);
            Customer result = dao.findCustomerById(3);
            System.out.println(result);
            Assertions.assertEquals("Tom",result.getFirstName());
            Assertions.assertEquals("Hanks",result.getLastName());
            Assertions.assertEquals(LocalDate.now(), result.getDateOfBirth());
        } catch (CustomerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findCustomerById() {
    }

    @Test
    void deleteCustomer() {
    }
}