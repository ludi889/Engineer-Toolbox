package com.example.android.airenthalpymollier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class HeatFlowResistanceCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heat_flow_reistance_calculator_menu);
//setting spinner
        Spinner spinner = (Spinner) findViewById(R.id.heat_flux_spinner);
        final ArrayList<String> list = new ArrayList<>();
        list.add("Heat flux");
        list.add("Area");
        list.add("Temperature difference");
        list.add("Heat flow resistance");
        list.add("Heat flux density");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
//setting menu on checkboxes changes
        CheckBox heatFluxDensityCheckBox = (CheckBox) findViewById(R.id.heat_flux_density_check_box);
        EditText heatFluxEditText = (EditText) findViewById(R.id.heat_flux);
        EditText areaEditText = (EditText) findViewById(R.id.area);
        final EditText heatFluxDensityEditText = (EditText) findViewById(R.id.heat_flux_density_edittext);

        if (heatFluxDensityCheckBox.isChecked()) {
            heatFluxEditText.setVisibility(GONE);
            areaEditText.setVisibility(GONE);
            heatFluxDensityEditText.setVisibility(VISIBLE);
        }
        if (!heatFluxDensityCheckBox.isChecked()) {
            heatFluxEditText.setVisibility(VISIBLE);
            areaEditText.setVisibility(VISIBLE);
            heatFluxDensityEditText.setVisibility(GONE);
        }
        //setting add resistances menu
        final Button addButton = (Button) findViewById(R.id.add_heat_resistance);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(HeatFlowResistanceCalculatorActivity.this, addButton);

            }
        });


    }
}
