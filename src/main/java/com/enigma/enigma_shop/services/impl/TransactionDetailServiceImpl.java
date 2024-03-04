package com.enigma.enigma_shop.services.impl;

import com.enigma.enigma_shop.entity.TransactionDetail;
import com.enigma.enigma_shop.repository.TransactionDetailRepository;
import com.enigma.enigma_shop.services.TransactionDetailService;
import com.enigma.enigma_shop.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {
    private final TransactionDetailRepository transactionDetailRepository;
    private  final ValidationUtil validationUtil;
    @Override
    public void createBulk(List<TransactionDetail> transactionDetails) {
        validationUtil.validate(transactionDetails);
        transactionDetailRepository.saveAllAndFlush(transactionDetails);
    }
}
