package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.entity.TransactionDetail;

import java.util.List;

public interface TransactionDetailService {
    void createBulk(List<TransactionDetail> transactionDetails);
}
