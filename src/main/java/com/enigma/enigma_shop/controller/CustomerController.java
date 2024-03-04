package com.enigma.enigma_shop.controller;

import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.services.CustomerService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Customer>> getAllCustomer(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "mobilePhoneNo", required = false) String phoneNumber,
            @RequestParam(name = "birthDate", required = false) @JsonFormat(pattern = "yyyy-MM-dd") String birthDate,
            @RequestParam(name = "status", required = false) Boolean status
    ) {
        SearchCustomerRequest request = SearchCustomerRequest.builder()
                .name(name)
                .mobilePhoneNumber(phoneNumber)
                .birthDate(birthDate)
                .status(status)
                .build();
        List<Customer> customer = customerService.getAll(request);
        return ResponseEntity.ok(customer);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable String id){
        Customer customer=customerService.getById(id);
        return ResponseEntity.ok(customer);
    }

//    @PostMapping
//    public ResponseEntity<Customer> create(@RequestBody Customer customer){
//        Customer customers=customerService.create(customer);
//        return ResponseEntity.status(HttpStatus.CREATED).body(customers);
//    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id){
         customerService.delete(id);
         ResponseEntity.ok("OK");
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
    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(
            @PathVariable String id,
            @RequestParam(name="status") Boolean status
    ){
        customerService.updateStatusById(id, status);
        return ResponseEntity.ok("OK");
    }
}
