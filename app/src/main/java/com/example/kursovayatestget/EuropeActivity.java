package com.example.kursovayatestget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class EuropeActivity extends AppCompatActivity {
    private MapView mapview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_europe);
    }

    public void onButtonReturnStart(View view){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
    public void onButtonChech(View view){
        Intent intent = new Intent(this, ChechActivity.class);
        startActivity(intent);
    }
    public void onButtonGerman(View view){
        Intent intent = new Intent(this, GermanAActivity.class);
        startActivity(intent);
    }


}