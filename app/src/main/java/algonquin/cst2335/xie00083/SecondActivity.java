package algonquin.cst2335.xie00083;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private Button callButton;
    private ImageView profileImage;
    private static final int REQUEST_CALL_PHONE = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 2;
    private static final String filename = "Picture.png";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent fromPrevious = getIntent();       
        String emailAddress=fromPrevious.getStringExtra("EmailAddress");

        TextView textView = findViewById(R.id.textView3);
        textView.setText("welcome back "+emailAddress);

        /*making a phone call*/
        phoneNumberEditText = findViewById(R.id.inputPhone);
        callButton = findViewById(R.id.callButton);
        //loaded the saved number
        SharedPreferences prefs = getSharedPreferences("PhoneNumber", Context.MODE_PRIVATE);
        String savedPhoneNumber = prefs.getString("PhoneNumber", "");
        phoneNumberEditText.setText(savedPhoneNumber);

        // Set click listener for the Call Number button
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the phone number from the EditText
                String phoneNumber = phoneNumberEditText.getText().toString().trim();

                // Check if the phone number is empty
                if (phoneNumber.isEmpty()) {
                    Toast.makeText(SecondActivity.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                    return;
                } else {//check permission
                    if (ContextCompat.checkSelfPermission(SecondActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // If permission is not granted, request it
                        ActivityCompat.requestPermissions(SecondActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                    } else {
                        // If permission is granted, execute call logic
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + phoneNumber));
                        startActivity(callIntent);
                    }
                }
            }
        });


        /*change profile picture*/

        //Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        profileImage = findViewById(R.id.imageView);
        // Check if the profile picture exists
        File file = new File(getFilesDir(), filename);
        if (file.exists()) {
            Bitmap theImage = BitmapFactory.decodeFile(file.getAbsolutePath());
            profileImage.setImageBitmap( theImage );
        }
        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");
                            profileImage.setImageBitmap( thumbnail );
                            FileOutputStream fOut = null;
                            // Save the bitmap to disk
                            try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);

                                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                                fOut.flush();

                                fOut.close();
                            }

                            catch (FileNotFoundException e)

                            { e.printStackTrace();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }


                    }
                });


        Button changePictureButton = findViewById(R.id.pictureButton);
        changePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraResult.launch(cameraIntent);
            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();
        // Save the phone number to SharedPreferences when the activity goes off the screen
        SharedPreferences prefs = getSharedPreferences("PhoneNumber", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("PhoneNumber", phoneNumberEditText.getText().toString());
        editor.apply();
    }
}

