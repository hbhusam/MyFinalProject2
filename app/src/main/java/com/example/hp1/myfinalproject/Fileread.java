package com.example.hp1.myfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Fileread extends AppCompatActivity implements View.OnClickListener {
    InputStream is = null;
    InputStreamReader in;
    BufferedReader br;

    //parameters for file reading used in both methods
    String temp = "", all = "";
    int num;


    Button bt1, bt2, bt3;
    TextView tv1, tv2;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fileread);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        et1 = (EditText) findViewById(R.id.et1);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        et1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == bt1) {
            try {
                temp = "";
                all = "";
                //4. open the file for reading
                is = getResources().openRawResource(R.raw.fileread);
                //open a channel for file reading
                in = new InputStreamReader(is);
                br = new BufferedReader(in);
                //while end of file not reached
                //readline() reads one line at a time
                while ((temp = br.readLine()) != null)
                    all += temp + "\n";//concatinate all lines to a string
                tv2.setText(all);
                is.close();//file close
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


