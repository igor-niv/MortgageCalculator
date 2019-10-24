package com.inarvaev.mortgagecalculator.UILayer;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Презентер (VIPER) - подготавливает данные, которые получил интерактор, к отображению в UI.
 * После чего он передает их в контроллер (activity), чем достигается разделение ответственностей:
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

class IMortgageCalculatorPresenter implements IMortgageCalculatorPresentationLogic {

    private IMortgageCalculatorDisplayLogic mMortgageCalculatorDisplayLogic;

    IMortgageCalculatorPresenter(IMortgageCalculatorDisplayLogic mortgageCalculatorDisplayLogic) {
        mMortgageCalculatorDisplayLogic = mortgageCalculatorDisplayLogic;
    }

    @Override
    public void presentLoanRate(BigDecimal loanRate, int firstPayment, int fullLoanPrice, int paymentPeriod, int oneMonthPayment) {

        // умножим коэффициент-ставку на 100, чтоб пролучить значение в процентах
        BigDecimal localLoanRate = loanRate
                                    .multiply(new BigDecimal(100))
                                    .stripTrailingZeros();

        localLoanRate = localLoanRate.setScale(2, RoundingMode.HALF_UP);

        String message = String.format(
                "Кредитная ставка (%%): %s.\n" +
                "Полная стоимость кредита (руб): %d.\n" +
                "Срок кредитования (лет): %d.\n" +
                "Месячный платеж по кредиту (руб): %d.", localLoanRate.toPlainString(), fullLoanPrice, paymentPeriod, oneMonthPayment);

        mMortgageCalculatorDisplayLogic.displayMessage(message);
    }

    @Override
    public void presentError(String error) {
        String message = String.format("Ошибка: %s", error);
        mMortgageCalculatorDisplayLogic.displayMessage(message);
    }
}
