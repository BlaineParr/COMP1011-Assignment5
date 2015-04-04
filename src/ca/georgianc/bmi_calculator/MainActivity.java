package ca.georgianc.bmi_calculator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * This program is a BMI calculator. It allows the user to input their height in weight in
 * either the imperial or metric system and calculates their BMI.
 * 
 * @author Blaine Parr
 * @version April 3, 2015
 */
public class MainActivity extends Activity {
	//instance variables
	private boolean _unitsImperial = true;
	
	/**
	 * This method sets up all of the application's methods when the application is created.
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //local variables
        final Button UNITS_BUTTON = (Button)findViewById(R.id.unitsButton);
        final Button CALCULATE_BMI_BUTTON = (Button)findViewById(R.id.calculateBMIButton);
        final Button RESET_BUTTON = (Button)findViewById(R.id.resetButton);
        final TextView UNITS_TEXT_VIEW = (TextView)findViewById(R.id.unitsTextView);
        final EditText HEIGHT_EDIT_TEXT = (EditText)findViewById(R.id.heightEditText);
        final EditText WEIGHT_EDIT_TEXT = (EditText)findViewById(R.id.weightEditText);
        final EditText BMI_EDIT_TEXT = (EditText)findViewById(R.id.bmiEditText);
        
        //set up unitsButton's click event
        UNITS_BUTTON.setOnClickListener(new View.OnClickListener() {
			
        	//when unitsButton is clicked...
			@Override
			public void onClick(View v) {
				//if the units are currently imperial...
				if(_unitsImperial) {
					//change the text to display that it is now in metric
					UNITS_TEXT_VIEW.setText("Units: Metric System");
					_unitsImperial = false; //set unitsImperial to false
					
					//set the hints to inches and pounds
					HEIGHT_EDIT_TEXT.setHint("Height in Inches");
					WEIGHT_EDIT_TEXT.setHint("Weight in Pounds");
				} //if ends
				//if the units are not currently imperial...
				else {
					//change the text to display that it is now in imperial
					UNITS_TEXT_VIEW.setText("Units: Imperial System");
					_unitsImperial = true; //set unitsImperial to true
					
					//set the hints to metres and kilograms
					HEIGHT_EDIT_TEXT.setHint("Height in Metres");
					WEIGHT_EDIT_TEXT.setHint("Weight in Kilograms");
				} //else ends
			} //method onClick ends
		});
        
        //set up calculateBMI's click event
        CALCULATE_BMI_BUTTON.setOnClickListener(new View.OnClickListener() {
			
        	//when calculateBMIButton is clicked...
			@Override
			public void onClick(View v) {
				//local variables
				double heightValue = 0;
				double weightValue = 0;
				double bmiValue = 0.0;
				boolean error = false;
				
				//try to parse the user's input into doubles
				try {
					heightValue = Double.parseDouble(HEIGHT_EDIT_TEXT.getText().toString());
					weightValue = Double.parseDouble(WEIGHT_EDIT_TEXT.getText().toString());
				} //try ends
				
				//if an error occurs, set error to true
				catch(Exception e) {
					error = true;
				} //catch ends
				
				//if there were no errors calculate the user's BMI
				if(!error) {
					//if calculating in imperial units...
					if(_unitsImperial) {
						bmiValue = weightValue / (heightValue * heightValue);
					} //if ends
					//if not calculating in imperial untis...
					else {
						bmiValue = (weightValue * 703) / (heightValue * heightValue);
					} //else ends
					
					//display the user's BMI to two decimal places
					BMI_EDIT_TEXT.setText(String.format("%.2f", bmiValue));
				} //if ends
			} //method onClick ends
		});
        
        //set up resetButton's click event
        RESET_BUTTON.setOnClickListener(new View.OnClickListener() {
			
        	//when resetButton is clicked...
			@Override
			public void onClick(View v) {
				//clear all of the editText fields
				HEIGHT_EDIT_TEXT.setText("");
				WEIGHT_EDIT_TEXT.setText("");
				BMI_EDIT_TEXT.setText("");
				
				//change the text to display that it is now in imperial
				UNITS_TEXT_VIEW.setText("Units: Imperial System");
				_unitsImperial = true; //set unitsImperial to true
				
				//set the hints to metres and kilograms
				HEIGHT_EDIT_TEXT.setHint("Height in Metres");
				WEIGHT_EDIT_TEXT.setHint("Weight in Kilograms");
			} //method onClick ends
		});
    } //method onCreate ends

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    } //method onCreateOptionsMenu ends

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    } //method onOptionsItemSelected ends
} //class MainActivity ends
