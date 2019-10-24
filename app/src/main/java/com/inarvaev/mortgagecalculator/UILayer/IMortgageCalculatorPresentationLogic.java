package com.inarvaev.mortgagecalculator.UILayer;

import java.math.BigDecimal;

interface IMortgageCalculatorPresentationLogic {
    /**
     * Форматирует данные для передаче в Activity, которая отобразит из на экране
     * @param loanRate  ставка по кредиту
     */
    void presentLoanRate(BigDecimal loanRate, int firstPayment, int fullLoanPrice, int paymentPeriod, int oneMonthPayment);

    /**
     * Форматирует данные для передаче в Activity, которая отобразит из на экране
     * @param error  текст ошибки
     */
    void presentError(String error);
}
