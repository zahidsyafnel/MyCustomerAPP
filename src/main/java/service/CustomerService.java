package service;

import id.co.bankmandiri.mycustomerapp.dao.CustomerDao;
import id.co.bankmandiri.mycustomerapp.domain.Customer;
import id.co.bankmandiri.mycustomerapp.domain.CustomerException;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    List<Customer> displayAllCustomer();
    void addCustomer(Customer customer) throws CustomerException;

    void editCustomer(Customer customer) throws  CustomerException;
    Customer findCustomerById(int id)throws  CustomerException;
    void deleteCustomer (int id) throws CustomerException;
}
