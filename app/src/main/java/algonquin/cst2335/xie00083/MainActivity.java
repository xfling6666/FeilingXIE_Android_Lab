package algonquin.cst2335.xie00083;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.String;

/** This is the main activity class for this android application.
 * @author  Feiling Xie 041104728
 * @version  1.0
 */
public class MainActivity extends AppCompatActivity {

    /**This holds a text that  indicates what user needs to do   */
    TextView tv=null;
    /**This holds a edit text that asks user to input password   */
    EditText et=null;
    /**This holds a button that to be log in  */
    Button btn=null;

    /** This is the main function to create an activity.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.textView);
        et=findViewById(R.id.password);
        btn=findViewById(R.id.button);

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

    /** This function is to check if the password is complex enough
     *
     * @param pw The String object means the password needs to be checked
     * @param context The context to indicate the application information
     * @return The result of checking. Return true if the password is complex enough, and false if it is not complex enough.
     */
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

    /** This function is to check if a character belongs to a list of special character.
     *
     * @param c  The character needs to be checked.
     * @return The boolean check result, return ture if it is a special charactor.
     */
    private static boolean isSpecialCharacter(char c) {
        // Check if c is one of: #$%^&*!@?
        String specialCharacters = "#$%^&*!@?";
        return specialCharacters.indexOf(c) != -1;
    }
}