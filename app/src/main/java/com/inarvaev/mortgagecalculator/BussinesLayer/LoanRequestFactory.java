package com.inarvaev.mortgagecalculator.BussinesLayer;

import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanRequest;
import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanPurpose;
import com.inarvaev.mortgagecalculator.BussinesLayer.Validator.LoanRequestValidator;
import com.inarvaev.mortgagecalculator.BussinesLayer.Validator.LoanRequestValidatorException;

public class LoanRequestFactory {

    private String mAge;
    private String mLoanPurpose;
    private String mApartmentPrice;
    private String mPaymentPeriod;
    private boolean mIsBankEmployee;
    private boolean mIsWoodFlooring;
    private boolean mHouseBuiltBefore1950Year;

    public LoanRequestFactory(String age, String loanPurpose, String apartmentPrice,
                              String paymentPeriod, boolean isBankEmployee,
                              boolean isWoodFlooring, boolean houseBuiltBefore1950Year) {
        this.mAge = age;
        this.mLoanPurpose = loanPurpose;
        this.mApartmentPrice = apartmentPrice;
        this.mPaymentPeriod = paymentPeriod;
        this.mIsBankEmployee = isBankEmployee;
        this.mIsWoodFlooring = isWoodFlooring;
        this.mHouseBuiltBefore1950Year = houseBuiltBefore1950Year;
    }

    /**
     * Создает объект, содержащий данные запроса на кредит.
     * @return LoanRequest - объект, содержащий данные запроса на кредит.
     */
    public LoanRequest createLoanRequest() throws LoanRequestValidatorException {

        validate();
        return createLoanRequestCore();
    }

    private LoanRequest createLoanRequestCore() {

        int age = Integer.parseInt(mAge);
        LoanPurpose loanPurpose = LoanPurpose.indexOf(mLoanPurpose);
        int apartmentPrice = Integer.parseInt(mApartmentPrice);
        int paymentPeriod = Integer.parseInt(mPaymentPeriod);

        return new LoanRequest(age, loanPurpose, mIsBankEmployee, mIsWoodFlooring, mHouseBuiltBefore1950Year,
                apartmentPrice, paymentPeriod);
    }

    /**
     * Проводит валидацию введенных пользователем данных
     * @throws LoanRequestValidatorException
     */
    private void validate() throws LoanRequestValidatorException {
        LoanRequestValidator validator = new LoanRequestValidator(mAge, mLoanPurpose, mApartmentPrice, mPaymentPeriod, mHouseBuiltBefore1950Year);
        if (!validator.validate()) {
            throw new LoanRequestValidatorException(validator.getValidationError());
        }
    }
}
