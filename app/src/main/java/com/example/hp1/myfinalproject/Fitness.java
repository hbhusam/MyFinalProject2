package com.example.hp1.myfinalproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Fitness extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener {
    ListView newsfeed;
    ArrayList<String> feed = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        newsfeed =(ListView) findViewById(R.id.lvNewsfeed);
        feed.add("Can AI Detect Sexual Orientation from Photos?");  // discreption of the item in the listview
        feed.add("Feeling Lonely? You May Be Damaging Your Health"); // discreption of the item in the listview
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, feed);
        newsfeed.setAdapter(adapter);// setting the adapter to the listview
        newsfeed.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.psychologytoday.com/blog/your-online-secrets/201709/can-ai-detect-sexual-orientation-photos"));
            startActivity(browserIntent); //listview with the adds that intent to browser on long click
        }
        if (position == 0) {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.psychologytoday.com/blog/the-mindful-self-express/201709/feeling-lonely-you-may-be-damaging-your-health"));
            startActivity(browserIntent);
        } //listview with the adds that intent to browser on long click


        if (position == 0) {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.simplypsychology.org/whatispsychology.html"));
            startActivity(browserIntent);
        } //listview with the adds that intent to browser on long click
        return false;
    }
    }

