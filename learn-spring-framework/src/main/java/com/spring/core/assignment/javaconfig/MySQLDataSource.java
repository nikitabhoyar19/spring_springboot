package com.spring.core.assignment.javaconfig;

public class MySQLDataSource implements DataSource {

    @Override
    public String[] getEmails() {

        String[] emails = {"ramesh@gmail.com", "tony@gmail.com", "john@gmail.com"};

        return emails;
    }
}
