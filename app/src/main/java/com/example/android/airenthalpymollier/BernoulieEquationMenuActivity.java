package com.example.android.airenthalpymollier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static com.example.android.airenthalpymollier.R.layout.bernoulie_equation_menu;

public class BernoulieEquationMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bernoulie_equation_menu);
    }

    /**
     * This method sets Content to Perfect Fluids Bernoullis's equation menu
     */
    public void setPerfectFluidsBernoullisEquationMenu(View view) {
        Intent setPerfectFluidsBernoullisEquationMenuIntent = new Intent(this, PerfectFluidsBernoullisEquation.class);
        startActivity(setPerfectFluidsBernoullisEquationMenuIntent);
    }

    public void setActualFluidsBernoullisEquationMenu(View view) {
        Intent setActualFluidsBernoullisEquationMenuIntent = new Intent(this, ActualFluidsBernoullisEquationMenuActivity.class);
        startActivity(setActualFluidsBernoullisEquationMenuIntent);
    }

}
