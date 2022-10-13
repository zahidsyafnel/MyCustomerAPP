package id.co.bankmandiri.mycustomerapp.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
    public int getCustomerId() {
        return customerId;
    }

    private int customerId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public Customer(String fistName, String lastName, LocalDate dateOfBirth) {
        this.firstName = fistName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer(int customerId, String firstName, String lastName, LocalDate dateOfBirth){
        this(firstName, lastName, dateOfBirth);
        this.customerId = customerId;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setFistName(String fistName) {
        this.firstName = fistName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", fistName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer1 = (Customer) o;
        return customerId == customer1.customerId &&
                Objects.equals(firstName, customer1.firstName) &&
                Objects.equals(lastName, customer1.lastName) &&
                Objects.equals(dateOfBirth, customer1.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, dateOfBirth);
    }
}
