package com.github.engineer.toolbox;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DarcyFrictionFactorActivity extends AppCompatActivity implements View.OnClickListener {
    //Binding views
    @BindView(R.id.darcy_friction_factor_blausius_criteria_text_view)
    TextView mBlausiusCriteria;
    @BindView(R.id.darcy_friction_factor_generaux_criteria_text_view)
    TextView mGenerauxCriteria;
    @BindView(R.id.darcy_friction_factor_koo_criteria_text_view)
    TextView mKooCriteria;
    @BindView(R.id.darcy_friction_factor_high_reynolds_number_criteria_text_view)
    TextView mHighReynoldsCriteria;
    @BindView(R.id.darcy_friction_factor_equation_turbulent_result_text_view)
    TextView mDarcyFrictionFactorTurbulentResult;
    @BindView(R.id.darcy_friction_factor_rough_pipe_submit_button)
    Button mRoughPipeFactorSubmit;
    @BindView(R.id.darcy_friction_factor_laminar_flow_submit_button)
    Button mLaminarFlowSubmit;
    @BindView(R.id.darcy_friction_factor_turbulent_flow_submit_button)
    Button mTurbulentFlowSubmit;
    @BindView(R.id.darcy_friction_factor_equation_rough_pipe_result_text_view)
    TextView mRoughPipeFactorResult;
    @BindView(R.id.darcy_friction_factor_view_flipper)
    ViewFlipper mViewFlipper;
    @BindView(R.id.darcy_friction_factor_laminar_flow_radio_button)
    RadioButton mLaminarFlowRadioButton;
    @BindView(R.id.darcy_friction_factor_turbulent_flow_radio_button)
    RadioButton mTurbulentFlowRadioButton;
    @BindView(R.id.darcy_friction_factor_rough_pipe_flow_radio_button)
    RadioButton mRoughPipeRadioButton;
    @BindView(R.id.darcy_friction_factor_laminar_flow_resistance_factor)
    EditText mResistanceFactorValue;
    @BindView(R.id.darcy_friction_factor_reynolds_number_laminar_flow_edit_text)
    EditText mLaminarFlowReynoldsNumber;
    @BindView(R.id.darcy_friction_factor_equation_laminar_result_text_view)
    TextView mLaminarFlowResult;
    @BindView(R.id.darcy_friction_factor_reynolds_number_turbulent_flow_edit_text)
    EditText mTurbulentFlowReynoldsNumber;
    @BindView(R.id.darcy_friction_factor_reynolds_number_rough_pipe_edit_text)
    EditText mRoughPipeReynoldsNumber;
    @BindView(R.id.darcy_friction_factor_roughness_parameter_edit_text)
    EditText mRoughParameter;
    //setting fields
    ListView resistanceFactorValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.darcy_friction_factor);
        //initalizating fields
        resistanceFactorValues = findViewById(R.id.darcy_friction_factor_resistance_values_list_view);
        //executing bind
        ButterKnife.bind(this);
        //setting listeners
        mBlausiusCriteria.setOnClickListener(this);
        mGenerauxCriteria.setOnClickListener(this);
        mKooCriteria.setOnClickListener(this);
        mHighReynoldsCriteria.setOnClickListener(this);
        mRoughPipeFactorSubmit.setOnClickListener(this);
        mLaminarFlowSubmit.setOnClickListener(this);
        mTurbulentFlowSubmit.setOnClickListener(this);
        //creating list of factors
        createList();
        //this part of method is used to flip layouts, depending on selected radioButton
        mTurbulentFlowRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mTurbulentFlowRadioButton.isChecked()) {
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.darcy_friction_factor_turbulent_linear_layout)));
                }
            }
        });
        mRoughPipeRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mRoughPipeRadioButton.isChecked()) {
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.darcy_friction_rough_pipe_linear_layout)));
                }
            }
        });
        mLaminarFlowRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mLaminarFlowRadioButton.isChecked()) {
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.darcy_friction_factor_laminar_linear_layout)));
                }
            }
        });
    }

    /**
     * This method is used to create list of typical resistance factor values
     **/
    private void createList() {
        //this part is used to construct list values
        ArrayAdapter<String> adapter;
        String resistanceValuesString[] = {getString(R.string.circular_section_resistance), getString(R.string.square_section_resistance), getString(R.string.annular_section_resistance), getString(R.string.rectangle_12_ratio_resistance)};
        ArrayList<String> resistanceValues = new ArrayList<>();
        resistanceValues.addAll(Arrays.asList(resistanceValuesString));

        adapter = new ArrayAdapter<>(this, R.layout.resistance_factor_values, resistanceValues);
        resistanceFactorValues.setAdapter(adapter);
    }


    /**
     * this method is used to expand list of factor values on click of corresponding textView
     */
    public void expandResistanceFactorValues(View view) {
        if (resistanceFactorValues.getVisibility() == View.GONE) {
            resistanceFactorValues.setVisibility(View.VISIBLE);
        } else {
            resistanceFactorValues.setVisibility(View.GONE);
        }

    }

    /**
     * this method is used to set content to reynolds number calculator
     */
    public void setReynoldsNumberMenu(View view) {
        Intent setReynoldsNumberMenuIntent = new Intent(this, ReynoldsNumberActivity.class);
        startActivity(setReynoldsNumberMenuIntent);
    }

    /**
     * This method is used to calculate Darcy Friction factor for laminar flow
     **/
    public void submitDarcyFrictionLaminarFlow(View view) {
        //this part of method is used to take resistance factor value, and parse it to double
        if (mResistanceFactorValue.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_resistance_factor_toast, Toast.LENGTH_SHORT).show();
        }
        Double resistanceFactor = Double.valueOf(mResistanceFactorValue.getText().toString().trim());
        //this part of method is used to take reynolds number value and parse it to double
        if (mLaminarFlowReynoldsNumber.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_reynolds_number_toast, Toast.LENGTH_SHORT).show();
        }
        Double reynoldsNumber = Double.valueOf(mLaminarFlowReynoldsNumber.getText().toString().trim());

        Double darcyFrictionFactorLaminar = (resistanceFactor / reynoldsNumber);
        //this part of method is used to show the result of equation

        String darcyFrictionFactorValueLaminar = getString(R.string.lambda_suffix) + darcyFrictionFactorLaminar.toString();
        mLaminarFlowResult.setText(darcyFrictionFactorValueLaminar);
    }

    /**
     * This method is used to get Double of reynolds number, when turbulent flow option is selected
     **/
    public double turbulentFlowReynoldsNumber() {
        if (mTurbulentFlowReynoldsNumber.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_reynolds_number_toast, Toast.LENGTH_SHORT).show();
        }
        return Double.valueOf(mTurbulentFlowReynoldsNumber.getText().toString().trim());
    }

    /**
     * This method shows criteria, which then on click calculates Friction Factor from formula of it
     */
    public void showCriteria() {
        double turbulentFlowReynoldsNumber = turbulentFlowReynoldsNumber();

        if (turbulentFlowReynoldsNumber > 3000 & turbulentFlowReynoldsNumber < 50000) {
            mBlausiusCriteria.setVisibility(View.VISIBLE);
        } else {
            mBlausiusCriteria.setVisibility(View.GONE);
        }
        if (turbulentFlowReynoldsNumber > 4000 & turbulentFlowReynoldsNumber < 20000000) {
            mGenerauxCriteria.setVisibility(View.VISIBLE);
        } else {
            mGenerauxCriteria.setVisibility(View.GONE);
        }
        if (turbulentFlowReynoldsNumber > 3000 & turbulentFlowReynoldsNumber < 3000000) {
            mKooCriteria.setVisibility(View.VISIBLE);
        } else {
            mKooCriteria.setVisibility(View.GONE);
        }
        if (turbulentFlowReynoldsNumber > 100000 & turbulentFlowReynoldsNumber < 100000000) {
            mHighReynoldsCriteria.setVisibility(View.VISIBLE);
        } else {
            mHighReynoldsCriteria.setVisibility(View.GONE);
        }
    }

    /**
     * This method is used to get Double of Reynolds number when pipe rough option is selected
     */
    public double roughPipeFlowReynoldsNumber() {
        String roughPipeFlowReynoldsNumberString = mRoughPipeReynoldsNumber.getText().toString();
        if (roughPipeFlowReynoldsNumberString.isEmpty()) {
            Toast.makeText(this, R.string.input_reynolds_number_toast, Toast.LENGTH_SHORT).show();
        }
        return Double.valueOf(roughPipeFlowReynoldsNumberString);

    }

    /**
     * this method is used to get Double of pipe roughness parameter when pipe rough option is selected
     **/
    public double roughPipeFlowRoughnessParameterNumber() {
        String roughPipeFlowRoughnessParameterString = mRoughParameter.getText().toString();
        if (roughPipeFlowRoughnessParameterString.isEmpty()) {
            Toast.makeText(this, R.string.roughness_parameter_input_warning, Toast.LENGTH_SHORT).show();
        }
        return Double.valueOf(roughPipeFlowRoughnessParameterString);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //This method is used to calculate turbulent friction factor, blausius formula on click of blausiusCriteria TextView
            case R.id.darcy_friction_factor_blausius_criteria_text_view:
                double blausiusCriteria = (0.3164 / Math.pow(turbulentFlowReynoldsNumber(), 0.25));
                String blausiusCriteriaString = getString(R.string.lambda_suffix) + Double.toString(blausiusCriteria);
                mDarcyFrictionFactorTurbulentResult.setText(blausiusCriteriaString);
                break;
            //This method is used to calculate turbulent friction factor, using generaux formula on click of generauxCriteria TextView
            case R.id.darcy_friction_factor_generaux_criteria_text_view:
                double generauxCriteria = (0.16 / Math.pow(turbulentFlowReynoldsNumber(), 0.16));
                String generauxCriteriaString = getString(R.string.lambda_suffix) + Double.toString(generauxCriteria);
                mDarcyFrictionFactorTurbulentResult.setText(generauxCriteriaString);
                break;
            //This method is used to calculate turbulent friction factor, using koo formula on click of kooCriteria TextView
            case R.id.darcy_friction_factor_koo_criteria_text_view:
                double kooCriteria = (0.0052 + (0.5 / Math.pow(turbulentFlowReynoldsNumber(), 0.32)));
                String kooCriteriaString = getString(R.string.lambda_suffix) + Double.toString(kooCriteria);
                mDarcyFrictionFactorTurbulentResult.setText(kooCriteriaString);
                break;
            //This method is used to calculate turbulent friction factor, using high reynolds number formula on click of highReynoldsCriteria TextView
            case R.id.darcy_friction_factor_high_reynolds_number_criteria_text_view:
                double highReynoldsCriteria = (0.0032 + (0.221 / Math.pow(turbulentFlowReynoldsNumber(), 0.237)));
                String highReynoldsCriteriaString = getString(R.string.lambda_suffix) + Double.toString(highReynoldsCriteria);
                mDarcyFrictionFactorTurbulentResult.setText(highReynoldsCriteriaString);
                break;
            //This method is used to calculate friction factor, when pipe is rough on click of submit button in rough pipe LinearLayout
            case R.id.darcy_friction_factor_rough_pipe_submit_button:
                double roughnessCompound = (roughPipeFlowRoughnessParameterNumber() / 3.7);
                double reynoldsCompound = Math.pow((6.81 / roughPipeFlowReynoldsNumber()), 0.9);
                double denominator = -2 * Math.log(roughnessCompound + reynoldsCompound);
                double roughPipeFlowFactor = Math.pow((1 / denominator), 0.5);
                String roughPipeFlowFactorResult = getString(R.string.lambda_suffix) + Double.toString(roughPipeFlowFactor);
                mRoughPipeFactorResult.setText(roughPipeFlowFactorResult);
                break;
            //This method is used to calculate friction factor, when pipe is rough, on click of submit button in Laminar flow Linear layout
            case R.id.darcy_friction_factor_laminar_flow_submit_button:
                submitDarcyFrictionLaminarFlow(v);
                break;
            //This method is used to show criteria to calculate darcy friction factor, when there is turbulent flow RadioBUtton selected
            case R.id.darcy_friction_factor_turbulent_flow_submit_button:
                turbulentFlowReynoldsNumber();
                showCriteria();
                break;
        }

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
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.darcy_friction_factor_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


