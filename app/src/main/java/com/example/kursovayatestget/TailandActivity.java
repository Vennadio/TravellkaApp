package com.example.kursovayatestget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class TailandActivity extends AppCompatActivity {
    private MapView mapview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("bd5f05c0-aed1-4d69-a8d6-f56c4679a9ba");
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_tailand);
        mapview = (MapView) findViewById(R.id.mapview);
        mapview.getMap().move(
                new CameraPosition(new Point(13.7017612876768, 100.53829941044332), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
    }

    @Override
    protected void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }
    public void onButtonReturnStart(View view){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
    public void onButtonBuyPlane(View view){
        String url = "https://www.csa.cz/ru-ru/";
        Intent openPage= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(openPage);
    }
    public void onButtonBuyHottel(View view){
        String url = "https://www.lastminute.com/hotels/city/hotels-in-Prague-CZ";
        Intent openPage= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(openPage);
    }
}