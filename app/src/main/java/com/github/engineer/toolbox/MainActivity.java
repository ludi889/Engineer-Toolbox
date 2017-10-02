package com.github.engineer.toolbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Binding views
    @BindView(R.id.engineering_theory_button)
    Button mSetEngineeringTheory;
    @BindView(R.id.temperature_converter_button)
    Button mSetTemperatureConverterMenu;
    @BindView(R.id.enthalpy_calculator_button)
    Button mSetEnthalpyCalculator;
    @BindView(R.id.heat_of_process_calculator_button)
    Button mSetHeatOfProcessCalculator;
    @BindView(R.id.heat_flow_resistance_calculator_button)
    Button mSetHeatFlowResistanceCalculator;
    @BindView(R.id.bernoullis_equation_calculator_button)
    Button mSetBernoullisEquation;
    @BindView(R.id.absolute_humidity_calculator_button)
    Button mSetAbsoluteHumidityCalculator;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Executing binding
        ButterKnife.bind(this);

        //setting listeners on buttons
        mSetEngineeringTheory.setOnClickListener(this);
        mSetTemperatureConverterMenu.setOnClickListener(this);
        mSetEnthalpyCalculator.setOnClickListener(this);
        mSetHeatOfProcessCalculator.setOnClickListener(this);
        mSetHeatFlowResistanceCalculator.setOnClickListener(this);
        mSetBernoullisEquation.setOnClickListener(this);
        mSetAbsoluteHumidityCalculator.setOnClickListener(this);

    }

    /**
     * This method is used, to set context menu on action bar, to show more info on project
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            case R.id.info_button:
                Intent intent = new Intent(MainActivity.this, InfoMenuActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is used, when user want to access option, which wasn't implemented yet
     */
    public void uploadReturnMessage(View view) {
        Toast.makeText(this, R.string.implement_toast, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is used to set content view, depending on clicked button, switching with id of button
     */
    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        switch (v.getId()) {
            case R.id.engineering_theory_button:
                Intent setEngineeringTheoryMenuIntent = new Intent(this, EngineerTheoryActivity.class);
                startActivity(setEngineeringTheoryMenuIntent);
                break;
            case R.id.temperature_converter_button:
                /*
                  This methods sets content view to temperature converter menu
                 */
                Intent setTemperatureConverterMenuIntent = new Intent(this, TemperatureConverterMenuActivity.class);
                startActivity(setTemperatureConverterMenuIntent);
                break;
            case R.id.enthalpy_calculator_button:
                /*
                  This method is used to start EnthalpyMenu Activity
                 */
                Intent setEnthalpyMenuIntent = new Intent(this, MolierGraphEnthalpyMenuActivity.class);
                startActivity(setEnthalpyMenuIntent);
                break;
            case R.id.heat_of_process_calculator_button: /*
              This method set the view on Heat of Process Calculator Menu
             */
                Intent setHeatOfProcessMenuIntent = new Intent(this, HeatOfProcessActivity.class);
                startActivity(setHeatOfProcessMenuIntent);
                break;
            case R.id.heat_flow_resistance_calculator_button:
                /*
                  This method sets content view to Heat flor resistance menu
                 */
                Intent setHeatFlowResistanceMenu = new Intent(this, HeatFlowResistanceCalculatorActivity.class);
                startActivity(setHeatFlowResistanceMenu);
                break;
            case R.id.bernoullis_equation_calculator_button:
                /*
                  This method sets Content to Bernoulie's Equation menu
                 */
                Intent setBernoullieEquationMenu = new Intent(this, BernoulieEquationMenuActivity.class);
                startActivity(setBernoullieEquationMenu);
                break;
            case R.id.absolute_humidity_calculator_button:
                /*
                  This method is seeting the view to humidity menu, from main menu
                 */
                Intent setAbsoluteHumidityMenuIntent = new Intent(this, AbsoluteHumidityActivity.class);
                startActivity(setAbsoluteHumidityMenuIntent);
                break;
        }
    }
}
