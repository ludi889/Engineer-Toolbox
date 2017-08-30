package com.github.engineer.toolbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LocalFlowResistanceActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_flow_resistance);

        Button submitLocalFlowResistanceButton = findViewById(R.id.submit_local_flow_resistance_button);
        submitLocalFlowResistanceButton.setOnClickListener(this);
    }

    /**
     * This method is used to calculate local flow resistance
     */
    private double calculateLocalFlowResistance(double resistanceCoefficient, double meanFluidVelocity, double density) {
        return resistanceCoefficient * (meanFluidVelocity * meanFluidVelocity * density / 2);
    }

    /**
     * This method is used to calculate flow resistance equation, and show the result of it
     */
    @Override
    public void onClick(View v) {

        //this part of method is used to get resistance coefficient value from EditText field, and get it to double
        EditText resistanceCoefficientEditText = findViewById(R.id.resistance_coefficent_local_flow_resistance);
        if (resistanceCoefficientEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_resistance_coefficient_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double resistanceCoefficient = Double.valueOf(resistanceCoefficientEditText.getText().toString().trim());
        //This part of method is used to get mean fluid velocity value from EditText field and get it to double
        EditText meanFluidVelocityEditText = findViewById(R.id.mean_fluid_velocity_local_flow_reistance);
        if (meanFluidVelocityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_mean_fluid_velocity_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double meanFluidVelocity = Double.valueOf(meanFluidVelocityEditText.getText().toString().trim());
        //this part of method is used to get density value from EditText field and get it to double
        EditText densityEditText = findViewById(R.id.density_local_flow_resistance);
        if (densityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_density_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double density = Double.valueOf(densityEditText.getText().toString().trim());

        //this part of method is used to show the result
        String localFlowResistanceValue = Double.toString(calculateLocalFlowResistance(resistanceCoefficient, meanFluidVelocity, density)) + getString(R.string.pascal_suffix);

        TextView localFlowResistanceResult = findViewById(R.id.local_flow_resistance_result);
        localFlowResistanceResult.setText(localFlowResistanceValue);


    }
}
