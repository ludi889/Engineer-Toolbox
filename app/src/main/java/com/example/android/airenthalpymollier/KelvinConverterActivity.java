package com.example.android.airenthalpymollier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class KelvinConverterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelvin_converter);
    }
    /**
     * This method submit Kelvin Converter Equations, in kelvin converter
     */
    public void submitKelvinConverterEquation(View view) {
        EditText inputTemperatureKelvinConverterEditText = (EditText) findViewById(R.id.input_temperature_kelvin_converter);
        String inputTemperatureKelvinConverterString = inputTemperatureKelvinConverterEditText.getText().toString();
        if (inputTemperatureKelvinConverterString.isEmpty()) {
            Toast.makeText(this, "You have to input temperature value", Toast.LENGTH_SHORT).show();
        }
        double inputTemperatureKelvinConverter = Double.valueOf(inputTemperatureKelvinConverterString);
        double kelvinToCelsius = kelvinToCelsiusConvert(inputTemperatureKelvinConverter);
        double kelvinToFahrenheit = kelvinFahrenheitConvert(inputTemperatureKelvinConverter);
        double kelvinToRankine = kelvinRankineConvert(inputTemperatureKelvinConverter);
        String kelvinToCelsiusValue = Double.toString(kelvinToCelsius);
        String kelvinToFahrenheitValue = Double.toString(kelvinToFahrenheit);
        String kelvinToRankineValue = Double.toString(kelvinToRankine);

        TextView kelvinConverterResultView = (TextView) findViewById(R.id.kelvin_converter_result);
        String kelvinConverterResult = kelvinToCelsiusValue + " °C";
        kelvinConverterResult += System.lineSeparator() + kelvinToFahrenheitValue + " °F";
        kelvinConverterResult += System.lineSeparator() + kelvinToRankineValue + " °R";
        kelvinConverterResultView.setText(kelvinConverterResult);
    }

    /**
     * This method is used to convert Kelvin to Celsius
     */
    public double kelvinToCelsiusConvert(double inputTemperatureKelvinConverter) {

        return inputTemperatureKelvinConverter - 273.15;
    }

    /**
     * This method is used to convert Kelvin to Fahrenheites
     */
    public double kelvinFahrenheitConvert(double inputTemperatureKelvinConverter) {
        return inputTemperatureKelvinConverter * 1.8 - 459.67;
    }

    /**
     * This method is used to convert Kelvin to Kelvins
     */
    public double kelvinRankineConvert(double inputTemperatureKelvinConverter) {
        return inputTemperatureKelvinConverter * 1.8;

    }

}
