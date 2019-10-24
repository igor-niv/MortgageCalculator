package com.inarvaev.mortgagecalculator.BussinesLayer.Model;

/**
 * Примечание: не совсем корректно сказать isBankEmployee (это булева переменная),
 * но amIBankEmployee не совсем подходит под java coding guides,
 * поэтому было приянто решение пока оставить "не корректный" английский.
 */

public class LoanRequest {

    private int mAge;                          // возраст заемщика
    private LoanPurpose mLoanPurpose;          // цель кредита: новостройка vs вторичка
    private boolean mBankEmployee;             // заемщик является работником банка
    private boolean mHouseHasWoodFlooring;     // дом имеет деревянные перекрытия (+ к риску)
    private boolean mHouseBuiltBefore1950Year; // дом построен до 1950 (+ к риску)
    private int mApartmentPrice;            // цена квартиры
    private int mPaymentPeriod;                // срок кредита


    public LoanRequest(int age,
                       LoanPurpose loanPurpose,
                       boolean bankEmployee,
                       boolean houseHasWoodFlooring,
                       boolean houseBuiltBefore1950Year,
                       int apartmentPrice,
                       int paymentPeriod)
    {
        mAge = age;
        mLoanPurpose = loanPurpose;
        mBankEmployee = bankEmployee;
        mHouseHasWoodFlooring = houseHasWoodFlooring;
        mHouseBuiltBefore1950Year = houseBuiltBefore1950Year;
        mApartmentPrice = apartmentPrice;
        mPaymentPeriod = paymentPeriod;
    }

    public int getAge() {
        return mAge;
    }

    public LoanPurpose getLoanPurpose() {
        return mLoanPurpose;
    }

    public boolean isBankEmployee() {
        return mBankEmployee;
    }

    public boolean isHouseHasWoodFlooring() {
        return mHouseHasWoodFlooring;
    }

    public boolean isHouseBuiltBefore1950Year() {
        return mHouseBuiltBefore1950Year;
    }

    public int getApartmentPrice() {
        return mApartmentPrice;
    }

    public int getPaymentPeriod() {
        return mPaymentPeriod;
    }
}
