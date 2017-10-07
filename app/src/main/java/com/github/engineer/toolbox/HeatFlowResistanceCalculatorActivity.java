package com.github.engineer.toolbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class HeatFlowResistanceCalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    //ints for resolving calculation
    private static final int HEAT_FLUX = 0;
    private static final int AREA = 1;
    private static final int TEMPERATURE_DIFFERENCE = 2;
    private static final int HEAT_FLOW_RESISTANCE = 3;
    private static final int HEAT_FLUX_DENSITY = 4;
    private static int sOptionSelected;
    //Binding views
    @BindView(R.id.heat_flow_resistance_heat_flux_density_check_box)
    CheckBox mHeatFluxDensityCheckBox;
    @BindView(R.id.heat_flow_resistance_heat_flux_edittext)
    EditText mHeatFlux;
    @BindView(R.id.heat_flow_resistance_area_edittext)
    EditText mArea;
    @BindView(R.id.heat_flow_resistance_heat_flux_density_edittext)
    EditText mHeatFluxDensity;
    @BindView(R.id.heat_flow_resistance_heat_resistance_edittext)
    EditText mHeatResistance;
    @BindView(R.id.heat_flow_resistance_option_spinner)
    Spinner mHeatFlowResistanceOptionSpinner;
    @BindView(R.id.heat_flow_resistance_temperature_difference_edittext)
    EditText mTemperatureDifference;
    @BindView(R.id.heat_flow_resistance_submit_button)
    Button mSubmitHeatFlowResistance;
    @BindView(R.id.heat_flow_resistance_add_heat_resistance)
    Button mAddResistance;
    @BindView(R.id.heat_flow_resistance_result_text_view)
    TextView mResult;
    @BindViews({R.id.heat_flow_resistance_heat_flux_edittext, R.id.heat_flow_resistance_area_edittext, R.id.heat_flow_resistance_heat_flux_density_edittext
            , R.id.heat_flow_resistance_temperature_difference_edittext})
    EditText[] editTexts;
    //setting fields
    private String selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set content view
        setContentView(R.layout.heat_flow_reistance_calculator_menu);
        //Executing bind view
        ButterKnife.bind(this);
        //setting listener on submit button
        mSubmitHeatFlowResistance.setOnClickListener(this);
//setting spinner
        setSpinner();
