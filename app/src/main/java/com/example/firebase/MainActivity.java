package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private GridView gridView;
    String[] HomeActivityAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HomeActivityAll=getResources().getStringArray(R.array.HomeNaveBar);
        gridView=findViewById(R.id.gridViewId);

        CustomAdapter adapter=new CustomAdapter(this,HomeActivityAll);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = HomeActivityAll[position];
                if ("Profile".equals(value)) {
                    Intent intent = new Intent(MainActivity.this, SignIn.class);
                    startActivity(intent);
                }
                if("Calender".equals(value)){
                    Intent intent = new Intent(MainActivity.this, Calender.class);
                    startActivity(intent);
                }
                if("Add Activity".equals(value)){
                    Intent intent = new Intent(MainActivity.this, AddActivity.class);
                    startActivity(intent);
                }
                if("Show Activity".equals(value)){
                    Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}