package com.example.david.simplecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{
    private EditText et;
    private EditText et2;
    private Button button10;
    private Button button15;
    private Button button20;
    private Button customTip;
    private Spinner spinner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        button10 = (Button)findViewById(R.id.tip10);
        button15 = (Button)findViewById(R.id.tip15);
        button20 = (Button)findViewById(R.id.tip20);
        customTip = (Button)findViewById(R.id.button_custom_tip);
       spinner1 = (Spinner)findViewById(R.id.spinner1);

        button10.setOnClickListener(this);
        button15.setOnClickListener(this);
        button20.setOnClickListener(this);
        customTip.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        double tipPercent = 0.0d;

        //get custom tip if one exists.
        String customTip = et2.getText().toString();
        double customTipAmount = 0;
        if (!customTip.equals("")) {
                customTipAmount = Double.parseDouble(customTip);
        }


        //calculate the tip.
        switch(v.getId()){
            case R.id.tip10:{
                tipPercent = 0.10;
                break;

            }
            case R.id.tip15:{
                tipPercent = 0.15;
                break;
            }
            case R.id.tip20:{
                tipPercent = 0.20;
                break;
            }
            case R.id.button_custom_tip:{
                tipPercent = customTipAmount / 100;
                break;
            }
            default:{
                break;
            }
        }

        //number of people to split by.
        int num_people = Integer.parseInt((String)spinner1.getSelectedItem());
        //int num_people = 2;

        String text = et.getText().toString();
        if (text.equals(""))
        {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.error_et), Toast.LENGTH_SHORT).show();
            return;
        }
        //pass tip info into result activity.
        //launch result activity.
        launchResultActivity(Double.parseDouble(et.getText().toString()),tipPercent, num_people);


    }

    private void launchResultActivity(double total, double tipPercent, int num_people){
        double tip = total * tipPercent;
        double grandTotal = total + tip;
        double perPerson = grandTotal / num_people;

        Intent resultActivity = new Intent(this, Result.class);
        resultActivity.putExtra(getResources().getString(R.string.common_tip_percent), tipPercent);
        resultActivity.putExtra(getResources().getString(R.string.common_tag_tip), tip);
        resultActivity.putExtra(getResources().getString(R.string.common_total_amount), grandTotal);
        resultActivity.putExtra(getResources().getString(R.string.common_split_between), num_people);
        resultActivity.putExtra(getResources().getString(R.string.common_split_total), perPerson);

        startActivity(resultActivity);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
