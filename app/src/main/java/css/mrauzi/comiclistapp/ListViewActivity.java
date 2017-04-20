package css.mrauzi.comiclistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    ListView lvComic;
    ComicTableDAO comicTable;
    ImageButton imgBtnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lvComic = (ListView) findViewById(R.id.listViewComic);

        comicTable = new ComicTableDAO(this);   // initialize the comic table data access object
        comicTable.open();                      // open the database


        List<Comic> comicList = comicTable.getAllComics();                                                              // list of all of the comics in the database
        ComicAdapter comicAdapter = new ComicAdapter(this, R.layout.comic_row, R.id.textViewComicName, comicList);      // creates a new ComicAdapter using the custom row and the list of comics
        comicAdapter.setDropDownViewResource(R.layout.comic_row);                                                       // sets the adapter to use the custom comic row
        lvComic.setAdapter(comicAdapter);                                                                               // sets the ListView to use the ComicAdapter

        /**
         *  Set up button click event listener for the user to delete a comic from the database
         */
        /*imgBtnDelete = (ImageButton) findViewById(R.id.imageButtonDelete);
        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Long id = Long.parseLong(DatabaseHelper.DB_FIELD_ID);
                String name = DatabaseHelper.DB_FIELD_COMICNAME;
                Double price = Double.parseDouble(DatabaseHelper.DB_FIELD_COMICPRICE);
                Integer volume = Integer.parseInt(DatabaseHelper.DB_FIELD_COMICVOLUME);
                Comic comic = new Comic(id, name, price, volume);
                comicTable.deleteComic(comic);
            }
        });*/
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
