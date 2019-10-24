package com.inarvaev.mortgagecalculator.BussinesLayer.Validator;

import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanPurpose;
import com.inarvaev.mortgagecalculator.DataAccessorLayer.IMortgageValidationRulesProvider;
import com.inarvaev.mortgagecalculator.DataAccessorLayer.DataProviders;

import java.math.BigDecimal;
import java.text.Format;

/**
 * Валидатор данных, которые предоставляет заещик при запросе кредита
 */
public class LoanRequestValidator {

    private String mAge;
    private String mLoanPurpose;
    private String mApartmentPrice;
    private String mPaymentPeriod;
    private boolean mHouseBuiltBefore1950Year;

    private String mValidationError;

    private IMortgageValidationRulesProvider mValidationRulesProvider;

    public LoanRequestValidator(String age, String loanPurpose, String apartmentPrice, String paymentPeriod, boolean houseBuiltBefore1950Year) {
       mAge = age;
       mLoanPurpose = loanPurpose;
       mApartmentPrice = apartmentPrice;
       mPaymentPeriod = paymentPeriod;
       mHouseBuiltBefore1950Year = houseBuiltBefore1950Year;
       mValidationRulesProvider = DataProviders.createMortgageValidationRulesProvider();
    }

    /**
     * Валидация запроса на кредит
     * @return Булевый признак того, прошел ли запрос на кредит валидацию
     */

    public boolean validate() {

        int age;
        int apartmentPrice;
        int paymentPeriod;

        try {
            age = Integer.parseInt(mAge);
            apartmentPrice = Integer.parseInt(mApartmentPrice);
            paymentPeriod = Integer.parseInt(mPaymentPeriod);
        } catch (NumberFormatException ex) {
            mValidationError = "Вы не ввели данные, проверьте, что все поля заполнены.";
            return false;
        }

        LoanPurpose eLoanPurpose = LoanPurpose.indexOf(mLoanPurpose);
        if (eLoanPurpose == LoanPurpose.Unknown) {
            mValidationError = "Неверно указана цель ипотечного кредита";
            return false;
        }

        if (mHouseBuiltBefore1950Year && eLoanPurpose == LoanPurpose.ApartmentInPrimaryMarket) {
            mValidationError = "Неверно указана цель ипотечного кредита: дом сданный до 1950 года не может считаться новостройкой";
            return false;
        }
        if (apartmentPrice > mValidationRulesProvider.getMaxLoanAmount()) {
            mValidationError = String.format("Банк не выдает ипотечный кредит, если его сумма составляет более %d рублей",
                    mValidationRulesProvider.getMaxLoanAmount());
            return false;
        }

        if (apartmentPrice < mValidationRulesProvider.getMinLoanAmount()) {
            mValidationError = String.format("Банк не выдает ипотечный кредит, если его сумма составляет менее %d рублей",
                    mValidationRulesProvider.getMinLoanAmount());
            return false;
        }

        if (paymentPeriod < mValidationRulesProvider.getMinPaymentPeriodYears() || paymentPeriod > mValidationRulesProvider.getMaxPaymentPeriodYears()) {
            mValidationError = String.format("Ипотечные кредиты выдаются на следкющий срок (лет): %d - %d",
                    mValidationRulesProvider.getMinPaymentPeriodYears(),
                    mValidationRulesProvider.getMaxPaymentPeriodYears());
            return false;
        }

        if (age < mValidationRulesProvider.getMinAge() || age > mValidationRulesProvider.getMaxAge()) {
            mValidationError = String.format("Ипотечные кредиты не выдаются лицам младше %d и старше %d лет.",
                    mValidationRulesProvider.getMinAge(),
                    mValidationRulesProvider.getMaxAge());

            return false;
        }

        return true;
    }

    /**
     * Получение описание ошибки в кредитном запросе, если она присутствует
     * @return описание ошибки, либо null
     */
    public String getValidationError() {
        return mValidationError;
    }
}
