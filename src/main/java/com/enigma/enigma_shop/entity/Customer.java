package com.enigma.enigma_shop.entity;

import com.enigma.enigma_shop.constant.ConstanTable;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstanTable.CUSTOMER_TABLE)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name ="name")
    private String name;

    @Column(name="mobile_phone_number")
    private String mobilePhoneNo;

    @Column(name="address")
    private String address;

    @Column(name="birrh_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Column(name="status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name="user_account_id", unique=true)
    private UserAccount userAccount;
}
