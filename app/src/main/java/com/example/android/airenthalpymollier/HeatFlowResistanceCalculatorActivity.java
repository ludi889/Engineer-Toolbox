package com.example.android.airenthalpymollier;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class HeatFlowResistanceCalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int HEAT_FLUX = 0;
    private static final int AREA = 1;
    private static final int TEMPERATURE_DIFFERENCE = 2;
    private static final int HEAT_FLOW_RESISTANCE = 3;
    private static final int HEAT_FLUX_DENSITY = 4;
    private static int optionSelected;
    private EditText heatResistanceEditText;
    private EditText heatFluxEditText;
    private EditText areaEditText;
    private EditText heatFluxDensityEditText;
    private EditText temperatureDifference;
    private Double resistance;
    private Double addResistance;
    private Spinner heatFlowResistanceOptionSpinner;
    private CheckBox heatFluxDensityCheckBox;
    private String selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set content view
        setContentView(R.layout.heat_flow_reistance_calculator_menu);
        //setting fields
        heatFluxDensityCheckBox = findViewById(R.id.heat_flux_density_check_box);
        heatFluxEditText = findViewById(R.id.heat_flux_edittext);
        areaEditText = findViewById(R.id.area_edittext);
        heatFluxDensityEditText = findViewById(R.id.heat_flux_density_edittext);
        heatResistanceEditText = findViewById(R.id.heat_resistance_edittext);
        heatFlowResistanceOptionSpinner = findViewById(R.id.heat_flow_resistance_option_spinner);
        temperatureDifference = findViewById(R.id.temperature_difference_edittext);
        Button submitHeatFlowResistance = findViewById(R.id.heat_flow_resistance_submit_button);
        //setting listener on submit button
        submitHeatFlowResistance.setOnClickListener(this);
        //Setting preferences
        SharedPreferences settings = getSharedPreferences(getString(R.string.preferences_key), 0);
        //getting bundle data from add resistance menu
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            addResistance = bundle.getDouble(getString(R.string.add_resistance_key));
            String resistanceString = settings.getString(getString(R.string.preferences_key), String.valueOf(0.0));
            if (resistanceString.isEmpty()) {
                resistanceString = "0.0";
            }
            resistance = Double.valueOf(resistanceString);
            if (addResistance == null) {
                addResistance = 0.0;
            }
            resistance += addResistance;
            heatResistanceEditText.setText(String.valueOf(resistance));

        }
//setting spinner
        setSpinner();
