package com.inarvaev.mortgagecalculator.UILayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.inarvaev.mortgagecalculator.R;

public class MortgageCalculatorActivity extends AppCompatActivity implements IMortgageCalculatorDisplayLogic {

    private Spinner mLoanPurposeSpinner;
    private Switch mBankEmployeeSwitch;
    private Switch mWoodFlooringSwitch;
    private Switch mYearBuildingSwitch;
    private EditText mAgesEdit;
    private EditText mApartmentPriceEdit;
    private EditText mPaymentPeriodEdit;

    /**
     * Интерактор используется через интерфейс IMortgageCalculatorBusinessLogic
     */
    private IMortgageCalculatorBusinessLogic mInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAgesEdit = (EditText) findViewById(R.id.ages_id);
        mApartmentPriceEdit = (EditText) findViewById(R.id.apartment_price_id);
        mBankEmployeeSwitch = (Switch) findViewById(R.id.bank_employee_id);
        mWoodFlooringSwitch = (Switch) findViewById(R.id.wood_flooring_id);
        mYearBuildingSwitch = (Switch) findViewById(R.id.year_building_id);
        mPaymentPeriodEdit = (EditText) findViewById(R.id.payment_period);
        mLoanPurposeSpinner = (Spinner) findViewById(R.id.loan_purpose_list_id);

        IMortgageCalculatorPresenter presenter = new IMortgageCalculatorPresenter(this);
        mInteractor = new MortgageCalculatorInteractor(presenter);
    }

    /**
     * Расчитать ставку по кредиту
     */

    public void onCalculateLoanRate(View view) {

        hideKeyboard();

        String loanPurpose = mLoanPurposeSpinner.getSelectedItem().toString();
        boolean isBankEmployee = mBankEmployeeSwitch.isChecked();
        boolean isWoodFlooring = mWoodFlooringSwitch.isChecked();
        boolean houseBuiltBefore1950Year = mYearBuildingSwitch.isChecked();
        String apartmentPrice = mApartmentPriceEdit.getText().toString().trim();
        String paymentPeriod = mPaymentPeriodEdit.getText().toString().trim();
        String age = mAgesEdit.getText().toString().trim();

        mInteractor.calculateLoanRate(age, loanPurpose, apartmentPrice, paymentPeriod,
                isWoodFlooring, houseBuiltBefore1950Year, isBankEmployee);
    }

    /**
     * Прячет виртуальную клавиатуру
     */
    public void hideKeyboard() {
        View view = findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Реализация интерфейса IMortgageCalculatorDisplayLogic
     */

    @Override
    public void displayMessage(String msg) {

        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("")
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton("ОК", null);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
