package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView title_view;
    private Button due_button;
    private Button done_button;
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title_view = findViewById(R.id.title_view);
        due_button = findViewById(R.id.due_button);
        done_button = findViewById(R.id.done_button);
        btn1 = findViewById(R.id.btn1);

        Intent intent1 = new Intent(this, DueActivity.class);
        due_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);

            }
        });
        Intent intent2 = new Intent(this, DoneActivity.class);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);

            }
        });

        Intent intent3 = new Intent(this, Addtaskactivity.class);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent3);

            }
        });


    }


}