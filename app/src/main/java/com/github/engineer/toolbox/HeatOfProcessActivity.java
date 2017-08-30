package com.github.engineer.toolbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HeatOfProcessActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heat_of_process_calculator);

        Button submitHeatOfProcessButton = findViewById(R.id.submit_heat_of_process_result_button);
        submitHeatOfProcessButton.setOnClickListener(this);
    }

    /**
     * This method is used to calculate HeatOfProcess and return it to submitHeatOfProcess Method
     */
    public double calculateHeatOfProcess(double specificHeatOfSubstance, double massOfSubstance, double initialTemperature, double finalTemperature) {
        return specificHeatOfSubstance * massOfSubstance * (finalTemperature - initialTemperature);
    }

    @Override
    public void onClick(View v) {

// This part of method takes value of specific Heat of Substance, and parse it to double
        EditText specificHeatOfSubstanceEditText = findViewById(R.id.specific_heat_of_substance_heat_of_process);
        if (specificHeatOfSubstanceEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_specific_heat_of_substance_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double specificHeatOfSubstance = Double.valueOf(specificHeatOfSubstanceEditText.getText().toString().trim());
        // This part of method takes value of mass of Substance, and parse it to double
        EditText massOfSubstanceEditText = findViewById(R.id.mass_of_substance_heat_of_process);
        if (massOfSubstanceEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_mass_of_substance_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double massOfSubstance = Double.valueOf(massOfSubstanceEditText.getText().toString().trim());
        //This part of method takes value of initial temperature and parse it to double
        EditText initialTemperatureEditText = findViewById(R.id.initial_temperature_heat_of_process);
        if (initialTemperatureEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_initial_temperature_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        Double initialTemperature = Double.valueOf(initialTemperatureEditText.getText().toString().trim());
//This part of method takes value of final temperature and parse it to double
        EditText finalTemperatureEditText = findViewById(R.id.final_temperature_heat_of_process);
        if (finalTemperatureEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_final_temperature_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double finalTemperature = Double.valueOf(finalTemperatureEditText.getText().toString().trim());


//This part of method calculate the heat of process
        String heatOfProcessValue = Double.toString(calculateHeatOfProcess(specificHeatOfSubstance, massOfSubstance, initialTemperature, finalTemperature)) + getString(R.string.kilojoules_suffix);
        //This part of method shows the result
        TextView heatOfProcessResult = findViewById(R.id.heat_of_process_result);
        heatOfProcessResult.setText(heatOfProcessValue);


    }
}
