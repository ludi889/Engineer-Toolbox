package com.example.android.airenthalpymollier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActualFluidsBernoullisEquationMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actual_fluids_bernoullis_menu);
    }
    public void setReynoldsNumberMenu (View view){
        Intent setReynoldsNumberMenuIntent = new Intent(this, ReynoldsNumberActivity.class);
        startActivity(setReynoldsNumberMenuIntent);
    }
    public void setDarcyWeisbachEquationMenu (View view){
        Intent setDarcyWeisbachEquationMenu = new Intent(this, DarcyWeisbachEquationActivity.class);
        startActivity(setDarcyWeisbachEquationMenu);
    }
    public void setLocalFlowResistanceEquationMenu (View view){
        Intent setLocalFlowResistanceMenu = new Intent (this, LocalFlowResistanceActivity.class);
        startActivity(setLocalFlowResistanceMenu);
    }
    public void setDarcyFrictionFactorEquationMenu (View view){
        Intent setDarcyFrictionFactorEquationMenu = new Intent (this, DarcyFrictionFactorActivity.class);
        startActivity(setDarcyFrictionFactorEquationMenu);
    }

}
