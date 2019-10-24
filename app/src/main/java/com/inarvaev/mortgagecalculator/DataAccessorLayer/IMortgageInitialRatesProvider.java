package com.inarvaev.mortgagecalculator.DataAccessorLayer;

import java.math.BigDecimal;

/**
 * Предоставляет ставки (максимизирующие и минимизирущие), который применяются в зависисмости от условий выдачи кредита.
 */

public interface IMortgageInitialRatesProvider {

    BigDecimal getBaseRate();

    BigDecimal getMinimizeRateBankEmployee();

    BigDecimal getMaximizeRatePeople();

    BigDecimal getMaximizeRateBuilding();

    BigDecimal getMaximizedRateIfHouseHasWoodFlooring();

    BigDecimal getMaximizedRateIfmHouseBuiltBefore1950Year();
}
