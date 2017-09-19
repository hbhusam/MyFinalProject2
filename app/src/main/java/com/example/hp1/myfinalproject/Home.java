package com.example.hp1.myfinalproject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Home  extends AppCompatActivity implements View.OnClickListener {
Button btrelationship, btask ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      //  btrelationship=(Button)findViewById(R.id.btcouple);
   //     btask=(Button)findViewById(R.id.btask);
//btrelationship.setOnClickListener(this);
      //  btask.setOnClickListener(this);
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
}
