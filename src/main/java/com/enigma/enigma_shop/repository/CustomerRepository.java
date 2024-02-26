package com.enigma.enigma_shop.repository;

import com.enigma.enigma_shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    List<Customer> findByNameAndMobilePhoneNoAndAddressAndBirthDateAndStatus(String name, String mobilePhoneNo, String address, Date date, Boolean status);
    List<Customer> findByNameOrMobilePhoneNoOrAddressOrBirthDateOrStatus(String name, String mobilePhoneNo, String address, Date date, Boolean status);
}
