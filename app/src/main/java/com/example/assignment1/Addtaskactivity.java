package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Addtaskactivity extends AppCompatActivity {
    private EditText task_field;

    private Button add_btn;

   public static ArrayList<Task> due = new ArrayList<>();
   public static ArrayList<Task> done = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask_activity);

        task_field = findViewById(R.id.task_field);
        add_btn = findViewById(R.id.add_btn);

        Intent intent1 = new Intent(this, DueActivity.class);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = task_field.getText().toString().trim();
                if(!text.isEmpty()) {
                    due.add(new Task(task_field.getText().toString(), false));
                    intent1.putExtra("due data", task_field.getText().toString());
                    task_field.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), "Task added to due list", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0); // Set toast position
                    toast.show();
                    startActivity(intent1);
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "text field is empty!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0); // Set toast position
                    toast.show();
                }


            }
        });


    }
}