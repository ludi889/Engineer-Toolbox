package com.example.android.airenthalpymollier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FahrenheitConverterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fahrenheit_converter);
    }
    /**
     * This method submit Fahrenheit Converter Equations, in fahrenheit converter
     */
    public void submitFahrenheitConverterEquation(View view) {
        EditText inputTemperatureFahrenheitConverterEditText = (EditText) findViewById(R.id.input_temperature_fahrenheit_converter);
        String inputTemperatureFahrenheitConverterString = inputTemperatureFahrenheitConverterEditText.getText().toString();
        if (inputTemperatureFahrenheitConverterString.isEmpty()) {
            Toast.makeText(this, "You have to input temperature value", Toast.LENGTH_SHORT).show();
        }
        double inputTemperatureFahrenheitConverter = Double.valueOf(inputTemperatureFahrenheitConverterString);
        double fahrenheitToCelsius = fahrenheitToCelsiusConvert(inputTemperatureFahrenheitConverter);
        double fahrenheitToKelvin = fahrenheitKelvinConvert(inputTemperatureFahrenheitConverter);
        double fahrenheitToRankine = fahrenheitRankineConvert(inputTemperatureFahrenheitConverter);
        String fahrenheitToCelsiusValue = Double.toString(fahrenheitToCelsius);
        String fahrenheitToKelvinValue = Double.toString(fahrenheitToKelvin);
        String fahrenheitToRankineValue = Double.toString(fahrenheitToRankine);

        TextView fahrenheitConverterResultView = (TextView) findViewById(R.id.fahrenheit_converter_result);
        String fahrenheitConverterResult = fahrenheitToCelsiusValue + " °C";
        fahrenheitConverterResult += System.lineSeparator() + fahrenheitToKelvinValue + " K";
        fahrenheitConverterResult += System.lineSeparator() + fahrenheitToRankineValue + " °R";
        fahrenheitConverterResultView.setText(fahrenheitConverterResult);
    }

    /**
     * This method is used to convert Fahrenheit to Celsius
     */
    public double fahrenheitToCelsiusConvert(double inputTemperatureFahrenheitConverter) {

        return (inputTemperatureFahrenheitConverter-32) * 0.5556;
    }

    /**
     * This method is used to convert Fahrenheit to Rankinees
     */
    public double fahrenheitKelvinConvert(double inputTemperatureFahrenheitConverter) {
        return (inputTemperatureFahrenheitConverter + 459.67) * 0.5556;
    }

    /**
     * This method is used to convert Fahrenheit to Rankines
     */
    public double fahrenheitRankineConvert(double inputTemperatureFahrenheitConverter) {
        return inputTemperatureFahrenheitConverter + 459.67;

    }

}
