package com.inarvaev.mortgagecalculator.UILayer;

import com.inarvaev.mortgagecalculator.BussinesLayer.Calculator.MortgageCalculator;
import com.inarvaev.mortgagecalculator.BussinesLayer.Validator.LoanRequestValidatorException;
import com.inarvaev.mortgagecalculator.BussinesLayer.LoanRequestFactory;
import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanRequest;

import java.math.BigDecimal;

/**
 * Интерактор - содержит бизнес логику для текущего контроллера (или activity - так "называется" контроллер в андройд).
 * Результаты работы интерактора (например, посчитанные данные) передаются в презентер, чтоб презентер подготовил их для вывода в UI,
 * чем достигается разделение ответственностей:
 *   - интерактор "считает" бизнес логику и общается со слоем сервисов, он не знает как эти данные должны отображаться в UI;
 *   - презентер готовит данные для отображения (например, работает со шрифтами, подготавливает данные для отображения в табличном виде и т.д.), он не знает откуда эти данные пришли;
 *   - контроллер - передает данные непосредственно в/из UI, получает от UI события (например, нажатия кнопки), и передает его в интерактор.
 *
 *   Контроллер напрямую общается только с интерактором.
 *   Интерактор напрямую общается только с презентером (и worker'ом, который по идее, и должен посылать запросы слою сервисов; но здесь worker не представлен, его обязанности делегированы интерактору).
 *   Презентер напрямую общается только с контроллероом.
 *
 *   Это позволяет отделить бизнес-логику, от отображения и от контроллера, который в тех же мобилках жестко завязан на наследования от системных UI классов.
 *   Так же интерактор и презентер можно переиспользовать, в пределах приложения, но контроллер - не всегда можно, т.к. он очень жестко завязан на UI.
 */

class MortgageCalculatorInteractor implements IMortgageCalculatorBusinessLogic {

    private IMortgageCalculatorPresenter mPresenter;

    MortgageCalculatorInteractor(IMortgageCalculatorPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void calculateLoanRate(String age, String loanPurpose, String apartmentPrice, String paymentPeriod, boolean houseHasWoodFlooring, boolean houseBuiltBefore1950Year, boolean bankEmployee) {
        LoanRequestFactory factory = new LoanRequestFactory(age,
                                                            loanPurpose,
                                                            apartmentPrice,
                                                            paymentPeriod,
                                                            bankEmployee,
                                                            houseHasWoodFlooring,
                                                            houseBuiltBefore1950Year);

        LoanRequest loanRequest = null;

        try {

            loanRequest = factory.createLoanRequest();
            MortgageCalculator calculator = new MortgageCalculator(loanRequest);
            BigDecimal rate = calculator.calculateRate();

            int firstPayment = calculator.calculateFirstPayment();
            int fullLoanPrice = calculator.calculateFullLoanPrice();
            int oneMonthPayment = calculator.calculateOneMonthPayment();

            mPresenter.presentLoanRate(rate, firstPayment, fullLoanPrice, loanRequest.getPaymentPeriod(), oneMonthPayment);

        } catch (LoanRequestValidatorException e) {
            mPresenter.presentError(e.getLocalizedMessage());
        }
    }
}
