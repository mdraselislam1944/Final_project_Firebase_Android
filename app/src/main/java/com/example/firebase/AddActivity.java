package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    MyDatabase myDatabase;

    private Button saveBtn;
    private EditText dateEditText, messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myDatabase=new MyDatabase(this);
        myDatabase.getWritableDatabase();
        saveBtn = findViewById(R.id.saveBtnDate);
        dateEditText = findViewById(R.id.AddAlarmDate);
        messageEditText = findViewById(R.id.addAlarmMessage);

        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.saveBtnDate) {
            String message = messageEditText.getText().toString();
            String selectedDate = dateEditText.getText().toString();

            if (!selectedDate.isEmpty() && !message.isEmpty()) {
                // Format the selected date
                String formattedDate = formatDate(selectedDate);
                long rowId=myDatabase.InsertData(message,formattedDate);
                if(rowId==-1){
                    Toast.makeText(getApplicationContext(),"data save is not successfully",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext()," row "+rowId+" data save is successfully",Toast.LENGTH_LONG).show();
                    messageEditText.setText("");
                    dateEditText.setText("");
                    Intent intent=new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "Please enter both message and date.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showDatePickerDialog(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        dateEditText.setText(selectedDate);
                    }
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }

    private String formatDate(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

            return outputFormat.format(inputFormat.parse(inputDate));
        } catch (ParseException e) {
            e.printStackTrace();
            return inputDate; // Return the original date if there's an error
        }
    }
}