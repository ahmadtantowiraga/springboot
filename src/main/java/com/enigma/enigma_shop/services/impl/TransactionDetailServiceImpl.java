package com.enigma.enigma_shop.services.impl;

import com.enigma.enigma_shop.entity.TransactionDetail;
import com.enigma.enigma_shop.repository.TransactionDetailRepository;
import com.enigma.enigma_shop.services.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {
    private TransactionDetailRepository transactionDetailRepository;
    @Override
    public List<TransactionDetail> createBulk(List<TransactionDetail> transactionDetails) {
        return transactionDetailRepository.saveAllAndFlush(transactionDetails);
    }
}
