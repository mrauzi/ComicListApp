package css.mrauzi.comiclistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    ListView lvComic;
    ComicTableDAO comicTable;


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
