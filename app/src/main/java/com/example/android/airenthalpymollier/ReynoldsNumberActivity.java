package com.example.android.airenthalpymollier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ReynoldsNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reynolds_number);
    }

    /**
     * This method is used to submit Reynolds number Equation, and return final value of it, in reynolds_number.xml
     */
    public void submitReynoldsNumberEquation (View view){
        //This part of method is used to get density value
        EditText densityEditText = (EditText) findViewById(R.id.density_reynolds_number);
        String densityString = densityEditText.getText().toString();
        if (densityString.isEmpty()) {
            Toast.makeText(this,"You have to input density value",Toast.LENGTH_SHORT).show();
        }
        double density = Double.valueOf(densityString);

        //This part of method is used to get mean fluid velocity value
        EditText meanFluidVelocityEditText = (EditText) findViewById(R.id.mean_fluid_velocity_reynolds_number);
        String meanFluidVelocityString = meanFluidVelocityEditText.getText().toString();
        if (meanFluidVelocityString.isEmpty()) {
            Toast.makeText(this,"You have to input mean Fluid Velocity value",Toast.LENGTH_SHORT).show();
        }
        double meanFluidVelocity = Double.valueOf(meanFluidVelocityString);
    //This part of method is used to get diameter value
    EditText diameterEditText = (EditText) findViewById(R.id.diameter_reynolds_number);
    String diameterString = diameterEditText.getText().toString();
    if (diameterString.isEmpty()) {
        Toast.makeText(this,"You have to input diameter value",Toast.LENGTH_SHORT).show();
    }
    double diameter = Double.valueOf(diameterString);
        //This part of method is used to get viscosity value
        EditText viscosityEditText = (EditText) findViewById(R.id.viscosity_reynolds_number);
        String viscosityString = viscosityEditText.getText().toString();
        if (viscosityString.isEmpty()) {
            Toast.makeText(this,"You have to input viscosity value",Toast.LENGTH_SHORT).show();
        }
        double viscosity = Double.valueOf(viscosityString);

        double reynoldsNumber = calculateReynoldsNumber(density, meanFluidVelocity, diameter, viscosity);
        //This part shows the result of equation
        String reynoldsNumberValue = Double.toString(reynoldsNumber);

        TextView reynoldsNumberResult = (TextView) findViewById(R.id.reynolds_number_result);
        if (reynoldsNumber<3000){
            reynoldsNumberValue += System.lineSeparator() + "It's laminar flow";
        }
        if (reynoldsNumber>=3000 & reynoldsNumber<=4000){
            reynoldsNumberValue += System.lineSeparator() + "It's transition flow";
        }
        if (reynoldsNumber>4000){
            reynoldsNumberValue += System.lineSeparator() + "It's turbulent flow";
        }
        reynoldsNumberResult.setText(reynoldsNumberValue);
}
    private double calculateReynoldsNumber (double density, double meanFluidVelocity, double diameter, double viscosity){
        return (density*meanFluidVelocity*diameter)/viscosity ;
    }

}
