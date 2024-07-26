package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.springboot.dao.ITransaction3Dao;

@Service
public class BuyAndLogService {
	@Autowired
	IBuyTicketService buyTicket;
	@Autowired
	TransactionTemplate transactionTemplate;
	@Autowired
	ITransaction3Dao transaction3;
	
	public int buy(String consumerId, int amount, String error) {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					int nResult = buyTicket.buy(consumerId, amount, error);
					if (error.equals("2")) {int n = 10/0 ; }
					transaction3.pay(consumerId, amount);
				}
			});
			return 1;
			
		} catch (Exception e) {
			System.out.println("[Transaction Propagation #1] Rollback");
			return 0;
		}
	}
	
}
