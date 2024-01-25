package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

import algonquin.cst2335.xie00083.databinding.ActivityMainBinding;
import data.MainViewModel;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding variableBinding;
    private MainViewModel model;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        //variableBinding.textview.setText(model.editString);
        variableBinding.mybutton.setOnClickListener(click->
                {
                    model.editString.postValue(variableBinding.myEditText.getText().toString());
                    model.editString.observe(this, s ->
                    {
                        variableBinding.textview.setText( "Your edit text has: " + s);
                    });
                });

           model.isSelected.observe(this,selected->{
               variableBinding.checkBox.setChecked(selected);
               variableBinding.radioButton.setChecked(selected);
               variableBinding.switchButton.setChecked(selected);
               Toast.makeText(this, "The value is now: " + selected, Toast.LENGTH_SHORT).show();
           });
           variableBinding.checkBox.setOnCheckedChangeListener((btn, isChecked) ->
           {
               model.isSelected.postValue(isChecked);

           });
           variableBinding.radioButton.setOnCheckedChangeListener((btn, isChecked) ->
           {
               model.isSelected.postValue(isChecked);

           });
           variableBinding.switchButton.setOnCheckedChangeListener((btn, isChecked) ->
           {
               model.isSelected.postValue(isChecked);

           });

           variableBinding.image01.setOnClickListener(click->
           {
               Toast.makeText(MainActivity.this, "ImageView clicked!", Toast.LENGTH_SHORT).show();
           });

           variableBinding.myimagebutton.setOnClickListener(click -> {
               int width = variableBinding.myimagebutton.getWidth();
               int height = variableBinding.myimagebutton.getHeight();
               Toast.makeText(MainActivity.this, "The width = " + width + " and height = " + height, Toast.LENGTH_SHORT).show();
           });
    }

}