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

public class MolierGraphEnthalpyMenuActivity extends AppCompatActivity implements View.OnClickListener {
    //Binding Views
    @BindView(R.id.enthalpy_menu_submit_button)
    Button mSubmitEnthalpyButton;
    @BindView(R.id.enthalpy_menu_specific_heat_dry_air)
    EditText mSpecificHeatOfDryAir;
    @BindView(R.id.enthalpy_menu_heat_of_vaporization)
    EditText mHeatOfVaporization;
    @BindView(R.id.enthalpy_menu_specific_heat_humid_air)
    EditText mSpecificHeatOfHumidAir;
    @BindView(R.id.enthalpy_menu_temperature)
    EditText mTemperature;
    @BindView(R.id.enthalpy_menu_moisture_content)
    EditText mMoistureContent;
    @BindView(R.id.enthalpy_menu_result_text_view)
    TextView mEnthalpyResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enthalpy_menu);
//executing binding
        ButterKnife.bind(this);
        mSubmitEnthalpyButton.setOnClickListener(this);
    }


    /**
     * This method is used, to calculate int for enthalpy
     */
    private double calculateEnthalpy(double specificHeatOfDryAir, double heatOfVaporization, double specificHeatOfHumidAir, double enthalpyTemperature, double moistureContent) {
        return specificHeatOfDryAir * enthalpyTemperature + moistureContent * (heatOfVaporization + specificHeatOfHumidAir * enthalpyTemperature);
    }

    /**
     * This method is used to get data from EditText field of Enthalpy data input, and then give out the result of enthalpy calculations
     */
    @Override
    public void onClick(View v) {

        //This part of method is taking data from Specific heat of dry air EditText field, get it to string, and then parse to double
        if (mSpecificHeatOfDryAir.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_specific_heat_of_dry_air_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        Double specificHeatOfDryAir = Double.valueOf(mSpecificHeatOfDryAir.getText().toString().trim());


        //This part of method is taking data from Heat of vaporization EditText field, and Parse to double
        if (mHeatOfVaporization.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_heat_of_vaporization_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double heatOfVaporization = Double.valueOf(mHeatOfVaporization.getText().toString().trim());


        ////This part of method is taking data from Specific Heat of Humid Air EditText field, and parse to Double
        if (mSpecificHeatOfHumidAir.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_specific_heat_of_humid_air_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double specificHeatOfHumidAir = Double.valueOf(mSpecificHeatOfHumidAir.getText().toString().trim());


        //This part of method is taking data from Temperature EditText field, get it to string, and then parse to Double
        if (mTemperature.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_temperature_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double enthalpyTemperature = Double.valueOf(mTemperature.getText().toString().trim());

        //This part of method is taking data from Moisture Content EditText field, get it to string, and then parse to Double
        if (mMoistureContent.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_moisture_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double moistureContent = Double.valueOf(mMoistureContent.getText().toString().trim());


        //This part of method is passing all of Double, to calculate enthalpy, and parse it to string
        String enthalpyValue = Double.toString(calculateEnthalpy(specificHeatOfDryAir, heatOfVaporization, specificHeatOfHumidAir, enthalpyTemperature, moistureContent)) + getString(R.string.kilojoules_kilograms_suffix);
        //This part of method shows enthalpy integer in enthalpy_menu result field
        mEnthalpyResult.setText(enthalpyValue);
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
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.molier_graph_enthalpy_calculator_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
