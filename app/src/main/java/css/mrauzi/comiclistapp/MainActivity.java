package css.mrauzi.comiclistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // data members
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *   Set up button click event listener for user to enter the main comic page
         */
        btnEnter = (Button)findViewById(R.id.buttonEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // button will send user to second activity (comic list page)
                Intent openComicPageIntent = new Intent(MainActivity.this, ComicActivity.class);
                startActivity(openComicPageIntent);

            }
        });
    }
}
