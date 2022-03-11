package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerH2Repository implements CustomerRepository {

    public CustomerH2Repository() {
        try {
            String JDBC_DRIVER = "org.h2.Driver";
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Connection getConnection() throws SQLException {
        String DB_URL = "jdbc:h2:~/my_db";
        String USER = "sa";
        String PASS = "";
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @Override
    public List<Customer> getAll() {
        try (Connection connection = getConnection()) {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("select * from CUSTOMER");
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3))
                );
            }
            connection.close();
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean createCustomer(String name, String eMail) {
        try (Connection connection = getConnection()) {
            PreparedStatement pStat = connection.prepareStatement("insert into CUSTOMER (NAME, EMAIL) values (?, ?)");
            pStat.setString(1, name);
            pStat.setString(2, eMail);
            int result = pStat.executeUpdate();
            connection.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}


