package algonquin.cst2335.xie00083;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent fromPrevious = getIntent();       
        String emailAddress=fromPrevious.getStringExtra("EmailAddress");

        TextView textView = findViewById(R.id.textView3);
        textView.setText("welcome back "+emailAddress);
        Intent call = new Intent(Intent.ACTION_DIAL);

        Button callButton=findViewById(R.id.callButton);
        callButton.setOnClickListener((isChecked) -> {
        EditText inputPhone=findViewById(R.id.inputPhone);
        String phoneNumber= String.valueOf(inputPhone.getText());
        call.setData(Uri.parse("tel:" + phoneNumber));
        });


        Button picButton = findViewById(R.id.pictureButton);
        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode() == Activity.RESULT_OK) {
                                    Intent data = result.getData();
                                    if (data != null && data.hasExtra("data")) {
                                        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                                        ImageView profileImage = findViewById(R.id.imageView);
                                        profileImage.setImageBitmap(thumbnail);
                                    }
                                }
                            }
                        });

                    cameraResult.launch(cameraIntent);
            }
        });

    }
}