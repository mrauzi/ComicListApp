package css.mrauzi.comiclistapp;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
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
    ComicTableDAO comicTable;
    ImageButton imgBtnDelete;
    CheckBox cbComicPurchased;


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
    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        // get the comic we are displaying
        final Comic comic = comicList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.comic_row, null);

        // setting the variables to display the data in the comic layout row
        TextView tvName = (TextView)view.findViewById(R.id.textViewComicName);
        TextView tvPrice = (TextView)view.findViewById(R.id.textViewComicPrice);
        TextView tvVolume = (TextView)view.findViewById(R.id.textViewComicVolume);



        cbComicPurchased = (CheckBox) view.findViewById(R.id.checkBoxPurchased);
        cbComicPurchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check the status of the checkbox in the list
                if (DatabaseHelper.DB_FIELD_COMICSTATUS)
                /*if(pref.getBoolean("check", false))
                {
                    cbComicPurchased.setChecked(false);
                    pref.edit().putBoolean("check", false).commit();

                } else {
                    cbComicPurchased.setChecked(true);
                    pref.edit().putBoolean("check", true).commit();
                }*/
            }
        });

        /**
         * Set up button click event listener for the user to delete a comic from the database using the image button
         */
        imgBtnDelete = (ImageButton) view.findViewById(R.id.imageButtonDelete);
        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("CIS3334", "DeleteImageView setOnClickListener clicked row " + position);
                // declare and open the comic database
                comicTable = new ComicTableDAO(getContext());
                comicTable.open();
                // call deleteComic method to delete a comic object from the database
                comicTable.deleteComic(comic);
                Snackbar.make(v, "Comic has been deleted from the list", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // remove the comic object from the ListView
                // comicList.remove(comic);
                remove(comic);
                notifyDataSetChanged();

            }
        });

        // setting the TextViews to display the data
        tvName.setText(comic.getName());
        tvPrice.setText(comic.getPrice().toString());
        tvVolume.setText(comic.getVolume().toString());

        return(view);
    }
}
