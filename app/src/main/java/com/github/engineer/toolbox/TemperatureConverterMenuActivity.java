package com.github.engineer.toolbox;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemperatureConverterMenuActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * Setting static int's for operating on them
     * 0 - Celsius
     * 1 - Fahrenheit
     * 2 - Kelvin
     * 3 - Rankine
     */
    private static int CELSIUS = 0;
    private static int FAHRENHEIT = 1;
    private static int KELVIN = 2;
    private static int RANKINE = 3;
    //Bindging views
    @BindView(R.id.temperature_converter_input_temperature_spinner)
    Spinner mInputSpinner;
    //setting fields
    @BindView(R.id.temperature_converter_output_temperature_spinner)
    Spinner mOutputSpinner;
    @BindView(R.id.temperature_converter_input_temperature_edit_text)
    EditText mInputTemperatureEditText;
    @BindView(R.id.temperature_converter_output_temperature_text_view)
    TextView mOutputTemperatureTextView;
    @BindView(R.id.temperature_converter_submit_temperature_conversion)
    Button mSubmitTemperatureConvert;
    private int mInputTemperatureKind;
    private int mOutputTemperatureKind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature_converter_menu);
        //Executing binding
        ButterKnife.bind(this);

        //Initialization of spinners
        setupSpinners();
        //Setting listener
        mSubmitTemperatureConvert.setOnClickListener(this);
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
                        mInputTemperatureKind = CELSIUS; //celsius
                        //todo placeholder for hiding celsius kind in second spinner
                    }
                    if (selection.equals(getString(R.string.fahrenheit))) {
                        mInputTemperatureKind = FAHRENHEIT; //fahrenheit
                        //todo placeholder for hiding fahrenheit kind in second spinner
                    }
                    if (selection.equals(getString(R.string.kelvin))) {
                        mInputTemperatureKind = KELVIN; //kelvin
                        //todo placeholder for hiding kelvin kind in second spinner
                    }
                    if (selection.equals(getString(R.string.rankine))) {
                        mInputTemperatureKind = RANKINE; //rankine
                        //todo placeholder for hiding rankine kind in second spinner
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mInputTemperatureKind = CELSIUS;
            }
        });
        //Output spinner listeners
        mOutputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.celsius))) {
                        mOutputTemperatureKind = CELSIUS; //celsius
                    }
                    if (selection.equals(getString(R.string.fahrenheit))) {
                        mOutputTemperatureKind = FAHRENHEIT;  //fahrenheit
                    }
                    if (selection.equals(getString(R.string.kelvin))) {
                        mOutputTemperatureKind = KELVIN; //kelvin
                    }
                    if (selection.equals(getString(R.string.rankine))) {
                        mOutputTemperatureKind = RANKINE; //rankine
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mOutputTemperatureKind = CELSIUS;
            }
        });


    }


    @Override
    public void onClick(View v) {
        Double inputTemperature = Double.valueOf(mInputTemperatureEditText.getText().toString());
        Double outputTemperature = null;
        //Celsius to ? conversion
        if (mInputTemperatureKind == CELSIUS & mOutputTemperatureKind == CELSIUS) {
            //celsius to celsius
            outputTemperature = inputTemperature;
            // TODO: 2017-08-02 after implementing hide second spinner same option, this can be deleted
        }
        if (mInputTemperatureKind == CELSIUS & mOutputTemperatureKind == FAHRENHEIT) {
            //celsius to fahrenheit
            outputTemperature = (inputTemperature * 1.8) + 32;
        }
        if (mInputTemperatureKind == CELSIUS & mOutputTemperatureKind == KELVIN) {
            //celsius to kelvin
            outputTemperature = inputTemperature + +273.15;
        }
        if (mInputTemperatureKind == CELSIUS & mOutputTemperatureKind == RANKINE) {
            //celsius to rankine
            outputTemperature = (inputTemperature + 273.15) * 1.8;
        }
        //Fahrenheit to ? conversion
        if (mInputTemperatureKind == FAHRENHEIT & mOutputTemperatureKind == CELSIUS) {
            //fahrenheit to celsius
            outputTemperature = (inputTemperature - 32) * 0.5556;
        }
        if (mInputTemperatureKind == FAHRENHEIT & mOutputTemperatureKind == FAHRENHEIT) {
            //fahrenheit to celsius
            outputTemperature = inputTemperature;
            // TODO: 2017-08-02 after implementing hide second spinner same option, this can be deleted
        }
        if (mInputTemperatureKind == FAHRENHEIT & mOutputTemperatureKind == KELVIN) {
            //fahrenheit to kelvin
            outputTemperature = (inputTemperature + 459.67) * 0.5556;
        }
        if (mInputTemperatureKind == FAHRENHEIT & mOutputTemperatureKind == RANKINE) {
            //fahrenheit to rankine
            outputTemperature = inputTemperature + 459.67;
        }
        //Kelvin to ? conversion
        if (mInputTemperatureKind == KELVIN & mOutputTemperatureKind == CELSIUS) {
            //kelvin to celsius
            outputTemperature = inputTemperature - 273.15;
        }
        if (mInputTemperatureKind == KELVIN & mOutputTemperatureKind == FAHRENHEIT) {
            //kelvin to fahrenheit
            outputTemperature = inputTemperature * 1.8 - 459.67;
        }
        if (mInputTemperatureKind == KELVIN & mOutputTemperatureKind == KELVIN) {
            //kelvin to kelvin
            outputTemperature = inputTemperature;
            // TODO: 2017-08-02 after implementing hide second spinner same option, this can be deleted
        }
        if (mInputTemperatureKind == KELVIN & mOutputTemperatureKind == RANKINE) {
            //kelvin to rankine
            outputTemperature = inputTemperature * 1.8;
        }
        //Rankine to ? conversion
        if (mInputTemperatureKind == RANKINE & mOutputTemperatureKind == CELSIUS) {
            //rankine to celsius
            outputTemperature = (inputTemperature - 491.67) * 0.5556;
        }
        if (mInputTemperatureKind == RANKINE & mOutputTemperatureKind == FAHRENHEIT) {
            //rankine to fahrenheit
            outputTemperature = inputTemperature - 459.67;
        }
        if (mInputTemperatureKind == RANKINE & mOutputTemperatureKind == KELVIN) {
            //rankine to kelvin
            outputTemperature = inputTemperature * 0.5556;
        }
        if (mInputTemperatureKind == RANKINE & mOutputTemperatureKind == RANKINE) {
            //rankine to kelvin
            outputTemperature = inputTemperature;
            // TODO: KELVIN0-FAHRENHEIT7-08-02 after implementing hide second spinner same option, this can be deleted
        }
        if (outputTemperature == null) {
            outputTemperature = 0.0;
        }
        mOutputTemperatureTextView.setText(String.valueOf(outputTemperature));
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
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.temperature_converter_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
