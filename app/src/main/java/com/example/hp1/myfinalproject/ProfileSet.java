package com.example.hp1.myfinalproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * this activity sets up the profile of the user
 */
public class ProfileSet extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private Bitmap bitmap;
    private ImageView imageView;
    Button takephotobt, photogallerybt, btsave;
    EditText etname, etmail;
    Switch snotify;
    /**
     * difining selct and take image for easier use
     */
    static final int SELECT_IMAGE = 1;
    static final int TAKE_IMAGE = 0;
    SharedPreferences pref;
    /**
     * setting up all and the widgets or defining them
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = (ImageView) findViewById(R.id.imageView);
        takephotobt = (Button) findViewById(R.id.takephotobt);
        btsave = (Button) findViewById(R.id.btsave);
        btsave.setOnClickListener(this);

        photogallerybt = (Button) findViewById(R.id.photogallerybt);
        takephotobt.setOnClickListener(this);
        photogallerybt.setOnClickListener(this);
        pref = getSharedPreferences("mypref",MODE_PRIVATE);// thats where the files are being saved
        etname = (EditText) findViewById(R.id.etname);
        etmail = (EditText) findViewById(R.id.etmail);
        snotify = (Switch) findViewById(R.id.snotify);



        String em=pref.getString("image",null);

        if(em != null){
            bitmap = BitmapFactory.decodeFile(em);//thats where the code file is saved of the image
            imageView.setImageBitmap(bitmap);
        }


    }

    /**
     * taking images and selecting them for setting up the profile
     * @param v
     */

    @Override
    public void onClick(View v) {
        if (v == takephotobt) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, TAKE_IMAGE);// takes new image
        } else if(v == photogallerybt) {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, SELECT_IMAGE);// selects image from library
        }
        else if (v == btsave) {// saves the information
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("name", etname.getText().toString());

            editor.putString("email", etmail.getText().toString());
            editor.putString("notification", snotify.getText().toString());
            editor.commit();
        }

    }

    /**
     * to check if the request was processed correctly and if the request code was recieved
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_IMAGE && resultCode == RESULT_OK) {
            Bundle extra = data.getExtras();
            bitmap = (Bitmap) extra.get("data");
            saveImage(bitmap);
            imageView.setImageURI(Uri.fromFile(saveImage(bitmap)));
        } else {
            if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK) ;
            {
                Uri targetUri = data.getData();
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    saveImage(bitmap);
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {

                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * this sends the photo which was taken and saves it in the phone so it will be shown in gallery
     * @param bitmap
     * @return
     */
    public File saveImage(Bitmap bitmap) {
        File root = Environment.getExternalStorageDirectory();// internal storage launching .
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        String filePath = root.getAbsolutePath() + "/DCIM/ProfileSet/IMG_" + timeStamp + ".jpg";
        File file = new File(filePath);// determinig the type of the file and its place.

        SharedPreferences.Editor editor=pref.edit();
        editor.putString("image", filePath);

        //need to move this code after adding save button

        editor.commit();
        try {
            // if gallary nit full create a file and save images
            file.createNewFile();// create new file to save image.
            FileOutputStream ostream = new FileOutputStream(file);//saves root in this file
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);// compass bitmap in file
            ostream.close();// close
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Faild to save image", Toast.LENGTH_SHORT).show();
        }
        return file;
    }

    /**
     * turning on notification switch for the user to remember to check for new info on a certain time of the day
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            Calendar calender = Calendar.getInstance();
/**
 *the time for the notification to be sent
 */
            calender.set(Calendar.HOUR_OF_DAY, 17);
            calender.set(Calendar.MINUTE, 29);
            calender.set(Calendar.SECOND, 12);


            Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmmaneger = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmmaneger.setRepeating(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }
}





