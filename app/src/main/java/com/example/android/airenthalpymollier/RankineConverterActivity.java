package com.example.android.airenthalpymollier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RankineConverterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rankine_converter);
    }
    /**
     * This method submit Kelvin Converter Equations, in rankine converter
     */
    public void submitRankineConverterEquation(View view) {
        EditText inputTemperatureRankineConverterEditText = (EditText) findViewById(R.id.input_temperature_rankine_converter);
        String inputTemperatureRankineConverterString = inputTemperatureRankineConverterEditText.getText().toString();
        if (inputTemperatureRankineConverterString.isEmpty()) {
            Toast.makeText(this, "You have to input temperature value", Toast.LENGTH_SHORT).show();
        }
        double inputTemperatureRankineConverter = Double.valueOf(inputTemperatureRankineConverterString);
        double rankineToCelsius = rankineToCelsiusConvert(inputTemperatureRankineConverter);
        double rankineToFahrenheit = rankineFahrenheitConvert(inputTemperatureRankineConverter);
        double rankineToKelvin = rankineKelvinConvert(inputTemperatureRankineConverter);
        String rankineToCelsiusValue = Double.toString(rankineToCelsius);
        String rankineToFahrenheitValue = Double.toString(rankineToFahrenheit);
        String rankineToKelvinValue = Double.toString(rankineToKelvin);

        TextView rankineConverterResultView = (TextView) findViewById(R.id.rankine_converter_result);
        String rankineConverterResult = rankineToCelsiusValue + " °C";
        rankineConverterResult += System.lineSeparator() + rankineToFahrenheitValue + " °F";
        rankineConverterResult += System.lineSeparator() + rankineToKelvinValue + " K";
        rankineConverterResultView.setText(rankineConverterResult);
    }

    /**
     * This method is used to convert Rankine to Celsius
     */
    public double rankineToCelsiusConvert(double inputTemperatureRankineConverter) {

        return (inputTemperatureRankineConverter - 491.67) * 0.55555555555555556;
    }

    /**
     * This method is used to convert Rankine to Fahrenheites
     */
    public double rankineFahrenheitConvert(double inputTemperatureRankineConverter) {
        return inputTemperatureRankineConverter - 459.67;
    }

    /**
     * This method is used to convert Rankine to Rankines
     */
    public double rankineKelvinConvert(double inputTemperatureRankineConverter) {
        return inputTemperatureRankineConverter * 0.55555555555555556;

    }
}
