import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class UserList extends SimpleAdapter {

    private Context mContext;
    public LayoutInflater inflater = null;

    public CustomAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    public UserList(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        try {
            if (convertView == null)
                vi = inflater.inflate(R.layout.cust_list_rest, null);
            HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
            TextView tvrestname = vi.findViewById(R.id.textView);
            TextView tvphone = vi.findViewById(R.id.textView2);
            TextView tvadd = vi.findViewById(R.id.textView3);
            TextView tvloc = vi.findViewById(R.id.textView4);
            String dname = (String) data.get("name");
            String dphone = (String) data.get("phone");
            String dadd = (String) data.get("address");
            String dloc = (String) data.get("location");
            String drid = (String) data.get("restid");
            tvrestname.setText(dname);
            tvphone.setText(dphone);
            tvadd.setText(dadd);
            tvloc.setText(dloc);


        } catch (IndexOutOfBoundsException e) {

        }

        return vi;
    }
}
