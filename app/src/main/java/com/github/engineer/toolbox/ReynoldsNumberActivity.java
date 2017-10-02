package com.github.engineer.toolbox;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReynoldsNumberActivity extends AppCompatActivity implements View.OnClickListener {
    //Binding views
    @BindView(R.id.reynolds_number_submit_button)
    Button mSubmitReynoldsNumber;
    @BindView(R.id.reynolds_number_density)
    EditText mDensity;
    @BindView(R.id.reynolds_number_mean_fluid_velocity)
    EditText mMeanFluidVelocity;
    @BindView(R.id.reynolds_number_hydraulic_diameter)
    EditText mHydraulicDiameter;
    @BindView(R.id.reynolds_number_viscosity)
    EditText mViscosity;
    @BindView(R.id.reynolds_number_result)
    TextView mReynoldsNumberResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reynolds_number);
        //Executing binding
        ButterKnife.bind(this);
        //Setting listener
        mSubmitReynoldsNumber.setOnClickListener(this);
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
        if (mDensity.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_density_toast, Toast.LENGTH_SHORT).show();
        }
        double density = Double.valueOf(mDensity.getText().toString().trim());

        //This part of method is used to get mean fluid velocity value
        if (mMeanFluidVelocity.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_mean_fluid_velocity_toast, Toast.LENGTH_SHORT).show();
        }
        double meanFluidVelocity = Double.valueOf(mMeanFluidVelocity.getText().toString().trim());
        //This part of method is used to get diameter value
        if (mHydraulicDiameter.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_hydraulic_diameter_toast, Toast.LENGTH_SHORT).show();
        }
        double diameter = Double.valueOf(mHydraulicDiameter.getText().toString().trim());
        //This part of method is used to get viscosity value
        if (mViscosity.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_viscosity_toast, Toast.LENGTH_SHORT).show();
        }
        double viscosity = Double.valueOf(mViscosity.getText().toString().trim());

        double reynoldsNumber = calculateReynoldsNumber(density, meanFluidVelocity, diameter, viscosity);
        //This part shows the result of equation
        String reynoldsNumberValue = Double.toString(reynoldsNumber);

        if (reynoldsNumber < 3000) {
            reynoldsNumberValue += System.lineSeparator() + getString(R.string.laminar_flow_annotation);
        }
        if (reynoldsNumber >= 3000 & reynoldsNumber <= 4000) {
            reynoldsNumberValue += System.lineSeparator() + getString(R.string.transition_flow_annotation);
        }
        if (reynoldsNumber > 4000) {
            reynoldsNumberValue += System.lineSeparator() + getString(R.string.turbulent_flow_annotation);
        }
        mReynoldsNumberResult.setText(reynoldsNumberValue);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            case R.id.info_button:
                Intent intent = null;
                try {
                    Utils utils = new Utils();
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.reynolds_number_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
