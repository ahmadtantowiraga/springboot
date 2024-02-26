package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.entity.Customer;

import java.util.Date;
import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getById(String id);
    List<Customer> getAll();
    Customer update(Customer customer);
    void delete(String id);
    List<Customer> findByNameAndMobilePhoneNoAndAddressAndBirthDateAndStatus(String name, String mobilePhoneNo, String address, Date date, Boolean status);
    List<Customer> findByNameOrMobilePhoneNoOrAddressOrBirthDateOrStatus(String name, String mobilePhoneNo, String address, Date date, Boolean status);
}
