package com.github.engineer.toolbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//setting buttons
        final Button setEngineeringTheoryButton = findViewById(R.id.engineering_theory_button);
        final Button setTemperatureConverterButton = findViewById(R.id.temperature_converter_button);
        final Button setEnthalpyCalculatorButton = findViewById(R.id.enthalpy_calculator_button);
        final Button setHeatOfProcessCalculatorButton = findViewById(R.id.heat_of_process_calculator_button);
        final Button setHeatFlowRessistanceCalculatorButton = findViewById(R.id.heat_flow_resistance_calculator_button);
        final Button setBernoullisEquationButton = findViewById(R.id.bernoullis_equation_calculator_button);
        final Button setAbsoluteHumidityCalculatorButton = findViewById(R.id.absolute_humidity_calculator_button);

        //setting listeners on buttons
        setEngineeringTheoryButton.setOnClickListener(this);
        setTemperatureConverterButton.setOnClickListener(this);
        setEnthalpyCalculatorButton.setOnClickListener(this);
        setHeatOfProcessCalculatorButton.setOnClickListener(this);
        setHeatFlowRessistanceCalculatorButton.setOnClickListener(this);
        setBernoullisEquationButton.setOnClickListener(this);
        setAbsoluteHumidityCalculatorButton.setOnClickListener(this);

    }

    /**
     * This method is used, to set context menu on action bar, to show more info on project
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
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
     * This method is used, when user want to acces option, which wasn't implemented yet
     */
    public void uploadReturnMessage(View view) {
        Toast.makeText(this, R.string.implement_toast, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is used to set content view, depending on clicked button, switching with id of button
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.engineering_theory_button:
                uploadReturnMessage(v);
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
                Intent setEnthalpyMenuIntent = new Intent(this, EnthalpyMenuActivity.class);
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
