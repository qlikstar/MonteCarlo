package com.decipherx.fintech.montecarlo.util;

import com.decipherx.fintech.montecarlo.DTO.JSObject;
import com.decipherx.fintech.montecarlo.DTO.PortfolioResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Utility {

    public JSObject convertListOfPortfolioToJSObject (List<PortfolioResult> portfolioResultList){

        final Logger logger = LoggerFactory.getLogger(Utility.class);

        JSObject jsObject = new JSObject();

        List<List> portfolioPercentile = new ArrayList<>();
        List<List> portfolioTypes = new ArrayList<>();

        //Format Currency
        NumberFormat currency= NumberFormat.getCurrencyInstance();
        NumberFormat percentage = NumberFormat.getPercentInstance();
        percentage.setMaximumFractionDigits(2);


        List<String> header = new ArrayList<>(Arrays.asList("Percentile"));
        for (PortfolioResult portfolioResult : portfolioResultList){
            header.add(portfolioResult.getPortfolio().getPortfolioName());

            //populate the fields for portfolioType
            List<String> portfolioType = new ArrayList<>();
            portfolioType.add(portfolioResult.getPortfolio().getPortfolioName());
            portfolioType.add(percentage.format(portfolioResult.getPortfolio().getMean()));
            portfolioType.add(Double.toString(portfolioResult.getPortfolio().getStandardDeviation()));
            portfolioType.add(currency.format((portfolioResult.getMeanOfIterations())));
            portfolioType.add(currency.format(portfolioResult.getMedianOfIterations()));
            portfolioType.add(currency.format(portfolioResult.get10PercentileOfIterations()));
            portfolioType.add(currency.format(portfolioResult.get90PercentileOfIterations()));

            for (int i=0; i<portfolioResult.getListOfAllPercentiles().length; i++){

                if (portfolioPercentile.size() == i){
                    portfolioPercentile.add(new ArrayList<>(Arrays.asList((i+1)*10)));
                    portfolioPercentile.get(i).add(portfolioResult.getListOfAllPercentiles()[i]);
                }
                else{
                    portfolioPercentile.get(i).add(portfolioResult.getListOfAllPercentiles()[i]);
                }
            }
            portfolioTypes.add(portfolioType);
        }


        portfolioPercentile.add(0, header);
        logger.debug("Result Obtained: " +portfolioPercentile.toString());
        jsObject.setPortfolioPercentile(portfolioPercentile);
        jsObject.setPortfolioTypes(portfolioTypes);

        return jsObject;
    }

}