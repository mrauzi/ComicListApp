package css.mrauzi.comiclistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HelpActivity extends AppCompatActivity {

    TextView tvStep1;
    TextView tvStep2;
    TextView tvStep3;
    TextView tvStep4;
    TextView tvStep5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        tvStep1 = (TextView) findViewById(R.id.textViewStep1);
        tvStep2 = (TextView) findViewById(R.id.textViewStep2);
        tvStep3 = (TextView) findViewById(R.id.textViewStep3);
        tvStep4 = (TextView) findViewById(R.id.textViewStep4);
        tvStep5 = (TextView) findViewById(R.id.textViewStep5);

        tvStep1.setText("Step 1: Once you have entered the app, press the 'Amazon' button to begin searching for comics.");
        tvStep2.setText("Step 2: When you find a comic, return to the app and enter in the information.");
        tvStep3.setText("Step 3: Press the 'Add Comic' button to add the comic to your list.");
        tvStep4.setText("Step 4: To view your comic list, press the 'View List' button.");
        tvStep5.setText("Step 5: While viewing your list you will be able to delete comics by pressing the trashcan button.");


    }
}
