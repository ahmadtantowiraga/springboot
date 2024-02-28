package com.enigma.enigma_shop.repository;

import com.enigma.enigma_shop.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {

    List<Customer> findByNameAndMobilePhoneNoAndAddressAndBirthDateAndStatus(String name, String mobilePhoneNo, String address, Date date, Boolean status);
    List<Customer> findByNameOrMobilePhoneNoOrAddressOrBirthDateOrStatus(String name, String mobilePhoneNo, String address, Date date, Boolean status);

    @Modifying
    @Query(value ="UPDATE m_customer SET status = :status WHERE id = :id", nativeQuery = true)
    void updateStatus(@Param("id") String id, @Param("status") Boolean status);

}
