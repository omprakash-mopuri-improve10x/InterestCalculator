package com.prakash.simpleinterest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.noowenz.customdatetimepicker.CustomDateTimePicker;
import com.prakash.simpleinterest.databinding.ActivitySimpleInterestBinding;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class SimpleInterestActivity extends AppCompatActivity {

    private ActivitySimpleInterestBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void handleCalculate() {
        binding.calculateBtn.setOnClickListener(view -> {
           findSimpleInterest();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void findSimpleInterest() {
        String amountStr = binding.amountTxt.getText().toString();
        String interestRateStr = binding.interestRateTxt.getText().toString();
        String fromDate = binding.fromDateCalendarTxt.getText().toString();
        String toDate = binding.toDateCalendarTxt.getText().toString();
        if (amountStr.equals("") == false && interestRateStr.equals("") == false && fromDate.equals("") == false && toDate.equals("") == false) {
            long amount = Integer.parseInt(amountStr);
            long interestRate = Integer.parseInt(interestRateStr);
            long numberOfDays = DateUtils.getDiffDays(fromDate, toDate);
            long interestAmount = amount * interestRate / 100 * numberOfDays / 30;
            long totalAmount = amount + interestAmount;
            Toast.makeText(this, String.valueOf(interestAmount), Toast.LENGTH_SHORT).show();
            binding.resultsLayout.setVisibility(View.VISIBLE);
            setData(fromDate, toDate, amount, interestAmount, totalAmount);
        } else {
            Toast.makeText(this, "Fill the details", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleClear() {
        binding.clearBtn.setOnClickListener(view -> {
            binding.amountTxt.setText("");
            binding.interestRateTxt.setText("");
            binding.fromDateCalendarTxt.setText("");
            binding.toDateCalendarTxt.setText("");
            binding.resultsLayout.setVisibility(View.INVISIBLE);
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
                        editText.setText(dayOfMonth + "-" + month + "-" +year);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setData(String fromDate, String toDate, long principleAmount, long interestAmount, long totalAmount) {
        binding.totalTimeTxt.setText(DateUtils.getDiffDate(fromDate, toDate));
        binding.principleAmountTxt.setText(String.valueOf(principleAmount));
        binding.interestAmountTxt.setText(String.valueOf(interestAmount));
        binding.totalAmountTxt.setText(String.valueOf(totalAmount));
    }
}