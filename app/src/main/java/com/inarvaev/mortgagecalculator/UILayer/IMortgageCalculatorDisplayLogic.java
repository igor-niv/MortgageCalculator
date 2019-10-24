package com.inarvaev.mortgagecalculator.UILayer;

import com.inarvaev.mortgagecalculator.BussinesLayer.Model.LoanRequest;

interface IMortgageCalculatorDisplayLogic {
    /**
     * Отобразить сообщение на экранее телефона
     * @param msg  текст сообщения
     */
    void displayMessage(String msg);
}