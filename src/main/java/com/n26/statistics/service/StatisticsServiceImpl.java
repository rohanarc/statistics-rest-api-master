package com.n26.statistics.service;

import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.n26.statistics.model.FixedSizeArrayBlockingQueue;
import com.n26.statistics.model.Statistics;
import com.n26.statistics.model.Transaction;

/**
 * Holds business logic for computing statistics
 *
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    private static final int MILI_SECONDS_STAT = 60000;

    private static final Queue<Transaction> queue = new FixedSizeArrayBlockingQueue<Transaction>(MILI_SECONDS_STAT);

    /**
     * add transaction to queue, as the size is fixed and queue will drop old entries its still O(1).
     * Accuracy is till last millisecond, we can go further if thats the priority
     * @param transaction new transaction data
     */
    @Override
    public void calculateStats(Transaction transaction) {
        queue.add(transaction);
    }

    /**
     * return statistics for last 60 mins
     */
    @Override
    public Statistics getStatistics() {
       return new Statistics(queue);
    }
}
