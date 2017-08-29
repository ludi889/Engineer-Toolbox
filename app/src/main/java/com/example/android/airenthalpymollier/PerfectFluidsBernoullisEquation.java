package com.example.android.airenthalpymollier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PerfectFluidsBernoullisEquation extends AppCompatActivity {
    private Spinner optionsSpinner;
    private Button calculateBySumOfWork;
    private Button calculateBySumOfPressure;
    private Button calculateBySumOfHeights;
    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfect_fluids_bernoullis_equation);

//setting Fields
        optionsSpinner = findViewById(R.id.perfect_fluids_spinner);
        calculateBySumOfWork = findViewById(R.id.submit_perfect_fluids_bernoullis_equation_by_sum_of_work);
        calculateBySumOfPressure = findViewById(R.id.submit_perfect_fluids_bernoullis_equation_by_sum_of_pressure);
        calculateBySumOfHeights = findViewById(R.id.submit_perfect_fluids_bernoullis_equation_by_sum_of_heights);
        resultTextView = findViewById(R.id.perfect_fluids_bernoullis_equation_result);
//setting option spinner
        setSpinner();
    }

    private void setSpinner() {
        //creating adapter
        ArrayAdapter optionsSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_perfect_fluids_bernoullis_options,
                android.R.layout.simple_dropdown_item_1line);
        //setting dropdown menu
        optionsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //setting adapter
        optionsSpinner.setAdapter(optionsSpinnerAdapter);
        //operating on selected option listeners
        optionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.first_section_dynamic_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility.
                        findViewById(R.id.first_section_velocity).setVisibility(GONE);
                        findViewById(R.id.mean_fluid_velocity_first_section_perfect_fluids).setVisibility(GONE);
                        //setting listeners
                        calculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                resultTextView.setText(getString(R.string.first_section_dynamic_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        calculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                resultTextView.setText(getString(R.string.first_section_dynamic_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        calculateBySumOfHeights.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                resultTextView.setText(getString(R.string.first_section_dynamic_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));
                            }
                        });
                    }
                    if (selection.equals(getString(R.string.first_section_static_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility
                        findViewById(R.id.first_section_static_value).setVisibility(GONE);
                        findViewById(R.id.static_value_first_section_perfect_fluids).setVisibility(GONE);
                        //setting listeners
                        calculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                resultTextView.setText(getString(R.string.first_section_static_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        calculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                resultTextView.setText(getString(R.string.first_section_static_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        calculateBySumOfHeights.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                resultTextView.setText(getString(R.string.first_section_static_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));

                            }
                        });
                    }
                    if (selection.equals(getString(R.string.first_section_hydrostatic_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility
                        findViewById(R.id.first_section_elevation).setVisibility(GONE);
                        findViewById(R.id.elevation_first_section_perfect_fluids).setVisibility(GONE);
                        //setting listeners
                        calculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                resultTextView.setText(getString(R.string.first_section_hydrostatic_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        calculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                resultTextView.setText(getString(R.string.first_section_hydrostatic_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        calculateBySumOfHeights.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                resultTextView.setText(getString(R.string.first_section_hydrostatic_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));
                            }
                        });
                    }
                    //setting listeners on calculating second section values
                    if (selection.equals(getString(R.string.second_section_dynamic_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility
                        findViewById(R.id.second_section_velocity).setVisibility(GONE);
                        findViewById(R.id.mean_fluid_velocity_second_section_perfect_fluids).setVisibility(GONE);
                        //setting listeners
                        calculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                resultTextView.setText(getString(R.string.second_section_dynamic_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        calculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                resultTextView.setText(getString(R.string.second_section_dynamic_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        calculateBySumOfHeights.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                resultTextView.setText(getString(R.string.second_section_dynamic_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));
                            }
                        });
                    }
                    if (selection.equals(getString(R.string.second_section_static_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility
                        findViewById(R.id.second_section_static_value).setVisibility(GONE);
                        findViewById(R.id.static_value_second_section_perfect_fluids).setVisibility(GONE);
                        //setting listeners
                        calculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                resultTextView.setText(getString(R.string.second_section_static_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        calculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                resultTextView.setText(getString(R.string.second_section_static_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        calculateBySumOfHeights.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                resultTextView.setText(getString(R.string.second_section_static_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));
                            }
                        });
                    }
                    if (selection.equals(getString(R.string.second_section_hydrostatic_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility
                        findViewById(R.id.second_section_elevation).setVisibility(GONE);
                        findViewById(R.id.elevation_second_section_perfect_fluids).setVisibility(GONE);
                        //setting listeners
                        calculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                resultTextView.setText(getString(R.string.second_section_hydrostatic_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        calculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                resultTextView.setText(getString(R.string.second_section_hydrostatic_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        calculateBySumOfHeights.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                resultTextView.setText(getString(R.string.second_section_hydrostatic_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));
                            }
                        });
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO: 2017-08-04  

            }
        });
    }

    private void ReloadViews() {
        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View child = linearLayout.getChildAt(i);
            if (child.getVisibility() == GONE) {
                child.setVisibility(VISIBLE);
            }
        }

    }

    private Double calculateHeight() {
        Double result = calculateFirstSectionHeight() - calculateSecondSectionHeight();
        if (result < 0) {
            result = result * -1;
        }
        return result;
    }

    private Double calculatePressure() {
        Double result = calculateFirstSectionPressure() - calculateSecondSectionPressure();
        if (result < 0) {
            result = result * -1;
        }
        return result;
    }

    private Double calculateWork() {
        Double result = calculateFirstSectionWork() - calculateSecondSectionWork();
        if (result < 0) {
            result = result * -1;
        }
        return result;
    }

    /**
     * This method is used to submit Perfect Fluids Bernoullis Equation by sum of work, and return a result of it
     */
    private double meanFluidVelocityFirstSection() {

        EditText meanFluidVelocityFirstEditText = findViewById(R.id.mean_fluid_velocity_first_section_perfect_fluids);
        if (meanFluidVelocityFirstEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(meanFluidVelocityFirstEditText.getText().toString().trim());
    }

    private double firstSectionStaticValue() {
        //This part takes static Value value from EditText field, and parse it to double
        EditText staticValueFirstEditText = findViewById(R.id.static_value_first_section_perfect_fluids);
        if (staticValueFirstEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(staticValueFirstEditText.getText().toString().trim());
    }

    private double density() {
        //This part, takes density value from EditText field and parse it to double
        EditText densityEditText = findViewById(R.id.density_perfect_fluids);
        if (densityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_density_toast, Toast.LENGTH_SHORT).show();
            return 0;
        }
        return Double.valueOf(densityEditText.getText().toString());
    }

    private double elevationFirst() {
        //This part, takes elevation value from Edit Text field and parse it to double
        final EditText elevationFirstEditText = findViewById(R.id.elevation_first_section_perfect_fluids);
        if (elevationFirstEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(elevationFirstEditText.getText().toString().trim());
    }

    private double accelerationOfGravity() {

        //This part, takes Acceleration of gravity value, from Edit Text field and parse it to double
        EditText accelerationOfGravityEditText = findViewById(R.id.acceleration_of_gravity_perfect_fluids);
        if (accelerationOfGravityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_acceleration_of_gravity_toast, Toast.LENGTH_SHORT).show();
        }
        return Double.valueOf(accelerationOfGravityEditText.getText().toString().trim());
    }

    private double meanFluidVelocitySecondSection() {
//This method takes mean fluid velocity on second section value from EditText field and parse it to double
        EditText meanFluidVelocitySecondEditText = findViewById(R.id.mean_fluid_velocity_second_section_perfect_fluids);
        if (meanFluidVelocitySecondEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(meanFluidVelocitySecondEditText.getText().toString().trim());
    }

    private double secondSectionStaticValue() {
        EditText staticValueSecondEditText = findViewById(R.id.static_value_second_section_perfect_fluids);
        //This part takes static Value value from EditText field, and parse it to double
        if (staticValueSecondEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(staticValueSecondEditText.getText().toString().trim());
    }

    private double elevationSecond() {
        //This part, takes elevation value from Edit Text field and parse it to double
        EditText elevationSecondEditText = findViewById(R.id.elevation_second_section_perfect_fluids);
        if (elevationSecondEditText.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(elevationSecondEditText.getText().toString());
    }

    /**
     * This method is used to calculate first section sum of work
     */
    private double calculateFirstSectionWork() {
        double firstSectionDynamicWork = Math.pow(meanFluidVelocityFirstSection(), 2) / 2;
        double firstSectionStaticWork = firstSectionStaticValue() / density();
        double firstSectionHydrostaticWork = elevationFirst() * accelerationOfGravity();
        return firstSectionDynamicWork + firstSectionStaticWork + firstSectionHydrostaticWork;

    }

    /**
     * This method is used to calculate second section sum of work
     */
    private double calculateSecondSectionWork() {
        double secondSectionDynamicWork = Math.pow(meanFluidVelocitySecondSection(), 2) / 2;
        double secondSectionStaticWork = secondSectionStaticValue() / density();
        double secondSectionHydrostaticWork = elevationSecond() * accelerationOfGravity();
        return secondSectionDynamicWork + secondSectionStaticWork + secondSectionHydrostaticWork;
    }

    /**
     * This method is used to calculate first section sum of pressure
     */
    private double calculateFirstSectionPressure() {
        double firstSectionDynamicPressure = Math.pow(meanFluidVelocityFirstSection(), 2) * density() / 2;
        double firstSectionHydrostaticPressure = density() * accelerationOfGravity() * elevationFirst();
        return firstSectionDynamicPressure + firstSectionStaticValue() + firstSectionHydrostaticPressure;

    }

    /**
     * This method is used to calculate second section sum of pressure
     */
    private double calculateSecondSectionPressure() {
        double secondSectionDynamicPressure = Math.pow(meanFluidVelocitySecondSection(), 2) * density() / 2;
        double secondSectionHydrostaticPressure = density() * accelerationOfGravity() * elevationSecond();
        return secondSectionDynamicPressure + secondSectionStaticValue() + secondSectionHydrostaticPressure;
    }

    /**
     * This method is used to calculate first section sum of heights
     */
    private double calculateFirstSectionHeight() {
        double firstSectionDynamicHeight = Math.pow(meanFluidVelocityFirstSection(), 2) / accelerationOfGravity();
        double firstSectionStaticHeight = firstSectionStaticValue() / (density() * accelerationOfGravity());
        double firstSectionHydrostaticHeight = elevationFirst();
        return firstSectionDynamicHeight + firstSectionStaticHeight + firstSectionHydrostaticHeight;

    }

    /**
     * This method is used to calculate second section sum of heights
     */
    private double calculateSecondSectionHeight() {
        double secondSectionDynamicHeight = Math.pow(meanFluidVelocitySecondSection(), 2) / accelerationOfGravity();
        double secondSectionStaticHeight = secondSectionStaticValue() / (density() * accelerationOfGravity());
        double secondSectionHydrostaticHeight = elevationSecond();
        return secondSectionDynamicHeight + secondSectionStaticHeight + secondSectionHydrostaticHeight;
    }

}
