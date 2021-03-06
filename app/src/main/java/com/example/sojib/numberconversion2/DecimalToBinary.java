package com.example.sojib.numberconversion2;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class DecimalToBinary extends AppCompatActivity {

    EditText et_in;
    TextView tv_show;
    Button btn_conv,btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decimal2binary);

        ActionBar actionBar = getSupportActionBar();

        et_in= (EditText) findViewById(R.id.inputDecimal);

        btn_conv= (Button) findViewById(R.id.convert);
        tv_show=(TextView) findViewById(R.id.outputTV);
        btn_reset=(Button) findViewById(R.id.tryAgain);

        btn_conv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    String value;
                    value = et_in.getText().toString();
                    double number;
                    int intPart;
                    char[] concStore = new char[128];
                    ArrayList<Integer> storeFloat = new ArrayList<>();
                    ArrayList<Integer> storeInt = new ArrayList<>();

                       number = Double.parseDouble(value);
                       intPart = (int) number;
                       number -= intPart;

                       //integer part
                       while (intPart != 0) {
                           storeInt.add(intPart % 2);
                           intPart /= 2;
                       }
                       Collections.reverse(storeInt);
                       //float part
                       while (number != 0) {
                           number *= 2;
                           storeFloat.add((int) number);
                           number -= (int) number;
                       }
                       int i = 0;
                       if (storeInt.size() == 0) {
                           concStore[i] = '0';
                           i++;
                       } else {
                           for (int n : storeInt) {
                               concStore[i] = (char) (n + '0');
                               i++;
                           }
                       }
                       if (storeFloat.size() == 0) {
                           String output = new String(concStore);
                           tv_show.setText(output);
                       } else {
                           concStore[i] = '.';
                           i++;
                           for (int n : storeFloat) {
                               concStore[i] = (char) (n + '0');
                               i++;
                           }

                           String output = new String(concStore);
                           tv_show.setText(output);
                       }
                   }
                   catch(Exception e){
                       Toast.makeText(DecimalToBinary.this, "সংখাটি সঠিকভাবে লিখুন!", Toast.LENGTH_SHORT).show();
                   }



                }


        });

        btn_reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                tv_show.setText("");
                et_in.setText("");

            }
        });


    }
}
