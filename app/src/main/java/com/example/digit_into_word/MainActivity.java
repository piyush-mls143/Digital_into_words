package com.example.digit_into_word;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText digit;
    TextView words;
    Button convert;

    private static final String[] tensName= {
            "",
            "ten",
            "twenty",
            "thirty",
            "fourty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
    };

    private static final String[] numsName= {
            "",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        digit=(EditText)findViewById(R.id.digit);
        words=(TextView)findViewById(R.id.words);
        convert=(Button)findViewById(R.id.convert);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                words.setText(convert(Integer.parseInt(digit.getText().toString())));
            }
        });
    }

    private static String convertLessThenOneThousand(int number){
        String soFar;
        if (number % 100<20){
            soFar = numsName[number % 10];
            number/=100;
        }else {
            soFar=numsName[number%10];
            number/=10;

            soFar =tensName[number%10] +soFar;
            number/=10;
        }
        if (number==0)return soFar;
        return numsName[number]+ "hendred" + soFar;
    }


    public  static String convert (long number){
        if (number == 0){
            return "Zero";
        }
        String snumber= Long.toString(number);

        String mask= "000000000000";
        DecimalFormat df= new DecimalFormat(mask);
        snumber = df.format(number);

        int billions = Integer.parseInt(snumber.substring(0, 3));
        int millions =Integer.parseInt(snumber.substring(3, 6));

        int hundredThousand = Integer.parseInt(snumber.substring(6,9));
        int Thousand = Integer.parseInt(snumber.substring(9, 12));

        String tradBillions;
        switch (billions){
            case 0:
                tradBillions= " ";
                break;
            case 1:
                tradBillions= convertLessThenOneThousand(billions)+ " billion ";
                break;
            default:
                tradBillions=convertLessThenOneThousand(billions)+ " billion ";
        }
        String result= tradBillions;


        String treadMillion;
        switch (millions){
            case 0:
                treadMillion= " ";
                break;
            case 1:
                treadMillion= convertLessThenOneThousand(billions)+ " million ";
                break;
            default:
                treadMillion=convertLessThenOneThousand(billions)+ " million ";

        }
        result= result+treadMillion;

        String tradHundredThousand;
        switch (hundredThousand){
            case 0:
                tradHundredThousand= " ";
                break;
            case 1:
                tradHundredThousand= " one thousand ";
                break;
            default:
                tradHundredThousand=convertLessThenOneThousand(hundredThousand)+ " thousand ";
        }

        result=result+tradHundredThousand;

        String tradThousand;
        tradThousand= convertLessThenOneThousand(Thousand);
        result=result+tradThousand;

        return result.replaceAll("^\\s+", " ").replaceAll("\\b\\s{2,}\\b", " ");


    }




}
