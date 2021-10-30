package com.nhatminhnguyen.puzzleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class IntroductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        ImageButton backBtn = findViewById(R.id.closeButton);
        TextView introGame = findViewById(R.id.textView3);
        introGame.setMovementMethod(new ScrollingMovementMethod());
        this.getSupportActionBar().hide();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}