package com.decipherx.fintech.montecarlo.DTO;

import com.decipherx.fintech.montecarlo.enums.Portfolio;

public class PortfolioResult {

    private Portfolio portfolio;

    private Double meanOfIterations;

    private Double medianOfIterations;

    private Double percentile10OfIterations;

    private Double percentile90OfIterations;

    private Double[] listOfAllPercentiles;

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Double getMeanOfIterations() {
        return meanOfIterations;
    }

    public void setMeanOfIterations(Double meanOfIterations) {
        this.meanOfIterations = meanOfIterations;
    }

    public Double getMedianOfIterations() {
        return medianOfIterations;
    }

    public void setMedianOfIterations(Double medianOfIterations) {
        this.medianOfIterations = medianOfIterations;
    }

    public Double get10PercentileOfIterations() {
        return percentile10OfIterations;
    }

    public void set10PercentileOfIterations(Double percentile10OfIterations) {
        this.percentile10OfIterations = percentile10OfIterations;
    }

    public Double get90PercentileOfIterations() {
        return percentile90OfIterations;
    }

    public void set90PercentileOfIterations(Double percentile90OfIterations) {
        this.percentile90OfIterations = percentile90OfIterations;
    }

    public Double[] getListOfAllPercentiles() {
        return listOfAllPercentiles;
    }

    public void setListOfAllPercentiles(Double[] listOfAllPercentiles) {
        this.listOfAllPercentiles = listOfAllPercentiles;
    }

    public PortfolioResult() {
    }


}
