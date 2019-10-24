package com.inarvaev.mortgagecalculator.BussinesLayer.Calculator;

import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanPurpose;
import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanRequest;
import com.inarvaev.mortgagecalculator.DataAccessorLayer.IMortgageInitialRatesProvider;
import com.inarvaev.mortgagecalculator.DataAccessorLayer.DataProviders;
import com.inarvaev.mortgagecalculator.DataAccessorLayer.IMortgageValidationRulesProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class MortgageCalculatorBase {

    protected IMortgageInitialRatesProvider mInitialRatesProvider;       // предоставляет значения ставок
    protected IMortgageValidationRulesProvider mValidationRulesProvider; // содержит условия для валидации запроса на кредит
    protected LoanRequest mLoanRequest;

    protected MortgageCalculatorBase(LoanRequest loanRequest) {
        mInitialRatesProvider = DataProviders.createMortgageInitialRatesProvider();
        mValidationRulesProvider = DataProviders.createMortgageValidationRulesProvider();
        mLoanRequest = loanRequest;
    }

    /**
     * Расчтитать первый взнос
     */
    public abstract int calculateFirstPayment();

    /**
     * Расчитать полную стоимость кредита
     */
    public abstract int calculateFullLoanPrice();


    /**
     * Расчитать ежемесячный платеж
     */
    public abstract int calculateOneMonthPayment();

    /**
     * Расчиать ставку по кредиту
     */
    public abstract BigDecimal calculateRate();
}
