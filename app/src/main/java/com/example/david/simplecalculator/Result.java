package com.example.david.simplecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by David on 11/04/14.
 */
public class Result extends Activity{

    private TextView tipPercentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private TextView splitBetweenTextView;
    private TextView splitTotalTextView;
    private Button finished;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result);

        tipPercentTextView = (TextView)findViewById(R.id.tip_percent);
        tipTextView = (TextView)findViewById(R.id.tip);
        totalTextView = (TextView)findViewById(R.id.total);
        splitBetweenTextView = (TextView)findViewById(R.id.result_tv_split_between);
        splitTotalTextView= (TextView)findViewById(R.id.result_tv_split_total);

        initializeTextViews();
        finished = (Button)findViewById(R.id.confirm);

        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initializeTextViews(){
       double tip = getIntent().getExtras().getDouble(getResources().getString(R.string.common_tag_tip));
       double total = getIntent().getExtras().getDouble(getResources().getString(R.string.common_total_amount));
        double tipPercent = getIntent().getExtras().getDouble(getResources().getString(R.string.common_tip_percent));
        int splitBetween = getIntent().getExtras().getInt(getResources().getString(R.string.common_split_between));
        double splitTotal = getIntent().getExtras().getDouble(getResources().getString(R.string.common_split_total));
        NumberFormat formatter = new DecimalFormat("#0.00");
        NumberFormat formatter2 = new DecimalFormat("#%");


        String strTipPercent= tipPercentTextView.getText().toString();
        strTipPercent = strTipPercent + formatter2.format(tipPercent);

       String currentTipText = tipTextView.getText().toString();
        currentTipText = currentTipText + "$" + formatter.format(tip);

      String currentTotalText = totalTextView.getText().toString();
        currentTotalText = currentTotalText + "$" + formatter.format(total);

        String splitBetweenText = splitBetweenTextView.getText().toString();
        if (splitBetween == 1) {
            splitBetweenText = splitBetweenText + splitBetween + " Person";
        }
        else
        {
            splitBetweenText = splitBetweenText + splitBetween + " People";
        }

        String splitTotalText = splitTotalTextView.getText().toString();
        splitTotalText = splitTotalText+ "$" + formatter.format(splitTotal);

        tipPercentTextView.setText(strTipPercent);
        tipTextView.setText(currentTipText);
        totalTextView.setText(currentTotalText);
        splitBetweenTextView.setText(splitBetweenText);
        splitTotalTextView.setText(splitTotalText);
    }
}
