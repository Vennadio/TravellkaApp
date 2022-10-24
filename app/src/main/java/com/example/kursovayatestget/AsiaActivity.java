package com.example.kursovayatestget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AsiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asia);
    }
    public void onButtonReturnStart(View view){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
    public void onMyButtonTailand(View view)
    {
        Intent intent = new Intent(this, TailandActivity.class);
        startActivity(intent);
    }
    public void onButtonReturnTurkey(View view){
        Intent intent = new Intent(this, TurkeyActivity.class);
        startActivity(intent);
    }
}