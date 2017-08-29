package com.example.android.airenthalpymollier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActualFluidsBernoullisEquationMenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actual_fluids_bernoullis_menu);

//setting fields
        Button setReynoldsNumberMenuButton = findViewById(R.id.set_reynolds_number_menu);
        Button setDarcyFrictionFactorMenuButton = findViewById(R.id.set_darcy_friction_factor_menu);
        Button setDarcyWeisbachEquationButton = findViewById(R.id.set_darcy_weisbach_equation_menu);
        Button setLocalFlowResistance = findViewById(R.id.set_local_flow_resistance_menu);
        //setting listeners on buttons
        setReynoldsNumberMenuButton.setOnClickListener(this);
        setDarcyFrictionFactorMenuButton.setOnClickListener(this);
        setDarcyWeisbachEquationButton.setOnClickListener(this);
        setLocalFlowResistance.setOnClickListener(this);
    }

    /**
     * setting onClick method to set menus depending on clicked button
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.set_reynolds_number_menu):
                Intent setReynoldsNumberMenuIntent = new Intent(this, ReynoldsNumberActivity.class);
                startActivity(setReynoldsNumberMenuIntent);
                break;
            case (R.id.set_darcy_friction_factor_menu):
                Intent setDarcyFrictionFactorEquationMenu = new Intent(this, DarcyFrictionFactorActivity.class);
                startActivity(setDarcyFrictionFactorEquationMenu);
                break;
            case (R.id.set_darcy_weisbach_equation_menu):
                Intent setDarcyWeisbachEquationMenu = new Intent(this, DarcyWeisbachEquationActivity.class);
                startActivity(setDarcyWeisbachEquationMenu);
                break;
            case (R.id.set_local_flow_resistance_menu):
                Intent setLocalFlowResistanceMenu = new Intent(this, LocalFlowResistanceActivity.class);
                startActivity(setLocalFlowResistanceMenu);
                break;
        }
    }
}
