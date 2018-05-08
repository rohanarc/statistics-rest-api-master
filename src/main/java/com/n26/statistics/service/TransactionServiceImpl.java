package com.n26.statistics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.statistics.exception.InvalidDataException;
import com.n26.statistics.exception.StaleTransactionException;
import com.n26.statistics.model.Transaction;

/**
 * Holds business logic for processing transactions
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private StatisticsService statisticsService;

	@Override
	public void addTransaction(Transaction transaction) {
		if (transaction == null)
			throw new InvalidDataException("Invalid transaction");
		if (transaction.getTimestamp() == null)
			throw new InvalidDataException("Invalid transaction");
		if (transaction.getAmount() == null)
			throw new InvalidDataException("Invalid transaction");

		if ((System.currentTimeMillis() - transaction.getTimestamp()) / 1000 > 60)
			throw new StaleTransactionException("");

		logger.info("Received new transaction => {}", transaction);
		statisticsService.calculateStats(transaction);
	}
}
