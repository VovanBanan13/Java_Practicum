package ru.home.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.services.ReportService;

@RestController
@RequestMapping("/report")
@Api(value="report")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @ApiOperation(value = "View the sum of profit")
    @GetMapping(value = "/profit", produces = "application/json")
    public ResponseEntity<Double> profit(){
        double profit = reportService.getProfit();
        return new ResponseEntity<>(profit, HttpStatus.OK);
    }

    @ApiOperation(value = "View the average check of profit")
    @GetMapping(value = "/average_check", produces = "application/json")
    public ResponseEntity<Double> avgCheck(){
        double avg = reportService.getAverageCheck();
        return new ResponseEntity<>(avg, HttpStatus.OK);
    }

    @ApiOperation(value = "View a list of sold toys")
    @GetMapping(value = "/sold_toys", produces = "application/json")
    public ResponseEntity<Map<String, Integer>> toysSold(){
        Map<String, Integer> toys = reportService.getToysSold();
        return new ResponseEntity<>(toys, HttpStatus.OK);
    }
}
