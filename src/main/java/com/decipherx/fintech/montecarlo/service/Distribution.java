package com.decipherx.fintech.montecarlo.service;

public interface Distribution {
    /**
     *
     * @return
     */
    Double getFinalAssetValueEndOfPeriod();

    /**
     *
     * @return
     */
    public Integer getNoOfYears();

    /**
     *
     * @param investedAmt
     * @param mean
     * @param standardDeviation
     */
    public void processData (Double investedAmt, Double mean, Double standardDeviation);

    /**
     *
      * @param investedAmt
     * @param mean
     * @param standardDeviation
     * @param noOfYears
     */
    public void processData (Double investedAmt, Double mean, Double standardDeviation, Integer noOfYears);

    /**
     *
     * @param investedAmt
     * @param mean
     * @param standardDeviation
     * @param noOfYears
     * @param inflationRate
     */
    public void processData (Double investedAmt, Double mean, Double standardDeviation, Integer noOfYears, Double inflationRate);




}
