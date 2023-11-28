package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DoneActivity extends AppCompatActivity {
    private ListView list2;

    private Button del_btn1;
    private Button clear_btn1;
    private TextView txt2;

    private ArrayList<Task> arr1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);


        del_btn1 = findViewById(R.id.del_btn1);
        clear_btn1 = findViewById(R.id.clear_btn1);
        txt2 = findViewById(R.id.txt2);

        loadData();
        Intent intent = getIntent();

        String msg = intent.getStringExtra("done");

        if(msg != null) {
            arr1.add(new Task(msg, true));


        }
        saveDueData();


        ArrayAdapter<Task> listAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, arr1);
        list2 = (ListView) findViewById(R.id.list2);
        list2.setAdapter(listAdapter);


        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {

                del_btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arr1.remove(position);
                        ((ArrayAdapter<Task>) list2.getAdapter()).notifyDataSetChanged();
                        saveDueData();
                    }
                });

            }
        };

        clear_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr1.clear();
                ((ArrayAdapter<Task>) list2.getAdapter()).notifyDataSetChanged();
                saveDueData();
            }
        });

        list2.setOnItemClickListener(itemClickListener);


    }


    private void saveDueData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arr1);
        editor.putString("done list", json);
        editor.apply();

    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("done list", null);
        Type type = new TypeToken<ArrayList<Task>>(){}.getType();
        arr1 = gson.fromJson(json, type);

        if(arr1 == null){
            arr1 = new ArrayList<>();
        }

    }
}