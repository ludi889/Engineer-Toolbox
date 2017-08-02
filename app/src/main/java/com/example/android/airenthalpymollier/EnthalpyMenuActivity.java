package com.example.android.airenthalpymollier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnthalpyMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enthalpy_menu);
    }

    /**
     * This method is used to get data from EditText field of Enthalpy data input, and then give out the result of enthalpy calculations
     */
    public void submitEnthalpyEquation(View view) {
        //This part of method is taking data from Specific heat of dry air EditText field, get it to string, and then parse to integer
        EditText specificHeatOfDryAirEditText = (EditText) findViewById(R.id.Specific_Heat_Dry_Air);
        String specificHeatOfDryAirString = specificHeatOfDryAirEditText.getText().toString();
        if (specificHeatOfDryAirString.isEmpty()) {
            Toast.makeText(this, "You have to input Specific Heat of Dry Air value", Toast.LENGTH_SHORT).show();
            return;
        }
        double specificHeatOfDryAir = Double.valueOf(specificHeatOfDryAirString);
        if (specificHeatOfDryAir == 0) {
            Toast.makeText(this, "Specific Heat of Dry Air cannot be 0", Toast.LENGTH_SHORT).show();
            return;
        }

        //This part of method is taking data from Heat of vaporization EditText field, get it to string, and then parse to integer
        EditText heatOfVaporizationEditText = (EditText) findViewById(R.id.Heat_of_Vaporization);
        String heatOfVaporizationString = heatOfVaporizationEditText.getText().toString();
        if (heatOfVaporizationString.isEmpty()) {
            Toast.makeText(this, "You have to input Heat of Vaporization Value", Toast.LENGTH_SHORT).show();
            return;
        }
        double heatOfVaporization = Double.valueOf(heatOfVaporizationString);
        if (heatOfVaporization == 0) {
            Toast.makeText(this, "Heat of Vaporization cannot be 0", Toast.LENGTH_SHORT).show();
            return;
        }

        ////This part of method is taking data from Specific Heat of Humid Air EditText field, get it to string, and then parse to integer
        EditText specificHeatOfHumidAirEditText = (EditText) findViewById(R.id.Specific_Heat_Humid_Air);
        String specificHeatOfHumidAirString = specificHeatOfHumidAirEditText.getText().toString();
        if (specificHeatOfHumidAirString.isEmpty()) {
            Toast.makeText(this, "You have to input Specific Heat of Humid Air value", Toast.LENGTH_SHORT).show();
            return;
        }
        double specificHeatOfHumidAir = Double.valueOf(specificHeatOfHumidAirString);
        if (specificHeatOfHumidAir == 0) {
            Toast.makeText(this, "Specific Heat of Humid Air cannot be 0", Toast.LENGTH_SHORT).show();
            return;
        }


        //This part of method is taking data from Temperature EditText field, get it to string, and then parse to integer
        EditText temperatureEditText = (EditText) findViewById(R.id.Enthalpy_Temperature);
        String temperatureEnthalpyString = temperatureEditText.getText().toString();
        if (temperatureEnthalpyString.isEmpty()) {
            Toast.makeText(this, "You have to input Temperature value", Toast.LENGTH_SHORT).show();
            return;
        }
        double enthalpyTemperature = Double.valueOf(temperatureEnthalpyString);

        //This part of method is taking data from Moisture Content EditText field, get it to string, and then parse to integer
        EditText moistureContentEditText = (EditText) findViewById(R.id.Moisture_Content);
        String moistureContentString = moistureContentEditText.getText().toString();
        if (moistureContentString.isEmpty()) {
            Toast.makeText(this, "You have to input Moisture Content value", Toast.LENGTH_SHORT).show();
            return;
        }
        double moistureContent = Double.valueOf(moistureContentString);

        //This part of method is passing all of integers, to calculate enthalpy, and parse it to string
        double enthalpy = calculateEnthalpy(specificHeatOfDryAir, heatOfVaporization, specificHeatOfHumidAir, enthalpyTemperature, moistureContent);
        String enthalpyValue = Double.toString(enthalpy) + " kJ/kg";

        //This part of method shows enthalpy integer in enthalpy_menu result field
        TextView enthalpyResult = (TextView) findViewById(R.id.enthalpy_result);
        enthalpyResult.setText(enthalpyValue);
    }

    /**
     * This method is used, to calculate int for enthalpy
     */
    private double calculateEnthalpy(double specificHeatOfDryAir, double heatOfVaporization, double specificHeatOfHumidAir, double enthalpyTemperature, double moistureContent) {
        return specificHeatOfDryAir * enthalpyTemperature + moistureContent * (heatOfVaporization + specificHeatOfHumidAir * enthalpyTemperature);
    }
}
