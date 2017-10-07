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

public class DarcyWeisbachEquationActivity extends AppCompatActivity implements View.OnClickListener {
    //Binding Views
    @BindView(R.id.darcy_weisbach_equation_submit_button)
    Button mSubmitDarcyWeisbachEquation;
    @BindView(R.id.darcy_weisbach_equation_result_text_view)
    TextView mDarcyWeisbachResult;
    @BindView(R.id.darcy_weisbach_equation_darcy_friction_factor_edit_text)
    EditText mDarcyFrictionFactor;
    @BindView(R.id.darcy_weisbach_equation_length_edit_text)
    EditText mLength;
    @BindView(R.id.darcy_weisbach_equation_hydraulic_diameter_edit_text)
    EditText mHydraulicDiameter;
    @BindView(R.id.darcy_weisbach_equation_mean_fluid_velocity_edit_text)
    EditText mMeanFluidVelocity;
    @BindView(R.id.darcy_weisbach_equation_density_edit_text)
    EditText mDensityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.darcy_weisbach_equation);
//xcuting binding
        ButterKnife.bind(this);
        mSubmitDarcyWeisbachEquation.setOnClickListener(this);
    }

    /**
     * This method is used to calculate Darcy weisbach equation, and return result of it
     */
    private double calculateDarcyWeisbach(double darcyFrictionFactor, double length, double hydraulicDiameter, double meanFluidVelocity, double density) {
        return darcyFrictionFactor * (length / hydraulicDiameter) * (Math.pow(meanFluidVelocity, 2) * density / 2);
    }

    /**
     * This method is used to submit Darcy Weisbach Equation, and show the result of it
     */
    @Override
    public void onClick(View v) {

//this part is used to get darcy friction factor value from EditText field and get it to double
        if (mDarcyFrictionFactor.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_darcy_friction_factor_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double darcyFrictionFactor = Double.valueOf(mDarcyFrictionFactor.getText().toString().trim());
        //this part is used to get length value from EditText field and get it to double
        if (mLength.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_length_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double length = Double.valueOf(mLength.getText().toString().trim());
        //this part is used to get hydraulic diameter value from EditText field and get it to double
        if (mHydraulicDiameter.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_hydraulic_diameter_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double hydraulicDiameter = Double.valueOf(mHydraulicDiameter.getText().toString().trim());
        //this part is used to get mean fluid velocity value from EditText field and get it to double
        if (mMeanFluidVelocity.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_mean_fluid_velocity_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double meanFluidVelocity = Double.valueOf(mMeanFluidVelocity.getText().toString().trim());

        //this part is used to get density value from EditText field and get it to double
        if (mDensityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_density_toast, Toast.LENGTH_SHORT).show();
            return;
        }
        double density = Double.valueOf(mDensityEditText.getText().toString().trim());

        //this part is used to show the result
        String darcyWeisbachValue = Double.toString(calculateDarcyWeisbach(darcyFrictionFactor, length, hydraulicDiameter, meanFluidVelocity, density)) + getString(R.string.pascal_suffix);
        mDarcyWeisbachResult.setText(darcyWeisbachValue);
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
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.darcy_weisbach_equation_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
