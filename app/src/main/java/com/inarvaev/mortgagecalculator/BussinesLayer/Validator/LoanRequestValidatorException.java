package com.inarvaev.mortgagecalculator.BussinesLayer.Validator;

public class LoanRequestValidatorException extends Exception
{
    public LoanRequestValidatorException(String msg)
    {
        super(msg);
    }

    public LoanRequestValidatorException(String msg, Exception cause)
    {
        super(msg, cause);
    }
}
