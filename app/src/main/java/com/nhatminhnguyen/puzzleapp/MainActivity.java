package com.nhatminhnguyen.puzzleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView questionButton;
    ImageView playButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_main);


        playButton = findViewById(R.id.buttonplay);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "play", Toast.LENGTH_LONG).show();
                Intent startIntent = new Intent(MainActivity.this,ChooseImagesActivity.class);
                startActivity(startIntent);
            }


        });

        questionButton = findViewById(R.id.quesButtton);
        questionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"instruction",Toast.LENGTH_LONG).show();
                Intent startIntent = new Intent(MainActivity.this,IntroductionActivity.class);
                startActivity(startIntent);
            }
        });

    }


    };
