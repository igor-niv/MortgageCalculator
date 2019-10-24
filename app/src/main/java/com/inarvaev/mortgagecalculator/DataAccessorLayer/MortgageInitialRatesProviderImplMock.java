package com.inarvaev.mortgagecalculator.DataAccessorLayer;

import java.math.BigDecimal;

public class MortgageInitialRatesProviderImplMock implements IMortgageInitialRatesProvider {

    private BigDecimal mBaseRate;
    private BigDecimal mMaximizeRatePeople;
    private BigDecimal mMaximizeRateBuilding;
    private BigDecimal mMaximizedRateIfHouseHasWoodFlooring;
    private BigDecimal mMaximizedRateIfmHouseBuiltBefore1950Year;
    private BigDecimal mMinimizeRateBankEmployee;

    public MortgageInitialRatesProviderImplMock() {
        mBaseRate = new BigDecimal(0.09);
        mMaximizeRatePeople = new BigDecimal(0.005);
        mMaximizeRateBuilding = new BigDecimal(0.005);
        mMaximizedRateIfHouseHasWoodFlooring =new BigDecimal( 0.005);
        mMaximizedRateIfmHouseBuiltBefore1950Year = new BigDecimal(0.005);
        mMinimizeRateBankEmployee = new BigDecimal(0.005);
    }

    @Override
    public BigDecimal getBaseRate() {
        return mBaseRate;
    }

    @Override
    public BigDecimal getMinimizeRateBankEmployee() {
        return mMinimizeRateBankEmployee;
    }

    @Override
    public BigDecimal getMaximizeRatePeople() {
        return mMaximizeRatePeople;
    }

    @Override
    public BigDecimal getMaximizeRateBuilding() {
        return mMaximizeRateBuilding;
    }

    @Override
    public BigDecimal getMaximizedRateIfHouseHasWoodFlooring() {
        return mMaximizedRateIfHouseHasWoodFlooring;
    }

    @Override
    public BigDecimal getMaximizedRateIfmHouseBuiltBefore1950Year() {
        return mMaximizedRateIfmHouseBuiltBefore1950Year;
    }


}
