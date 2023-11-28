package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DueActivity extends AppCompatActivity {

    private ListView list1;
    private Button ch_btn;
    private Button del_btn;
    private Button clear_btn;
    private TextView txt1;

   private ArrayList<Task> ar1 = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_due);


        ch_btn = findViewById(R.id.ch_btn);
        del_btn = findViewById(R.id.del_btn);
        clear_btn = findViewById(R.id.clear_btn);
        txt1 = findViewById(R.id.txt1);
        loadData();
        Intent intent = getIntent();

        String msg = intent.getStringExtra("due data");

        if(msg != null) {
            ar1.add(new Task(msg, false));


        }
        saveData();


        ArrayAdapter<Task> listAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, ar1);
        list1 = (ListView) findViewById(R.id.list1);
        list1.setAdapter(listAdapter);

        Intent intent1 = new Intent(this, DoneActivity.class);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                ch_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        intent1.putExtra("done",ar1.get(position).toString() );
                        Toast toast = Toast.makeText(getApplicationContext(), "Task added to done list", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0); // Set toast position
                        toast.show();
                        ar1.remove(position);
                        ((ArrayAdapter<Task>) list1.getAdapter()).notifyDataSetChanged();
                        saveData();
                        startActivity(intent1);

                    }
                });
                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ar1.remove(position);
                        ((ArrayAdapter<Task>) list1.getAdapter()).notifyDataSetChanged();
                        saveData();
                    }
                });






            }
        };
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ar1.clear();
                ((ArrayAdapter<Task>) list1.getAdapter()).notifyDataSetChanged();
                saveData();
            }
        });

        list1.setOnItemClickListener(itemClickListener);



    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ar1);
        editor.putString("due list", json);
        editor.apply();

    }


    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("due list", null);
        Type type = new TypeToken<ArrayList<Task>>(){}.getType();
        ar1 = gson.fromJson(json, type);

        if(ar1 == null){
            ar1 = new ArrayList<>();
        }

    }

}