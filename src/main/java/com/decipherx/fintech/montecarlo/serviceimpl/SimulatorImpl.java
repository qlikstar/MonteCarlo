package com.decipherx.fintech.montecarlo.serviceimpl;

import com.decipherx.fintech.montecarlo.DTO.PortfolioResult;
import com.decipherx.fintech.montecarlo.Exception.IllegalPercentileException;
import com.decipherx.fintech.montecarlo.enums.Portfolio;
import com.decipherx.fintech.montecarlo.service.Distribution;
import com.decipherx.fintech.montecarlo.service.Simulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SimulatorImpl implements Simulator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Distribution distribution;

    private Double investedAmt;

    private Portfolio portfolio;

    private Integer iterations = 10000;

    private Double[] iterationValues;

    private Integer noOfYears = 20;

    private Double[] listOfAllPercentiles;

    public SimulatorImpl() {
    }

    @Override
    public PortfolioResult processPortfolioInvestment(Double investedAmt, Portfolio portfolio){

        PortfolioResult portfolioResult = new PortfolioResult();
        this.investedAmt = investedAmt;
        this.portfolio = portfolio;

        try {
            getAllIterations(this.investedAmt, this.portfolio);
            portfolioResult.setPortfolio(this.portfolio);
            portfolioResult.setMeanOfIterations(getMeanOfAllIterations());
            portfolioResult.setMedianOfIterations(getMedianOfAllIterations());
            portfolioResult.set10PercentileOfIterations(getPercentileOfAllIterations(10));
            portfolioResult.set90PercentileOfIterations(getPercentileOfAllIterations(90));

            listOfAllPercentiles = new Double[9];
            for ( int i=10; i<100; i=i+10 ){
                listOfAllPercentiles[(i/10)-1] =  getPercentileOfAllIterations(i);
            }
            portfolioResult.setListOfAllPercentiles(listOfAllPercentiles);

        }catch (IllegalPercentileException iex){
            //TODO: Implement logger
            logger.error("Error: " + iex.getMessage());
        }
        return portfolioResult;
    }

    @Override
    public void getAllIterations(Double investedAmt, int noOfYears, Portfolio portfolio){
        this.noOfYears = noOfYears;
        getAllIterations(investedAmt,portfolio);
    }

    @Override
    public void getAllIterations(Double investedAmt, Portfolio portfolio){

        this.investedAmt = investedAmt;
        this.portfolio = portfolio;

        iterationValues = new Double[iterations];
        for (int i = 0; i < iterations; i++) {

            distribution.processData(this.investedAmt, this.portfolio.getMean(), this.portfolio.getStandardDeviation(), this.noOfYears);
            iterationValues[i] = distribution.getFinalAssetValueEndOfPeriod();
        }
        Arrays.sort(this.iterationValues);

    }

    @Override
    public Double getMeanOfAllIterations(){

        Double total = 0.0;
        //check if iteration has run
        if (null == iterationValues){
            getAllIterations(investedAmt, portfolio);
        }

        for (int i=0; i<iterations; i++) {
            total = total + iterationValues[i];
        }
        return total/iterations;
    }

    @Override
    public Double getMedianOfAllIterations(){

        //check if iteration has run
        if (null == iterationValues){
            getAllIterations(investedAmt, portfolio);
        }

        double median;
        if (iterationValues.length % 2 == 0)
            median = (iterationValues[iterationValues.length/2] + iterationValues[iterationValues.length/2 - 1])/2;
        else
            median = iterationValues[iterationValues.length/2];
        return median;
    }

    @Override
    public Double getPercentileOfAllIterations(int percentile) throws IllegalPercentileException {

        if (percentile >=0 && percentile <= 99) {

            //check if iteration has run
            if (null == iterationValues) {
                getAllIterations(investedAmt, portfolio);
            }
            //System.out.println("median " + iterationValues.length/2 );
            //System.out.println("percentile " + (int) (iterations * ((float)percentile/100)) );
            return iterationValues[(int) (iterations * ((float)percentile/100))];

        }else{
            throw new IllegalPercentileException("Illegal percentile entered : " + percentile);
        }
    }




//    public static void main1(String[] args) {
//        SimulatorImpl simulator = new SimulatorImpl();
//        simulator.getAllIterations(1000000.0, 0.094324, .15675);
//        System.out.println(simulator.getMeanOfAllIterations());
//        System.out.println(simulator.iterationValues);
//        System.out.println(simulator.getMedianOfAllIterations());
//        System.out.println(simulator.iterationValues[5000]);
//        System.out.println(simulator.getPercentileOfAllIterations(100));
//        System.out.println(simulator.iterationValues[5000]);
//        System.out.println("ckjce");
//    }



}
