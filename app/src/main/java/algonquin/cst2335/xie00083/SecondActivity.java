package algonquin.cst2335.xie00083;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

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

    }
}