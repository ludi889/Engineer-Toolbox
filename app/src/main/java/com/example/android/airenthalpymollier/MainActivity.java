package com.example.android.airenthalpymollier;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.style.TtsSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.util.Locale;

import static android.R.attr.duration;
import static android.R.attr.onClick;
import static android.R.attr.value;
import static android.R.attr.valueFrom;
import static android.R.id.edit;
import static android.R.id.input;
import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.android.airenthalpymollier.R.id.Air_Pressure;
import static com.example.android.airenthalpymollier.R.id.Relative_Humidity;
import static com.example.android.airenthalpymollier.R.id.Specific_Heat_Dry_Air;
import static com.example.android.airenthalpymollier.R.id.input_temperature_celsius_converter;
import static com.example.android.airenthalpymollier.R.id.submit_enhalpy_result_button;
import static com.example.android.airenthalpymollier.R.string.result;
import static com.example.android.airenthalpymollier.R.string.temperature;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is used to start EnthalpyMenu Activity
     */
    public void setEnthalpyMenu(View view) {
        Intent setEnthalpyMenuIntent = new Intent(this,EnthalpyMenuActivity.class);
        startActivity(setEnthalpyMenuIntent);
    }

    /**
     * This method is used, when user want to acces option, which wasn't implemented yet
     */
    public void uploadReturnMessage(View view) {
        Toast.makeText(this, "This function was not implemented yet", Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is seeting the view to humidity menu, from main menu
     */
    public void setAbsoluteHumitidtyMenu(View view) {
        Intent setAbsoluteHumidityMenuIntent = new Intent(this,AbsoluteHumidityActivity.class);
        startActivity(setAbsoluteHumidityMenuIntent);
    }

    /**
     * This method set the view on Heat of Process Calculator Menu
     */
    public void setHeatOfProcessMenu(View view) {
        Intent setHeatOfProcessMenuIntent = new Intent(this,HeatOfProcessActivity.class);
        startActivity(setHeatOfProcessMenuIntent);

    }

    /**
     * This methods sets content view to temperature converter menu
     */
    public void setTemperatureConverterMenu(View view) {
        Intent setTemperatureConverterMenuIntent = new Intent(this,TemperatureConverterMenuActivity.class);
        startActivity(setTemperatureConverterMenuIntent);

    }

    /**
     * This method sets Content to Bernoulie's Equation menu
     */
    public void setBernoullieEquationMenu(View view){
        Intent setBernoullieEquationMenu = new Intent(this,BernoulieEquationMenuActivity.class);
        startActivity(setBernoullieEquationMenu);
    }
    /**
     * This method sets content view to Heat flor resistance menu
     */
    public void setHeatFlowResistanceMenu(View view){
        Intent setHeatFlowResistanceMenu = new Intent(this, HeatFlowResistanceCalculatorActivity.class);
        startActivity(setHeatFlowResistanceMenu);
    }
}
