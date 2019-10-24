package com.inarvaev.mortgagecalculator;

import com.inarvaev.mortgagecalculator.BussinesLayer.Calculator.MortgageCalculator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Тестирует калькуляцию ставки, первого платежа, полной стоимости кредита и месячного платежа по кредиту.
 */
public class MortgageCalculatorTests {

    @Test
    public void calculateRateTest() {
        // arrange

        List<LoanRequestWithCalculatedAnswer> requestsWithCalculatedAnswer = LoanRequestsHelper.createLoanRequestsWithCalculatedAnswer();

        // act && assert

        for (LoanRequestWithCalculatedAnswer requestWithCalculatedAnswer : requestsWithCalculatedAnswer) {

            MortgageCalculator calculator = new MortgageCalculator(requestWithCalculatedAnswer.getLoanRequest());

            double rate = calculator.calculateRate().doubleValue();
            int firstPayment = calculator.calculateFirstPayment();
            int fullLoanPrice = calculator.calculateFullLoanPrice();
            int oneMonthPayment = calculator.calculateOneMonthPayment();

            BigDecimal calculatedRate = requestWithCalculatedAnswer.getRate();
            BigDecimal testedRate = new BigDecimal(rate);
            boolean isRateGood = (calculatedRate.compareTo(testedRate) == 0);
            Assert.assertTrue(isRateGood);

            Assert.assertEquals(firstPayment, requestWithCalculatedAnswer.getFirstPayment());
            Assert.assertEquals(fullLoanPrice, requestWithCalculatedAnswer.getFullLoanPrice());
            Assert.assertEquals(oneMonthPayment, requestWithCalculatedAnswer.getOneMonthPayment());
        }
    }
}
