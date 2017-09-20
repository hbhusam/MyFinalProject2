package com.example.hp1.myfinalproject;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Home  extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener
{
    Button btrelationship, btask ;
    ListView newsfeed;
    ArrayList<String> feed = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //  btrelationship=(Button)findViewById(R.id.btcouple);
        //     btask=(Button)findViewById(R.id.btask);
        //btrelationship.setOnClickListener(this);
        //  btask.setOnClickListener(this);
        newsfeed =(ListView) findViewById(R.id.lvNewsfeed);

        feed.add("Can AI Detect Sexual Orientation from Photos?");
        feed.add("Feeling Lonely? You May Be Damaging Your Health");
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, feed);
        newsfeed.setAdapter(adapter);
        newsfeed.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btrelationship){
            Intent i = new Intent(this, Relationship.class);
            startActivity(i);
        }
        if (v==btask){
            Intent i = new Intent(this, Ask.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0 ){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.psychologytoday.com/blog/your-online-secrets/201709/can-ai-detect-sexual-orientation-photos"));
            startActivity(browserIntent);
        }
        if(position == 0 ){

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.psychologytoday.com/blog/the-mindful-self-express/201709/feeling-lonely-you-may-be-damaging-your-health"));
        startActivity(browserIntent);}
        return false;
        }
}
