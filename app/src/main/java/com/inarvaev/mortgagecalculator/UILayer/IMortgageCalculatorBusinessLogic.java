package com.inarvaev.mortgagecalculator.UILayer;

interface IMortgageCalculatorBusinessLogic {

    /**
     * Расчет ставки по кредиту
     * @param age  возраст
     * @param loanPurpose  цель кредита
     * @param apartmentPrice  цена квартиры
     * @param paymentPeriod  срок, на который берется кредит
     * @param houseHasWoodFlooring  дом содержит деревянные перегородки
     * @param houseBuiltBefore1950Year  дом построен ранее 1960 года
     * @param bankEmployee  заемщик является сотрудником банка
     */
    void calculateLoanRate(String age,
                           String loanPurpose,
                           String apartmentPrice,
                           String paymentPeriod,
                           boolean houseHasWoodFlooring,
                           boolean houseBuiltBefore1950Year,
                           boolean bankEmployee);

}
