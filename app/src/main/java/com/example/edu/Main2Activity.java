package com.example.edu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    RecyclerView rv;
    MyDataBase db;
    private static final String TAG = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rv = findViewById(R.id.rv_main);
        rv.hasFixedSize();

        db = new MyDataBase(this);
        ArrayList<String> qu = new ArrayList<>();
        qu.add("what is your name?");
        qu.add("what is your favorite sport?");
        qu.add("How many brothers you have ?");
        qu.add("what is your department ?");
        qu.add("what is your favorite job ?");
        qu.add("what is your favorite subject ?");


        ArrayList<String> ans = new ArrayList<>();
        ans.add("my name is");
        ans.add("my favourite sport is");
        ans.add("I have");
        ans.add("my department is");
        ans.add("my favourite job is");
        ans.add("my favourite subject is");

        db.insert(qu, ans);




        RecyclerViewAdapter adapter = new RecyclerViewAdapter(qu, new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(String name,int pos) {
                List<String> answer = db.getAnswer();
                Log.i(TAG, "onCreate: " + answer.get(pos));

                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("x", name);
                intent.putExtra("y", answer.get(pos));

                startActivity(intent);
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}
