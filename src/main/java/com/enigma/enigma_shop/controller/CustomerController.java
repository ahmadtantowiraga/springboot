package com.enigma.enigma_shop.controller;

import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> findAll(){
       return customerService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Customer findById(@PathVariable String id){
        return customerService.getById(id);
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer){
        return customerService.create(customer);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id){
         customerService.delete(id);
    }
    @GetMapping(path = "/filter-and")
    public List<Customer> findByNameAndMobilePhoneNoAndAddressAndBirthDateAndStatus(@RequestParam(name="name", required = false) String name,
                                                                                    @RequestParam(name="mobile-phone-no") String mobilePhoneNo,
                                                                                    @RequestParam(name="address", required = false) String address,
                                                                                    @RequestParam(name="date", required = false) String date,
                                                                                    @RequestParam(name="status", required = false) Boolean status) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date2 = dateFormat.parse(date);

        return customerService.findByNameAndMobilePhoneNoAndAddressAndBirthDateAndStatus(name, mobilePhoneNo, address, date2, status);
    }

    @GetMapping(path = "/filter-or")
    public List<Customer> findByNameOrMobilePhoneNoOrAddressOrBirthDateOrStatus(@RequestParam(name="name", required = false) String name,
                                                                                    @RequestParam(name="mobile-phone-no") String mobilePhoneNo,
                                                                                    @RequestParam(name="address", required = false) String address,
                                                                                    @RequestParam(name="date", required = false) String date,
                                                                                    @RequestParam(name="status", required = false) Boolean status) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date2 = dateFormat.parse(date);

        return customerService.findByNameOrMobilePhoneNoOrAddressOrBirthDateOrStatus(name, mobilePhoneNo, address, date2, status);
    }
}
