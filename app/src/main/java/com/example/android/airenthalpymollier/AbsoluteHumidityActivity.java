package com.example.android.airenthalpymollier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AbsoluteHumidityActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.absolute_humidity_calculator);

        Button submitAbsoluteHumidityButton = (Button) findViewById(R.id.submit_humidity_button);
        submitAbsoluteHumidityButton.setOnClickListener(this);
    }

    /**
     * This method is used to calculate Absolute Humidity and return it back to submitAbsoluteHumidityEquation
     */
    private double calculateAbsoluteHumidity(double saturatedAirPressure, double relativeHumidity, double airPressure) {
        return (0.622 * relativeHumidity * saturatedAirPressure) / (airPressure - (relativeHumidity * saturatedAirPressure));

    }

    /**
     * This method is used to get data from EdiText field of Humidity Equation data input, and then give out the result of enthalpy calculations
     */
    @Override
    public void onClick(View v) {

        //This part of method is taking data from Saturated Air Pressure EditText field, get it to string, and then parse to integer
        EditText saturatedAirPressureEditText = (EditText) findViewById(R.id.saturated_air_pressure);
        if (saturatedAirPressureEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "You have to input Saturated Air Pressure value", Toast.LENGTH_SHORT).show();
            return;
        }
        double saturatedAirPressure = Double.valueOf(saturatedAirPressureEditText.getText().toString().trim());
        //This part of method is taking data from Relative humidity EditText field, get it to string, and then parse to integer
        EditText relativeHumidityEditText = (EditText) findViewById(R.id.relative_humidity);
        if (relativeHumidityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "You have to input Relative Humidity value", Toast.LENGTH_SHORT).show();
            return;
        }
        double relativeHumidity = Double.valueOf(relativeHumidityEditText.getText().toString().trim());

        //This part of method is taking data from Air Pressure EditText field, get it to string, and then parse to integer
        EditText airPressureEditText = (EditText) findViewById(R.id.air_pressure);
        if (airPressureEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "You have to input Air Pressure value", Toast.LENGTH_SHORT).show();
            return;
        }
        double airPressure = Double.valueOf(airPressureEditText.getText().toString().trim());

        //calculating absoluteHumidity
        String absoluteHumidity = Double.toString(calculateAbsoluteHumidity(saturatedAirPressure, relativeHumidity, airPressure)) + "kgH2O/kgdryair";

        //Displaying result
        TextView absoluteHumidityResult = (TextView) findViewById(R.id.humidity_result);
        absoluteHumidityResult.setText(absoluteHumidity);


    }
}
