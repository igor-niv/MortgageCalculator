package com.inarvaev.mortgagecalculator.BussinesLayer.Calculator;

import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanPurpose;
import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanRequest;
import com.inarvaev.mortgagecalculator.DataAccessorLayer.IMortgageInitialRatesProvider;
import com.inarvaev.mortgagecalculator.DataAccessorLayer.DataProviders;
import com.inarvaev.mortgagecalculator.DataAccessorLayer.IMortgageValidationRulesProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MortgageCalculator {

    private IMortgageInitialRatesProvider mInitialRatesProvider;       // предоставляет значения ставок
    private IMortgageValidationRulesProvider mValidationRulesProvider; // содержит условия для валидации запроса на кредит
    private LoanRequest mLoanRequest;

    private BigDecimal mRate;                 // ставка по ипотечному кредиту
    private BigDecimal mFirstPayment;         // первый платеж по ипотечному кредиту
    private BigDecimal mOneMonthPayment;      // месячный платеж по кредиту = (полная стоимость кредита - первый взнос) / коичество месяцев кредитования

    public MortgageCalculator(LoanRequest loanRequest) {
        mInitialRatesProvider = DataProviders.createMortgageInitialRatesProvider();
        mValidationRulesProvider = DataProviders.createMortgageValidationRulesProvider();
        mLoanRequest = loanRequest;
    }

    /**
     * Расчтитать первый взнос
     */
    public int calculateFirstPayment() {

        if (mFirstPayment != null)
            return mFirstPayment.intValue();

        BigDecimal apartmentPriceDecimal = new BigDecimal(mLoanRequest.getApartmentPrice());
        mFirstPayment =  mValidationRulesProvider
                                    .getFirstPaymentRate()
                                    .multiply(apartmentPriceDecimal);
        mFirstPayment = mFirstPayment.setScale(2, RoundingMode.HALF_UP);
        return mFirstPayment.intValue();
    }

    /**
     * Расчитать полную стоимость кредита
     */
    public int calculateFullLoanPrice() {
        calculateRate();

        BigDecimal overpayment = mRate.multiply(new BigDecimal(mLoanRequest.getApartmentPrice()));
        return overpayment.intValue() * mLoanRequest.getPaymentPeriod() + mLoanRequest.getApartmentPrice();
    }


    /**
     * Расчитать ежемесячный платеж
     */
    public int calculateOneMonthPayment() {
        if (mOneMonthPayment != null)
            return mOneMonthPayment.intValue();

        BigDecimal fullLoanPrice = new BigDecimal(calculateFullLoanPrice());
        BigDecimal fullLoanPriceWoFirstPayment = fullLoanPrice.subtract(mFirstPayment);
        BigDecimal paymentPeriodMonthsDecimal = new BigDecimal(mLoanRequest.getPaymentPeriod() * 12);
        mOneMonthPayment = fullLoanPriceWoFirstPayment.divide(paymentPeriodMonthsDecimal, 2, RoundingMode.HALF_UP);
        return mOneMonthPayment.intValue();
    }

    /**
     * Расчиать ставку по кредиту
     */
    public BigDecimal calculateRate() {

        if (mRate != null)
            return mRate;

        mRate = mInitialRatesProvider.getBaseRate();

        if (mLoanRequest.isBankEmployee())
            mRate = mRate.subtract(mInitialRatesProvider.getMinimizeRateBankEmployee());

        if (mLoanRequest.isHouseBuiltBefore1950Year())
            mRate = mRate.add(mInitialRatesProvider.getMaximizedRateIfmHouseBuiltBefore1950Year());

        if (mLoanRequest.isHouseHasWoodFlooring())
            mRate = mRate.add(mInitialRatesProvider.getMaximizedRateIfHouseHasWoodFlooring());

        if (mLoanRequest.getAge() > mValidationRulesProvider.getAgeForMaximumRate())
            mRate = mRate.add(mInitialRatesProvider.getMaximizeRatePeople());

        if (mLoanRequest.getLoanPurpose() == LoanPurpose.ApartmentInPrimaryMarket)
            mRate = mRate.add(mInitialRatesProvider.getMaximizeRateBuilding());

        mRate = mRate.setScale(2, RoundingMode.HALF_UP);

        return mRate;
    }
}
