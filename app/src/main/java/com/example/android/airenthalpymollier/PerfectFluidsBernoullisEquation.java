package com.example.android.airenthalpymollier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PerfectFluidsBernoullisEquation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfect_fluids_bernoullis_equation);

        Spinner spinner = (Spinner) findViewById(R.id.calculations_spinner);
        final ArrayList<String> optionsList = new ArrayList<>();
        optionsList.add("First section dynamic pressure");
        optionsList.add("First section static pressure");
        optionsList.add("First section hydrostatic pressure");
        optionsList.add("Second section dynamic pressure");
        optionsList.add("Second section static pressure");
        optionsList.add("Second section hydrostatic pressure");
        optionsList.add("Static pressures difference");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, optionsList);
        spinner.setAdapter(adapter);
        final int optionIndex = spinner.getSelectedItemPosition();

        final CheckBox staticPressureDifferenceCheckBox = (CheckBox) findViewById(R.id.static_pressure_difference_checkbox);
        final TextView firstStaticPressureTextView = (TextView) findViewById(R.id.first_section_static_pressure);
        final TextView secondStaticPressureTextView = (TextView) findViewById(R.id.second_section_static_pressure);
        final EditText staticPressureFirstEditText = (EditText) findViewById(R.id.static_pressure_first_section_perfect_fluids_by_sum_of_work);
        final EditText staticPressureSecondEditText = (EditText) findViewById(R.id.static_pressure_second_section_perfect_fluids_by_sum_of_work);
        final TextView staticPressureDifferenceTextView = (TextView) findViewById(R.id.static_pressure_difference_textView);
        final EditText staticPressureDifferenceEditText = (EditText) findViewById(R.id.static_pressure_difference_edittext);
        staticPressureDifferenceCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (staticPressureDifferenceCheckBox.isChecked()) {
                    firstStaticPressureTextView.setVisibility(View.GONE);
                    secondStaticPressureTextView.setVisibility(View.GONE);
                    staticPressureFirstEditText.setVisibility(View.GONE);
                    staticPressureSecondEditText.setVisibility(View.GONE);
                    staticPressureDifferenceTextView.setVisibility(View.VISIBLE);
                    staticPressureDifferenceEditText.setVisibility(View.VISIBLE);
                    optionsList.remove(1);
                    optionsList.remove(4);

                } else {
                    firstStaticPressureTextView.setVisibility(View.VISIBLE);
                    secondStaticPressureTextView.setVisibility(View.VISIBLE);
                    staticPressureFirstEditText.setVisibility(View.VISIBLE);
                    staticPressureSecondEditText.setVisibility(View.VISIBLE);
                    staticPressureDifferenceTextView.setVisibility(View.GONE);
                    staticPressureDifferenceEditText.setVisibility(View.GONE);
                }
            }
        });


        TextView calculateBySumOfWork = (TextView) findViewById(R.id.submit_perfect_fluids_bernoullis_equation_by_sum_of_work);
        TextView calculateBySumOfPressure = (TextView) findViewById(R.id.submit_perfect_fluids_bernoullis_equation_by_sum_of_pressure);
        TextView calculateBySumOfHeights = (TextView) findViewById(R.id.submit_perfect_fluids_bernoullis_equation_by_sum_of_heights);
        final TextView result = (TextView) findViewById(R.id.perfect_fluids_bernoullis_equation_result);


        calculateBySumOfWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double firstSectionDynamicWork = Math.pow(meanFluidVelocityFirstSection(), 2) / 2;
                double firstSectionStaticWork = firstSectionStaticPressure() / density();
                double firstSectionHydrostaticWork = elevationFirst() * accelerationOfGravity();
                double secondSectionDynamicWork = Math.pow(meanFluidVelocitySecondSection(), 2) / 2;
                double secondSectionStaticWork = secondSectionStaticPressure() / density();
                double secondSectionHydrostaticWork = elevationSecond() * accelerationOfGravity();
                double firstSectionWork = firstSectionDynamicWork + firstSectionStaticWork + firstSectionHydrostaticWork;
                double secondSectionWork = secondSectionDynamicWork + secondSectionStaticWork + secondSectionHydrostaticWork;
                double resultValue = 0;
                if (!staticPressureDifferenceCheckBox.isChecked()) {
                    resultValue = secondSectionWork - firstSectionWork;
                    if (resultValue < 0) {
                        resultValue = resultValue * -1;
                    }
                }
                if (staticPressureDifferenceCheckBox.isChecked()) {
                    if (optionIndex == 0) {
                        resultValue = secondSectionWork - staticPressureDifference() - firstSectionHydrostaticWork;
                    }
                    if (optionIndex == 2) {
                        resultValue = secondSectionWork - staticPressureDifference() - firstSectionDynamicWork;
                    }
                    if (optionIndex == 3) {
                        resultValue = firstSectionWork + staticPressureDifference() - secondSectionHydrostaticWork;
                    }
                    if (optionIndex == 5) {
                        resultValue = firstSectionWork + staticPressureDifference() - secondSectionDynamicWork;
                    }
                    if (optionIndex == 6) {
                        resultValue = secondSectionWork - firstSectionWork;
                    }

                }
                String resultString = Double.toString(resultValue) + "J/kg";
                result.setText(resultString);

            }
        });
        calculateBySumOfPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double firstSectionDynamicPressure = Math.pow(meanFluidVelocityFirstSection(), 2) * density() / 2;
                double firstSectionHydrostaticPressure = density() * accelerationOfGravity() * elevationFirst();
                double secondSectionDynamicPressure = Math.pow(meanFluidVelocitySecondSection(), 2) * density() / 2;
                double secondSectionHydrostaticPressure = density() * accelerationOfGravity() * elevationSecond();
                double firstSectionPressure = firstSectionDynamicPressure + firstSectionStaticPressure() + firstSectionHydrostaticPressure;
                double secondSectionPressure = secondSectionDynamicPressure + secondSectionStaticPressure() + secondSectionHydrostaticPressure;
                double resultValue = 0;

                if (!staticPressureDifferenceCheckBox.isChecked()) {
                    resultValue = secondSectionPressure - firstSectionPressure;
                    if (resultValue < 0) {
                        resultValue = resultValue * -1;
                    }
                }
                if (staticPressureDifferenceCheckBox.isChecked()) {
                    if (optionIndex == 0) {
                        resultValue = secondSectionPressure - staticPressureDifference() - firstSectionHydrostaticPressure;
                    }
                    if (optionIndex == 2) {
                        resultValue = secondSectionPressure - staticPressureDifference() - firstSectionDynamicPressure;
                    }
                    if (optionIndex == 3) {
                        resultValue = firstSectionPressure + staticPressureDifference() - secondSectionHydrostaticPressure;
                    }
                    if (optionIndex == 5) {
                        resultValue = firstSectionPressure + staticPressureDifference() - secondSectionDynamicPressure;
                    }
                    if (optionIndex == 6) {
                        resultValue = secondSectionPressure - firstSectionPressure;
                    }
                }
                String resultString = Double.toString(resultValue) + "Pa";
                result.setText(resultString);
            }
        });
        calculateBySumOfHeights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double firstSectionDynamicHeight = Math.pow(meanFluidVelocityFirstSection(), 2) / accelerationOfGravity();
                double firstSectionStaticHeight = firstSectionStaticPressure() / (density() * accelerationOfGravity());
                double firstSectionHydrostaticHeight = elevationFirst();
                double secondSectionDynamicHeight = Math.pow(meanFluidVelocitySecondSection(), 2) / accelerationOfGravity();
                double secondSectionStaticHeight = secondSectionStaticPressure() / (density() * accelerationOfGravity());
                double secondSectionHydrostaticHeight = elevationSecond();
                double firstSectionHeight = firstSectionDynamicHeight + firstSectionStaticHeight + firstSectionHydrostaticHeight;
                double secondSectionHeight = secondSectionDynamicHeight + secondSectionStaticHeight + secondSectionHydrostaticHeight;
                double resultValue = 0;
                if (!staticPressureDifferenceCheckBox.isChecked()) {
                    resultValue = secondSectionHeight - firstSectionHeight;
                    if (resultValue < 0) {
                        resultValue = resultValue * -1;
                    }
                }
                if (staticPressureDifferenceCheckBox.isChecked()) {
                    if (optionIndex == 0) {
                        resultValue = secondSectionHeight - staticPressureDifference() - firstSectionHydrostaticHeight;
                    }
                    if (optionIndex == 2) {
                        resultValue = secondSectionHeight - staticPressureDifference() - firstSectionDynamicHeight;
                    }
                    if (optionIndex == 3) {
                        resultValue = firstSectionHeight + staticPressureDifference() - secondSectionHydrostaticHeight;
                    }
                    if (optionIndex == 5) {
                        resultValue = firstSectionHeight + staticPressureDifference() - secondSectionDynamicHeight;
                    }
                    if (optionIndex == 6) {
                        resultValue = secondSectionHeight - firstSectionHeight;
                    }
                }
                String resultString = Double.toString(resultValue) + "m";
                result.setText(resultString);

            }
        });
    }

    /**
     * This method is used to submit Perfect Fluids Bernoullis Equation by sum of work, and return a result of it
     */
    public double meanFluidVelocityFirstSection() {

        EditText meanFluidVelocityFirstEditText = (EditText) findViewById(R.id.mean_fluid_velocity_first_section_perfect_fluids_by_sum_of_work);
        String meanFluidVelocityFirstString = meanFluidVelocityFirstEditText.getText().toString();
        if (meanFluidVelocityFirstString.isEmpty()) {
            return 0;
        }
        double meanFluidVelocityFirst = Double.valueOf(meanFluidVelocityFirstString);
        return meanFluidVelocityFirst;
    }

    final public double firstSectionStaticPressure() {
        EditText staticPressureFirstEditText = (EditText) findViewById(R.id.static_pressure_first_section_perfect_fluids_by_sum_of_work);
        //This part takes static pressure value from EditText field, and parse it to double
        String staticPressureFirstString = staticPressureFirstEditText.getText().toString();
        if (staticPressureFirstString.isEmpty()) {
            return 0;
        }
        double staticPressureFirst = Double.valueOf(staticPressureFirstString);
        return staticPressureFirst;
    }

    public double density() {


        //This part, takes density value from EditText field and parse it to double
        EditText densityEditText = (EditText) findViewById(R.id.density_perfect_fluids_by_sum_of_work);
        String densityString = densityEditText.getText().toString();
        if (densityString.isEmpty()) {
            Toast.makeText(this, "You have to input density value", Toast.LENGTH_SHORT).show();
        }
        double density = Double.valueOf(densityString);
        return density;
    }

    public double elevationFirst() {
        //This part, takes elevation value from Edit Text field and parse it to double
        final EditText elevationFirstEditText = (EditText) findViewById(R.id.elevation_first_section_perfect_fluids_by_sum_of_work);
        String elevationFirstString = elevationFirstEditText.getText().toString();
        if (elevationFirstString.isEmpty()) {
            return 0;
        }
        double elevationFirst = Double.valueOf(elevationFirstString);
        return elevationFirst;
    }

    public double accelerationOfGravity() {

        //This part, takes Acceleration of gravity value, from Edit Text field and parse it to double
        EditText accelerationOfGravityEditText = (EditText) findViewById(R.id.acceleration_of_gravity_perfect_fluids_by_sum_of_work);
        String accelerationOfGravityString = accelerationOfGravityEditText.getText().toString();
        if (accelerationOfGravityString.isEmpty()) {
            Toast.makeText(this, "You have to input acceleration of gravity value", Toast.LENGTH_SHORT).show();
        }
        double accelerationOfGravity = Double.valueOf(accelerationOfGravityString);
        return accelerationOfGravity;
    }

    public double meanFluidVelocitySecondSection() {

        EditText meanFluidVelocitySecondEditText = (EditText) findViewById(R.id.mean_fluid_velocity_second_section_perfect_fluids_by_sum_of_work);
        String meanFluidVelocitySecondString = meanFluidVelocitySecondEditText.getText().toString();
        if (meanFluidVelocitySecondString.isEmpty()) {
            return 0;
        }
        double meanFluidVelocitySecond = Double.valueOf(meanFluidVelocitySecondString);
        return meanFluidVelocitySecond;
    }

    public double secondSectionStaticPressure() {
        EditText staticPressureSecondEditText = (EditText) findViewById(R.id.static_pressure_second_section_perfect_fluids_by_sum_of_work);
        //This part takes static pressure value from EditText field, and parse it to double
        String staticPressureSecondString = staticPressureSecondEditText.getText().toString();
        if (staticPressureSecondString.isEmpty()) {
            return 0;
        }
        double staticPressureSecond = Double.valueOf(staticPressureSecondString);
        return staticPressureSecond;
    }

    public double elevationSecond() {
        //This part, takes elevation value from Edit Text field and parse it to double
        EditText elevationSecondEditText = (EditText) findViewById(R.id.elevation_second_section_perfect_fluids_by_sum_of_work);
        String elevationSecondString = elevationSecondEditText.getText().toString();
        if (elevationSecondString.isEmpty()) {
            return 0;
        }
        double elevationSecond = Double.valueOf(elevationSecondString);
        return elevationSecond;
    }

    final public double staticPressureDifference() {
        EditText staticPressureDifferenceEditText = (EditText) findViewById(R.id.static_pressure_difference_edittext);
        String staticPressureDifferenceString = staticPressureDifferenceEditText.getText().toString();
        if (staticPressureDifferenceString.isEmpty()) {
            return 0;
        }
        double staticPressureDifference = Double.valueOf(staticPressureDifferenceString);
        return staticPressureDifference;
    }

    public class calculations {

    }

}
