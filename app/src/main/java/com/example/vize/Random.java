package com.example.vize;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

public class Random extends AppCompatActivity {

    private EditText countTextField, minTextField,maxTextField;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        countTextField = findViewById(R.id.counTextField);
        minTextField = findViewById(R.id.minTextField);
        maxTextField = findViewById(R.id.maxTextField);
        mainLayout = findViewById(R.id.mainLayout);

        Button createButton = findViewById(R.id.btnCreate);
        createButton.setOnClickListener(v-> generateRandomNumber());
    }

    private void generateRandomNumber(){
        int count = Integer.parseInt(countTextField.getText().toString());
        int min = Integer.parseInt(minTextField.getText().toString());
        int max = Integer.parseInt(maxTextField.getText().toString());

        java.util.Random rnd = new java.util.Random();

        ScrollView scrollview = new ScrollView(this);
        LinearLayout scrollLinearLayout = new LinearLayout(this);
        scrollLinearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollview.addView(scrollLinearLayout);

        for(int i =0; i< count;i++){
            int randomValueOne = rnd.nextInt(max - min + 1 ) +min;
            int randomValueTwo = rnd.nextInt(max - min + 1 ) +min;

            int maxValue = Math.max(randomValueOne,randomValueTwo);
            int minValue = Math.min(randomValueOne,randomValueTwo);

            int randomNumber = rnd.nextInt(maxValue - minValue + 1) +minValue;
            double percent =((double) (randomNumber - minValue) * 100) / (maxValue - minValue);

            LinearLayout columnOne = createColumnTextView("Sonuç: "+ randomNumber+"=%"+percent);
            LinearLayout columnTwo = createColumnTextView("   min: "+ minValue);
            LinearLayout columnFour = createColumnTextView("max: "+ maxValue);
            LinearLayout columnThree = createProgressBarColumn(minValue,maxValue,percent);

            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.addView(columnOne);
            row.addView(columnTwo);
            scrollLinearLayout.addView(row);
            scrollLinearLayout.addView(columnThree);
            scrollLinearLayout.addView(columnFour);
        }
        mainLayout.addView(scrollview);
    }

    private LinearLayout createColumnTextView(String text){
        TextView textView = new TextView(this);
        textView.setText(text);

        LinearLayout column = new LinearLayout(this);
        column.setOrientation(LinearLayout.VERTICAL);
        column.addView(textView);

        return column;
    }

    private  LinearLayout createProgressBarColumn(int minValue, int maxValue, double persent){
        ProgressBar progressBar = new ProgressBar(this, null,android.R.attr.progressBarStyleHorizontal);
        int maxProgress = 100;
        int progressValue = (int) ((persent/100.0)* maxProgress);
        progressBar.setProgress(progressValue);

        LinearLayout column = new LinearLayout(this);
        column.setOrientation(LinearLayout.VERTICAL);
        column.addView(progressBar);
        return column;
    }


}