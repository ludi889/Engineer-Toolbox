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

public class AbsoluteHumidityActivity extends AppCompatActivity implements View.OnClickListener {
    //Binding views
    @BindView(R.id.absolute_humidity_calculator_submit_button)
    Button mSubmitAbsoluteHumidity;
    @BindView(R.id.absolute_humidity_calculator_saturated_air_pressure_edit_text)
    EditText mSaturatedAirPressure;
    @BindView(R.id.absolute_humidity_calculator_relative_humidity_edit_text)
    EditText mRelativeHumidity;
    @BindView(R.id.absolute_humidity_calculator_air_pressure_edit_text)
    EditText mAirPressure;
    @BindView(R.id.absolute_humidity_calculator_result_text_view)
    TextView mAbsoluteHumidityResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.absolute_humidity_calculator);
        //Executing bindings
        ButterKnife.bind(this);

        mSubmitAbsoluteHumidity.setOnClickListener(this);
    }

    /**
     * This method is used to calculate Absolute Humidity and return it back to submitAbsoluteHumidityEquation
     */
    private double calculateAbsoluteHumidity(double saturatedAirPressure, double relativeHumidity, double airPressure) {
        return (0.622 * relativeHumidity * saturatedAirPressure) / (airPressure - (relativeHumidity * saturatedAirPressure));

    }

    /**
     * This method is used to get data from EdiText field of Humidity Equation data input, and then give out the result of enthalpy calculations
     */
    @Override
    public void onClick(View v) {

        //This part of method is taking data from Saturated Air Pressure EditText field, get it to string, and then parse to integer
        if (mSaturatedAirPressure.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_saturated_air_pressure_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double saturatedAirPressure = Double.valueOf(mSaturatedAirPressure.getText().toString().trim());
        //This part of method is taking data from Relative humidity EditText field, get it to string, and then parse to integer
        if (mRelativeHumidity.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_relative_humidity_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double relativeHumidity = Double.valueOf(mRelativeHumidity.getText().toString().trim());

        //This part of method is taking data from Air Pressure EditText field, get it to string, and then parse to integer
        if (mAirPressure.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_air_pressure_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double airPressure = Double.valueOf(mAirPressure.getText().toString().trim());

        //calculating absoluteHumidity
        String absoluteHumidity = Double.toString(calculateAbsoluteHumidity(saturatedAirPressure, relativeHumidity, airPressure)) + getString(R.string.absolute_humidity_unit);

        //Displaying result
        mAbsoluteHumidityResult.setText(absoluteHumidity);


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
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.absolute_humidity_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
