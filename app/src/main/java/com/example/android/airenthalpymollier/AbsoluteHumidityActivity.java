package com.example.android.airenthalpymollier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.airenthalpymollier.R.id.Air_Pressure;
import static com.example.android.airenthalpymollier.R.id.Relative_Humidity;

public class AbsoluteHumidityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.absolute_humidity);
    }
    /**
     * This method is used to get data from EdiText field of Humidity Equation data input, and then give out the result of enthalpy calculations
     */
    public void submitAbsoluteHumidityEquation(View view) {
        //This part of method is taking data from Saturated Air Pressure EditText field, get it to string, and then parse to integer
        EditText saturatedAirPressureEditText = (EditText) findViewById(R.id.Saturated_Air_Pressure);
        String saturatedAirPressureString = saturatedAirPressureEditText.getText().toString();
        if (saturatedAirPressureString.isEmpty()) {
            Toast.makeText(this, "You have to input Saturated Air Pressure value", Toast.LENGTH_SHORT).show();
            return;
        }
        double saturatedAirPressure = Double.valueOf(saturatedAirPressureString);
        if (saturatedAirPressure == 0) {
            Toast.makeText(this, "Saturated Air Pressure cannot be 0", Toast.LENGTH_SHORT).show();
            return;
        }

        //This part of method is taking data from Relative humidity EditText field, get it to string, and then parse to integer
        EditText relativeHumidityEditText = (EditText) findViewById(Relative_Humidity);
        String relativeHumidityString = relativeHumidityEditText.getText().toString();
        if (relativeHumidityString.isEmpty()) {
            Toast.makeText(this, "You have to input Relative Humidity value", Toast.LENGTH_SHORT).show();
            return;
        }
        double relativeHumidity = Double.valueOf(relativeHumidityString);

        //This part of method is taking data from Air Pressure EditText field, get it to string, and then parse to integer
        EditText airPressureEditText = (EditText) findViewById(Air_Pressure);
        String airPressureString = airPressureEditText.getText().toString();
        if (airPressureString.isEmpty()) {
            Toast.makeText(this, "You have to input Air Pressure value", Toast.LENGTH_SHORT).show();
            return;
        }
        double airPressure = Double.valueOf(airPressureString);
        if (airPressure == 0) {
            Toast.makeText(this, "Air Pressure cannot be 0", Toast.LENGTH_SHORT).show();
            return;
        }
        //calculating absoluteHumidity
        double absoluteHumidity = calculateAbsoluteHumidity(saturatedAirPressure, relativeHumidity, airPressure);
        String absoluteHumidityValue = Double.toString(absoluteHumidity) + "kgH2O/kgdryair";

        //Displaying result
        TextView absoluteHumidityResult = (TextView) findViewById(R.id.humidity_result);
        absoluteHumidityResult.setText(absoluteHumidityValue);

    }

    /**
     * This method is used to calculate Absolute Humidity and return it back to submitAbsoluteHumidityEquation
     */
    private double calculateAbsoluteHumidity(double saturatedAirPressure, double relativeHumidity, double airPressure) {
        return (0.622 * relativeHumidity * saturatedAirPressure) / (airPressure - (relativeHumidity * saturatedAirPressure));

    }

}
