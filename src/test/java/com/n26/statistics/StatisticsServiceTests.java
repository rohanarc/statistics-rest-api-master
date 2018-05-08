package com.n26.statistics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.n26.statistics.model.Statistics;
import com.n26.statistics.model.Transaction;
import com.n26.statistics.service.StatisticsService;

/**
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsServiceTests {

    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void whenOutdatedTimestamp_doNothing(){
        Statistics summaryBefore = statisticsService.getStatistics();
        statisticsService.calculateStats(new Transaction(12.0, System.currentTimeMillis() - 60000));
        assertEquals(summaryBefore.getCount(), statisticsService.getStatistics().getCount());
    }

    @Test
    public void whenValidTimestamp_computeStatistics(){
        statisticsService.calculateStats(new Transaction(5.5, System.currentTimeMillis() - 10000));
        statisticsService.calculateStats(new Transaction(15.5, System.currentTimeMillis() - 9000));
        statisticsService.calculateStats(new Transaction(25.2, System.currentTimeMillis() - 8000));
        statisticsService.calculateStats(new Transaction(65.5, System.currentTimeMillis() - 7000));
        statisticsService.calculateStats(new Transaction(5.7, System.currentTimeMillis() - 6000));
        statisticsService.calculateStats(new Transaction(5.8, System.currentTimeMillis() - 5000));
        statisticsService.calculateStats(new Transaction(3.5, System.currentTimeMillis() - 4000));
        statisticsService.calculateStats(new Transaction(2.8, System.currentTimeMillis() - 3000));
        statisticsService.calculateStats(new Transaction(9.5, System.currentTimeMillis() - 2000));
        statisticsService.calculateStats(new Transaction(12.3, System.currentTimeMillis() - 1000));

        Statistics summary = statisticsService.getStatistics();
        assertEquals(summary.getCount(), new Long(10l));
        assertEquals(summary.getSum(), 151.3, 0.0);
        assertEquals(summary.getMax(), 65.5, 0.0);
        assertEquals(summary.getMin(), 2.8, 0.0);
        assertEquals(summary.getAvg(), 151.3 / 10, 0.0);
    }

}
