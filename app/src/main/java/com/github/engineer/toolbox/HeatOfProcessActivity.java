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

public class HeatOfProcessActivity extends AppCompatActivity implements View.OnClickListener {
    //Binding views
    @BindView(R.id.submit_heat_of_process_result_button)
    Button mSubmitHeatOfProcess;
    @BindView(R.id.heat_of_process_specific_heat_of_substance_edit_text)
    EditText mSpecificHeatOfSubstance;
    @BindView(R.id.heat_of_process_mass_of_substance_edit_text)
    EditText mMassOfSubstance;
    @BindView(R.id.heat_of_process_initial_temperature_edit_text)
    EditText mInitialTemperature;
    @BindView(R.id.heat_of_process_final_temperature_edit_text)
    EditText mFinalTemperature;
    @BindView(R.id.heat_of_process_result_text_view)
    TextView mHeatOfProcessResult;
    //setting fields
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heat_of_process_calculator);
        //Executing binding
        ButterKnife.bind(this);

        mSubmitHeatOfProcess.setOnClickListener(this);
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
        if (mSpecificHeatOfSubstance.getText().toString().trim().length() == 0) {
            Toast.makeText(this, getString(R.string.input_specific_heat_of_substance_toast), Toast.LENGTH_SHORT).show();
            return;
        }
        double specificHeatOfSubstance = Double.valueOf(mSpecificHeatOfSubstance.getText().toString().trim());
        // This part of method takes value of mass of Substance, and parse it to double
        if (mMassOfSubstance.getText().toString().trim().length() == 0) {
            Toast.makeText(this, getString(R.string.input_mass_of_substance_toast), Toast.LENGTH_SHORT).show();
            return;
        }
        double massOfSubstance = Double.valueOf(mMassOfSubstance.getText().toString().trim());
        //This part of method takes value of initial temperature and parse it to double
        if (mInitialTemperature.getText().toString().trim().length() == 0) {
            Toast.makeText(this, getString(R.string.input_initial_temperature_toast), Toast.LENGTH_SHORT).show();
            return;
        }
        Double initialTemperature = Double.valueOf(mInitialTemperature.getText().toString().trim());
//This part of method takes value of final temperature and parse it to double
        if (mFinalTemperature.getText().toString().trim().length() == 0) {
            Toast.makeText(this, getString(R.string.input_final_temperature_toast), Toast.LENGTH_SHORT).show();
            return;
        }
        double finalTemperature = Double.valueOf(mFinalTemperature.getText().toString().trim());


//This part of method calculate the heat of process
        String heatOfProcessValue = Double.toString(calculateHeatOfProcess(specificHeatOfSubstance, massOfSubstance, initialTemperature, finalTemperature)) + getString(R.string.kilojoules_suffix);
        //This part of method shows the result
        mHeatOfProcessResult.setText(heatOfProcessValue);
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
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.heat_of_process_calculator_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
