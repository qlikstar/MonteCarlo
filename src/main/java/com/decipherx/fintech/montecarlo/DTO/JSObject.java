package com.decipherx.fintech.montecarlo.DTO;

import java.util.List;

public class JSObject{
    List<List> portfolioPercentile;
    List<List> portfolioTypes;

    public JSObject() {
    }

    public List<List> getPortfolioPercentile() {
        return portfolioPercentile;
    }

    public void setPortfolioPercentile(List<List> portfolioPercentile) {
        this.portfolioPercentile = portfolioPercentile;
    }

    public List<List> getPortfolioTypes() {
        return portfolioTypes;
    }

    public void setPortfolioTypes(List<List> portfolioTypes) {
        this.portfolioTypes = portfolioTypes;
    }
}
