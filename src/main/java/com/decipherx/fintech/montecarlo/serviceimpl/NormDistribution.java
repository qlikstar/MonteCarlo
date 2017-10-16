package com.decipherx.fintech.montecarlo.serviceimpl;

import com.decipherx.fintech.montecarlo.service.Distribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class NormDistribution implements Distribution {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Random random = new Random();

    private Double investedAmt;

    private Double mean;

    private Double standardDeviation;

    private Integer noOfYears = 20;

    private Double inflationRate = 3.5;

    private Double finalAssetValueEndOfPeriod;

    @Override
    public Double getFinalAssetValueEndOfPeriod() {
        return finalAssetValueEndOfPeriod;
    }

    @Override
    public Integer getNoOfYears() {
        return noOfYears;
    }

    public NormDistribution() {
    }

    public NormDistribution(Double investedAmt, Double mean, Double standardDeviation) {
        this.investedAmt = investedAmt;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        getReturnPercentage();
    }

    public NormDistribution(Double investedAmt, Double mean, Double standardDeviation, Integer noOfYears) {
        this.investedAmt = investedAmt;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.noOfYears = noOfYears;
        getReturnPercentage();
    }

    @Override
    public void processData (Double investedAmt, Double mean, Double standardDeviation) {
        this.investedAmt = investedAmt;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.noOfYears = noOfYears;
        getReturnPercentage();
    }

    @Override
    public void processData (Double investedAmt, Double mean, Double standardDeviation, Integer noOfYears) {
        this.investedAmt = investedAmt;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.noOfYears = noOfYears;
        getReturnPercentage();
    }

    @Override
    public void processData (Double investedAmt, Double mean, Double standardDeviation, Integer noOfYears, Double inflationRate) {
        this.investedAmt = investedAmt;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.noOfYears = noOfYears;
        this.inflationRate = inflationRate;
        getReturnPercentage();
    }

    private void getReturnPercentage() {
        NormalDistribution distribution = new NormalDistribution(mean, standardDeviation);

        for (int i = 0; i < noOfYears; i++) {
            Double rand = random.nextDouble();
            //Inverse Cumulative Probability implements Gaussian distribution
            double outcomeRisk = distribution.inverseCumulativeProbability(rand);
            investedAmt = getAssetValueAtEndOfYear(investedAmt, outcomeRisk);
        }
        finalAssetValueEndOfPeriod = investedAmt;

    }

    private Double getAssetValueAtEndOfYear( Double currentAmount , Double risk){
        Double amtAtYearEnd = currentAmount * (1+ risk);

        //Calculate actual value at Year end - (Inflation rate)
        return amtAtYearEnd * ((Double) (100/(100+inflationRate)));
    }

    public static void main1(String[] args) {

        for (int i=0; i<=10; i++){
            NormDistribution  normDistribution = new NormDistribution(1000000.0, 0.094324, .15675);
            System.out.println(normDistribution.getFinalAssetValueEndOfPeriod());
        }
        System.out.println("done");

    }

}
