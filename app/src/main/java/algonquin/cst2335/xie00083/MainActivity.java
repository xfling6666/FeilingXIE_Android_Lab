package algonquin.cst2335.xie00083;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );
        Log.d( TAG, "Message");

    }
    @Override
    protected void onStart() {
        Log.w( "MainActivity", "In onStart() - The application is now visible on screen." );
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.w( "MainActivity", "In onResume() - The application is now responding to user input" );
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.w( "MainActivity", "In onPause() - The application no longer responds to user input." );
    }

    @Override
    protected void onStop() {
        Log.w( "MainActivity", "In onStop() - The application is no longer visible." );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.w( "MainActivity", "In onDestroy() - Any memory used by the application is freed." );
        super.onDestroy();
    }

}