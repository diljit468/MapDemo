package com.example.mapdemo;

import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapdemo.Model.Favourite;
import com.example.mapdemo.util.SQLHelperClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    SQLHelperClass myDB;
    private RecyclerView recyclerView;
    private FavouriteAdapter favouriteAdapter;
    private List<Favourite> FavouriteArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        myDB = new SQLHelperClass(FavouriteActivity.this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());

        SQLHelperClass sqlHelperClass = new SQLHelperClass(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(FavouriteActivity.this));

        FavouriteArrayList.addAll(sqlHelperClass.getAllNotes());
        favouriteAdapter = new FavouriteAdapter(FavouriteActivity.this,FavouriteArrayList,s.toString());
        recyclerView.setAdapter(favouriteAdapter);
    }




}
