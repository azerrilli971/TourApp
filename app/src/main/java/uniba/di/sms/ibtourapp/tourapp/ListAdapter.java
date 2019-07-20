package uniba.di.sms.ibtourapp.tourapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    //to store the images
    private final Integer[] imageIDarray;

    //to store the list of names
    private final String[] nameArray;

    //to store the list of addresses
    private final String[] infoArray;

    //to store the list of prices
    private final String[] priceArray;


    public ListAdapter(Activity context, String[] nameArrayParam, String[] infoArrayParam, String[] priceArrayParam, Integer[] imageIDArrayParam){

        super(context,R.layout.hotels_list_row , nameArrayParam);


        this.context=context;
        this.imageIDarray = imageIDArrayParam;
        this.nameArray = nameArrayParam;
        this.infoArray = infoArrayParam;
        this.priceArray = priceArrayParam;
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.hotels_list_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.hotelNome);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.hotelVia);
        TextView priceTextField = (TextView) rowView.findViewById(R.id.hotelCosto);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.hotelImmagine);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);
        infoTextField.setText(infoArray[position]);
        priceTextField.setText(priceArray[position]);
        imageView.setImageResource(imageIDarray[position]);

        return rowView;

    };
}
