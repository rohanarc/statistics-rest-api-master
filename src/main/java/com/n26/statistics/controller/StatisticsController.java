package com.n26.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.statistics.model.Statistics;
import com.n26.statistics.service.StatisticsService;

/**
 * API for statistics
 *
 */
@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public ResponseEntity<?> getStatistics(){
        return new ResponseEntity<Statistics>(statisticsService.getStatistics(),HttpStatus.OK);
    }
}
