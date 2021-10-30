package com.nhatminhnguyen.puzzleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.nhatminhnguyen.puzzleapp.Class.Database;
import com.nhatminhnguyen.puzzleapp.Class.GridImageAdapter;

import java.util.ArrayList;

public class ChooseImagesActivity extends AppCompatActivity {
    GridView gridListView;
    ArrayList<String> textList = new ArrayList<>();
    ArrayList<Integer> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_images);
        ImageButton backBtn = findViewById(R.id.closeButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });;
        gridListView = findViewById(R.id.gridListImage);
        loadDataToArray();
        GridImageAdapter imageAdapter = new GridImageAdapter(this, textList, imageList);
        gridListView.setAdapter(imageAdapter);
        gridListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), textList.get(position), Toast.LENGTH_SHORT).show();
                //making navigation
                Intent animalPicsIntent = new Intent(ChooseImagesActivity.this, GameActivity.class);
                animalPicsIntent.putExtra("imageToPlay",textList.get(position));
                startActivity(animalPicsIntent);
            }
        });
    }

    void loadDataToArray() {
        textList.add(Database.babyPandaName);
        textList.add(Database.grayBearName);
        textList.add(Database.pandaName);
        textList.add(Database.polarBearName);
        textList.add(Database.redPandaName);
        textList.add(Database.tibetanBearName);
        textList.add(Database.ursidaeName);

        imageList.add(R.drawable.babypanda);
        imageList.add(R.drawable.graybear);
        imageList.add(R.drawable.panda);
        imageList.add(R.drawable.polarbear);
        imageList.add(R.drawable.redpanda);
        imageList.add(R.drawable.tibetanbear);
        imageList.add(R.drawable.ursidae);
    }
}