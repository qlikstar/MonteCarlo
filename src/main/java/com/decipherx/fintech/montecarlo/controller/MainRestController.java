package com.decipherx.fintech.montecarlo.controller;


import com.decipherx.fintech.montecarlo.DTO.JSObject;
import com.decipherx.fintech.montecarlo.DTO.PortfolioResult;
import com.decipherx.fintech.montecarlo.Response.MonteCarloResponse;
import com.decipherx.fintech.montecarlo.enums.Portfolio;
import com.decipherx.fintech.montecarlo.service.Simulator;
import com.decipherx.fintech.montecarlo.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Simulator simulator;

    @Autowired
    private Utility utility;

    @RequestMapping(name="/portfolio", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonteCarloResponse<JSObject>> getAllDetails() {

        List<PortfolioResult> portfolioResults = new ArrayList<>();
        Double investedAmt = 1000000.00;
        PortfolioResult portfolioResultAggressive = simulator.processPortfolioInvestment(investedAmt, Portfolio.AGGRESSIVE);
        PortfolioResult portfolioResultModerate = simulator.processPortfolioInvestment(investedAmt, Portfolio.MODERATE);
        PortfolioResult portfolioResultVeryConservative = simulator.processPortfolioInvestment(investedAmt, Portfolio.VERY_CONSERVATIVE);

        portfolioResults.add(portfolioResultAggressive);
        portfolioResults.add(portfolioResultModerate);
        portfolioResults.add(portfolioResultVeryConservative);

        JSObject jsObject = utility.convertListOfPortfolioToJSObject(portfolioResults);
        return ok(jsObject);
    }


    private static <D> ResponseEntity<MonteCarloResponse<D>> error(D dto, int errorCode, String errorMsg, HttpStatus httpStatus) {
        return new ResponseEntity<>(new MonteCarloResponse<>(errorCode, errorMsg), httpStatus);
    }
    private static <D> ResponseEntity<MonteCarloResponse<D>> ok(D dto) {
        return ResponseEntity.ok(new MonteCarloResponse<>(dto));
    }


}
