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

public class LocalFlowResistanceActivity extends AppCompatActivity implements View.OnClickListener {
    //Binding views
    @BindView(R.id.submit_local_flow_resistance_button)
    Button mSubmitLocalFlowResistance;
    @BindView(R.id.local_flow_resistance_resistance_coefficient)
    EditText mResistanceCoefficient;
    @BindView(R.id.local_flow_resistance_mean_fluid_velocity)
    EditText mMeanFluidVelocity;
    @BindView(R.id.local_flow_resistance_density)
    EditText mDensity;
    @BindView(R.id.local_flow_resistance_result)
    TextView mLocalFlowResistanceResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_flow_resistance);
        //Executing view binding
        ButterKnife.bind(this);
        mSubmitLocalFlowResistance.setOnClickListener(this);
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
        if (mResistanceCoefficient.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_resistance_coefficient_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double resistanceCoefficient = Double.valueOf(mResistanceCoefficient.getText().toString().trim());
        //This part of method is used to get mean fluid velocity value from EditText field and get it to double
        if (mMeanFluidVelocity.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_mean_fluid_velocity_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double meanFluidVelocity = Double.valueOf(mMeanFluidVelocity.getText().toString().trim());
        //this part of method is used to get density value from EditText field and get it to double
        if (mDensity.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_density_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double density = Double.valueOf(mDensity.getText().toString().trim());

        //this part of method is used to show the result
        String localFlowResistanceValue = Double.toString(calculateLocalFlowResistance(resistanceCoefficient, meanFluidVelocity, density)) + getString(R.string.pascal_suffix);
        mLocalFlowResistanceResult.setText(localFlowResistanceValue);
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
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.local_flow_resistance_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
