package algonquin.cst2335.xie00083;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.String;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv=findViewById(R.id.textView);
        EditText et=findViewById(R.id.password);
        Button btn=findViewById(R.id.button);

        btn.setOnClickListener( clk->{
            String password =et.getText().toString();

            boolean isValid =checkPasswordComplexity(password,this);
            if (isValid) {
                tv.setText("Your password meets the requirements");
            } else {
                tv.setText("You shall not pass!");
            }
        });

    }

    static boolean checkPasswordComplexity(String pw,Context context)
    {
// Initialize boolean variables
        boolean foundUpperCase = false;
        boolean foundLowerCase = false;
        boolean foundNumber = false;
        boolean foundSpecial = false;

        // Iterate through each character in the password
        for (int i = 0; i < pw.length(); i++)
        {
            char c = pw.charAt(i);

            // Check for uppercase, lowercase, number, and special characters
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }

    // Display Toast messages for missing criteria
        if (!foundUpperCase) {
        Toast.makeText(context, "Missing an uppercase letter.", Toast.LENGTH_LONG).show();
        return false;
    } else if (!foundLowerCase) {
        Toast.makeText(context, "Missing a lowercase letter.", Toast.LENGTH_LONG).show();
        return false;
    } else if (!foundNumber) {
        Toast.makeText(context, "Missing a number.", Toast.LENGTH_LONG).show();
        return false;
    } else if (!foundSpecial) {
        Toast.makeText(context, "Missing a special character.", Toast.LENGTH_LONG).show();
        return false;
    } else {
        // All criteria are met
        return true;
    }
}

    private static boolean isSpecialCharacter(char c) {
        // Check if c is one of: #$%^&*!@?
        String specialCharacters = "#$%^&*!@?";
        return specialCharacters.indexOf(c) != -1;
    }
}