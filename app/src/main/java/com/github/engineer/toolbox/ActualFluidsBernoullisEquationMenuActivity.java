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

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActualFluidsBernoullisEquationMenuActivity extends AppCompatActivity implements View.OnClickListener {
    //binding views
    @BindView(R.id.set_reynolds_number_menu)
    Button mSetReynoldsNumberMenuButton;
    @BindView(R.id.set_darcy_friction_factor_menu)
    Button mSetDarcyFrictionFactorMenuButton;
    @BindView(R.id.set_darcy_weisbach_equation_menu)
    Button mSetDarcyWeisbachEquationButton;
    @BindView(R.id.set_local_flow_resistance_menu)
    Button mSetLocalFlowResistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actual_fluids_bernoullis_menu);
        ButterKnife.bind(this);
        //setting listeners on buttons
        mSetReynoldsNumberMenuButton.setOnClickListener(this);
        mSetDarcyFrictionFactorMenuButton.setOnClickListener(this);
        mSetDarcyWeisbachEquationButton.setOnClickListener(this);
        mSetLocalFlowResistance.setOnClickListener(this);
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
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.actual_fluids_menu_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
