package com.example.hp1.myfinalproject;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
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
        btnNotify = (Button) findViewById(R.id.btnNotify);

        //create builder object
        builder = new NotificationCompat.Builder(this);

        //customize the builder
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Notification Title");
        builder.setContentText("this is the body of the notification message");

        //
        Intent bIntent = new Intent(this, about.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, bIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add as notification
                NotificationManager manager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                manager.notify(0, builder.build());
            }
        });

        //  btrelationship=(Button)findViewById(R.id.btcouple);
        //     btask=(Button)findViewById(R.id.btask);
        //btrelationship.setOnClickListener(this);
        //  btask.setOnClickListener(this);
        newsfeed =(ListView) findViewById(R.id.lvNewsfeed);

        feed.add("Can AI Detect Sexual Orientation from Photos?");  // discreption of the item in the listview
        feed.add("Feeling Lonely? You May Be Damaging Your Health"); // discreption of the item in the listview
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, feed);
        newsfeed.setAdapter(adapter);// setting the adapter to the listview
        newsfeed.setOnItemLongClickListener(this);
    }
public boolean onCreateOptionsMenu (Menu menu) {        // coneccting the option menu from the resource file
    getMenuInflater().inflate(R.menu.homemenu, menu);
return true;
}
    public boolean onOptionsItemSelected (MenuItem item) { //setting up the option menu to work
        switch (item.getItemId()){
            case R.id.about:
                Intent i = new Intent(this, about.class);// when clicking on the option menu intents to the page
                startActivity(i);

        }
        return true;

    }
    @Override
    public void onClick(View v) {
        if (v==btrelationship){
            Intent i = new Intent(this, Relationship.class);// clicking on button and intenting to page
            startActivity(i);
        }
        if (v==btask){
            Intent i = new Intent(this, Ask.class);// clicking on button and intenting to page
            startActivity(i);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0 ){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.psychologytoday.com/blog/your-online-secrets/201709/can-ai-detect-sexual-orientation-photos"));
            startActivity(browserIntent); //listview with the adds that intent to browser on long click
        }
        if(position == 0 ){

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.psychologytoday.com/blog/the-mindful-self-express/201709/feeling-lonely-you-may-be-damaging-your-health"));
        startActivity(browserIntent);} //listview with the adds that intent to browser on long click
        return false;
        }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No Why? Stay:(")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Home.this.finish();
                    }
                })
                .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private NotificationCompat.Builder builder;
    private Button btnNotify;



    }


