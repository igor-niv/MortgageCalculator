package com.inarvaev.mortgagecalculator;

import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanRequest;

import java.math.BigDecimal;

/**
 * Содержит запрос на ипотечный кредит с уже посчитанными результатами: ставка, первый платеж, полная стоимость кредита, месячный платеж по кредиту.
 */
public class LoanRequestWithCalculatedAnswer {
    private LoanRequest mLoanRequest;
    private BigDecimal mRate;
    private int mFirstPayment;
    private int mFullLoanPrice;
    private int mOneMonthPayment;


    public LoanRequestWithCalculatedAnswer(LoanRequest loanRequest, BigDecimal rate, int firstPayment, int fullLoanPrice, int oneMonthPayment) {
        mLoanRequest = loanRequest;
        mRate = rate;
        mFirstPayment = firstPayment;
        mFullLoanPrice = fullLoanPrice;
        mOneMonthPayment = oneMonthPayment;
    }

    public LoanRequest getLoanRequest() {
        return mLoanRequest;
    }

    public BigDecimal getRate() {
        return mRate;
    }

    public int getFirstPayment() {
        return mFirstPayment;
    }

    public int getFullLoanPrice() {
        return mFullLoanPrice;
    }

    public int getOneMonthPayment() {
        return mOneMonthPayment;
    }
}
