package com.inarvaev.mortgagecalculator;

import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanPurpose;
import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanRequest;
import com.inarvaev.mortgagecalculator.BussinesLayer.Validator.LoanRequestValidator;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Тест валидации запросов на кредит
 */
public class LoanRequestValidatorTests {

    @Test
    public void validateTest()
    {
        // arrange

        List<LoanRequest> badRequests = LoanRequestsHelper.createBadLoanRequests();
        List<LoanRequest> goodRequests = LoanRequestsHelper.createGoodLoanRequests();

        // act && assert

        for (LoanRequest loanRequest : badRequests) {

            String age = String.valueOf(loanRequest.getAge());
            String loanPurpose = LoanPurpose.indexOf(loanRequest.getLoanPurpose());
            String apartmentPrice = String.valueOf(loanRequest.getApartmentPrice());
            String paymentPeriod = String.valueOf(loanRequest.getPaymentPeriod());
            boolean isHouseBuiltBefore1950Year = loanRequest.isHouseBuiltBefore1950Year();

            LoanRequestValidator validator = new LoanRequestValidator(age, loanPurpose, apartmentPrice, paymentPeriod, isHouseBuiltBefore1950Year);
            Assert.assertFalse(validator.validate());
        }

        for (LoanRequest loanRequest : goodRequests) {

            String age = String.valueOf(loanRequest.getAge());
            String loanPurpose = LoanPurpose.indexOf(loanRequest.getLoanPurpose());
            String apartmentPrice = String.valueOf(loanRequest.getApartmentPrice());
            String paymentPeriod = String.valueOf(loanRequest.getPaymentPeriod());
            boolean isHouseBuiltBefore1950Year = loanRequest.isHouseBuiltBefore1950Year();

            LoanRequestValidator validator = new LoanRequestValidator(age, loanPurpose, apartmentPrice, paymentPeriod, isHouseBuiltBefore1950Year);
            Assert.assertTrue(validator.validate());
        }
    }
}



















