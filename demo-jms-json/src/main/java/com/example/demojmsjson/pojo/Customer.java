package com.example.demojmsjson.pojo;

import lombok.ToString;

@ToString
public class Customer {
    public Customer(String customerId, String fullName) {
        this.customerId = customerId;
        this.fullName = fullName;
    }

    public Customer() {
    }

    private  String customerId;
    private  String fullName;

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }
}
