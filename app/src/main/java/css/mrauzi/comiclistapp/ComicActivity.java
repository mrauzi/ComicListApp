package css.mrauzi.comiclistapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class ComicActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnAmazon;
    Button btnAddComic;
    Button btnViewList;
    ComicTableDAO comicTable;
    EditText etComicName;
    EditText etComicPrice;
    EditText etComicVol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        comicTable = new ComicTableDAO(this);   // initialize the comic table data access object
        comicTable.open();                      // open the database

        etComicName = (EditText)findViewById(R.id.editTextComicName);
        etComicPrice = (EditText)findViewById(R.id.editTextComicPrice);
        etComicVol = (EditText)findViewById(R.id.editTextComicVolume);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "We hope you are enjoying the app so far!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         *   Set up button click event listener for the user to enter Amazon.com
         */
        btnAmazon = (Button)findViewById(R.id.buttonAmazon);
        btnAmazon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // button will send user to Amazon.com to search for comics
                Uri webpage = Uri.parse("https://www.amazon.com/s/ref=lp_283155_nr_n_6?fst=as%3Aoff&rh=n%3A283155%2Cn%3A%211000%2Cn%3A4366&bbn=1000&ie=UTF8&qid=1491589883&rnid=1000");
                Intent amazonIntent = new Intent(Intent.ACTION_VIEW, webpage);
                if (amazonIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(amazonIntent);
                }


            }
        });

        /**
         *  Set up button click event listener for the user to view the list of comics from the database
         */
        btnViewList = (Button)findViewById(R.id.buttonViewList);
        btnViewList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // button will open the activity to view the list of comics
                Intent listViewIntent = new Intent(ComicActivity.this, ListViewActivity.class);
                startActivity(listViewIntent);

            }
        });

        /**
         *  Set up button click event listener for the user to add a comic to the database
         */
        btnAddComic = (Button)findViewById(R.id.buttonAddComic);
        btnAddComic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // button will add a comic to the comic database
                // if the edit text fields are not null, add the comic to the database
                if (etComicName.getText().toString().trim().length() != 0 && etComicPrice.getText().toString().trim().length() != 0 && etComicVol.getText().toString().trim().length() != 0) {
                    // call createComic method to create and add a comic object to the database
                    comicTable.createComic(etComicName.getText().toString(), Double.parseDouble(etComicPrice.getText().toString()), Integer.parseInt(etComicVol.getText().toString()));
                    Snackbar.make(v, "Comic has been added to the list", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    etComicName.setText("");
                    etComicPrice.setText("");
                    etComicVol.setText("");
                }
                else {
                    Snackbar.make(v, "You must include all of the comic information to add to your list", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.comic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_help) {
            // selecting "help" menu item will send user to the help page activity
            Intent helpPageIntent = new Intent(ComicActivity.this, HelpActivity.class);
            startActivity(helpPageIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_help) {
            // selecting "help" nav_bar item will send user to the help page activity
            Intent helpPageIntent = new Intent(ComicActivity.this, HelpActivity.class);
            startActivity(helpPageIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * onResume() - when the ComicActivity returns to the app foreground, the comic database
     * is opened again
     */
    @Override
    protected void onResume() {
        comicTable.open();
        super.onResume();
    }

    /**
     * onPause() - when the ComicActivity is left or "paused" by the user, the comic database
     * is closed
     */
    @Override
    protected void onPause() {
        comicTable.close();
        super.onPause();
    }
}
