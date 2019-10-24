package com.inarvaev.mortgagecalculator.DataAccessorLayer;

import java.math.BigDecimal;

public class MortgageValidationRulesProviderMock implements IMortgageValidationRulesProvider {

    private int mMaxLoanAmount;
    private int mMinLoanAmount;
    private BigDecimal mFirstPaymentRate;
    private int mMaxPaymentPeriodYears;
    private int mMinPaymentPeriodYears;
    private int mMaxAge;
    private int mMinAge;
    private int mAgeForMaximumRate;

    public MortgageValidationRulesProviderMock() {

        mMaxLoanAmount = 30_000_000;
        mMinLoanAmount = 300_000;
        mFirstPaymentRate = new BigDecimal(0.15);
        mMaxPaymentPeriodYears = 30;
        mMinPaymentPeriodYears = 1;
        mMaxAge = 60;
        mMinAge = 18;
        mAgeForMaximumRate = 50;
    }

    @Override
    public int getMaxLoanAmount() {
        return mMaxLoanAmount;
    }

    @Override
    public int getMinLoanAmount() {
        return mMinLoanAmount;
    }

    @Override
    public BigDecimal getFirstPaymentRate() {
        return mFirstPaymentRate;
    }

    @Override
    public int getMaxPaymentPeriodYears() {
        return mMaxPaymentPeriodYears;
    }

    @Override
    public int getMinPaymentPeriodYears() {
        return mMinPaymentPeriodYears;
    }

    @Override
    public int getMaxAge() {
        return mMaxAge;
    }

    @Override
    public int getMinAge() {
        return mMinAge;
    }

    @Override
    public int getAgeForMaximumRate() {
        return mAgeForMaximumRate;
    }
}


/*

    if (apartmentPrice > 30000000)  {
        mValidationError = "Банк не выдает ипотечный кредит, если его сумма составляет более 30 000 0000 рублей";
        return false;
    }

        if (apartmentPrice < 300000)  {
        mValidationError = "Банк не выдает ипотечный кредит, если его сумма составляет менее 300 000 рублей";
        return false;
    }

        if (firstPayment < apartmentPrice * 0.15)  {
        mValidationError = "Если вы хотите внести первоначальный взнос, то он должен быть не меньше, чем 15% от стоимости недвижимости ";
        return false;
    }

        if (paymentPeriod < 1 || paymentPeriod > 25)  {
        mValidationError = "Ипотечные кредиты не выдаются на срок меньше года, либо больше 25 лет.";
        return false;
    }

        if (age < 18 || age > 55) {
        mValidationError = "Ипотечные кредиты не выдаются лицам младше 18 и старше 55 лет.";
        return false;
    }

        return true;

 */