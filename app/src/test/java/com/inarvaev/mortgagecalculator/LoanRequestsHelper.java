package com.inarvaev.mortgagecalculator;

import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanPurpose;
import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanRequest;
import com.inarvaev.mortgagecalculator.DataAccessorLayer.DataProviders;
import com.inarvaev.mortgagecalculator.DataAccessorLayer.IMortgageValidationRulesProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Создает объекты LoanRequest с заранее подготовлеными для тестирвоания значениями
 */
public class LoanRequestsHelper {

    public static List<LoanRequest> createBadLoanRequests()
    {
        IMortgageValidationRulesProvider mValidationRulesProvider = DataProviders.createMortgageValidationRulesProvider();

        List<LoanRequest> badRequests = new ArrayList<>();
        LoanRequest badRequest = null;

        int validApartmentPrice = mValidationRulesProvider.getMinLoanAmount() + 1;
        int validPaymentPeriod = mValidationRulesProvider.getMinPaymentPeriodYears() + 1;
        int validAge = mValidationRulesProvider.getMinAge() + 1;

        // возраст заемщика ниже допустимого
        badRequest = new LoanRequest(mValidationRulesProvider.getMinAge() - 1, LoanPurpose.ApartmentInPrimaryMarket,false,false,false, validApartmentPrice, validPaymentPeriod);
        badRequests.add(badRequest);

        // возраст заемщика выше допустимого
        badRequest = new LoanRequest(mValidationRulesProvider.getMaxAge() + 1, LoanPurpose.ApartmentInPrimaryMarket,false,false,false,validApartmentPrice, validPaymentPeriod);
        badRequests.add(badRequest);

        // дом еще не сдан, но указана опция, что он построен до 1950 года
        badRequest = new LoanRequest(validAge, LoanPurpose.ApartmentInPrimaryMarket,false,false,true,validApartmentPrice, validPaymentPeriod);
        badRequests.add(badRequest);

        // период кредитования выше допустимого
        badRequest = new LoanRequest(validAge, LoanPurpose.ApartmentInPrimaryMarket,false,false,false,validApartmentPrice, mValidationRulesProvider.getMaxPaymentPeriodYears() + 1);
        badRequests.add(badRequest);

        // период кредитования ниже допустимого
        badRequest = new LoanRequest(validAge, LoanPurpose.ApartmentInPrimaryMarket,false,false,false,validApartmentPrice, mValidationRulesProvider.getMinPaymentPeriodYears() - 1);
        badRequests.add(badRequest);

        // цена на квартиру выше допустимого
        badRequest = new LoanRequest(validAge, LoanPurpose.ApartmentInPrimaryMarket,false,false,false, mValidationRulesProvider.getMaxLoanAmount() + 1, validPaymentPeriod);
        badRequests.add(badRequest);

        // цена на квартиру ниже допустимого
        badRequest = new LoanRequest(validAge, LoanPurpose.ApartmentInPrimaryMarket,false,false,false, mValidationRulesProvider.getMinLoanAmount() - 1, validPaymentPeriod);
        badRequests.add(badRequest);

        return badRequests;
    }

    public static List<LoanRequest> createGoodLoanRequests()
    {
        IMortgageValidationRulesProvider mValidationRulesProvider = DataProviders.createMortgageValidationRulesProvider();

        int validApartmentPrice = mValidationRulesProvider.getMinLoanAmount() + 1;
        int validPaymentPeriod = mValidationRulesProvider.getMinPaymentPeriodYears() + 1;
        int validAge = mValidationRulesProvider.getMinAge() + 1;

        List<LoanRequest> goodRequests = new ArrayList<>();
        LoanRequest goodRequest = null;

        goodRequest = new LoanRequest(validAge, LoanPurpose.ApartmentInSecondaryMarket,true,true,true,validApartmentPrice, validPaymentPeriod);
        goodRequests.add(goodRequest);

        return goodRequests;
    }

    /**
     * Создает объект LoanRequest и ассоциирует с ним заранее посчитанные значение процентной ставки
     */
    public static List<LoanRequestWithCalculatedAnswer> createLoanRequestsWithCalculatedAnswer()
    {
        IMortgageValidationRulesProvider mValidationRulesProvider = DataProviders.createMortgageValidationRulesProvider();

        int validApartmentPrice = mValidationRulesProvider.getMinLoanAmount() + 1;
        int validPaymentPeriod = mValidationRulesProvider.getMinPaymentPeriodYears() + 1;
        int validAge = mValidationRulesProvider.getMinAge() + 1;

        List<LoanRequestWithCalculatedAnswer> goodRequests = new ArrayList<>();
        LoanRequest goodRequest = null;

        goodRequest = new LoanRequest(validAge, LoanPurpose.ApartmentInSecondaryMarket,true,true,true,validApartmentPrice, validPaymentPeriod);
        LoanRequestWithCalculatedAnswer loanRequestWithAnswer = new LoanRequestWithCalculatedAnswer(goodRequest, new BigDecimal(0.09), 45000, 354001, 12875);
        goodRequests.add(loanRequestWithAnswer);

        return goodRequests;
    }
}
