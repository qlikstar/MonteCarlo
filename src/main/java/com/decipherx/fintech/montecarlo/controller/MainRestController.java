package com.decipherx.fintech.montecarlo.controller;


import com.decipherx.fintech.montecarlo.DTO.PortfolioResult;
import com.decipherx.fintech.montecarlo.Response.MonteCarloResponse;
import com.decipherx.fintech.montecarlo.enums.Portfolio;
import com.decipherx.fintech.montecarlo.service.Simulator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainRestController {

    @Autowired
    private Simulator simulator;

    @RequestMapping(name="/portfolio", method = RequestMethod.GET)
    public ResponseEntity<MonteCarloResponse<List<PortfolioResult>>> getAllDetails() throws JsonProcessingException {

        List<PortfolioResult> portfolioResults = new ArrayList<>();
        Double investedAmt = 1000000.00;
        PortfolioResult portfolioResultAggressive = simulator.processPortfolioInvestment(investedAmt, Portfolio.AGGRESSIVE);
        PortfolioResult portfolioResultModerate = simulator.processPortfolioInvestment(investedAmt, Portfolio.MODERATE);
        PortfolioResult portfolioResultVeryConservative = simulator.processPortfolioInvestment(investedAmt, Portfolio.VERY_CONSERVATIVE);

        portfolioResults.add(portfolioResultAggressive);
        portfolioResults.add(portfolioResultModerate);
        portfolioResults.add(portfolioResultVeryConservative);

        return ok(portfolioResults);
    }


    protected static <D> ResponseEntity<MonteCarloResponse<D>> error(D dto, int errorCode, String errorMsg, HttpStatus httpStatus) {
        return new ResponseEntity<>(new MonteCarloResponse<>(errorCode, errorMsg), httpStatus);
    }
    protected static <D> ResponseEntity<MonteCarloResponse<D>> ok(D dto) {
        return ResponseEntity.ok(new MonteCarloResponse<>(dto));
    }


}
