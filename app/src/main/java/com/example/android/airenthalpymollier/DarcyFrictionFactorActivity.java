package com.example.android.airenthalpymollier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import static com.example.android.airenthalpymollier.R.id.laminar;
import static com.example.android.airenthalpymollier.R.id.rough_pipe;
import static com.example.android.airenthalpymollier.R.id.turbulent;

public class DarcyFrictionFactorActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView blausiusCriteria;
    private TextView generauxCriteria;
    private TextView kooCriteria;
    private TextView highReynoldsCriteria;
    private TextView darcyFrictionFactorTurbulentResult;
    private TextView roughPipeFactorResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.darcy_friction_factor);
        //setting fields
        blausiusCriteria = findViewById(R.id.blausius_criteria);
        generauxCriteria = findViewById(R.id.generaux_criteria);
        kooCriteria = findViewById(R.id.koo_criteria);
        highReynoldsCriteria = findViewById(R.id.high_reynolds_criteria);
        darcyFrictionFactorTurbulentResult = findViewById(R.id.darcy_friction_factor_equation_turbulent_result);
        Button roughPipeFactorSubmit = findViewById(R.id.rough_pipe_factor_submit);
        Button laminarFlowSubmit = findViewById(R.id.submit_darcy_friction_factor_laminar_flow);
        Button turbulentFlowSubmit = findViewById(R.id.submit_darcy_friction_factor_turbulent_flow);
        roughPipeFactorResult = findViewById(R.id.darcy_friction_factor_equation_rough_pipe_result);
        //setting listeners
        blausiusCriteria.setOnClickListener(this);
        generauxCriteria.setOnClickListener(this);
        kooCriteria.setOnClickListener(this);
        highReynoldsCriteria.setOnClickListener(this);
        roughPipeFactorSubmit.setOnClickListener(this);
        laminarFlowSubmit.setOnClickListener(this);
        turbulentFlowSubmit.setOnClickListener(this);
        //creating list of factors
        createList();
        //this part of method is used to flip layouts, depending on selected radioButton
        final ViewFlipper viewFlip = findViewById(R.id.ViewFlipper);
        final RadioButton laminarFlowRadioButton = findViewById(R.id.laminar_flow_radio_button);
        final RadioButton turbulentFlowRadioButton = findViewById(R.id.turbulent_flow_radio_button);
        final RadioButton roughPipeRadioButton = findViewById(R.id.rough_pipe_radio_button);
        turbulentFlowRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (turbulentFlowRadioButton.isChecked()) {
                    viewFlip.setDisplayedChild(viewFlip.indexOfChild(findViewById(turbulent)));
                }
            }
        });
        roughPipeRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (roughPipeRadioButton.isChecked()) {
                    viewFlip.setDisplayedChild(viewFlip.indexOfChild(findViewById(rough_pipe)));
                }
            }
        });
        laminarFlowRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (laminarFlowRadioButton.isChecked()) {
                    viewFlip.setDisplayedChild(viewFlip.indexOfChild(findViewById(laminar)));
                }
            }
        });
    }

    /**
     * This method is used to create list of typical resistance factor values
     **/
    private void createList() {
        //this part is used to construct list values
        ListView resistanceFactorValues = findViewById(R.id.resistance_factor_values);
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
        ListView resistanceFactorValues = findViewById(R.id.resistance_factor_values);
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
        EditText resistanceFactorEditText = findViewById(R.id.resistance_factor_darcy_friction_factor);
        if (resistanceFactorEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_resistance_factor_toast, Toast.LENGTH_SHORT).show();
        }
        Double resistanceFactor = Double.valueOf(resistanceFactorEditText.getText().toString().trim());
        //this part of method is used to take reynolds number value and parse it to double
        EditText reynoldsNumberEditText = findViewById(R.id.reynolds_number_darcy_friction_factor_laminar);
        if (reynoldsNumberEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_reynolds_number_toast, Toast.LENGTH_SHORT).show();
        }
        Double reynoldsNumber = Double.valueOf(reynoldsNumberEditText.getText().toString().trim());

        Double darcyFrictionFactorLaminar = (resistanceFactor / reynoldsNumber);
        //this part of method is used to show the result of equation

        String darcyFrictionFactorValueLaminar = getString(R.string.lambda_suffix) + darcyFrictionFactorLaminar.toString();
        TextView darcyFrictionFactorResultLaminar = findViewById(R.id.darcy_friction_factor_equation_laminar_result);
        darcyFrictionFactorResultLaminar.setText(darcyFrictionFactorValueLaminar);
    }

    /**
     * This method is used to get Double of reynolds number, when turbulent flow option is selected
     **/
    public double turbulentFlowReynoldsNumber() {
        EditText turbulentFlowReynoldsNumberEditText = findViewById(R.id.reynolds_number_darcy_friction_factor_turbulent);
        if (turbulentFlowReynoldsNumberEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_reynolds_number_toast, Toast.LENGTH_SHORT).show();
        }
        return Double.valueOf(turbulentFlowReynoldsNumberEditText.getText().toString().trim());
    }

    /**
     * This method shows criteria, which then on click calculates Friction Factor from formula of it
     */
    public void showCriteria() {
        double turbulentFlowReynoldsNumber = turbulentFlowReynoldsNumber();

        if (turbulentFlowReynoldsNumber > 3000 & turbulentFlowReynoldsNumber < 50000) {
            blausiusCriteria.setVisibility(View.VISIBLE);
        } else {
            blausiusCriteria.setVisibility(View.GONE);
        }
        if (turbulentFlowReynoldsNumber > 4000 & turbulentFlowReynoldsNumber < 20000000) {
            generauxCriteria.setVisibility(View.VISIBLE);
        } else {
            generauxCriteria.setVisibility(View.GONE);
        }
        if (turbulentFlowReynoldsNumber > 3000 & turbulentFlowReynoldsNumber < 3000000) {
            kooCriteria.setVisibility(View.VISIBLE);
        } else {
            kooCriteria.setVisibility(View.GONE);
        }
        if (turbulentFlowReynoldsNumber > 100000 & turbulentFlowReynoldsNumber < 100000000) {
            highReynoldsCriteria.setVisibility(View.VISIBLE);
        } else {
            highReynoldsCriteria.setVisibility(View.GONE);
        }
    }

    /**
     * This method is used to get Double of Reynolds number when pipe rough option is selected
     */
    public double roughPipeFlowReynoldsNumber() {
        EditText roughPipeFlowReynoldsNumberEditText = findViewById(R.id.reynolds_number_darcy_friction_factor_rough_pipe);
        String roughPipeFlowReynoldsNumberString = roughPipeFlowReynoldsNumberEditText.getText().toString();
        if (roughPipeFlowReynoldsNumberString.isEmpty()) {
            Toast.makeText(this, R.string.input_reynolds_number_toast, Toast.LENGTH_SHORT).show();
        }
        return Double.valueOf(roughPipeFlowReynoldsNumberString);

    }

    /**
     * this method is used to get Double of pipe roughness parameter when pipe rough option is selected
     **/
    public double roughPipeFlowRoughnessParameterNumber() {
        EditText roughPipeFlowRoughnessParameterEditText = findViewById(R.id.roughness_parameter);
        String roughPipeFlowRoughnessParameterString = roughPipeFlowRoughnessParameterEditText.getText().toString();
        if (roughPipeFlowRoughnessParameterString.isEmpty()) {
            Toast.makeText(this, R.string.input_reynolds_number_toast, Toast.LENGTH_SHORT).show();
        }
        return Double.valueOf(roughPipeFlowRoughnessParameterString);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //This method is used to calculate turbulent friction factor, blausius formula on click of blausiusCriteria TextView
            case R.id.blausius_criteria:
                double blausiusCriteria = (0.3164 / Math.pow(turbulentFlowReynoldsNumber(), 0.25));
                String blausiusCriteriaString = getString(R.string.lambda_suffix) + Double.toString(blausiusCriteria);
                darcyFrictionFactorTurbulentResult.setText(blausiusCriteriaString);
                break;
            //This method is used to calculate turbulent friction factor, using generaux formula on click of generauxCriteria TextView
            case R.id.generaux_criteria:
                double generauxCriteria = (0.16 / Math.pow(turbulentFlowReynoldsNumber(), 0.16));
                String generauxCriteriaString = getString(R.string.lambda_suffix) + Double.toString(generauxCriteria);
                darcyFrictionFactorTurbulentResult.setText(generauxCriteriaString);
                break;
            //This method is used to calculate turbulent friction factor, using koo formula on click of kooCriteria TextView
            case R.id.koo_criteria:
                double kooCriteria = (0.0052 + (0.5 / Math.pow(turbulentFlowReynoldsNumber(), 0.32)));
                String kooCriteriaString = getString(R.string.lambda_suffix) + Double.toString(kooCriteria);
                darcyFrictionFactorTurbulentResult.setText(kooCriteriaString);
                break;
            //This method is used to calculate turbulent friction factor, using high reynolds number formula on click of highReynoldsCriteria TextView
            case R.id.high_reynolds_criteria:
                double highReynoldsCriteria = (0.0032 + (0.221 / Math.pow(turbulentFlowReynoldsNumber(), 0.237)));
                String highReynoldsCriteriaString = getString(R.string.lambda_suffix) + Double.toString(highReynoldsCriteria);
                darcyFrictionFactorTurbulentResult.setText(highReynoldsCriteriaString);
                break;
            //This method is used to calculate friction factor, when pipe is rough on click of submit button in rough pipe LinearLayout
            case R.id.rough_pipe_factor_submit:
                double roughnessCompound = (roughPipeFlowRoughnessParameterNumber() / 3.7);
                double reynoldsCompound = Math.pow((6.81 / roughPipeFlowReynoldsNumber()), 0.9);
                double denominator = -2 * Math.log(roughnessCompound + reynoldsCompound);
                double roughPipeFlowFactor = Math.pow((1 / denominator), 0.5);
                String roughPipeFlowFactorResult = getString(R.string.lambda_suffix) + Double.toString(roughPipeFlowFactor);
                roughPipeFactorResult.setText(roughPipeFlowFactorResult);
                break;
            //This method is used to calculate friction factor, when pipe is rough, on click of submit button in Laminar flow Linear layout
            case R.id.submit_darcy_friction_factor_laminar_flow:
                submitDarcyFrictionLaminarFlow(v);
                break;
            //This method is used to show criteria to calculate darcy friction factor, when there is turbulent flow RadioBUtton selected
            case R.id.submit_darcy_friction_factor_turbulent_flow:
                turbulentFlowReynoldsNumber();
                showCriteria();
                break;
        }

    }
}


