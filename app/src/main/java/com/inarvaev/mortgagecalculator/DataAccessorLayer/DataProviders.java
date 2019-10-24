package com.inarvaev.mortgagecalculator.DataAccessorLayer;

/**
 * В "реальном" приложении данные должны бы были приходить из сети, но в учем прилложении здесь будут моки.
 */

public class DataProviders {

    private DataProviders() {}

    public static IMortgageInitialRatesProvider createMortgageInitialRatesProvider() {
        return new MortgageInitialRatesProviderImplMock();
    }

    public static IMortgageValidationRulesProvider createMortgageValidationRulesProvider() {
        return new MortgageValidationRulesProviderMock();
    }
}
