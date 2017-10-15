package com.decipherx.fintech.montecarlo.service;

import com.decipherx.fintech.montecarlo.DTO.PortfolioResult;
import com.decipherx.fintech.montecarlo.Exception.IllegalPercentileException;
import com.decipherx.fintech.montecarlo.enums.Portfolio;

public interface Simulator {

    /**
     *
     * @param investedAmt
     * @param portfolio
     * @return
     */
    public PortfolioResult processPortfolioInvestment(Double investedAmt, Portfolio portfolio);
    /**
     *
     * @param investedAmt
     * @param portfolio
     */
    public void getAllIterations(Double investedAmt, Portfolio portfolio);
    /**
     *
     * @return
     */
    public Double getMeanOfAllIterations();

    /**
     *
     * @return
     */
    public Double getMedianOfAllIterations();

    /**
     *
     * @param percentile
     * @return
     */
    public Double getPercentileOfAllIterations(int percentile) throws IllegalPercentileException;

}
