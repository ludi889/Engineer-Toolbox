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

public class DarcyFrictionFactorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.darcy_friction_factor);
        final TextView blausiusCriteria = (TextView) findViewById(R.id.blausius_criteria);
        final TextView generauxCriteria = (TextView) findViewById(R.id.generaux_criteria);
        final TextView kooCriteria = (TextView) findViewById(R.id.koo_criteria);
        final TextView highReynoldsCriteria = (TextView) findViewById(R.id.high_reynolds_criteria);
        final TextView darcyFrictionFactorTurbulentResult = (TextView) findViewById(R.id.darcy_friction_factor_equation_turbulent_result);
        final Button roughPipeFactorSubmit = (Button) findViewById(R.id.rough_pipe_factor_submit);
        final TextView roughPipeFactorResult = (TextView) findViewById(R.id.darcy_friction_factor_equation_rough_pipe_result);
        //setting listeners
        blausiusCriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double blausiusCriteria = (0.3164 / Math.pow(turbulentFlowReynoldsNumberGetter(), 0.25));
                String blausiusCriteriaString = "λ=" + Double.toString(blausiusCriteria);
                darcyFrictionFactorTurbulentResult.setText(blausiusCriteriaString);
            }
        });
        generauxCriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double generauxCriteria = (0.16 / Math.pow(turbulentFlowReynoldsNumberGetter(), 0.16));
                String generauxCriteriaString = "λ=" + Double.toString(generauxCriteria);
                darcyFrictionFactorTurbulentResult.setText(generauxCriteriaString);
            }
        });
        kooCriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double kooCriteria = (0.0052 + (0.5 / Math.pow(turbulentFlowReynoldsNumberGetter(), 0.32)));
                String kooCriteriaString = "λ=" + Double.toString(kooCriteria);
                darcyFrictionFactorTurbulentResult.setText(kooCriteriaString);
            }
        });
        highReynoldsCriteria.setOnClickListener(new View.OnClickListener() {
            ;

            @Override
            public void onClick(View v) {
                double highReynoldsCriteria = (0.0032 + (0.221 / Math.pow(turbulentFlowReynoldsNumberGetter(), 0.237)));
                String highReynoldsCriteriaString = "λ=" + Double.toString(highReynoldsCriteria);
                darcyFrictionFactorTurbulentResult.setText(highReynoldsCriteriaString);
            }
        });
        roughPipeFactorSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double roughnessCompound = (roughPipeFlowRoughnessParameterNumberGetter() / 3.7);
                double reynoldsCompound = Math.pow((6.81 / roughPipeFlowReynoldsNumberGetter()), 0.9);
                double denominator = -2 * Math.log(roughnessCompound + reynoldsCompound);
                double roughPipeFlowFactor = Math.pow((1 / denominator), 0.5);

                String roughPipeFlowFactorResult = "λ=" + Double.toString(roughPipeFlowFactor);
                roughPipeFactorResult.setText(roughPipeFlowFactorResult);


            }
        });


        //this part is used to construct list values
        ListView resistanceFactorValues = (ListView) findViewById(R.id.resistance_factor_values);
        ArrayAdapter<String> adapter;
        String resistanceValuesString[] = {"Circular Section = 64", "Square Section = 57", "Annular Section = 96", "Rectangle with side ratio 1:2 = 59"};
        ArrayList<String> resistanceValues = new ArrayList<>();
        resistanceValues.addAll(Arrays.asList(resistanceValuesString));

        adapter = new ArrayAdapter<>(this, R.layout.resistance_factor_values, resistanceValues);
        resistanceFactorValues.setAdapter(adapter);
        //viewFlip.setDisplayedChild(viewFlip.indexOfChild(findViewById(rough_pipe)));
        //this part of method is used to flip layout to turbulent flow
        final ViewFlipper viewFlip = (ViewFlipper) findViewById(R.id.ViewFlipper);
        final RadioButton laminarFlowRadioButton = (RadioButton) findViewById(R.id.laminar_flow_radio_button);
        final RadioButton turbulentFlowRadioButton = (RadioButton) findViewById(R.id.turbulent_flow_radio_button);
        final RadioButton roughPipeRadioButton = (RadioButton) findViewById(R.id.rough_pipe_radio_button);
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


    //this method is used to expand list of factor values on click
    public void expandResistanceFactorValues(View view) {
        ListView resistanceFactorValues = (ListView) findViewById(R.id.resistance_factor_values);


        if (resistanceFactorValues.getVisibility() == View.GONE) {
            resistanceFactorValues.setVisibility(View.VISIBLE);
        } else {
            resistanceFactorValues.setVisibility(View.GONE);
        }

    }


    //this part of method is used to set content to reynolds number calculator
    public void setReynoldsNumberMenu(View view) {
        Intent setReynoldsNumberMenuIntent = new Intent(this, ReynoldsNumberActivity.class);
        startActivity(setReynoldsNumberMenuIntent);
    }

    public void submitDarcyFrictionLaminarSmoothPipeEquation(View view) {
        //this part of method is used to take resistance factor value, and parse it to double
        EditText resistanceFactorEditText = (EditText) findViewById(R.id.resistance_factor_darcy_friction_factor);
        String resistanceFactorString = resistanceFactorEditText.getText().toString();
        if (resistanceFactorString.isEmpty()) {
            Toast.makeText(this, "You have to input resistance factor value ", Toast.LENGTH_SHORT).show();
        }
        Double resistanceFactor = Double.valueOf(resistanceFactorString);
        //this part of method is used to take reynolds number value and parse it to double
        EditText reynoldsNumberEditText = (EditText) findViewById(R.id.reynolds_number_darcy_friction_factor_laminar);
        String reynoldsNumberString = reynoldsNumberEditText.getText().toString();
        if (reynoldsNumberString.isEmpty()) {
            Toast.makeText(this, "You have to input resistance factor value ", Toast.LENGTH_SHORT).show();
        }
        Double reynoldsNumber = Double.valueOf(reynoldsNumberString);

        Double darcyFrictionFactorLaminar = calculateDarcyFrictionLaminarSmoothPipeFactor(resistanceFactor, reynoldsNumber);
        //this part of method is used to show the result of equation

        String darcyFrictionFactorValueLaminar = "λ=" + darcyFrictionFactorLaminar.toString();
        TextView darcyFrictionFactorResultLaminar = (TextView) findViewById(R.id.darcy_friction_factor_equation_laminar_result);
        darcyFrictionFactorResultLaminar.setText(darcyFrictionFactorValueLaminar);
    }

    private double calculateDarcyFrictionLaminarSmoothPipeFactor(double resistanceFactor, double reynoldsNumber) {
        return (resistanceFactor / reynoldsNumber);
    }

    /**
     * This method shows criteria, which then on click calculates it
     */
    public double turbulentFlowReynoldsNumberGetter() {
        EditText turbulentFlowReynoldsNumberEditText = (EditText) findViewById(R.id.reynolds_number_darcy_friction_factor_turbulent);
        String turbulentFlowReynoldsNumberString = turbulentFlowReynoldsNumberEditText.getText().toString();
        if (turbulentFlowReynoldsNumberString.isEmpty()) {
            Toast.makeText(this, "You have to input Reynolds Number value", Toast.LENGTH_SHORT).show();
        }
        return Double.valueOf(turbulentFlowReynoldsNumberString);


    }

    public void showCriteria() {
        final TextView blausiusCriteria = (TextView) findViewById(R.id.blausius_criteria);
        final TextView generauxCriteria = (TextView) findViewById(R.id.generaux_criteria);
        final TextView kooCriteria = (TextView) findViewById(R.id.koo_criteria);
        final TextView highReynoldsCriteria = (TextView) findViewById(R.id.high_reynolds_criteria);

        double turbulentFlowReynoldsNumber = turbulentFlowReynoldsNumberGetter();

        if (turbulentFlowReynoldsNumber > 3000 & turbulentFlowReynoldsNumber < 50000) {
            blausiusCriteria.setVisibility(View.VISIBLE);
        }
        if (turbulentFlowReynoldsNumber > 4000 & turbulentFlowReynoldsNumber < 20000000) {
            generauxCriteria.setVisibility(View.VISIBLE);
        }
        if (turbulentFlowReynoldsNumber > 3000 & turbulentFlowReynoldsNumber < 3000000) {
            kooCriteria.setVisibility(View.VISIBLE);
        }
        if (turbulentFlowReynoldsNumber > 100000 & turbulentFlowReynoldsNumber < 100000000) {
            highReynoldsCriteria.setVisibility(View.VISIBLE);
        }
    }

    public void turbulentSubmit(View view) {
        turbulentFlowReynoldsNumberGetter();
        showCriteria();


    }

    public double roughPipeFlowReynoldsNumberGetter() {
        EditText roughPipeFlowReynoldsNumberEditText = (EditText) findViewById(R.id.reynolds_number_darcy_friction_factor_rough_pipe);
        String roughPipeFlowReynoldsNumberString = roughPipeFlowReynoldsNumberEditText.getText().toString();
        if (roughPipeFlowReynoldsNumberString.isEmpty()) {
            Toast.makeText(this, "You have to input Reynolds Number value", Toast.LENGTH_SHORT).show();
        }
        return Double.valueOf(roughPipeFlowReynoldsNumberString);

    }

    public double roughPipeFlowRoughnessParameterNumberGetter() {
        EditText roughPipeFlowRoughnessParameterEditText = (EditText) findViewById(R.id.roughness_parameter);
        String roughPipeFlowRoughnessParameterString = roughPipeFlowRoughnessParameterEditText.getText().toString();
        if (roughPipeFlowRoughnessParameterString.isEmpty()) {
            Toast.makeText(this, "You have to input Reynolds Number value", Toast.LENGTH_SHORT).show();
        }
        return Double.valueOf(roughPipeFlowRoughnessParameterString);

    }

}


