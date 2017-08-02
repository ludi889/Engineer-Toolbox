package com.example.android.airenthalpymollier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TemperatureConverterMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner mInputSpinner;
    private Spinner mOutputSpinner;
    private EditText mInputTemperatureEditText;
    private TextView mOutputTemperatureTextView;
    /**
     * 0 - Celsius
     * 1 - Fahrenheit
     * 2 - Kelvin
     * 3 - Rankine
     */
    private int mInputTemperatureKind;
    private int mOutputTemperatureKind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature_converter_menu);

        //Setting fields
        mInputSpinner = (Spinner) findViewById(R.id.input_temperature_spinner_converter);
        mOutputSpinner = (Spinner) findViewById(R.id.output_temperature_spinner_converter);
        mInputTemperatureEditText = (EditText) findViewById(R.id.input_temperature_converter_Et);
        mOutputTemperatureTextView = (TextView) findViewById(R.id.output_temperature_converter_tV);
        Button submitTemperatureConvert = (Button) findViewById(R.id.submit_temperature_conversion);
        //Initialization of spinners
        setupSpinners();
        //Setting listener
        submitTemperatureConvert.setOnClickListener(this);
    }

    /**
     * this method is used to set spinners
     */
    private void setupSpinners() {
        //Creating adapter for spinners
        ArrayAdapter temperatureSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_temperature_options,
                android.R.layout.simple_dropdown_item_1line);
        // Specify dropdown layout style - simple list view with 1 item per line
        temperatureSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        //Setting adapters on spinners
        mInputSpinner.setAdapter(temperatureSpinnerAdapter);
        mOutputSpinner.setAdapter(temperatureSpinnerAdapter);

        //Operating on click listeners
        //Input spinner listeners
        mInputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.celsius))) {
                        mInputTemperatureKind = 0; //celsius
                        //todo placeholder for hiding celsius kind in second spinner
                    }
                    if (selection.equals(getString(R.string.fahrenheit))) {
                        mInputTemperatureKind = 1; //fahrenheit
                        //todo placeholder for hiding fahrenheit kind in second spinner
                    }
                    if (selection.equals(getString(R.string.kelvin))) {
                        mInputTemperatureKind = 2; //kelvin
                        //todo placeholder for hiding kelvin kind in second spinner
                    }
                    if (selection.equals(getString(R.string.rankine))) {
                        mInputTemperatureKind = 3; //rankine
                        //todo placeholder for hiding rankine kind in second spinner
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mInputTemperatureKind = 0;
            }
        });
        //Output spinner listeners
        mOutputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.celsius))) {
                        mOutputTemperatureKind = 0; //celsius
                    }
                    if (selection.equals(getString(R.string.fahrenheit))) {
                        mOutputTemperatureKind = 1;  //fahrenheit
                    }
                    if (selection.equals(getString(R.string.kelvin))) {
                        mOutputTemperatureKind = 2; //kelvin
                    }
                    if (selection.equals(getString(R.string.rankine))) {
                        mOutputTemperatureKind = 3; //rankine
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mOutputTemperatureKind = 0;
            }
        });


    }


    @Override
    public void onClick(View v) {
        Double inputTemperature = Double.valueOf(mInputTemperatureEditText.getText().toString());
        Double outputTemperature = null;
        //Celsius to ? conversion
        if (mInputTemperatureKind == 0 & mOutputTemperatureKind == 0) {
            //celsius to celsius
            outputTemperature = inputTemperature;
            // TODO: 2017-08-02 after implementing hide second spinner same option, this can be deleted
        }
        if (mInputTemperatureKind == 0 & mOutputTemperatureKind == 1) {
            //celsius to fahrenheit
            outputTemperature = (inputTemperature * 1.8) + 32;
        }
        if (mInputTemperatureKind == 0 & mOutputTemperatureKind == 2) {
            //celsius to kelvin
            outputTemperature = inputTemperature + +273.15;
        }
        if (mInputTemperatureKind == 0 & mOutputTemperatureKind == 3) {
            //celsius to rankine
            outputTemperature = (inputTemperature + 273.15) * 1.8;
        }
        //Fahrenheit to ? conversion
        if (mInputTemperatureKind == 1 & mOutputTemperatureKind == 0) {
            //fahrenheit to celsius
            outputTemperature = (inputTemperature - 32) * 0.5556;
        }
        if (mInputTemperatureKind == 1 & mOutputTemperatureKind == 1) {
            //fahrenheit to celsius
            outputTemperature = inputTemperature;
            // TODO: 2017-08-02 after implementing hide second spinner same option, this can be deleted
        }
        if (mInputTemperatureKind == 1 & mOutputTemperatureKind == 2) {
            //fahrenheit to kelvin
            outputTemperature = (inputTemperature + 459.67) * 0.5556;
        }
        if (mInputTemperatureKind == 1 & mOutputTemperatureKind == 3) {
            //fahrenheit to rankine
            outputTemperature = inputTemperature + 459.67;
        }
        //Kelvin to ? conversion
        if (mInputTemperatureKind == 2 & mOutputTemperatureKind == 0) {
            //kelvin to celsius
            outputTemperature = inputTemperature - 273.15;
        }
        if (mInputTemperatureKind == 2 & mOutputTemperatureKind == 1) {
            //kelvin to fahrenheit
            outputTemperature = inputTemperature * 1.8 - 459.67;
        }
        if (mInputTemperatureKind == 2 & mOutputTemperatureKind == 2) {
            //kelvin to kelvin
            outputTemperature = inputTemperature;
            // TODO: 2017-08-02 after implementing hide second spinner same option, this can be deleted
        }
        if (mInputTemperatureKind == 2 & mOutputTemperatureKind == 3) {
            //kelvin to rankine
            outputTemperature = inputTemperature * 1.8;
        }
        //Rankine to ? conversion
        if (mInputTemperatureKind == 3 & mOutputTemperatureKind == 0) {
            //rankine to celsius
            outputTemperature = (inputTemperature - 491.67) * 0.5556;
        }
        if (mInputTemperatureKind == 3 & mOutputTemperatureKind == 1) {
            //rankine to fahrenheit
            outputTemperature = inputTemperature - 459.67;
        }
        if (mInputTemperatureKind == 3 & mOutputTemperatureKind == 2) {
            //rankine to kelvin
            outputTemperature = inputTemperature * 0.5556;
        }
        if (mInputTemperatureKind == 3 & mOutputTemperatureKind == 3) {
            //rankine to kelvin
            outputTemperature = inputTemperature;
            // TODO: 2017-08-02 after implementing hide second spinner same option, this can be deleted
        }
        if (outputTemperature == null) {
            return;
        }
        mOutputTemperatureTextView.setText(String.valueOf(outputTemperature));
    }
}
