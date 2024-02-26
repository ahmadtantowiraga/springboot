package com.enigma.enigma_shop.services.impl;

import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.repository.CustomerRepository;
import com.enigma.enigma_shop.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public Customer create(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getById(String id) {
        if(customerRepository.findById(id).isEmpty()) throw new RuntimeException("Customer not found");
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Customer customer) {
        getById(customer.getId());
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void delete(String id) {
        getById(id);
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findByNameAndMobilePhoneNoAndAddressAndBirthDateAndStatus(String name, String mobilePhoneNo, String address, Date date, Boolean status) {

        if (name==null && mobilePhoneNo==null && address==null && date==null && status==null){
            return getAll();
        }
        return customerRepository.findByNameAndMobilePhoneNoAndAddressAndBirthDateAndStatus(name, mobilePhoneNo, address, date, status);
    }

    @Override
    public List<Customer> findByNameOrMobilePhoneNoOrAddressOrBirthDateOrStatus(String name, String mobilePhoneNo, String address, Date date, Boolean status) {
        return customerRepository.findByNameOrMobilePhoneNoOrAddressOrBirthDateOrStatus(name, mobilePhoneNo, address, date, status);
    }
}
