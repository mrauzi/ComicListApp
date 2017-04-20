package css.mrauzi.comiclistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    ListView lvComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lvComic = (ListView) findViewById(R.id.listViewComic);

        final ArrayList<Comic> comicList = new ArrayList<>();
        ComicAdapter comicAdapter = new ComicAdapter(this, R.layout.comic_row, R.id.textViewComicName, comicList);
        comicAdapter.setDropDownViewResource(R.layout.comic_row);
        lvComic.setAdapter(comicAdapter);
    }
}
