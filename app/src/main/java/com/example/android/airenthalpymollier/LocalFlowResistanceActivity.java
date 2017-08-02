package com.example.android.airenthalpymollier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LocalFlowResistanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_flow_resistance);
    }

    /**
     * This method is used to calculate flow resistance equation, and show the result of it
     *
     * @param view
     */
    public void submitLocalFlowResistanceEquation(View view) {
        //this part of method is used to get resistance coefficient value from EditText field, and get it to double
        EditText resistanceCoefficientEditText = (EditText) findViewById(R.id.resistance_coefficent_local_flow_resistance);
        String resistanceCoefficientString = resistanceCoefficientEditText.getText().toString();
        if (resistanceCoefficientString.isEmpty()) {
            Toast.makeText(this, "You have to input resistance coefficient value", Toast.LENGTH_SHORT).show();
            return;
        }
        double resistanceCoefficient = Double.valueOf(resistanceCoefficientString);
        //This part of method is used to get mean fluid velocity value from EditText field and get it to double
        EditText meanFluidVelocityEditText = (EditText) findViewById(R.id.mean_fluid_velocity_local_flow_reistance);
        String meanFluidVelocityString = meanFluidVelocityEditText.getText().toString();
        if (meanFluidVelocityString.isEmpty()) {
            Toast.makeText(this, "You have to input mean fluid velocity value", Toast.LENGTH_SHORT).show();
            return;
        }
        double meanFluidVelocity = Double.valueOf(meanFluidVelocityString);
        //this part of method is used to get density value from EditText field and get it to double
        EditText densityEditText = (EditText) findViewById(R.id.density_local_flow_resistance);
        String densityString = densityEditText.getText().toString();
        if (densityString.isEmpty()) {
            Toast.makeText(this, "You have to input density value", Toast.LENGTH_SHORT).show();
            return;
        }
        double density = Double.valueOf(densityString);

        double localFlowResistance = calculateLocalFlowResistance(resistanceCoefficient, meanFluidVelocity, density);
        //this part of method is used to show the result
        String localFlowResistanceValue = Double.toString(localFlowResistance) + "Pa";

        TextView localFlowResistanceResult = (TextView) findViewById(R.id.local_flow_resistance_result);
        localFlowResistanceResult.setText(localFlowResistanceValue);


    }

    private double calculateLocalFlowResistance(double resistanceCoefficient, double meanFluidVelocity, double density) {
        return resistanceCoefficient * (meanFluidVelocity * meanFluidVelocity * density / 2);
    }
}
