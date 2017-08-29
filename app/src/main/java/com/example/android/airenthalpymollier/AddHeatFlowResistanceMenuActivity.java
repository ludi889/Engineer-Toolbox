package com.example.android.airenthalpymollier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Damian on 2017-08-08.
 */

public class AddHeatFlowResistanceMenuActivity extends AppCompatActivity implements View.OnClickListener {
    EditText materialThicknessEditText;
    EditText materialThermalConductivityEditText;
    EditText materialHeatTransferCoefficientEditText;
    Button insertResistanceButton;
    private Spinner addHeatResistanceOptionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_heat_flow_ressistance_menu);
        //setting fields
        addHeatResistanceOptionSpinner = findViewById(R.id.add_resistance_option_spinner);
        insertResistanceButton = findViewById(R.id.insert_thermal_resistance_button);
        materialThicknessEditText = findViewById(R.id.material_thickness);
        materialThermalConductivityEditText = findViewById(R.id.material_thermal_conductivity);
        materialHeatTransferCoefficientEditText = findViewById(R.id.material_heat_transfer_coefficient);
        //setting listeners
        insertResistanceButton.setOnClickListener(this);
//setting spinner
        setSpinner();
    }

    /**
     * This method is used to set Spinner with resistance option
     */
    private void setSpinner() {
        //creating adapter
        ArrayAdapter addHeatFlowResistanceOptionAdapter = ArrayAdapter.createFromResource(this, R.array.array_add_heat_flow_resistance_option,
                android.R.layout.simple_spinner_dropdown_item);
        //setting dropdown option
        addHeatFlowResistanceOptionAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        //setting adapter
        addHeatResistanceOptionSpinner.setAdapter(addHeatFlowResistanceOptionAdapter);

        addHeatResistanceOptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.conductivity_resistance))) {
                        materialThicknessEditText.setVisibility(VISIBLE);
                        materialThermalConductivityEditText.setVisibility(VISIBLE);
                        materialHeatTransferCoefficientEditText.setVisibility(GONE);
                    }
                    if (selection.equals(getString(R.string.heat_transfer_resistance))) {
                        materialThicknessEditText.setVisibility(GONE);
                        materialThermalConductivityEditText.setVisibility(GONE);
                        materialHeatTransferCoefficientEditText.setVisibility(VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    /**
     * This method is used to get material thickness value from EditText and parse it to double (asserting it's visibility and null)
     */
    private double materialThickness() {
        if (materialThicknessEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(materialThicknessEditText.getText().toString().trim());
    }

    /**
     * This method is used to get material thermal conductivity value
     * from EditText and parse it to double (asserting it's visibility and null)
     */
    private double materialThermalConductivity() {
        if (materialThermalConductivityEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(materialThermalConductivityEditText.getText().toString().trim());
    }

    /**
     * This method is used to get material heat transfer coefficient
     * value from EditText and parse it to double (asserting it's visibility and null)
     */
    private double materialHeatTransferCoefficient() {
        if (materialHeatTransferCoefficientEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(materialHeatTransferCoefficientEditText.getText().toString().trim());
    }

    /**
     * On Click is used to sent resistance value to heat flow resistance calculator menu in bundle. The value which is sent
     * depends on chosen spinner option.
     */
    @Override
    public void onClick(View v) {
        double addResistance = 0;
        Intent i = new Intent(AddHeatFlowResistanceMenuActivity.this, HeatFlowResistanceCalculatorActivity.class);
        Bundle bundle = new Bundle();
        addHeatResistanceOptionSpinner.getCount();
        if (addHeatResistanceOptionSpinner.getSelectedItemPosition() == 0) {
            if (materialThermalConductivity() == 0 || materialThickness() == 0) {
                addResistance = 0;
            } else
                addResistance = materialThickness() / materialThermalConductivity();

        }
        if (addHeatResistanceOptionSpinner.getSelectedItemPosition() == 1) {
            if (materialHeatTransferCoefficient() == 0) {
                addResistance = 0;
            } else
                addResistance = 1 / materialHeatTransferCoefficient();
        }
        bundle.putDouble(getString(R.string.add_resistance_key), addResistance);
        i.putExtras(bundle);
        if (bundle.containsKey(getString(R.string.add_resistance_key))) {
            startActivity(i);
        }

    }
}
