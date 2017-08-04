package com.example.android.airenthalpymollier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnthalpyMenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enthalpy_menu);

        Button submitEnthalpyButton = (Button) findViewById(R.id.submit_enthalpy_calculation_button);
        submitEnthalpyButton.setOnClickListener(this);
    }


    /**
     * This method is used, to calculate int for enthalpy
     */
    private double calculateEnthalpy(double specificHeatOfDryAir, double heatOfVaporization, double specificHeatOfHumidAir, double enthalpyTemperature, double moistureContent) {
        return specificHeatOfDryAir * enthalpyTemperature + moistureContent * (heatOfVaporization + specificHeatOfHumidAir * enthalpyTemperature);
    }

    /**
     * This method is used to get data from EditText field of Enthalpy data input, and then give out the result of enthalpy calculations
     */
    @Override
    public void onClick(View v) {

        //This part of method is taking data from Specific heat of dry air EditText field, get it to string, and then parse to double
        EditText specificHeatOfDryAirEditText = (EditText) findViewById(R.id.Specific_Heat_Dry_Air);
        if (specificHeatOfDryAirEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "You have to input specific heat of dry air", Toast.LENGTH_SHORT).show();
            return;
        }
        Double specificHeatOfDryAir = Double.valueOf(specificHeatOfDryAirEditText.getText().toString().trim());


        //This part of method is taking data from Heat of vaporization EditText field, and Parse to double
        EditText heatOfVaporizationEditText = (EditText) findViewById(R.id.Heat_of_Vaporization);
        if (heatOfVaporizationEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "You have to input heat of vaporization", Toast.LENGTH_SHORT).show();
            return;
        }
        double heatOfVaporization = Double.valueOf(heatOfVaporizationEditText.getText().toString().trim());


        ////This part of method is taking data from Specific Heat of Humid Air EditText field, and parse to Double
        EditText specificHeatOfHumidAirEditText = (EditText) findViewById(R.id.Specific_Heat_Humid_Air);
        if (specificHeatOfHumidAirEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "You have to input specific heat of humid air", Toast.LENGTH_SHORT).show();
            return;
        }
        double specificHeatOfHumidAir = Double.valueOf(specificHeatOfHumidAirEditText.getText().toString().trim());


        //This part of method is taking data from Temperature EditText field, get it to string, and then parse to Double
        EditText temperatureEditText = (EditText) findViewById(R.id.Enthalpy_Temperature);
        if (temperatureEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "You have to input temperature", Toast.LENGTH_SHORT).show();
            return;
        }
        double enthalpyTemperature = Double.valueOf(temperatureEditText.getText().toString().trim());

        //This part of method is taking data from Moisture Content EditText field, get it to string, and then parse to Double
        EditText moistureContentEditText = (EditText) findViewById(R.id.Moisture_Content);
        if (moistureContentEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "You have to input moisture content", Toast.LENGTH_SHORT).show();
            return;
        }
        double moistureContent = Double.valueOf(moistureContentEditText.getText().toString().trim());


        //This part of method is passing all of Double, to calculate enthalpy, and parse it to string
        String enthalpyValue = Double.toString(calculateEnthalpy(specificHeatOfDryAir, heatOfVaporization, specificHeatOfHumidAir, enthalpyTemperature, moistureContent)) + "kJ/kg";
        //This part of method shows enthalpy integer in enthalpy_menu result field
        TextView enthalpyResult = (TextView) findViewById(R.id.enthalpy_result);
        enthalpyResult.setText(enthalpyValue);

    }
}
