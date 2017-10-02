package com.github.engineer.toolbox;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static com.github.engineer.toolbox.R.layout.bernoulie_equation_menu;

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
                    intent = utils.engineeringTheoryIntent(this, getString(R.string.bernoullis_equation_menu_engineering_theory_key));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
