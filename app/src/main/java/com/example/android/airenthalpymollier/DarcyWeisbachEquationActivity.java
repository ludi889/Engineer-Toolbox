package com.example.android.airenthalpymollier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DarcyWeisbachEquationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.darcy_weisbach_equation);

        Button submitDarcyWeisbachEquation = findViewById(R.id.submit_darcy_weisbach_equation);
        submitDarcyWeisbachEquation.setOnClickListener(this);
    }

    /**
     * This method is used to calculate Darcy weisbach equation, and return result of it
     */
    private double calculateDarcyWeisbach(double darcyFrictionFactor, double length, double hydraulicDiameter, double meanFluidVelocity, double density) {
        return darcyFrictionFactor * (length / hydraulicDiameter) * (meanFluidVelocity * meanFluidVelocity * density / 2);
    }

    /**
     * This method is used to submit Darcy Weisbach Equation, and show the result of it
     */
    @Override
    public void onClick(View v) {

//this part is used to get darcy friction factor value from EditText field and get it to double
        EditText darcyFrictionFactorEditText = findViewById(R.id.darcy_friction_factor_darcy_weisbach_equation);
        if (darcyFrictionFactorEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_darcy_friction_factor_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double darcyFrictionFactor = Double.valueOf(darcyFrictionFactorEditText.getText().toString().trim());
        //this part is used to get length value from EditText field and get it to double
        EditText lengthEditText = findViewById(R.id.length_darcy_weisbach_equation);
        if (lengthEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_length_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double length = Double.valueOf(lengthEditText.getText().toString().trim());
        //this part is used to get hydraulic diameter value from EditText field and get it to double
        EditText hydraulicDiameterEditText = findViewById(R.id.hydraulic_diameter_darcy_weisbach_equation);
        if (hydraulicDiameterEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_hydraulic_diameter_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double hydraulicDiameter = Double.valueOf(hydraulicDiameterEditText.getText().toString().trim());
        //this part is used to get mean fluid velocity value from EditText field and get it to double
        EditText meanFluidVelocityEditText = findViewById(R.id.mean_fluid_velocity_darcy_weisbach_equation);
        if (meanFluidVelocityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_mean_fluid_velocity_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double meanFluidVelocity = Double.valueOf(meanFluidVelocityEditText.getText().toString().trim());

        //this part is used to get density value from EditText field and get it to double
        EditText densityEditText = findViewById(R.id.density_darcy_weisbach_equation);
        if (densityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_density_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double density = Double.valueOf(densityEditText.getText().toString().trim());

        //this part is used to show the result
        String darcyWeisbachValue = Double.toString(calculateDarcyWeisbach(darcyFrictionFactor, length, hydraulicDiameter, meanFluidVelocity, density)) + getString(R.string.pascal_suffix);

        TextView darcyWeisbachResult = findViewById(R.id.darcy_weisbach_equation_result);
        darcyWeisbachResult.setText(darcyWeisbachValue);

    }
}
