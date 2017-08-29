package com.example.android.airenthalpymollier;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReynoldsNumberActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reynolds_number);

        Button submitReynoldsNumberButton = findViewById(R.id.submit_reynolds_number_button);
        submitReynoldsNumberButton.setOnClickListener(this);
    }

    /**
     * This method is used to calculate reynolds number
     */

    private double calculateReynoldsNumber(double density, double meanFluidVelocity, double diameter, double viscosity) {
        return (density * meanFluidVelocity * diameter) / viscosity;
    }

    /**
     * This method is used to submit Reynolds number Equation, and return final value of it, in reynolds_number.xml
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        //This part of method is used to get density value
        EditText densityEditText = findViewById(R.id.density_reynolds_number);
        if (densityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_density_toast, Toast.LENGTH_SHORT).show();
        }
        double density = Double.valueOf(densityEditText.getText().toString().trim());

        //This part of method is used to get mean fluid velocity value
        EditText meanFluidVelocityEditText = findViewById(R.id.mean_fluid_velocity_reynolds_number);
        if (meanFluidVelocityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_mean_fluid_velocity_toast, Toast.LENGTH_SHORT).show();
        }
        double meanFluidVelocity = Double.valueOf(meanFluidVelocityEditText.getText().toString().trim());
        //This part of method is used to get diameter value
        EditText diameterEditText = findViewById(R.id.diameter_reynolds_number);
        if (diameterEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_hydraulic_diameter_toast, Toast.LENGTH_SHORT).show();
        }
        double diameter = Double.valueOf(diameterEditText.getText().toString().trim());
        //This part of method is used to get viscosity value
        EditText viscosityEditText = findViewById(R.id.viscosity_reynolds_number);
        if (viscosityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_viscosity_toast, Toast.LENGTH_SHORT).show();
        }
        double viscosity = Double.valueOf(viscosityEditText.getText().toString().trim());

        double reynoldsNumber = calculateReynoldsNumber(density, meanFluidVelocity, diameter, viscosity);
        //This part shows the result of equation
        String reynoldsNumberValue = Double.toString(reynoldsNumber);

        TextView reynoldsNumberResult = findViewById(R.id.reynolds_number_result);
        if (reynoldsNumber < 3000) {
            reynoldsNumberValue += System.lineSeparator() + getString(R.string.laminar_flow_annotation);
        }
        if (reynoldsNumber >= 3000 & reynoldsNumber <= 4000) {
            reynoldsNumberValue += System.lineSeparator() + getString(R.string.transition_flow_annotation);
        }
        if (reynoldsNumber > 4000) {
            reynoldsNumberValue += System.lineSeparator() + getString(R.string.turbulent_flow_annotation);
        }
        reynoldsNumberResult.setText(reynoldsNumberValue);

    }
}
