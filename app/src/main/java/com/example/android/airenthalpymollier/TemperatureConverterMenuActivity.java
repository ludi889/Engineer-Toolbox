package com.example.android.airenthalpymollier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TemperatureConverterMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature_converter_menu);
    }
    /**
     * This method set the content view to celsius converter menu
     */
    public void setCelsiusConverterMenu(View view) {
        Intent setCelsiusConverterMenuIntent = new Intent(this,CelsiusConverterActivity.class);
        startActivity(setCelsiusConverterMenuIntent);
    }
    public void setFahrenheitConverterMenu(View view) {
        Intent setFahrenheitConverterMenuIntent = new Intent(this,FahrenheitConverterActivity.class);
        startActivity(setFahrenheitConverterMenuIntent);
    }
    public void setKelvinConverterMenu(View view) {
        Intent setKelvinConverterMenuIntent = new Intent(this,KelvinConverterActivity.class);
        startActivity(setKelvinConverterMenuIntent);
    }
    public void setRankineConverterMenu(View view) {
        Intent setRankineConverterMenuIntent = new Intent(this,RankineConverterActivity.class);
        startActivity(setRankineConverterMenuIntent);
    }

}
