package com.github.engineer.toolbox;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PerfectFluidsBernoullisEquation extends AppCompatActivity {
    //Binding Views
    @BindView(R.id.perfect_fluids_bernoullis_spinner)
    Spinner mOptionsSpinner;
    @BindView(R.id.perfect_fluids_bernoullis_equation_by_sum_of_work_submit_button)
    Button mCalculateBySumOfWork;
    @BindView(R.id.perfect_fluids_bernoullis_equation_by_sum_of_pressure_submit_button)
    Button mCalculateBySumOfPressure;
    @BindView(R.id.perfect_fluids_bernoullis_equation_by_sum_of_height_submit_button)
    Button mCalculateBySumOfHeight;
    @BindView(R.id.perfect_fluids_bernoullis_equation_result)
    TextView mResult;
    @BindView(R.id.perfect_fluids_bernoullis_mean_fluid_velocity_first_section_edit_text)
    EditText mMeanFluidVelocityFirst;
    @BindView(R.id.perfect_fluids_bernoullis_mean_fluid_velocity_second_section_edit_text)
    EditText mMeanFluidVelocitySecond;
    @BindView(R.id.perfect_fluids_bernoullis_static_value_first_section_edit_text)
    EditText mStaticValueFirst;
    @BindView(R.id.perfect_fluids_bernoullis_elevation_first_section_edit_text)
    EditText mElevationFirst;
    @BindView(R.id.perfect_fluids_bernoullis_acceleration_of_gravity_edit_text)
    EditText mAccelerationOfGravity;
    @BindView(R.id.perfect_fluids_bernoullis_static_value_second_section_edit_text)
    EditText mStaticValueSecond;
    @BindView(R.id.perfect_fluids_bernoullis_elevation_second_section_edit_text)
    EditText mElevationSecond;
    @BindView(R.id.perfect_fluids_bernoullis_density_edit_text)
    EditText mDensity;
    @BindView(R.id.perfect_fluid_beroullis_linear_layout)
    LinearLayout mLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfect_fluids_bernoullis_equation);
        //Executing binding
        ButterKnife.bind(this);
