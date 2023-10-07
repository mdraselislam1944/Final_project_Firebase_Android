package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteAlarm extends AppCompatActivity implements View.OnClickListener{
    private MyHelperDB dbHelper;

    private Button deleteBtnId;
    private EditText DeleteId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_alarm);
        dbHelper = new MyHelperDB(this);
        deleteBtnId=findViewById(R.id.deleteBtn);
        DeleteId=findViewById(R.id.deleteId);
        deleteBtnId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String rowIdToDeleteString = DeleteId.getText().toString();

        if (!rowIdToDeleteString.isEmpty()) {
            try {
                int rowIdToDelete = Integer.parseInt(rowIdToDeleteString);
                if (v.getId() == R.id.deleteBtn) {
                    if (dbHelper.deleteData(rowIdToDelete)) {
                        showToast("Data deleted successfully");

                        Intent intent = new Intent(DeleteAlarm.this, ShowActivity.class);
                        startActivity(intent);

                    } else {
                        showToast("Failed to delete data. Row not found.");
                    }
                }
            } catch (NumberFormatException e) {
                showToast("Invalid input. Please enter a valid integer.");
                e.printStackTrace(); // Log the exception or handle it accordingly
            }
        } else {
            showToast("Please enter a row ID to delete.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}