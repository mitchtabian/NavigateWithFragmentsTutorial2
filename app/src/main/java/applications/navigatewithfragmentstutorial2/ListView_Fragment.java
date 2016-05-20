package applications.navigatewithfragmentstutorial2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListView_Fragment extends Fragment {

    View myView;
    ListView listView;
    DatabaseHelper myDB;
    ArrayList<String> theList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.listview_page,container, false);
        listView = (ListView) myView.findViewById(R.id.theListView);
        myDB = new DatabaseHelper(this.getActivity());

        //retrieve the items from the database
        theList = new ArrayList<>();
        Cursor data = myDB.getItems();
        if (data.getCount() == 0) {
            Toast.makeText(getActivity(), "No data to display.", Toast.LENGTH_LONG).show();
        }else {
            while (data.moveToNext()) {
                theList.add(data.getString(1));
            }
            //once theList is populated we can make an adapter and adapter it to the listView
            ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(adapter);
        }

        return myView;
    }


}