//setting option spinner
        setSpinner();
    }

    private void setSpinner() {
        //creating adapter
        ArrayAdapter mOptionsSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_perfect_fluids_bernoullis_options,
                android.R.layout.simple_dropdown_item_1line);
        //setting dropdown menu
        mOptionsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //setting adapter
        mOptionsSpinner.setAdapter(mOptionsSpinnerAdapter);
        //operating on selected option listeners
        mOptionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.first_section_dynamic_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility.
                        findViewById(R.id.perfect_fluids_bernoullis_mean_fluid_velocity_first_section_text_view).setVisibility(GONE);
                        mMeanFluidVelocityFirst.setVisibility(GONE);
                        //setting listeners
                        mCalculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                mResult.setText(getString(R.string.first_section_dynamic_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        mCalculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                mResult.setText(getString(R.string.first_section_dynamic_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        mCalculateBySumOfHeight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                mResult.setText(getString(R.string.first_section_dynamic_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));
                            }
                        });
                    }
                    if (selection.equals(getString(R.string.first_section_static_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility
                        findViewById(R.id.perfect_fluids_bernoullis_static_value_first_section_text_view).setVisibility(GONE);
                        mStaticValueFirst.setVisibility(GONE);
                        //setting listeners
                        mCalculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                mResult.setText(getString(R.string.first_section_static_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        mCalculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                mResult.setText(getString(R.string.first_section_static_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        mCalculateBySumOfHeight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                mResult.setText(getString(R.string.first_section_static_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));

                            }
                        });
                    }
                    if (selection.equals(getString(R.string.first_section_hydrostatic_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility
                        findViewById(R.id.perfect_fluids_bernoullis_elevation_first_section_text_view).setVisibility(GONE);
                        mElevationFirst.setVisibility(GONE);
                        //setting listeners
                        mCalculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                mResult.setText(getString(R.string.first_section_hydrostatic_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        mCalculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                mResult.setText(getString(R.string.first_section_hydrostatic_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        mCalculateBySumOfHeight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                mResult.setText(getString(R.string.first_section_hydrostatic_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));
                            }
                        });
                    }
                    //setting listeners on calculating second section values
                    if (selection.equals(getString(R.string.second_section_dynamic_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility
                        findViewById(R.id.perfect_fluids_bernoullis_mean_fluid_velocity_second_section_text_view).setVisibility(GONE);
                        mMeanFluidVelocitySecond.setVisibility(GONE);
                        //setting listeners
                        mCalculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                mResult.setText(getString(R.string.second_section_dynamic_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        mCalculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                mResult.setText(getString(R.string.second_section_dynamic_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        mCalculateBySumOfHeight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                mResult.setText(getString(R.string.second_section_dynamic_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));
                            }
                        });
                    }
                    if (selection.equals(getString(R.string.second_section_static_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility
                        findViewById(R.id.perfect_fluids_bernoullis_static_value_second_section_text_view).setVisibility(GONE);
                        mStaticValueSecond.setVisibility(GONE);
                        //setting listeners
                        mCalculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                mResult.setText(getString(R.string.second_section_static_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        mCalculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                mResult.setText(getString(R.string.second_section_static_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        mCalculateBySumOfHeight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                mResult.setText(getString(R.string.second_section_static_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));
                            }
                        });
                    }
                    if (selection.equals(getString(R.string.second_section_hydrostatic_value))) {
                        //Reloading views, for making sure, that not any necessary view was hidden on changing options
                        ReloadViews();
                        //hiding views for better UI visibility
                        findViewById(R.id.perfect_fluids_bernoullis_elevation_second_section_text_view).setVisibility(GONE);
                        mElevationSecond.setVisibility(GONE);
                        //setting listeners
                        mCalculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateWork());
                                mResult.setText(getString(R.string.second_section_hydrostatic_work) + getString(R.string.is_suffix) + result + getString(R.string.kilojoules_suffix));
                            }
                        });
                        mCalculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculatePressure());
                                mResult.setText(getString(R.string.second_section_hydrostatic_pressure) + getString(R.string.is_suffix) + result + getString(R.string.pascal_suffix));

                            }
                        });
                        mCalculateBySumOfHeight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String result = String.valueOf(calculateHeight());
                                mResult.setText(getString(R.string.second_section_hydrostatic_height) + getString(R.string.is_suffix) + result + getString(R.string.metres_suffix));
                            }
                        });
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Not possible situation; ignoring statement

            }
        });
    }

    private void ReloadViews() {
        for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
            View child = mLinearLayout.getChildAt(i);
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

        if (mMeanFluidVelocityFirst.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(mMeanFluidVelocityFirst.getText().toString().trim());
    }

    private double firstSectionStaticValue() {
        //This part takes static Value value from EditText field, and parse it to double
        if (mStaticValueFirst.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(mStaticValueFirst.getText().toString().trim());
    }

    private double density() {
        //This part, takes density value from EditText field and parse it to double
        if (mDensity.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_density_toast, Toast.LENGTH_SHORT).show();
            return 0;
        }
        return Double.valueOf(mDensity.getText().toString());
    }

    private double elevationFirst() {
        //This part, takes elevation value from Edit Text field and parse it to double
        if (mElevationFirst.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(mElevationFirst.getText().toString().trim());
    }

    private double accelerationOfGravity() {

        //This part, takes Acceleration of gravity value, from Edit Text field and parse it to double
        if (mAccelerationOfGravity.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.input_acceleration_of_gravity_toast, Toast.LENGTH_SHORT).show();
        }
        return Double.valueOf(mAccelerationOfGravity.getText().toString().trim());
    }

    private double meanFluidVelocitySecondSection() {
//This method takes mean fluid velocity on second section value from EditText field and parse it to double
        if (mMeanFluidVelocitySecond.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(mMeanFluidVelocitySecond.getText().toString().trim());
    }

    private double secondSectionStaticValue() {
        //This part takes static Value value from EditText field, and parse it to double
        if (mStaticValueSecond.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(mStaticValueSecond.getText().toString().trim());
    }

    private double elevationSecond() {
        //This part, takes elevation value from Edit Text field and parse it to double
        if (mElevationSecond.getText().toString().trim().length() == 0) {
            return 0;
        }
        return Double.valueOf(mElevationSecond.getText().toString());
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
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.perfect_fluids_bernoullis_equation_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