//setting UI on checkboxes changes
        mHeatFluxDensityCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mHeatFluxDensityCheckBox.isChecked()) {
                    mHeatFlux.setVisibility(GONE);
                    mArea.setVisibility(GONE);
                    mHeatFluxDensity.setVisibility(VISIBLE);
                    mHeatFlux.getText().clear();
                    mArea.getText().clear();

                }
                if (!mHeatFluxDensityCheckBox.isChecked()) {
                    mHeatFlux.setVisibility(VISIBLE);
                    mArea.setVisibility(VISIBLE);
                    mHeatFluxDensity.setVisibility(GONE);
                    mHeatFluxDensity.getText().clear();
                }
            }
        });
        //setting click listener on add heat flow resistance button to show special menu for that possibility
        mAddResistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setAddHeatFlowResistanceMenu = new Intent(HeatFlowResistanceCalculatorActivity.this, AddHeatFlowResistanceMenuActivity.class);
                startActivity(setAddHeatFlowResistanceMenu);
            }
        });
        //getting values from onSaveInstaceState bundle and setting fields if there is any value
        if (savedInstanceState != null) {
            for (int i = 0; i < editTexts.length; i++) {
                String keyValue = getString(R.string.value_key) + String.valueOf(i) + getString(R.string.key_key);
                if (savedInstanceState.containsKey(keyValue)) {
                    editTexts[i].setText(savedInstanceState.getString(keyValue));
                }
            }
        }
    }

    /**
     * This method is used to get save value of resistance in edittext on rotating from calculation menu and add resistance menu
     */
    @Override
    protected void onStop() {
        super.onStop();
        // Making preference changes to save resistance value, for further resistance addition
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(getString(R.string.preferences_key), 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(getString(R.string.add_resistance_key), mHeatResistance.getText().toString().trim());
        // Commit the edits!
        editor.apply();
    }

    /**
     * This method is used to set spinner, with options of calculations
     **/
    private void setSpinner() {
        //creating adapter
        ArrayAdapter heatResistanceOptionAdapter = ArrayAdapter.createFromResource(this, R.array.array_heat_flow_resistance_option,
                android.R.layout.simple_dropdown_item_1line);
        //setting dropdown menu
        heatResistanceOptionAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        //setting adapter on spinner
        mHeatFlowResistanceOptionSpinner.setAdapter(heatResistanceOptionAdapter);
        //setting listeners on selected item
        mHeatFlowResistanceOptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.heat_flux))) {
                        /* This part of method is executed on "heat flux" option selected*/
                        ReloadViews();
                        ReloadCheckBox();
                        if (mHeatFluxDensityCheckBox.isChecked()) {
                            Toast.makeText(HeatFlowResistanceCalculatorActivity.this, getString(R.string.isolated_heat_flux_calculation_warning),
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        sOptionSelected = HEAT_FLUX;
                        mHeatFlux.setVisibility(GONE);


                    }
                    if (selection.equals(getString(R.string.area))) {
                        /* This part of method is executed on "area" option selected*/
                        ReloadViews();
                        ReloadCheckBox();
                        if (mHeatFluxDensityCheckBox.isChecked()) {
                            Toast.makeText(HeatFlowResistanceCalculatorActivity.this, getString(R.string.isolated_area_calculation_warning), Toast.LENGTH_LONG).show();
                            return;
                        }
                        sOptionSelected = AREA;
                        mArea.setVisibility(GONE);


                    }
                    if (selection.equals(getString(R.string.temperature_difference))) {
                        /* This part of method is executed on "temperature difference" option selected*/
                        ReloadViews();
                        ReloadCheckBox();
                        sOptionSelected = TEMPERATURE_DIFFERENCE;
                        mTemperatureDifference.setVisibility(GONE);


                    }
                    if (selection.equals(getString(R.string.heat_flow_resistance))) {
                        /* This part of method is executed on "heat flow resistance" option selected*/
                        ReloadViews();
                        ReloadCheckBox();
                        sOptionSelected = HEAT_FLOW_RESISTANCE;
                        mHeatResistance.setVisibility(GONE);
                    }
                    if (selection.equals(getString(R.string.heat_flow_density))) {
                        /* This part of method is executed on "heat flow density" option selected*/
                        ReloadViews();
                        ReloadCheckBox();
                        sOptionSelected = HEAT_FLUX_DENSITY;
                        mHeatFlux.setVisibility(GONE);
                        mArea.setVisibility(GONE);
                        mHeatFluxDensity.setVisibility(GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//nothing
            }
        });
    }

    public void ReloadCheckBox() {
        if (mHeatFluxDensityCheckBox.isChecked()) {
            mHeatFluxDensityCheckBox.setChecked(false);
            mHeatFluxDensityCheckBox.setChecked(true);
        } else {
            mHeatFluxDensityCheckBox.setChecked(true);
            mHeatFluxDensityCheckBox.setChecked(false);
        }

    }


    /**
     * This method is used to get heatFluxDensity value from EditText (if it's visible, look
     * at heatFluxDensity CheckBox) and parse it to double for further calculations
     */
    private double heatFluxDensity() {
        if (mHeatFluxDensity.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(mHeatFluxDensity.getText().toString().trim());
    }

    /**
     * This method is used to get heatFlux value from EditText (if it's visible, look
     * at heatFluxDensity CheckBox) and parse it to double for further calculations
     */
    private double heatFlux() {
        if (mHeatFlux.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(mHeatFlux.getText().toString().trim());
    }

    /**
     * This method is used to get area value from EditText and parse it to double for further calculations
     */
    private double area() {
        if (mArea.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(mArea.getText().toString().trim());
    }

    /**
     * This method is used to get Temperature Difference value from EditText and parse it to double for further calculations
     */
    private double temperatureDifference() {
        if (mTemperatureDifference.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(mTemperatureDifference.getText().toString().trim());
    }

    /**
     * This method is used to get heatResistance value from EditText and parse it to double for further calculations
     */
    private double heatResistance() {
        if (mHeatResistance.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(mHeatResistance.getText().toString().trim());

    }

    /**
     * This method is used to make sure, that every view is visible, with operating on visibility attributes on spinner items.
     * Nesting function is necessary for nested linear layouts
     */

    private void ReloadViews() {
        LinearLayout linearLayout = findViewById(R.id.heat_flow_resistance_hidelinearlayout);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View child = linearLayout.getChildAt(i);
            if (child instanceof LinearLayout) {
                for (int isecond = 0; isecond < ((LinearLayout) child).getChildCount(); isecond++) {
                    View childsecond = ((LinearLayout) child).getChildAt(isecond);
                    if (childsecond.getVisibility() == GONE) {
                        childsecond.setVisibility(VISIBLE);
                    }
                }
                if (child.getVisibility() == GONE) {
                    child.setVisibility(VISIBLE);
                }
            }

        }
    }


    /**
     * This method is used on Click of submit calculation button
     */
    @Override
    public void onClick(View v) {

        Double result;
        String resultString = null;
        if (sOptionSelected == HEAT_FLUX) {
            /*This part of method is used, when user wants to calculate Heat Flux*/
            result = temperatureDifference() * area() / heatResistance();
            resultString = selection + getString(R.string.value_is_suffix) + String.valueOf(result) + getString(R.string.watt_suffix);
        }
        if (sOptionSelected == AREA) {
            /*This part of method is used, when user wants to calculate area*/
            result = heatFlux() * heatResistance() / temperatureDifference();
            resultString = selection + getString(R.string.value_is_suffix) + String.valueOf(result) + getString(R.string.square_meter_suffix);
        }
        if (sOptionSelected == TEMPERATURE_DIFFERENCE) {
            /*This part of method is used, when user wants to calculate Temperature Difference (way of doing it
            * depends on if user have Heat Flux Density, or Heat Flux and area)*/
            if (mHeatFluxDensityCheckBox.isChecked()) {
                result = heatFluxDensity() * heatResistance();
            } else {
                result = heatFlux() * heatResistance() / area();
            }
            resultString = selection + getString(R.string.value_is_suffix) + String.valueOf(result) + getString(R.string.temperature_celsius_suffix);


        }
        if (sOptionSelected == HEAT_FLOW_RESISTANCE) {
             /*This part of method is used, when user wants to calculate Heat Flox Resistance (way of doing it
            * depends on if user have Heat Flux Density, or Heat Flux and area)*/
            if (mHeatFluxDensityCheckBox.isChecked()) {
                result = heatFluxDensity() * temperatureDifference();
            } else {
                result = temperatureDifference() * area() / heatFlux();
            }
            resultString = selection + getString(R.string.value_is_suffix) + String.valueOf(result) + getString(R.string.heat_flow_reistance_suffix);

        }
        if (sOptionSelected == HEAT_FLUX_DENSITY) {
             /*This part of method is used, when user wants to calculate Heat Flux Density*/
            result = temperatureDifference() / heatResistance();
            resultString = selection + getString(R.string.value_is_suffix) + String.valueOf(result) + getString(R.string.heat_flux_density_suffix);

        }

         /*This part of method is used, to show result of calculation*/
        mResult.setText(resultString);
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
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.heat_flow_resistance_calculator_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //getting values from edittext fields
        for (int i = 0; i < editTexts.length; i++) {
            if (editTexts[i].getText().toString().trim().length() != 0) {
                outState.putString(getString(R.string.value_key) + String.valueOf(i) + R.string.key_key, editTexts[i].getText().toString());
            }

        }
    }

    /**
     * Method used to get double from shared preferences
     */
    double getDouble(final SharedPreferences prefs, final String key, final double defaultValue) {
        if (!prefs.contains(key)) {
            return defaultValue;
        }
        return Double.longBitsToDouble(prefs.getLong(key, 0));
    }

    @Override
    public void onResume() {
        super.onResume();
        //getting add resistance value from preferences
        Double currentResistance = 0.0;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Double addResistance = getDouble(sharedPref, getString(R.string.add_resistance_key), 0.0);
        if (mHeatResistance.getText().toString().trim().length() != 0) {
            currentResistance = Double.valueOf(mHeatResistance.getText().toString());
        }
        mHeatResistance.setText(String.valueOf(currentResistance + addResistance));
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear().apply();

    }
}



