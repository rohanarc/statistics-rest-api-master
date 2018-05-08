package com.n26.statistics.service;

import com.n26.statistics.model.Statistics;
import com.n26.statistics.model.Transaction;

/**
 *
 */
public interface StatisticsService {

    /**
     * Compute the statistics for current transaction
     * @param transaction
     */
    void calculateStats(Transaction transaction);

    /**
     * get final summary of statistics
     * @return
     */
    Statistics getStatistics();

}
