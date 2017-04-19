package css.mrauzi.comiclistapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * ComicAdapter - creates a custom array adapter for the list of comics
 * to be used with a custom comic xml layout file
 */
public class ComicAdapter extends ArrayAdapter<Comic> {

    private List<Comic> comicList;
    private Context context;

    /**
     * ComicAdapter() - constructor for the ComicAdapter which will create the comic lisr
     *
     * @param context
     * @param resource
     * @param tvID
     * @param objects the list of type Comic
     */
    public ComicAdapter(Context context, int resource, int tvID, List<Comic> objects) {
        super(context, resource, tvID, objects);
        this.context = context;
        this.comicList = objects;
    }

    /**
     * getView() - creates the view to be displayed using the custom comic xml file
     *
     * @param position the position in the list
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        // get the comic we are displaying
        Comic comic = comicList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.comic_row, null);

        // setting the variables to display the data in the comic layout row
        TextView tvName = (TextView)view.findViewById(R.id.textViewComicName);
        TextView tvPrice = (TextView)view.findViewById(R.id.textViewComicPrice);
        TextView tvVolume = (TextView)view.findViewById(R.id.textViewComicVolume);
        //Button btnDelete = (Button) view.findViewById(R.id.buttonDelete);

        tvName.setText(comic.getName());
        tvPrice.setText(comic.getPrice().toString());
        tvVolume.setText(comic.getVolume().toString());

        return(view);
    }
}
