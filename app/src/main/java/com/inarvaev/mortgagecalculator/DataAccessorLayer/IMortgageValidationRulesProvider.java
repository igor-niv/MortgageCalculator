package com.inarvaev.mortgagecalculator.DataAccessorLayer;

import java.math.BigDecimal;

/**
 * Предоставляет различные условия для валидации запроса на ипотечный кредит
 */

public interface IMortgageValidationRulesProvider {
    int getMaxLoanAmount();

    int getMinLoanAmount();

    BigDecimal getFirstPaymentRate();

    int getMaxPaymentPeriodYears();

    int getMinPaymentPeriodYears();

    int getMaxAge();

    int getMinAge();

    int getAgeForMaximumRate();
}
