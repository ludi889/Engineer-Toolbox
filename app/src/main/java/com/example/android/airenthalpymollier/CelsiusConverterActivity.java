package com.example.android.airenthalpymollier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CelsiusConverterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.celsius_converter);
    }
    /**
     * This method submit Celsius Converter Equations, in celsius converter
     */
    public void submitCelsiusConverterEquation(View view) {
        EditText inputTemperatureCelsiusConverterEditText = (EditText) findViewById(R.id.input_temperature_celsius_converter);
        String inputTemperatureCelsiusConverterString = inputTemperatureCelsiusConverterEditText.getText().toString();
        if (inputTemperatureCelsiusConverterString.isEmpty()) {
            Toast.makeText(this, "You have to input temperature value", Toast.LENGTH_SHORT).show();
        }
        double inputTemperatureCelsiusConverter = Double.valueOf(inputTemperatureCelsiusConverterString);
        double celsiusToFahrenheit = celsiusToFahrenheitConvert(inputTemperatureCelsiusConverter);
        double celsiusToKelvin = celsiusKelvinConvert(inputTemperatureCelsiusConverter);
        double celsiusToRankine = celsiusRankineConvert(inputTemperatureCelsiusConverter);
        String celsiusToFahrenheitValue = Double.toString(celsiusToFahrenheit);
        String celsiusToKelvinValue = Double.toString(celsiusToKelvin);
        String celsiusToRankineValue = Double.toString(celsiusToRankine);

        TextView celsiusConverterResultView = (TextView) findViewById(R.id.celsius_converter_result);
        String celsiusConverterResult = celsiusToFahrenheitValue + " °F";
        celsiusConverterResult += System.lineSeparator() + celsiusToKelvinValue + " K";
        celsiusConverterResult += System.lineSeparator() + celsiusToRankineValue + " °R";
        celsiusConverterResultView.setText(celsiusConverterResult);
    }

    /**
     * This method is used to convert Celsiuses to Fahrenheites
     */
    public double celsiusToFahrenheitConvert(double inputTemperatureCelsiusConverter) {

        return (inputTemperatureCelsiusConverter * 1.8) + 32;
    }

    /**
     * This method is used to convert Celsiuses to Rankinees
     */
    public double celsiusKelvinConvert(double inputTemperatureCelsiusConverter) {
        return inputTemperatureCelsiusConverter + 273.15;
    }

    /**
     * This method is used to convert Celsiuses to Rankines
     */
    public double celsiusRankineConvert(double inputTemperatureCelsiusConverter) {
        return (inputTemperatureCelsiusConverter + 273.15) * 1.8;

    }
}
