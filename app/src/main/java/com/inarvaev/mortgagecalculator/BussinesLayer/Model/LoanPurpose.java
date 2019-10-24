package com.inarvaev.mortgagecalculator.BussinesLayer.Model;

public enum     LoanPurpose {
    Unknown,
    ApartmentInPrimaryMarket,       // новостройка
    ApartmentInSecondaryMarket;     // вторичное жилье

    public static LoanPurpose indexOf(String value)
    {
        if (value.equalsIgnoreCase("Готовое жилье"))
            return  ApartmentInSecondaryMarket;
        else if (value.equalsIgnoreCase("Квартира в новостройке"))
            return ApartmentInPrimaryMarket;
        else
            return Unknown;
    }

    public static String indexOf(LoanPurpose value)
    {
        if (ApartmentInSecondaryMarket == value)
            return  "Готовое жилье";
        else if (value == ApartmentInPrimaryMarket)
            return "Квартира в новостройке";
        else
            return null;
    }
}