//setting UI on checkboxes changes
        heatFluxDensityCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (heatFluxDensityCheckBox.isChecked()) {
                    heatFluxEditText.setVisibility(GONE);
                    areaEditText.setVisibility(GONE);
                    heatFluxDensityEditText.setVisibility(VISIBLE);
                    heatFluxEditText.getText().clear();
                    areaEditText.getText().clear();

                }
                if (!heatFluxDensityCheckBox.isChecked()) {
                    heatFluxEditText.setVisibility(VISIBLE);
                    areaEditText.setVisibility(VISIBLE);
                    heatFluxDensityEditText.setVisibility(GONE);
                    heatFluxDensityEditText.getText().clear();
                }
            }
        });
        //setting click listener on add heat flow resistance button to show special menu for that possibility
        Button addResistanceButton = findViewById(R.id.add_heat_resistance);
        addResistanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setAddHeatFlowResistanceMenu = new Intent(HeatFlowResistanceCalculatorActivity.this, AddHeatFlowResistanceMenuActivity.class);
                startActivity(setAddHeatFlowResistanceMenu);
            }
        });

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
        editor.putString(getString(R.string.add_resistance_key), heatResistanceEditText.getText().toString().trim());
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
        heatFlowResistanceOptionSpinner.setAdapter(heatResistanceOptionAdapter);
        //setting listeners on selected item
        heatFlowResistanceOptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.heat_flux))) {
                        /* This part of method is executed on "heat flux" option selected*/
                        ReloadViews();
                        ReloadCheckBox();
                        if (heatFluxDensityCheckBox.isChecked()) {
                            Toast.makeText(HeatFlowResistanceCalculatorActivity.this, getString(R.string.isolated_heat_flux_calculation_warning),
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        optionSelected = HEAT_FLUX;
                        heatFluxEditText.setVisibility(GONE);


                    }
                    if (selection.equals(getString(R.string.area))) {
                        /* This part of method is executed on "area" option selected*/
                        ReloadViews();
                        ReloadCheckBox();
                        if (heatFluxDensityCheckBox.isChecked()) {
                            Toast.makeText(HeatFlowResistanceCalculatorActivity.this, getString(R.string.isolated_area_calculation_warning), Toast.LENGTH_LONG).show();
                            return;
                        }
                        optionSelected = AREA;
                        areaEditText.setVisibility(GONE);


                    }
                    if (selection.equals(getString(R.string.temperature_difference))) {
                        /* This part of method is executed on "temperature difference" option selected*/
                        ReloadViews();
                        ReloadCheckBox();
                        optionSelected = TEMPERATURE_DIFFERENCE;
                        temperatureDifference.setVisibility(GONE);


                    }
                    if (selection.equals(getString(R.string.heat_flow_resistance))) {
                        /* This part of method is executed on "heat flow resistance" option selected*/
                        ReloadViews();
                        ReloadCheckBox();
                        optionSelected = HEAT_FLOW_RESISTANCE;
                        heatResistanceEditText.setVisibility(GONE);
                    }
                    if (selection.equals(getString(R.string.heat_flow_density))) {
                        /* This part of method is executed on "heat flow density" option selected*/
                        ReloadViews();
                        ReloadCheckBox();
                        optionSelected = HEAT_FLUX_DENSITY;
                        heatFluxEditText.setVisibility(GONE);
                        areaEditText.setVisibility(GONE);
                        heatFluxDensityEditText.setVisibility(GONE);
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
        if (heatFluxDensityCheckBox.isChecked()) {
            heatFluxDensityCheckBox.setChecked(false);
            heatFluxDensityCheckBox.setChecked(true);
        } else {
            heatFluxDensityCheckBox.setChecked(true);
            heatFluxDensityCheckBox.setChecked(false);
        }

    }


    /**
     * This method is used to get HeatFluxDensity value from EditText (if it's visible, look
     * at heatFluxDensity CheckBox) and parse it to double for further calculations
     */
    private double HeatFluxDensity() {
        if (heatFluxDensityEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(heatFluxDensityEditText.getText().toString().trim());
    }

    /**
     * This method is used to get HeatFlux value from EditText (if it's visible, look
     * at heatFluxDensity CheckBox) and parse it to double for further calculations
     */
    private double HeatFlux() {
        if (heatFluxEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(heatFluxEditText.getText().toString().trim());
    }

    /**
     * This method is used to get Area value from EditText and parse it to double for further calculations
     */
    private double Area() {
        if (areaEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(areaEditText.getText().toString().trim());
    }

    /**
     * This method is used to get Temperature Difference value from EditText and parse it to double for further calculations
     */
    private double TemperatureDifference() {
        EditText temperatureDifferenceEditText = findViewById(R.id.temperature_difference_edittext);
        if (temperatureDifferenceEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(temperatureDifferenceEditText.getText().toString().trim());
    }

    /**
     * This method is used to get HeatResistance value from EditText and parse it to double for further calculations
     */
    private double HeatResistance() {
        if (heatResistanceEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(heatResistanceEditText.getText().toString().trim());

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

        Double result = null;
        if (optionSelected == HEAT_FLUX) {
            /*This part of method is used, when user wants to calculate Heat Flux*/
            result = TemperatureDifference() * Area() / HeatResistance();
        }
        if (optionSelected == AREA) {
            /*This part of method is used, when user wants to calculate Area*/
            result = HeatFlux() * HeatResistance() / TemperatureDifference();
        }
        if (optionSelected == TEMPERATURE_DIFFERENCE) {
            /*This part of method is used, when user wants to calculate Temperature Difference (way of doing it
            * depends on if user have Heat Flux Density, or Heat Flux and Area)*/
            if (heatFluxDensityCheckBox.isChecked()) {
                result = HeatFluxDensity() * HeatResistance();
            } else {
                result = HeatFlux() * HeatResistance() / Area();
            }
        }
        if (optionSelected == HEAT_FLOW_RESISTANCE) {
             /*This part of method is used, when user wants to calculate Heat Flox Resistance (way of doing it
            * depends on if user have Heat Flux Density, or Heat Flux and Area)*/
            if (heatFluxDensityCheckBox.isChecked()) {
                result = HeatFluxDensity() * (1 / TemperatureDifference());
            } else {
                result = TemperatureDifference() * Area() / HeatFlux();
            }
        }
        if (optionSelected == HEAT_FLUX_DENSITY) {
             /*This part of method is used, when user wants to calculate Heat Flux Density*/
            result = TemperatureDifference() / HeatResistance();
        }
         /*This part of method is used, to show result of calculation*/
        TextView resultTextView = findViewById(R.id.heat_flow_resistance_result_textview);
        resultTextView.setText(selection + getString(R.string.value_is_suffix) + String.valueOf(result));
    }
}
