package com.example.hp1.myfinalproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
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

public class Home extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener
{
    Button btrelationship, btask, btcamera, btread, btfitness;
    Button  btsex, btmind, btbody;
    ListView newsfeed;
    ArrayList<String> feed = new ArrayList<>();
    ArrayAdapter<String> adapter;

    /**
     * photo saving profile or choosing from gallery a profile picture
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnNotify = (Button) findViewById(R.id.btnNotify);
        btcamera = (Button) findViewById(R.id.btcamera);
        btcamera.setOnClickListener(this);
        btread = (Button) findViewById(R.id.btread);
        btread.setOnClickListener(this);
        btsex = (Button) findViewById(R.id.btsex);
        btsex.setOnClickListener(this);
        btbody = (Button) findViewById(R.id.btbody);
        btbody.setOnClickListener(this);
        btmind = (Button) findViewById(R.id.btmind);
        btmind.setOnClickListener(this);
        btask = (Button) findViewById(R.id.btask);
        btask.setOnClickListener(this);
        btfitness = (Button) findViewById(R.id.btfitness);
        btfitness.setOnClickListener(this);



        //create builder object
        builder = new NotificationCompat.Builder(this);

        //customize the builder
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Notification Title");
        builder.setContentText("this is the body of the notification message");

        //
        Intent bIntent = new Intent(this, About.class);
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
/**
 * Q&A in the ask page.
 */
        feed.add("Can AI Detect Sexual Orientation from Photos?");  // discreption of the item in the listview
        feed.add("Feeling Lonely? You May Be Damaging Your Health"); // discreption of the item in the listview
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, feed);
        newsfeed.setAdapter(adapter);// setting the adapter to the listview
        newsfeed.setOnItemLongClickListener(this);
    }

    /**
     * in the memu a list of options
     * @param menu on the left top corner
     * @return activity start true or false.
     */
    public boolean onCreateOptionsMenu (Menu menu) {        // coneccting the option menu from the resource file
    getMenuInflater().inflate(R.menu.homemenu, menu);
return true;
}
    public boolean onOptionsItemSelected (MenuItem item) { //setting up the option menu to work
        switch (item.getItemId()){
            case R.id.about:
                Intent i = new Intent(this, About.class);// when clicking on the option menu intents to the page
                startActivity(i);

        }
        return true;

    }

    /**
     * each button is a picture when clicked it intents to the page as described.
     * @param v the view of the activity
     */
    @Override
    public void onClick(View v) {
        if (v==btrelationship){
            Intent i = new Intent(this, Relationship.class);// clicking on button and intenting to page
            startActivity(i);
        }
        if (v==btcamera){
            Intent i = new Intent(this, Camera.class);// clicking on button and intenting to page
            startActivity(i);
        }
        if (v==btask){
            Intent i = new Intent(this, Ask.class);// clicking on button and intenting to page
            startActivity(i);
        }
        if (v==btread){
            Intent i = new Intent(this, Fileread.class);// clicking on button and intenting to page
            startActivity(i);
        }
        if (v==btfitness){
            Intent i = new Intent(this, Fileread.class);// clicking on button and intenting to page
            startActivity(i);
        }
        if (v==btsex){
            Intent i = new Intent(this, Fileread.class);// clicking on button and intenting to page
            startActivity(i);
        }
        if (v==btmind){
            Intent i = new Intent(this, Fileread.class);// clicking on button and intenting to page
            startActivity(i);
        }



    }

    /**
     * on each item clicked the page intents to the items url as described
     * @param parent
     * @param view
     * @param position is where the user has touched or clicked
     * @param id
     * @return onItemLongClick return true or false
     */
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

    /**
     * on clicking baCK PRESS, an alert duialog is shown whether to stay or exit
     */
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


