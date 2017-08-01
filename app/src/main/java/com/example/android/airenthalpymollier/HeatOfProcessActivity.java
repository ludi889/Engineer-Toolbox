package com.example.android.airenthalpymollier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HeatOfProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heat_of_process);
    }

    public void submitHeatOfProcessEquation(View view) {
// This part of method takes value of specific Heat of Substance, and parse it to double
        EditText specificHeatOfSubstanceHeatOfProcessEditText = (EditText) findViewById(R.id.Specific_Heat_of_Substantion_heat_of_process);
        String specificHeatOfSubstanceHeatOfProcessString = specificHeatOfSubstanceHeatOfProcessEditText.getText().toString();
        if (specificHeatOfSubstanceHeatOfProcessString.isEmpty()) {
            Toast.makeText(this, "You have to input Specific Heat of Substance value", Toast.LENGTH_SHORT).show();
        }
        double specificHeatOfSubstanceHeatOfProcess = Double.valueOf(specificHeatOfSubstanceHeatOfProcessString);
// This part of method takes value of mass of Substance, and parse it to double
        if (specificHeatOfSubstanceHeatOfProcess == 0) {
            Toast.makeText(this, "Specific Heat of Substance cannot be 0", Toast.LENGTH_SHORT).show();
        }
        EditText massOfSubstanceHeatOfProcessEditText = (EditText) findViewById(R.id.Mass_of_substantion_heat_of_process);
        String massOfSubstanceHeatOfProcessString = massOfSubstanceHeatOfProcessEditText.getText().toString();
        if (massOfSubstanceHeatOfProcessString.isEmpty()) {
            Toast.makeText(this, "You have to input Mass of Substance value", Toast.LENGTH_SHORT).show();
        }
        double massOfSubstanceHeatOfProcess = Double.valueOf(massOfSubstanceHeatOfProcessString);
        //This part of method takes value of initial temperature and parse it to double
        EditText initialTemperatureHeatOfProcessEditText = (EditText) findViewById(R.id.Initial_temperature_heat_of_process);
        String initialTemperatureHeatOfProcessString = initialTemperatureHeatOfProcessEditText.getText().toString();
        if (initialTemperatureHeatOfProcessString.isEmpty()) {
            Toast.makeText(this, "You have to input Initial Temperature value", Toast.LENGTH_SHORT).show();
        }
        double initialTemperatureHeatOfProcess = Double.valueOf(initialTemperatureHeatOfProcessString);
//This part of method takes value of final temperature and parse it to double
        EditText finalTemperatureHeatOfProcessEditText = (EditText) findViewById(R.id.Final_temeprature_heat_of_process);
        String finalTemperatureHeatOfProcessString = finalTemperatureHeatOfProcessEditText.getText().toString();
        if (finalTemperatureHeatOfProcessString.isEmpty()) {
            Toast.makeText(this, "You have to input Final Temperature value", Toast.LENGTH_SHORT).show();
        }
        double finalTemperatureHeatOfProcess = Double.valueOf(finalTemperatureHeatOfProcessString);


//This part of method calculate the heat of process
        double HeatOfProcess = calculateHeatOfProcess(specificHeatOfSubstanceHeatOfProcess, massOfSubstanceHeatOfProcess, initialTemperatureHeatOfProcess, finalTemperatureHeatOfProcess);
        String heatOfProcessValue = Double.toString(HeatOfProcess) + "kJ/kg";
        //This part of method shows the result
        TextView heatOfProcessResult = (TextView) findViewById(R.id.heat_of_process_result);
        heatOfProcessResult.setText(heatOfProcessValue);

    }

    /**
     * This method is used to calculate HeatOfProcess and return it to submitHeatOfProcess Method
     */
    public double calculateHeatOfProcess(double specificHeatOfSubstanceHeatOfProcess, double massOfSubstanceHeatOfProcess, double initialTemperatureHeatOfProcess, double finalTemperatureHeatOfProcess) {
        return specificHeatOfSubstanceHeatOfProcess * massOfSubstanceHeatOfProcess * (finalTemperatureHeatOfProcess - initialTemperatureHeatOfProcess);
    }

}
