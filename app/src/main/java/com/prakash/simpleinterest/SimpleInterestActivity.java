package com.prakash.simpleinterest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.noowenz.customdatetimepicker.CustomDateTimePicker;
import com.prakash.simpleinterest.databinding.ActivitySimpleInterestBinding;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class SimpleInterestActivity extends AppCompatActivity {

    private ActivitySimpleInterestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySimpleInterestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handleFromDate();
        handleToDate();
        handleCalculate();
        handleClear();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    private void handleCalculate() {
        binding.calculateBtn.setOnClickListener(view -> {
           findSimpleInterest();
        });
    }

    private void findSimpleInterest() {
        String amountStr = binding.amountTxt.getText().toString();
        int amount = Integer.valueOf(amountStr);
        String interestRateStr = binding.interestRateTxt.getText().toString();
        int interestRate = Integer.valueOf(interestRateStr);
        //String numberOfMonthsStr = binding.numberOfMonthsTxt.getText().toString();
        //int numberOfMonths = Integer.valueOf(numberOfMonthsStr);
        //int calculate = amount * interestRate * numberOfMonths / 100;
        //String calculateStr = String.valueOf(calculate);
        //Toast.makeText(this, calculateStr, Toast.LENGTH_SHORT).show();
    }

    private void handleClear() {
        binding.clearBtn.setOnClickListener(view -> {
            binding.amountTxt.setText("");
            binding.interestRateTxt.setText("");
           // binding.numberOfMonthsTxt.setText("");
        });
    }

    private void showDatePickerDialog(EditText editText, String title) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(SimpleInterestActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update the selected date in the Calendar instance
                        calendar.set(year, month, dayOfMonth);
                        editText.setText(year + " " + month + " " + dayOfMonth);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setTitle(title);
        datePickerDialog.show();
    }

    private void handleFromDate() {
        binding.fromDateCalendarIb.setOnClickListener(view -> {
            showDatePickerDialog(binding.fromDateCalendarTxt, "Select from date");
        });
    }

    private void handleToDate() {
        binding.toDateCalendarIb.setOnClickListener(view -> {
            showDatePickerDialog(binding.toDateCalendarTxt, "Select to date");
        });
    }
}