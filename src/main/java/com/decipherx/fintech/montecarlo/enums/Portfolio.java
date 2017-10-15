package com.decipherx.fintech.montecarlo.enums;

public enum Portfolio {

    AGGRESSIVE ("Aggressive", 0.094324, .15675),
    MODERATE ("Moderate", 0.081890, .102193),
    VERY_CONSERVATIVE("Very Conservative" , 0.06189, 0.063438);

    String portfolioName;

    Double mean;

    Double standardDeviation;

    public String getPortfolioName() {
        return portfolioName;
    }

    public Double getMean() {
        return mean;
    }

    public Double getStandardDeviation() {
        return standardDeviation;
    }

    private Portfolio(String portfolioName, Double mean, Double standardDeviation) {
        this.portfolioName = portfolioName;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
    }



}
